package xyz.larkyy.animationlib.animationlib.animation;

import java.util.HashMap;
import java.util.Map;

public class SingleAnimationHandler {

    private final Map<String, Animation> animations = new HashMap<>();

    public void update() {
        var set = this.animations.keySet();
        if (set.size() < 1) {
            return;
        }

        var id = set.stream().findFirst().get();
        var animation = animations.get(id);
        if (!animation.update()) {
            animations.remove(id);
        }
    }

    public void run(String identifier, Animation animation) {
        animations.put(identifier,animation);
    }

    public boolean isRunning(String identifier) {
        var first = animations.keySet().stream().findFirst();
        return first.map(s -> (s.equals(identifier))).orElse(false);
    }

    public Animation getRunning() {
        var first = animations.keySet().stream().findFirst();
        return first.map(animations::get).orElse(null);
    }

    public boolean isQueued(String identifier) {
        return animations.containsKey(identifier);
    }

    public void stopAll() {
        animations.clear();
    }

    public void stop(String identifier) {
        animations.remove(identifier);
    }

    public void stopRunning() {
        var first = animations.keySet().stream().findFirst();
        if (!first.isPresent()) {
            return;
        }
        animations.remove(first.get());
    }
}
