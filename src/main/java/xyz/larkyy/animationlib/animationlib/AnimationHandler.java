package xyz.larkyy.animationlib.animationlib;

import java.util.HashMap;
import java.util.Map;

public class AnimationHandler {

    private final Map<String, Animation> animations = new HashMap<>();

    public void update() {
        this.animations.entrySet().removeIf(entry -> !entry.getValue().update());
    }

    public void run(String identifier, Animation animation) {
        animations.put(identifier,animation);
    }

    public boolean isRunning(String identifier) {
        return this.animations.containsKey(identifier);
    }

    public void stopAll() {
        animations.clear();
    }

    public void stop(String identifier) {
        animations.remove(identifier);
    }

}
