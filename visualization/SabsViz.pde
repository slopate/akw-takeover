String[] mytext;
PImage img;
float xDiff = 30;
float yDiff = 30;
int numLines = 26;

float danielRotation = 1;
boolean sarimBool = false;
int sarimCount = 0;

class SabsViz {
  SabsViz() {
    mytext = loadStrings("sabs_media/sabs.txt");
    textSize(20);

    img = loadImage("sabs_media/lava.jpg");
    tint(255, 0); 
    img.resize(width, height);
  }

  void draw(int alexiData, int danielData, int[] samData, int sarimData, int[] sabrinaData) {
    
    // SABRINA ------------------------------------------------------

    if (sabrinaData[1] == 0) {
      background(0);
    } else if (sabrinaData[1] < 3) {
      background(#f8f1a5);
    } else if (sabrinaData[1] < 6) {
      background(#f88e3a);
    } else if (sabrinaData[1] < 10) {
      background(#f86336);
    } else if (sabrinaData[1] >= 10) {
      background(#f80600);
    } 

    // SARIM ------------------------------------------------------
    if (sarimData == 1 || sarimBool == true) {
      image(img, width/2, height/2);
      tint(255, 180);
      sarimBool = true;
      sarimCount += 1;
      if (sarimCount > 6) sarimBool = false;
    } else {
      image(img, width/2, height/2);
      tint(255, 0);
    }

    // DANIEL and ALEXI ------------------------------------------------------
    if (danielData == 1) {
      danielRotation += .01;
    }

    pushMatrix();
    translate(width/4, height/2); 
    rotate(danielRotation * radians(45));
    fill(#f8964b); // orange
    if (alexiData == 4095) ellipse(0, 0, 40, 40);
    else rect(0, 0, 40, 40);
    popMatrix();

    pushMatrix();
    translate(width/2, height/4); 
    rotate(2 * danielRotation * radians(45));
    fill(#63aaf8); // blue
    if (alexiData == 4095) ellipse(0, 0, 70, 70);
    else rect(0, 0, 70, 70);
    popMatrix();

    pushMatrix();
    translate(width/2, 3*height/4); 
    rotate(.5 * danielRotation * radians(45));
    fill(#a6f87c); // green
    if (alexiData == 4095) ellipse(0, 0, 100, 100);
    else rect(0, 0, 100, 100);
    popMatrix();

    pushMatrix();
    translate(3*width/4, height/2); 
    rotate(4 * danielRotation * radians(45));
    fill(#e5b1f8); // purple
    if (alexiData == 4095) ellipse(0, 0, 10, 10);
    else rect(0, 0, 10, 10);
    popMatrix();

    // SAM ------------------------------------------------------

    float x = 0;
    float y = height;
    pushMatrix();
    translate(x, y);
    rotate(-HALF_PI);
    fill(#f8eb48, 150); // yellow

    xDiff = map(samData[0], 0, 600, 30, 70);
    yDiff = map(samData[1], 0, 600, 20, 40);

    for (int i = 0; i < numLines / 2; i++) {
      text(mytext[i], 10 + i*yDiff, 10 + i*xDiff);
    }
    for (int i = numLines / 2; i < 3* numLines / 4; i++) {
      text(mytext[i], height - 1.2*i*yDiff, 10 + i*xDiff);
    }
    for (int i = 3* numLines / 4; i < numLines; i++) {
      text(mytext[i], 10 + i*yDiff/2, 10 + i*xDiff);
    }
    popMatrix();
    
  }
}
