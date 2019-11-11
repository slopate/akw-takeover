import hypermedia.net.*;

int PORT = 57222;
String IP = "192.168.1.2";
UDP udp;

void setup() {
    udp = new UDP(this, PORT, IP);
    udp.listen(true);
    size(500, 500);
    colorMode(HSB);
}

void draw() {

}

void receive(byte[] data, String PORT, int IP) {
    String value = new String(data);
    println(value);
}