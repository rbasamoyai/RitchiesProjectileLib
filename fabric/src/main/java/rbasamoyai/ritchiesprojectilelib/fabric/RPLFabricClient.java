package rbasamoyai.ritchiesprojectilelib.fabric;

import io.github.fabricators_of_create.porting_lib.event.client.CameraSetupCallback;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import rbasamoyai.ritchiesprojectilelib.RPLClient;
import rbasamoyai.ritchiesprojectilelib.network.fabric.RPLNetworkImpl;

public class RPLFabricClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		RPLClient.init();
		RPLNetworkImpl.clientInit();

		ClientTickEvents.END_CLIENT_TICK.register(this::onEndClientTick);
		CameraSetupCallback.EVENT.register(this::onCameraSetup);
		ClientLoginConnectionEvents.DISCONNECT.register(this::onPlayerLogout);
	}

	public void onEndClientTick(Minecraft minecraft) {
		RPLClient.onEndClientGameTick(minecraft);
	}

	public boolean onCameraSetup(CameraSetupCallback.CameraInfo cameraInfo) {
		return RPLClient.onCameraSetup(cameraInfo.camera, (float) cameraInfo.partialTicks, new FabricCameraModifier(cameraInfo));
	}

	public void onPlayerLogout(ClientHandshakePacketListenerImpl listener, Minecraft minecraft) {
		RPLClient.onPlayerLogout(minecraft.player);
	}

}
