s.boot;

NetAddr.langPort;
NetAddr.localAddr;

n = NetAddr.new("127.0.0.1", 44444);
o = OSCFunc({arg msg, time, addr, recvPort; [msg, time, addr, recvPort].postln; }, '/goodbye', n, 44444);

o.free;

(
f = {|msg, time, addr, recvPort|
	if(msg[0] != '/status.reply') {
		~data = msg[1].asString.split($-).postln;

		if (~data[0] == "sam", {
			if ((~data[1].asInteger > 0) || (~data[1].asInteger > 0) || (~data[1].asInteger > 0), {
				a = Array.with(~data[1].asInteger, ~data[2].asInteger, ~data[3].asInteger).normalizeSum.postln;
				~sound.set(\ampEarth, a[0], \ampWater, a[1], \ampFire, a[2])
			})
		})

	}
};
thisProcess.addOSCRecvFunc(f);
);

thisProcess.removeOSCRecvFunc(f);

(
b = Buffer.readDir(("akwSounds/").resolveRelative, 0, server: s);
//(b.size).do{arg i; b[i].normalize};
)

SynthDef(\player, {|bufEarth, bufFire, bufWater, bufVolc, ampEarth=0, ampFire=0, ampWater=0, ampVolc = 0, start=0, rate=1, loop=1|
	var sigEarth = PlayBuf.ar(2, bufEarth, rate, 1, start, loop);
	var sigFire = PlayBuf.ar(2, bufFire, rate, 1, start, loop);
	var sigWater = PlayBuf.ar(2, bufWater, rate, 1, start, loop);
	var sigVolc = PlayBuf.ar(2, bufVolc, rate, 1, start, loop);
	var sigElement = Mix([sigEarth * ampEarth, sigFire * ampFire, sigWater * ampWater, sigVolc * ampVolc]);
	Out.ar(0, sigElement);
}, [0, 0, 0, 0, 4, 4, 4, 4]
).add;

~sound = Synth(\player, [\bufEarth, b[1], \bufFire, b[3], \bufWater, b[5], \bufVolc, b[7]]);
