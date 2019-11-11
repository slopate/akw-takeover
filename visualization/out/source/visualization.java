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

public class visualization extends PApplet {

boolean running = true;
BufferedInputStream reader = new BufferedInputStream(new FileInputStream( "out.txt" ) );

public void run() {
    while ( running ) {
        if ( reader.available() > 0 ) {
            System.out.print( (char)reader.read() );
        }
        else {
            try {
                sleep( 500 );
            }
            catch ( InterruptedException ex ) {
                running = false;
            }
        }
    }
}


// import hypermedia.net.*;

// int PORT = 5555;
// String IP = "192.168.1.2";
// UDP udp;

// void setup() {
//     udp = new UDP(this, PORT, IP);
//     udp.listen(true);
//     size(500, 500);
//     colorMode(HSB);
// }

// void draw() {

// }

// void receive(byte[] data, String PORT, int IP) {
//     String value = new String(data);
//     println(value);
// }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "visualization" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
