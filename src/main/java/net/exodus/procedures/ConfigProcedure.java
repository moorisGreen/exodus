package net.exodus.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.exodus.ExodusModVariables;
import net.exodus.ExodusMod;

import java.util.Map;
import java.util.Collections;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class ConfigProcedure {
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void init(FMLCommonSetupEvent event) {
			executeProcedure(Collections.emptyMap());
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		File config = new File("");
		com.google.gson.JsonObject jsonobj = new com.google.gson.JsonObject();
		config = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config"), File.separator + "exodus.config");
		if (!config.exists()) {
			NewConf(config, jsonobj);
		} else {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(config));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					jsonobj = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					ExodusModVariables.HoleSize = jsonobj.get("HoleSize").getAsDouble();
					ExodusModVariables.HoleSpeed = jsonobj.get("HoleSpeed").getAsDouble();
					ExodusModVariables.CollectorSpeed = jsonobj.get("CollectorSpeed").getAsDouble();

				} catch (Exception e) {
					e.printStackTrace();
					NewConf(config, jsonobj);
				}
			}
		}
		ExodusMod.LOGGER.fatal(("" + ExodusModVariables.HoleSize));
		ExodusMod.LOGGER.fatal(("" + ExodusModVariables.HoleSpeed));
		ExodusMod.LOGGER.fatal(("" + ExodusModVariables.CollectorSpeed));
	}
	private static void NewConf(File config, com.google.gson.JsonObject jsonobj) {
		try {
				config.getParentFile().mkdirs();
				config.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			jsonobj.addProperty("HoleSize", 200);
			jsonobj.addProperty("HoleSpeed", 10000);
			jsonobj.addProperty("CollectorSpeed", 84000);
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(config);
					fileWriter.write(mainGSONBuilderVariable.toJson(jsonobj));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
			ExodusModVariables.HoleSize = 200;
			ExodusModVariables.HoleSpeed = 10000;
			ExodusModVariables.CollectorSpeed = 84000;
	}
}
