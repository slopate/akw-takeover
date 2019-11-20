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

int alexiData = 0;
int danielData = 0;
int samData = 0;
int sarimData = 0;
int[] sabrinaData = {0, 0};

public void setup() {
  

  oscP5 = new OscP5(this, 44444);   //listening

  alexiViz = new AlexiViz();
}

public void draw() {
  alexiViz.draw(alexiData, danielData, samData, sarimData, sabrinaData);
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
        samData = PApplet.parseInt(parsed[1]);
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
    AlexiViz() {

    }

    public void draw(int alexiData, int danielData, int samData, int sarimData, int[] sabrinaData) {

    }
}
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
