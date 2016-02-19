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

public void setup()
{
 

  interpolationPoints = new ArrayList<PVector>();
  linePoints = new ArrayList<PVector>();
  drawGrid();
  //surface.setResizable(true);
}

public void draw()
{
}

public void drawGrid()
{
  background(0);
  stroke(255, 30);
  strokeWeight(0.5f);

  float w_interval = width/10;
  float h_interval = height/10;

  float ww=0, hh = 0;
  while (ww < width)
  {
    line(ww, 0, ww, height);
    ww += w_interval;
  }

  while (hh < width)
  {
    line(0, hh, width, hh);
    hh += h_interval;
  }

  stroke(200, 10, 40);
  line(width/2, 0, width/2, height);
  line(0, height/2, width, height/2);
}

public void mousePressed()
{
  interpolationPoints.add(new PVector(mouseX, mouseY));
  drawPoints();
}

public void drawPoints()
{
  noStroke();
  strokeWeight(1);
  fill(255);

  for (int i=0; i<interpolationPoints.size(); i++)
  {
    ellipse(interpolationPoints.get(i).x, interpolationPoints.get(i).y, 5, 5);  
    //println(points.get(i).x + "  " + points.get(i).y);
  }
}

public void compute()
{
  //get the xpoints
  float xInterval = width/300;
  float xx = 0;
  while (xx < width)
  {
    float yy = xToY(xx);
    linePoints.add(new PVector(xx, yy));
    xx += xInterval;
  }
}

public void drawLine()
{
  stroke(0xff22C652);
  strokeWeight(1);
  for (int i=1; i<linePoints.size(); i++)
  {
    line(linePoints.get(i).x, linePoints.get(i).y, linePoints.get(i-1).x, linePoints.get(i-1).y);
    //ellipse(linePoints.get(i).x, linePoints.get(i).y, 2, 2);
  }
}

public void keyPressed()
{
  if (key == 'd' || key == 'D')
  {
    background(0);
    drawGrid();
    drawPoints();
    compute();
    drawLine();
  }
  else
  {
    interpolationPoints = new ArrayList<PVector>();
    linePoints = new ArrayList<PVector>();
    background(0);
    drawGrid();
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
