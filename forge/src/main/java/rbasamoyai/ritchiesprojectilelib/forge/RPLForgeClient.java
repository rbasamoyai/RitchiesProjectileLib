package rbasamoyai.ritchiesprojectilelib.forge;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import rbasamoyai.ritchiesprojectilelib.RPLClient;

public class RPLForgeClient {

    public static void init(IEventBus modBus, IEventBus forgeBus) {
        RPLClient.init();

        forgeBus.addListener(RPLForgeClient::onClientGameTick);
        forgeBus.addListener(RPLForgeClient::onCameraSetup);
        forgeBus.addListener(RPLForgeClient::onPlayerLogOut);
    }

    public static void onClientGameTick(final TickEvent.ClientTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (event.phase == TickEvent.Phase.END) {
            RPLClient.onEndClientGameTick(minecraft);
        }
    }

    public static void onCameraSetup(final EntityViewRenderEvent.CameraSetup event) {
        if (RPLClient.onCameraSetup(event.getCamera(), (float) event.getPartialTicks(), new ForgeCameraModifier(event)) && event.isCancelable()) {
            event.setCanceled(true);
        }
    }

    public static void onPlayerLogOut(final PlayerEvent.PlayerLoggedOutEvent event) {
        RPLClient.onPlayerLogout(event.getPlayer());
    }

}
