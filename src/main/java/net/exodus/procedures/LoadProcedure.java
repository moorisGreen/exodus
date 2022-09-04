package net.exodus.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import net.exodus.ExodusModVariables;
import net.exodus.ExodusMod;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class LoadProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldLoad(WorldEvent.Load event) {
			IWorld world = event.getWorld();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("world", world);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ExodusMod.LOGGER.warn("Failed to load dependency world for procedure WorldLoad!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		File file = new File("");
		com.google.gson.JsonObject json = new com.google.gson.JsonObject();
		file = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config"), File.separator + "Exodus.config");
		double Time = 86400;
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				json = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				Time = json.get("ColletorSpeed").getAsDouble();
					} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ExodusModVariables.MapVariables.get(world).CollectorSp = Time;
		ExodusModVariables.MapVariables.get(world).syncData(world);
	}

}
