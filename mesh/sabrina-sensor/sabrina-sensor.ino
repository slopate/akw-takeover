// Required dependencies:
// From Arduino library manager: ArduinoJson, TaskScheduler
// From Github (https://github.com/me-no-dev/AsyncTCP): AsyncTCP
    // to install, download as zip file
    // Sketch --> Include Library --> Add .zip libary
    
#include <painlessMesh.h>
#include <AsyncTCP.h>

Scheduler userScheduler; // to control your personal task
painlessMesh mesh;

#define   ssid     "akw-takeover"
#define   password   "scott-squad"
#define   port       5555

// -------------------------------
// Sensor Setup

const int piezoPin = 32;
const int touchPin = 27;

const int touchThreshold = 30;
int touchTime = 0;
int allowedSkips = 0;
// -------------------------------

// User stub
void sendMessage(); // Prototype so PlatformIO doesn't complain

Task taskSendMessage( TASK_SECOND * 1 , TASK_FOREVER, &sendMessage );

String readSensors() {
  int piezoVal = analogRead(piezoPin);
  int touchVal = touchRead(touchPin);

  if (touchVal < touchThreshold) {
    touchTime ++;
  }
  else {
    if (allowedSkips > 2) touchTime = 0;
    else allowedSkips ++;
  }

  String returnpiezo = String(piezoVal) + "-";
  String returntime = String(touchTime);
  String returnVal = String("sabrina-" + returnpiezo + returntime + "-");

  Serial.println(returnVal);
  return returnVal;
}

void sendMessage() {
  String msg = readSensors();
  msg += mesh.getNodeId();
  mesh.sendBroadcast( msg );
  taskSendMessage.setInterval(random( TASK_SECOND * 1, TASK_SECOND * 5 ));
}

// Needed for painless library
void receivedCallback( uint32_t from, String &msg ) {
  Serial.printf("startHere: Received from %u msg=%s\n", from, msg.c_str());
}

void newConnectionCallback(uint32_t nodeId) {
  Serial.printf("--> startHere: New Connection, nodeId = %u\n", nodeId);
}

void changedConnectionCallback() {
  Serial.printf("Changed connections\n");
}

void nodeTimeAdjustedCallback(int32_t offset) {
  Serial.printf("Adjusted time %u. Offset = %d\n", mesh.getNodeTime(), offset);
}


void setup() {
  Serial.begin(115200);
  pinMode(piezoPin, INPUT_PULLUP);
  digitalWrite(piezoPin, LOW);
  
  mesh.setDebugMsgTypes( ERROR | STARTUP );  // set before init() so that you can see startup messages
  mesh.init( ssid, password, &userScheduler, port );

  mesh.onReceive(&receivedCallback);
  mesh.onNewConnection(&newConnectionCallback);
  mesh.onChangedConnections(&changedConnectionCallback);
  mesh.onNodeTimeAdjusted(&nodeTimeAdjustedCallback);

  userScheduler.addTask( taskSendMessage );
  taskSendMessage.enable();
}

void loop() {
  mesh.update();
}
