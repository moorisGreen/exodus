package net.complete.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.complete.CompleteMod;
import net.minecraft.entity.Entity;
import net.minecraft.world.IWorld;

import java.util.Map;
import java.util.HashMap;

public class TestexeProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CompleteMod.LOGGER.warn("Failed to load dependency entity for procedure EXE!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CompleteMod.LOGGER.warn("Failed to load dependency x for procedure EXE!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CompleteMod.LOGGER.warn("Failed to load dependency y for procedure EXE!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CompleteMod.LOGGER.warn("Failed to load dependency z for procedure EXE!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CompleteMod.LOGGER.warn("Failed to load dependency world for procedure EXE!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			WaitExploProcedure.executeProcedure($_dependencies);
		}
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			CompleteTnTProcedure.executeProcedure($_dependencies);
		}
	}
}
