import oscP5.*;
import netP5.*;

OscP5 oscP5;

String val;

void setup() {
  size(720, 480);

  oscP5 = new OscP5(this, 44444);   //listening
}

void draw() {
  
}

/* incoming osc message are forwarded to the oscEvent method. */
void oscEvent(OscMessage theOscMessage) {
  print("### received an osc message.");
  println(" addrpattern: "+theOscMessage.addrPattern());

  val = theOscMessage.get(0).stringValue();

  println(val);
}