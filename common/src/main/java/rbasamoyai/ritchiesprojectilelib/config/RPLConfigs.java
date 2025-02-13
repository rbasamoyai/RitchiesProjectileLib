package rbasamoyai.ritchiesprojectilelib.config;

import java.util.function.BiConsumer;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class RPLConfigs {

	public static class Server {
		public final ForgeConfigSpec.IntValue maxChunksForceLoaded;
		public final ForgeConfigSpec.IntValue maxChunksLoadedEachTick;
		public final ForgeConfigSpec.IntValue projectileChunkAge;
        public final ForgeConfigSpec.IntValue entityLoadTimeout;

		Server(ForgeConfigSpec.Builder builder) {
			builder.comment("Server configuration settings for Ritchie's Projectile Library").push("server");

			maxChunksForceLoaded = builder
					.comment("The maximum amount of chunks forced to load during a single tick. Set to 0 to force load unlimited chunks.")
					.translation("ritchiesprojectilelib.configgui.maxChunksForceLoaded")
					.defineInRange("maxChunksForceLoaded", 64, 0, Integer.MAX_VALUE);

			maxChunksLoadedEachTick = builder
					.comment("The maximum amount of chunks that can be processed for loading each tick.")
					.translation("ritchiesprojectilelib.configgui.maxChunksLoadedEachTick")
					.defineInRange("maxChunksLoadedEachTick", 32, 1, 256);

			projectileChunkAge = builder
					.comment("[in Ticks]", "How long chunks loaded by this mod last for before being unloaded.")
					.translation("ritchiesprojectilelib.configgui.projectileChunkAge")
					.defineInRange("projectileChunkAge", 3, 1, 20);

            entityLoadTimeout = builder
                    .comment("[in Ticks]", "How long chunks loaded by this mod last for before being unloaded if they have not yet loaded entities.")
                    .translation("ritchiesprojectilelib.configgui.entityLoadTimeout")
                    .defineInRange("entityLoadTimeout", 10, 1, 200);

			builder.pop();
		}
	}

	private static final ForgeConfigSpec serverSpec;
	private static final Server SERVER;
	static {
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
		serverSpec = specPair.getRight();
		SERVER = specPair.getLeft();
	}
	public static Server server() { return SERVER; }
	public static void registerConfigs(BiConsumer<ModConfig.Type, ForgeConfigSpec> cons) {
		cons.accept(ModConfig.Type.SERVER, serverSpec);
	}

	public static void onModConfigLoad(ModConfig modConfig) {
	}

	public static void onModConfigReload(ModConfig modConfig) {
	}

}
