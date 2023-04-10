package xyz.larkyy.animationlib.animationlib.timeline;

import java.util.TreeMap;

public class Timeline<T extends Keyframe> {

    private final TreeMap<Double, T> timeline = new TreeMap<>();

    public Timeline(TreeMap<Double,T> timeline) {
        this.timeline.putAll(timeline);
    }

    public Timeline() {

    }

    public void addFrame(double time, T frame) {
        this.timeline.put(time,frame);
    }

    public T getFrame(double time) {
        return timeline.get(time);
    }

    public void run(double time) {
        T frame = getFrame(time);
        if (frame != null) {
            frame.run();
        }
    }

    public TreeMap<Double, T> getTimeline() {
        return timeline;
    }
}
