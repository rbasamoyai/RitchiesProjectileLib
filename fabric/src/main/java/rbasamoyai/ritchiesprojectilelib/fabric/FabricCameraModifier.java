package rbasamoyai.ritchiesprojectilelib.fabric;

import io.github.fabricators_of_create.porting_lib.event.client.CameraSetupCallback.CameraInfo;
import rbasamoyai.ritchiesprojectilelib.effects.CameraModifier;

public class FabricCameraModifier implements CameraModifier {

    private final CameraInfo cameraInfo;

    public FabricCameraModifier(CameraInfo cameraInfo) {
        this.cameraInfo = cameraInfo;
    }

    @Override public void setYaw(float yaw) { this.cameraInfo.yaw = yaw; }
    @Override public float getYaw() { return this.cameraInfo.yaw; }

    @Override public void setPitch(float pitch) { this.cameraInfo.pitch = pitch; }
    @Override public float getPitch() { return this.cameraInfo.pitch; }

    @Override public void setRoll(float roll) { this.cameraInfo.roll = roll; }
    @Override public float getRoll() { return this.cameraInfo.roll; }

}