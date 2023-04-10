package xyz.larkyy.animationlib.animationlib.timeline;

import java.util.HashMap;
import java.util.Map;

public class TimelineHandler {

    private final Map<Class<? extends Keyframe>, Timeline<? extends Keyframe>> timelines = new HashMap<>();

    public <T extends Keyframe> void addTimeline(Class<T> clazz, Timeline<T> timeline) {
        timelines.put(clazz,timeline);
    }

    public <T extends Keyframe> Timeline<T> getTimeline(Class<T> clazz) {
        return (Timeline<T>) timelines.get(clazz);
    }

    public void run(double time) {
        timelines.values().forEach(tl->tl.run(time));
    }

}