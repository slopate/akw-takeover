
# Earth input

I decided to adopt the earth element, more specifically lava. 

I had an idea that I wanted the viewer to interact using a joystick. After a number of iterations, I decided on making the viewer rotate the joystick 360 degrees to activate an LED ring.

## LED ring

![LED Ring](./docs/ring.png)

Given the imprecision of the joystick, I decided to create a ring with only 8 LEDs. Although fewer LEDs, they can be distributed further apart and still allow the user to feel circular motion as the LEDs activate sequentially.

The 8 LEDs were encased in a 3D-printed enclosure. Each LED shares a ground and has independent GPI wiring. 


## Enclosure

After trying origami and PVC solutions, I eventually settled on creating my enclosure from cardboard, in the shape of a volcano.

![Volcano Cardboard](./docs/volcano_cardboard.png)

The enclosure is hollow, which has the benefit of having enough space for all the electronics, including the ESP32 and the power source. 

The volcano was painted using acrylic paint. 

![Volcano Paint](./docs/volcano_painted.png)

## Technical Problems

1. Pins for LED output

Not all pins were suitable. Those that were:

- Left track
  - GPIO32
  - GPIO33
  - GPIO26
  - GPIO27