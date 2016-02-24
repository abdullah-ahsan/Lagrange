void compute()
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


float xToY (double xx) {

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