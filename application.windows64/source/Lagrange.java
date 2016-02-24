import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Lagrange extends PApplet {

ArrayList<PVector> interpolationPoints;
ArrayList<PVector> linePoints;
PGraphics canvas;

public void setup()
{
 
  surface.setResizable(true);
  
  
  canvas = createGraphics(1080, 720);
  
  interpolationPoints = new ArrayList<PVector>();
  linePoints = new ArrayList<PVector>();
  drawGrid();
  
  imageMode(CENTER);
}

public void draw()
{
  image(canvas, width/2, height/2, width, height);
  drawInstructions();
}

public void mousePressed()
{
  float xx = map(mouseX, 0, width, -canvas.width/2, canvas.width/2);
  float yy = map(mouseY, 0, height, -canvas.height/2, canvas.height/2);
  interpolationPoints.add(new PVector(xx, yy));
  drawPoints();
  //image(canvas, width/2, height/2, width, height);
  
  
  println(mouseX, mouseY);
}




public void keyPressed()
{
  if (key == 'd' || key == 'D')
  {
    background(0);
    drawGrid();
    drawPoints();
    compute();
    drawPolynomial();
    drawInstructions();
    image(canvas, width/2, height/2, width, height);
  
  }
  else if(key == 's' || key == 'S')
  {
    canvas.save("lagrange_plot.png");
  }
  else if(key == 'c')
  {
    interpolationPoints = new ArrayList<PVector>();
    linePoints = new ArrayList<PVector>();
    background(0);
    drawGrid();
    image(canvas, width/2, height/2, width, height);
  
  }
}
public void drawGrid()
{
  canvas.beginDraw();
  
  canvas.background(0);
  canvas.stroke(255, 20);
  canvas.strokeWeight(1);

  //float w_interval = canvas.width/10;
  //float h_interval = canvas.height/10;
  float interval = 50;
  
  //draw the right plane
  float ww=00, hh = 0;
  while (ww < canvas.width/2)
  {
    canvas.line(ww+canvas.width/2, 0, ww+canvas.width/2, canvas.height);
    ww += interval;
  }
  ww = 0;
  while(ww > -canvas.width/2)
  {
    canvas.line(ww+canvas.width/2, 0, ww+canvas.width/2, canvas.height);
    ww -= interval;
  }

  while (hh < canvas.height/2)
  {
    canvas.line(0, hh+canvas.height/2, canvas.width, hh+canvas.height/2);
    hh += interval;
  }
  hh = 0;
  while (hh > -canvas.height/2)
  {
    canvas.line(0, hh+canvas.height/2, canvas.width, hh+canvas.height/2);
    hh -= interval;
  }

  canvas.stroke(200, 10, 40);
  canvas.line(canvas.width/2, 0, canvas.width/2, canvas.height);
  canvas.line(0, canvas.height/2, canvas.width, canvas.height/2);
  
  canvas.endDraw();
}


public void drawPoints()
{
  canvas.beginDraw();
  canvas.noStroke();
  canvas.strokeWeight(1);
  canvas.fill(255);
  
  for (int i=0; i<interpolationPoints.size(); i++)
  {
    canvas.ellipse(interpolationPoints.get(i).x+canvas.width/2, interpolationPoints.get(i).y+canvas.height/2, 5, 5);  
    //println(points.get(i).x + "  " + points.get(i).y);
  }
  
  canvas.endDraw();
}

public void drawPolynomial()
{
  canvas.beginDraw();
  canvas.stroke(0xff22C652);
  canvas.strokeWeight(1);
  for (int i=1; i<linePoints.size(); i++)
  {
    canvas.line(linePoints.get(i).x+canvas.width/2, linePoints.get(i).y+canvas.height/2, linePoints.get(i-1).x+canvas.width/2, linePoints.get(i-1).y+canvas.height/2);
    //ellipse(linePoints.get(i).x, linePoints.get(i).y, 2, 2);
  }
  canvas.endDraw();
  
}

public void drawInstructions()
{
  noStroke();
  fill(40, 180);
  rect(15, height-120, 200, 85, 8, 8, 8, 8);
  
  String instructionString = "Add a point:           Left Click.\nClear canvas:          c\nDraw polynomial:   d\nSave screenshot:    s";
  fill(255);
  text(instructionString, 27, height-100);
  
}
public void compute()
{
  //get the xpoints
  float xInterval = width/300;
  float xx = -canvas.width/2;
  while (xx < canvas.width/2)
  {
    float yy = xToY(xx);
    linePoints.add(new PVector(xx, yy));
    xx += xInterval;
  }
}


public float xToY (double xx) {

  int i, j, size = interpolationPoints.size();
  float  sum = 0, term;

  for (i = 0; i < size; ++i) {
    term = interpolationPoints.get(i).y;
    for (j = 0; j < size; ++j) {
      if ( i != j)
        term *= (xx - interpolationPoints.get(j).x) / (interpolationPoints.get(i).x - interpolationPoints.get(j).x);
    }
    sum += term;
  }
  return sum;
}
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Lagrange" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
