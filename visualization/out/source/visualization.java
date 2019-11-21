import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import oscP5.*; 
import netP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class visualization extends PApplet {




OscP5 oscP5;

String val;

AlexiViz alexiViz;
SarimViz sarimViz;

int alexiData = 0;
int danielData = 0;
int sarimData = 0;
int[] samData = {0, 0, 0};
int[] sabrinaData = {0, 0};

public void setup() {
  

  oscP5 = new OscP5(this, 44444);   //listening

  alexiViz = new AlexiViz();
  sarimViz = new SarimViz();
}

int frame = 0;
int framesPer = 3 * 4;
public void draw() {

  alexiViz.draw(alexiData, danielData, sarimData, samData, sabrinaData);

  
  // here we can switch between each of our classes
  if (frame < framesPer) {
    println("alexi");
    // alexiViz.draw(alexiData, danielData, sarimData, samData, sabrinaData);
  } else if (frame < framesPer * 2) {
    println("sarim");
    // sarimViz.draw(alexiData, danielData, sarimData, samData, sabrinaData);
  } else if (frame < framesPer * 3) {
    println("daniel");
    // daniel
  } else if (frame < framesPer * 4) {
    println("sabrina");
    // sabrina
  } else {
    frame = 0;
  }

  frame++;

}

/* incoming osc message are forwarded to the oscEvent method. */
public void oscEvent(OscMessage theOscMessage) {
  val = theOscMessage.get(0).stringValue();

  String[] parsed = val.split("--");
  try {
    switch(parsed[0]) {
      case "sarim": {
        sarimData = PApplet.parseInt(parsed[1]);
        break;
      }

      case "daniel": {
        danielData = PApplet.parseInt(parsed[1]);
        break;
      }

      case "alexi": {
        alexiData = PApplet.parseInt(parsed[1]);
        break;
      }

      case "sabrina": {
        sabrinaData[0] = PApplet.parseInt(parsed[1]);
        sabrinaData[1] = PApplet.parseInt(parsed[2]);
        break;
      }

      case "sam": {
        samData[0] = PApplet.parseInt(parsed[1]);
        samData[1] = PApplet.parseInt(parsed[2]);
        samData[2] = PApplet.parseInt(parsed[3]);
        break;
      }

      default:
        break;
    }
  } catch(Exception e) {
    println("ERROR: unexpected message");
  }
}
class AlexiViz {
    AlexiViz() {}

    int numElements = 5;

    int elementColors[][] = {
        { 0xff5562D3, 0xff717AD3, 0xff959AD3, 0xff95C3D3, 0xff30A8D3 }, // sam - water 
        { 0xff86A4AF, 0xff94C4D3, 0xff9AA6AF, 0xffCEDFEB, 0xff94A1A9 }, // daniel - air 
        { 0xff483123, 0xffFFDD80, 0xffAD7C54, 0xff201710, 0xffFF8080 }, // sarim - lava
        { 0xff9C4749, 0xffD24749, 0xffD29B47, 0xffAF8339, 0xffAF3464 }, // sabrina - fire
        { 0xff30D392, 0xff186948, 0xff4B9322, 0xff496D34, 0xff304823 }, // alexi - earth
    };

    int prevData[][] = {
        {0, 0, 0}, // sam
        {0, 0, 0}, // daniel
        {0, 0, 0}, // sarim
        {0, 0, 0}, // sabrina
        {0, 0, 0}  // alexi
    };

    public int[][] constructNewData(int[] samData, int danielData, int sarimData, int[] sabrinaData, int alexiData) {
        int newData[][] = {
            {samData[0], samData[1], samData[2]},   // sam
            {danielData, 0, 0},                     // daniel
            {sarimData, 0, 0},                      // sarim
            {sabrinaData[0], sabrinaData[1], 0},    // sabrina
            {alexiData, 0, 0}                       // alexi
        };
        return newData;
    }

    public boolean dataChanged(int[] prev, int[] curr) {
        for (int i = 0; i < 3; i++) {
            if (prev[i] != curr[i]) {
                return true;
            }
        }
        return false;
    }


    public void drawChart(int[][] data) {
        int i = 0;
        for (float rad = 0; rad < TWO_PI; rad += TWO_PI / 5.0f) {
            fill(elementColors[i++][0]);
            arc(width / 2, height / 2, width / 2, width / 2, rad, rad + TWO_PI / 5.0f, PIE);
        }
    }


    public void draw(int alexiData, int danielData, int sarimData, int[] samData, int[] sabrinaData) {
        int newData[][] = this.constructNewData(samData, danielData, sarimData, sabrinaData, alexiData);
        for (int i = 0; i < numElements; i++) {
            this.drawChart(newData);
            if (this.dataChanged(this.prevData[i], newData[i])) {
                // print("changed", i);
                this.drawChart(newData);
            } else {
                // print("not changed", i);
            }
        }

        delay(2000);
    }
}
class SarimViz {

    SarimViz() {}

    /* ------------------------------- Properties ------------------------------- */

    int numElements = 5;

    int elementColors[][] = {
        {0xff5562D3, 0xff717AD3, 0xff959AD3, 0xff95C3D3, 0xff30A8D3}, // sam - water 
        {0xff86A4AF, 0xff94C4D3, 0xff9AA6AF, 0xffCEDFEB, 0xff94A1A9}, // daniel - air 
        {0xff483123, 0xffFFDD80, 0xffAD7C54, 0xff201710, 0xffFF8080}, // sarim - lava
        {0xff9C4749, 0xffD24749, 0xffD29B47, 0xffAF8339, 0xffAF3464}, // sabrina - fire
        {0xff30D392, 0xff186948, 0xff4B9322, 0xff496D34, 0xff304823}, // alexi - earth
    };

    int prevData[][] = {
        {0, 0, 0}, // sam
        {0, 0, 0}, // daniel
        {0, 0, 0}, // sarim
        {0, 0, 0}, // sabrina
        {0, 0, 0}  // alexi
    };

    int elapsedFrames = 0;

    /* --------------------------- Main draw method --------------------------- */
    
    public void draw(int alexiData, 
              int danielData, 
              int sarimData, 
              int[] samData, 
              int[] sabrinaData) {

        // generate gradient if elapsed frames == 0
        if (this.elapsedFrames > 10) {
            this.setGradient(
                0, 0, 
                width, 
                height, 
                this.elementColors[PApplet.parseInt(random(5))][PApplet.parseInt(random(5))], 
                this.elementColors[PApplet.parseInt(random(5))][PApplet.parseInt(random(5))]
            );
            this.elapsedFrames = 0;
        } 
        this.elapsedFrames++;
        
        // if mesh not working, random inputs
        // int newData[][] = this.generateRandomData();
      
        // get new data
        int newData[][] = this.constructNewData(samData, danielData, sarimData, sabrinaData, alexiData);
  
        // draw element circles based on changed readings
        for (int i = 0; i < numElements; i++) {
            if (this.dataChanged(this.prevData[i], newData[i])) {
                print("changed", i);
                this.drawCircle(this.elementColors[i]);
            } else {
                print("not changed", i);
            }
        }
       
        // mandate delay
        delay(2000);
    }

    /* ----------------------------- Helper methods ----------------------------- */

    public int[][] constructNewData(int[] samData, 
                         int danielData,
                         int sarimData,
                         int[] sabrinaData,
                         int alexiData) {
        int newData[][] = {
            {samData[0], samData[1], samData[2]},   // sam
            {danielData, 0, 0},                     // daniel
            {sarimData, 0, 0},                      // sarim
            {sabrinaData[0], sabrinaData[1], 0},    // sabrina
            {alexiData, 0, 0}                       // alexi
        };
        return newData;
    }

    public int[][] generateRandomData() {
        int[] samData = {PApplet.parseInt(random(5)), PApplet.parseInt(random(5)), PApplet.parseInt(random(5))};
        int danielData = PApplet.parseInt(random(5));
        int sarimData = PApplet.parseInt(random(5));
        int[] sabrinaData = {PApplet.parseInt(random(5)), PApplet.parseInt(random(5))};
        int alexiData = PApplet.parseInt(random(5));
        int randomData[][] = this.constructNewData(samData, danielData, sarimData, sabrinaData, alexiData);
        return randomData;
    }

    public boolean dataChanged(int[] prev, int[] curr) {
        for (int i = 0; i < 3; i++) {
            if (prev[i] != curr[i]) {
                return true;
            }
        }
        return false;
    }


    public void drawCircle(int[] elementColor) {
        float size = random (80, 300);
        float x = random(width);
        float y = random(height);
        for (float ring = size; ring >= 0; ring -= random(2, 10)) {
            int randomColorIndex = PApplet.parseInt(random(5));
            fill(elementColor[randomColorIndex]);
            ellipse(x, y, ring, ring);
        }
    }

    public void setGradient(int x, int y, float w, float h, int c1, int c2) {
        noFill();
        for (int i = y; i <= y+h; i++) {
            float inter = map(i, y, y+h, 0, 1);
            int c = lerpColor(c1, c2, inter);
            stroke(c);
            line(x, i, x+w, i);
        }
    }
}

/* -------------------------------- Test code ------------------------------- */

// SarimViz sv;

// void setup() {
//   size(720, 480); 
//   background(64);
//   noStroke();
//   sv = new SarimViz();
// }

// void draw() {
//   int[][]rd = sv.generateRandomData();
//   int alexiData = rd[4][0];
//   int danielData = rd[1][0];
//   int[] samData = rd[0];
//   int sarimData = rd[2][0];
//   int[] sabrinaData = rd[3];
//   sv.draw(alexiData, danielData, samData, sarimData, sabrinaData);
// }
  public void settings() {  size(720, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "visualization" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
