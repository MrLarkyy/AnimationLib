package xyz.larkyy.animationlib.animationlib.animation;

import xyz.larkyy.animationlib.animationlib.timeline.Timeline;

import java.util.Map;

public class Animation {

    private double speed;
    private double time = 0d;
    private final double length;
    private final LoopMode loopMode;
    private AnimationPhase animationPhase = AnimationPhase.PLAYING;
    private final Map<String,Timeline<?>> timelines;

    public Animation(LoopMode loopMode, double length, double speed, Map<String,Timeline<?>> timelines) {
        this.loopMode = loopMode;
        this.speed = speed;
        this.length = length;
        this.timelines = timelines;
    }

    public double getSpeed() {
        return speed;
    }

    public double getTime() {
        return time;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean update() {
        if (animationPhase == AnimationPhase.END) {
            return false;
        }
        switch (loopMode) {
            case ONCE:
                if (time < length) {
                    var newTime = time+speed/20d;
                    if (newTime > length) {
                        newTime = length;
                    }
                    time = newTime;
                    return true;
                }
                break;
            case LOOP:
                // +0.05d to also be able to get the last frame of the animation (+ 1tick)
                time = (time + speed / 20) % (length+0.05d);
                return true;
            case HOLD: {
                var newTime = time+speed/20d;
                if (newTime > length) {
                    newTime = length;
                }
                time = newTime;
                return true;
            }
        }
        this.animationPhase = AnimationPhase.END;
        return false;
    }

    public void run() {
        timelines.values().forEach(tl -> tl.run(time));
    }

    public Timeline<?> getTimeline(String id) {
        return this.timelines.get(id);
    }

    public void stop() {
        this.animationPhase = AnimationPhase.END;
    }

    public AnimationPhase getAnimationPhase() {
        return animationPhase;
    }
}
