package net.exodus.procedures;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.exodus.ExodusModVariables;
import net.exodus.ExodusMod;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class WaitExploProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ExodusMod.LOGGER.warn("Failed to load dependency entity for procedure WaitExplo!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ExodusMod.LOGGER.warn("Failed to load dependency entity for procedure Explo!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ExodusMod.LOGGER.warn("Failed to load dependency x for procedure Explo!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ExodusMod.LOGGER.warn("Failed to load dependency y for procedure Explo!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ExodusMod.LOGGER.warn("Failed to load dependency z for procedure Explo!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ExodusMod.LOGGER.warn("Failed to load dependency world for procedure Explo!");
			return;
		}
		File file = new File("");
		com.google.gson.JsonObject json = new com.google.gson.JsonObject();
		file = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config"), File.separator + "complete.config");
		double Rate = 1000;
		double Sizemx = 200;
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
				Rate = json.get("BlackHoleSpeed").getAsDouble();
				Sizemx = json.get("BlackHoleSize").getAsDouble();
					} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Entity entity = (Entity) dependencies.get("entity");
		
		entity.rotationYaw = (float) (0);
		entity.setRenderYawOffset(entity.rotationYaw);
		entity.prevRotationYaw = entity.rotationYaw;

		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).prevRenderYawOffset = entity.rotationYaw;
			((LivingEntity) entity).rotationYawHead = entity.rotationYaw;
			((LivingEntity) entity).prevRotationYawHead = entity.rotationYaw;
		}

		entity.rotationPitch = (float) (entity.rotationPitch + 1);
		
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double Size = entity.getPersistentData().getDouble("Size");

		if (world instanceof ServerWorld) {
			((World) world).getServer().getCommandManager().handleCommand(
					new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
							new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
					"/execute as @a[distance=.." + (Size + 10) + "] at @s run playsound complete:blackhole master @s ~ ~ ~");
		}
		double x2 = entity.getPersistentData().getDouble("x2");
		double y2 = entity.getPersistentData().getDouble("y2");
		double z2 = entity.getPersistentData().getDouble("z2");
		String nbt = entity.getPersistentData().getString("Items");
		int Multiply2 = 0;
		int Multiply = 0;
		int waittest = 0;
		int waittest2 = 0;
		if (Size > Sizemx) {
			if (!entity.world.isRemote())
				entity.remove();
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z),
						Vector2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"/summon item ~ ~ ~ {NoGravity:1b,Item:{id:\"exodus:blackhole\",Count:1b,tag:{" + NbtToTag(nbt) + "}}}");
			}
		}
		
		if (x2 >= Size && y2 >= Size && z2 >= Size) {
			entity.getPersistentData().putDouble("Size", Size+1);
			Size = Size+1;
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z),
						Vector2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"/scale delay set pehkui:base " + (Size*10) + " " + entity.getUniqueID().toString());
			}
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z),
						Vector2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"/scale set pehkui:model_height " + (Size*2) + " " + entity.getUniqueID().toString());
			}
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z),
						Vector2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"/scale set pehkui:model_width " + (Size*2) + " " + entity.getUniqueID().toString());
			}
			entity.getPersistentData().putDouble("x2", -Size);
			entity.getPersistentData().putDouble("y2", -Size);
			entity.getPersistentData().putDouble("z2", -Size);
		}
		else {
			Explo(Rate,x2, y2, z2, Size, x, y, z, world, entity);
		}
	}
	private static void Explo(double Rate, double SizeX,double SizeY, double SizeZ, double Size, double x, double y, double z, IWorld world, Entity entity) {
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			$_dependencies.put("SizeX", SizeX);
			$_dependencies.put("SizeY", SizeY);
			$_dependencies.put("SizeZ", SizeZ);
			$_dependencies.put("Size", Size);
			$_dependencies.put("Rate", Rate);
			ExploProcedure.executeProcedure($_dependencies);
		}
	}
	private static String NbtToTag(String nbt) {
		String FinalString = "Items:\"";
		String String = "";
		int Itemnum = nbt.length() - nbt.replace(",", "").length();
		for (int ItemCounter = 0; ItemCounter < Itemnum; ItemCounter++) {
			String = nbt.substring(0, nbt.indexOf(","));
			nbt = nbt.replace(String + ",", "");
			if (!FinalString.equals("Items:\"")) {
				FinalString = FinalString + ",";
			}
			if (!String.equals("")) {
				FinalString = FinalString + "{" + String.substring(0, String.indexOf(";")) + ", " + String.substring(String.indexOf(";") + 1, String.length()) + "}";
			}
		}
		return FinalString + "\"";
	}
}
