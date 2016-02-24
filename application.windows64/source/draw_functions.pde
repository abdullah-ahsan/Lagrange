void drawGrid()
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


void drawPoints()
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

void drawPolynomial()
{
  canvas.beginDraw();
  canvas.stroke(#22C652);
  canvas.strokeWeight(1);
  for (int i=1; i<linePoints.size(); i++)
  {
    canvas.line(linePoints.get(i).x+canvas.width/2, linePoints.get(i).y+canvas.height/2, linePoints.get(i-1).x+canvas.width/2, linePoints.get(i-1).y+canvas.height/2);
    //ellipse(linePoints.get(i).x, linePoints.get(i).y, 2, 2);
  }
  canvas.endDraw();
  
}

void drawInstructions()
{
  noStroke();
  fill(40, 180);
  rect(15, height-120, 200, 85, 8, 8, 8, 8);
  
  String instructionString = "Add a point:           Left Click.\nClear canvas:          c\nDraw polynomial:   d\nSave screenshot:    s";
  fill(255);
  text(instructionString, 27, height-100);
  
}