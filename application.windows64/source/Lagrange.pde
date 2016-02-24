ArrayList<PVector> interpolationPoints;
ArrayList<PVector> linePoints;
PGraphics canvas;

void setup()
{
  size(800, 600);
  surface.setResizable(true);
  
  
  canvas = createGraphics(1080, 720);
  
  interpolationPoints = new ArrayList<PVector>();
  linePoints = new ArrayList<PVector>();
  drawGrid();
  
  imageMode(CENTER);
}

void draw()
{
  image(canvas, width/2, height/2, width, height);
  drawInstructions();
}

void mousePressed()
{
  float xx = map(mouseX, 0, width, -canvas.width/2, canvas.width/2);
  float yy = map(mouseY, 0, height, -canvas.height/2, canvas.height/2);
  interpolationPoints.add(new PVector(xx, yy));
  drawPoints();
  //image(canvas, width/2, height/2, width, height);
  
  
  println(mouseX, mouseY);
}




void keyPressed()
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