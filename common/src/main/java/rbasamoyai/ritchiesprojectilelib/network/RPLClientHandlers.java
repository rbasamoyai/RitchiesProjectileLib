package rbasamoyai.ritchiesprojectilelib.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import rbasamoyai.ritchiesprojectilelib.effects.screen_shake.RPLScreenShakeHandlerClient;

public class RPLClientHandlers {

	public static void checkVersion(ClientboundCheckChannelVersionPacket packet) {
		if (RPLNetwork.VERSION.equals(packet.serverVersion())) return;
		Minecraft mc = Minecraft.getInstance();
		if (mc.getConnection() != null)
			mc.getConnection().onDisconnect(new TextComponent("Ritchie's Projectile Library on the client uses a different network format than the server.")
					.append(" Please use a matching format."));
	}

	public static void syncPreciseMotion(ClientboundPreciseMotionSyncPacket packet) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null) return;
		Entity entity = mc.level.getEntity(packet.entityId());
		if (entity == null) return;

		int lerpSteps = packet.lerpSteps();
		if (lerpSteps < 1) lerpSteps = 3;
		entity.lerpTo(packet.x(), packet.y(), packet.z(), packet.yRot(), packet.xRot(), lerpSteps, false);
		entity.setDeltaMovement(packet.dx(), packet.dy(), packet.dz());
		entity.setOnGround(packet.onGround());
	}

    public static void shakeScreen(ClientboundShakeScreenPacket packet) {
        if (packet.modHandlerId() == null) {
            RPLScreenShakeHandlerClient.addShakeEffect(packet.effect());
        } else {
            RPLScreenShakeHandlerClient.addShakeEffect(packet.modHandlerId(), packet.effect());
        }
    }

}
