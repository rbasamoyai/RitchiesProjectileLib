package rbasamoyai.ritchiesprojectilelib.effects.screen_shake;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;

public interface ModScreenShakeHandler {

    void tick(Minecraft minecraft);
    void addEffect(ScreenShakeEffect effect);
    void clearEffects();
    void applyEffects(ScreenShakeContext context);

    class Impl implements ModScreenShakeHandler {
        private final List<ScreenShakeEffect> activeEffects = new LinkedList<>();

        public final PerlinSimplexNoise yawNoise;
        public final PerlinSimplexNoise pitchNoise;
        public final PerlinSimplexNoise rollNoise;

        public Impl() {
            long seed = new Random().nextLong();
            this.yawNoise = new PerlinSimplexNoise(new LegacyRandomSource(seed), ImmutableList.of(-2, -1, 0));
            this.pitchNoise = new PerlinSimplexNoise(new LegacyRandomSource(seed + 1), ImmutableList.of(-2, -1, 0));
            this.rollNoise = new PerlinSimplexNoise(new LegacyRandomSource(seed + 2), ImmutableList.of(-2, -1, 0));
        }

        @Override
        public void tick(Minecraft minecraft) {
            this.activeEffects.removeIf(ScreenShakeEffect::tick);
        }

        @Override
        public void applyEffects(ScreenShakeContext context) {
            float partialTicks = context.partialTicks();
            float deltaYaw = 0;
            float deltaPitch = 0;
            float deltaRoll = 0;
            for (ScreenShakeEffect effect : this.activeEffects) {
                ScreenShakeEffect modified = this.modifyScreenShake(effect);
                float f = modified.getProgressNormalized(partialTicks);
                float decay = f * f;
                float offset = modified.getProgress(partialTicks);
                deltaYaw += modified.yawMagnitude * decay * (float) this.yawNoise.getValue(0, offset, false);
                deltaPitch += modified.pitchMagnitude * decay * (float) this.pitchNoise.getValue(0, offset, false);
                deltaRoll += modified.rollMagnitude * decay * (float) this.rollNoise.getValue(0, offset, false);
            }
            context.setDeltaYaw(context.getDeltaYaw() + deltaYaw);
            context.setDeltaPitch(context.getDeltaPitch() + deltaPitch);
            context.setDeltaRoll(context.getDeltaRoll() + deltaRoll);
        }

        @Override public void addEffect(ScreenShakeEffect effect) { this.activeEffects.add(effect); }
        @Override public void clearEffects() { this.activeEffects.clear(); }

        public ScreenShakeEffect modifyScreenShake(ScreenShakeEffect effect) {
            return effect;
        }
    }

}
