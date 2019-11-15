//#include <AxisJoystick.h>
#define VRX 26
#define VRY 27
#define JOY_LOW 0
#define JOY_HIGH 4095

//AxisJoystick joystick(-1, VRX, VRY);
int LEDPins[] = {12, 14, 27, 33, 23, 22, 21, 19};

void mapJoystickToLED();
int getYAxis();
int getXAxis();
void setLowAllExcept(int LEDNotLow);
void animateComplete();

void animateComplete() {
    //  rotate circle 8 times
    for (int i = 0; i < 40; i++) {
        digitalWrite(LEDPins[i % 8], HIGH);
        delay(50);
        digitalWrite(LEDPins[i % 8], LOW);
    }
}

int getYAxis() {
//    if (joystick.isUp()) {
//        return -1;
//    } else if (joystick.isDown()) {
//        return +1;
//    } else {
//        return 0;
//    }
}

int getXAxis() {
//    if (joystick.isLeft()) {
//        return -1;
//    } else if (joystick.isRight()) {
//        return +1;
//    } else {
//        return 0;
//    }
}

void setLowAllExcept(int LEDNotLow) {
    for (int i = 0; i < 8; i++) {
        if (i == LEDNotLow) {
            continue;
        }
        digitalWrite(LEDPins[i], LOW);
    }
}

void mapJoystickToLED() {
    int x = getXAxis();
    int y = getYAxis();

    if (x == 1 && y == 0) {
        Serial.println("1, 0");
        digitalWrite(LEDPins[0], HIGH);
        setLowAllExcept(0);

    } else if (x == 1 && y == 1) {
        Serial.println("1, 1");
        digitalWrite(LEDPins[1], HIGH);
        setLowAllExcept(1);

    } else if (x == 0 && y == 1) {
        //    Serial.println("0, 1");
        digitalWrite(LEDPins[2], HIGH);
        setLowAllExcept(2);

    } else if (x == -1 && y == -1) {
        Serial.println("-1, 1");
        digitalWrite(LEDPins[3], HIGH);
        setLowAllExcept(3);

    } else if (x == -1 && y == 0) {
        Serial.println("-1, 0");
        digitalWrite(LEDPins[4], HIGH);
        setLowAllExcept(4);

    } else if (x == -1 && y == -1) {
        Serial.println("-1, -1");
        digitalWrite(LEDPins[5], HIGH);
        setLowAllExcept(5);

    } else if (x == 0 && y == -1) {
        Serial.println("0, -1");
        digitalWrite(LEDPins[6], HIGH);
        setLowAllExcept(6);

    } else if (x == 1 && y == -1) {
        Serial.println("1, -1");
        digitalWrite(LEDPins[7], HIGH);
        setLowAllExcept(7);

    } else {
        Serial.println("0, 0");
        setLowAllExcept(-1);
    }
}

void setup() {
    // put your setup code here, to run once:
    Serial.begin(9600);
//    joystick.calibrate(JOY_LOW, JOY_HIGH);

    for (int i = 0; i < 8; i++) {
        pinMode(LEDPins[i], OUTPUT);
    }
}

void loop() {
    // put your main code here, to run repeatedly:
    //  joystick.multipleRead();

    //  mapJoystickToLED();

    animateComplete();
    delay(5000);
}
