package xyz.larkyy.animationlib.animationlib.timeline;

import org.bukkit.util.Vector;

public abstract class InterpolatedKeyframe implements Keyframe {

    private final Vector vector;
    private Vector interpolatedVector;
    private InterpolationType interpolationType = InterpolationType.LINEAR;
    public InterpolatedKeyframe(Vector vector) {
        this.vector = vector;
        this.interpolatedVector = vector;
    }

    public Vector getVector() {
        return vector;
    }

    public Vector getInterpolatedVector() {
        return interpolatedVector;
    }

    public void setInterpolatedVector(Vector interpolatedVector) {
        this.interpolatedVector = interpolatedVector;
    }

    public InterpolationType getInterpolationType() {
        return interpolationType;
    }

    public void setInterpolationType(InterpolationType interpolationType) {
        this.interpolationType = interpolationType;
    }
}
