package rbasamoyai.ritchiesprojectilelib.effects.screen_shake;

public class ScreenShakeEffect {

    public final int duration;
    private int timer;

    public final float yawMagnitude;
    public final float pitchMagnitude;
    public final float rollMagnitude;

    public ScreenShakeEffect(int duration, float yawMagnitude, float pitchMagnitude, float rollMagnitude) {
        this.duration = duration;
        this.yawMagnitude = yawMagnitude;
        this.pitchMagnitude = pitchMagnitude;
        this.rollMagnitude = rollMagnitude;
    }

    public ScreenShakeEffect(int duration, float rotationMagnitude) {
        this(duration, rotationMagnitude, rotationMagnitude, rotationMagnitude);
    }

    public boolean tick() {
        if (this.duration < 1) return true;
        this.timer--;
        return this.timer < 1;
    }

    public float getProgress(float partialTicks) { return this.timer - partialTicks; }
    public float getProgressNormalized(float partialTicks) { return this.duration < 1 ? 0 : this.getProgress(partialTicks) / this.duration; }

}
