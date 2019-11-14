import OSC # sudo pip install pyosc (works with py2)
import serial # sudo pip install pyserial
import re
import gpiozero as gp
import time


def parseLogEntry(raw_input):


    return "hello"

class Listener():
	osc_client = None

	def add_osc_connect(self, client_connection):
		self.osc_client = client_connection
		print(self.osc_client)

	def sendOSC(self, content):
		msg = OSC.OSCMessage()
		msg.setAddress("/wand")
		for c in content:
			msg.append(c)
		self.osc_client.send(msg)


def main():
    file = open('mesh_logs.log', 'r')

    c = OSC.OSCClient()
    c.connect(('127.0.0.1', 44444))

    listener = Listener()
    listener.add_osc_connect(c)


    while True:
        where = file.tell()
        line = file.readline()
        if not line:
            time.sleep(1)
            file.seek(where)
        else:
            data = parseLogEntry(line)
            
            try:
                listener.sendOSC(data)
            except Exception as e:
                print(e)



if __name__ == "__main__":
	main()