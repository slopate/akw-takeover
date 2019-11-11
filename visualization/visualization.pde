import oscP5.*;
import netP5.*;

OscP5 oscP5;

void setup() {
  size(720, 480);
  sine = new SinOsc(this);
  sine.play();
  sine.amp(amp);

  oscP5 = new OscP5(this, 44444);   //listening
}

/* incoming osc message are forwarded to the oscEvent method. */
void oscEvent(OscMessage theOscMessage) {
  print("### received an osc message.");
  println(" addrpattern: "+theOscMessage.addrPattern());

//   photoVal = theOscMessage.get(0).intValue();
//   piezoVal = theOscMessage.get(1).intValue();
//   touchVal = theOscMessage.get(2).intValue();

//   print(theOscMessage.get(0).intValue() + "--");
//   print(theOscMessage.get(1).intValue() + "--");
//   println(theOscMessage.get(2).intValue());
}