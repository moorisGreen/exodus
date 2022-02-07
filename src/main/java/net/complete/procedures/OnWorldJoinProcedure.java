package net.complete.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.complete.CompleteModVariables;
import net.complete.CompleteMod;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class OnWorldJoinProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onEntityJoin(EntityJoinWorldEvent event) {
			World world = event.getWorld();
			Entity entity = event.getEntity();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CompleteMod.LOGGER.warn("Failed to load dependency world for procedure OnWorldJoin!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		File file = new File("");
		com.google.gson.JsonObject json = new com.google.gson.JsonObject();
		file = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config"), File.separator + "complete.config");
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
		CompleteModVariables.MapVariables.get(world).CollectorSp = Time;
		CompleteModVariables.MapVariables.get(world).syncData(world);
	}
}
