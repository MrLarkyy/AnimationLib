package xyz.larkyy.animationlib.animationlib.timeline;

import org.bukkit.util.Vector;

import java.util.TreeMap;

public class InterpolatedTimeline<T extends InterpolatedKeyframe> extends Timeline<T> {


    public InterpolatedTimeline(TreeMap<Double, T> timeline) {
        super(timeline);
    }

    public InterpolatedTimeline() {

    }

    public Vector getInterpolatedValue(double time) {
        if (getTimeline().isEmpty()) {
            return new Vector(0,0,0);
        }

        double lowerD = getLower(time,getTimeline());
        double higherD = getHigher(time,getTimeline());


        var lower = getTimeline().get(lowerD);
        var higher = getTimeline().get(higherD);

        if (lowerD == higherD) {
            return lower.getVector();
        }

        var interpolation = getInterpolation(lower,higher);

        // Progress
        double d = (time - lowerD) / (higherD - lowerD);

        if (interpolation == InterpolationType.LINEAR) {
            return TimelineUtil.lerp(lower.getVector(),higher.getVector(),d);
        } else if (interpolation == InterpolationType.SMOOTH) {
            double lowerLowerD = getLower(lowerD,getTimeline());
            double higherHigherD = getHigher(higherD,getTimeline());

            var lowerLower = getTimeline().get(lowerLowerD);
            var higherHigher = getTimeline().get(higherHigherD);

            return TimelineUtil.smoothLerp(lowerLower.getVector(),lower.getVector(),higher.getVector(),higherHigher.getVector(),d);
        } else if (interpolation == InterpolationType.STEP) {
            return lower.getVector();
        }
        return new Vector();
    }

    private double getLower(double value, TreeMap<Double,?> map) {
        Double d = map.lowerKey(value);
        if (d == null) {
            return map.firstKey();
        }
        return d;
    }

    private double getHigher(double value, TreeMap<Double,?> map) {
        Double d = map.higherKey(value);
        if (d == null) {
            return map.lastKey();
        }
        return d;
    }

    private InterpolationType getInterpolation(InterpolatedKeyframe keyFrame1, InterpolatedKeyframe keyFrame2) {
        if (keyFrame1.getInterpolationType() == InterpolationType.STEP) {
            return InterpolationType.STEP;
        }

        if (keyFrame1.getInterpolationType() == InterpolationType.SMOOTH || keyFrame2.getInterpolationType() == InterpolationType.SMOOTH) {
            return InterpolationType.SMOOTH;
        }

        return InterpolationType.LINEAR;
    }

}
