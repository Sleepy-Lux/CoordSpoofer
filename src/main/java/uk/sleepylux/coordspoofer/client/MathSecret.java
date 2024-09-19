package uk.sleepylux.coordspoofer.client;

public class MathSecret {
    public static float MathSecret(float absolute_seed, float multiplier) {
        return (float) (((absolute_seed * multiplier) / 89.3) * 1.4);
    }
}
