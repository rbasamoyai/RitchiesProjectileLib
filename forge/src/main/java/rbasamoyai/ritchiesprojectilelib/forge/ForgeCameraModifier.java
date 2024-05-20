package rbasamoyai.ritchiesprojectilelib.forge;

import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import rbasamoyai.ritchiesprojectilelib.effects.CameraModifier;

public class ForgeCameraModifier implements CameraModifier {

    private final CameraSetup cameraSetup;

    public ForgeCameraModifier(CameraSetup cameraSetup) {
        this.cameraSetup = cameraSetup;
    }

    @Override public void setYaw(float yaw) { this.cameraSetup.setYaw(yaw); }
    @Override public float getYaw() { return this.cameraSetup.getYaw(); }

    @Override public void setPitch(float pitch) { this.cameraSetup.setPitch(pitch); }
    @Override public float getPitch() { return this.cameraSetup.getPitch(); }

    @Override public void setRoll(float roll) { this.cameraSetup.setRoll(roll); }
    @Override public float getRoll() { return this.cameraSetup.getRoll(); }

}
