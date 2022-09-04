package net.exodus.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.item.ItemStack;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.exodus.ExodusMod;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class DropBlackHoleProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				ExodusMod.LOGGER.warn("Failed to load dependency itemstack for procedure DropBlackHole!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ExodusMod.LOGGER.warn("Failed to load dependency x for procedure DropBlackHole!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ExodusMod.LOGGER.warn("Failed to load dependency y for procedure DropBlackHole!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ExodusMod.LOGGER.warn("Failed to load dependency z for procedure DropBlackHole!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ExodusMod.LOGGER.warn("Failed to load dependency world for procedure DropBlackHole!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ExodusMod.LOGGER.warn("Failed to load dependency entity for procedure DropBlackHole!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		String Tag = "";
		String Isum = "";
		String IsumT = "";
		String IsumN = "";
		List List1 = new ArrayList<String>();
		List List2 = new ArrayList<Integer>();
		int sumN = 0;
		int AllItems = 0;
		boolean Long = false;
		Tag = (String) ((itemstack).getOrCreateTag().getString("Items"));
		Tag = Tag.replace(",", "");
		int TagLength = Tag.length() - Tag.replace("{", "").length();
		for (int LengthCur= 0; LengthCur < TagLength; LengthCur++) {
			Isum = Tag.substring(Tag.indexOf("{") + 1);
			Isum = Isum.substring(0, Isum.indexOf("}"));
			Tag = Tag.replace("{" + Isum + "}", "");
			IsumT = Isum.substring(0, Isum.lastIndexOf(' '));
			IsumN = Isum.substring(Isum.lastIndexOf(' ') + 1);
			try {
				sumN = Integer.parseInt(IsumN);
			}
			catch (NumberFormatException e)
			{
				sumN = 0;
			}
			List1.add(IsumT);
			List2.add(sumN);
			AllItems = AllItems + sumN;
		}
		if (AllItems >= 8128) {
			Long = true;
		}
		if (Long == false) {
			for (int Count = 0; Count < List1.size(); Count++) {
				IsumT = (String) List1.get(Count);
				sumN = (int) List2.get(Count);
				if (sumN < 127) {
					if (world instanceof ServerWorld) {
					((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
					new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),("/summon item ~ ~ ~ {Item:{id:\"" + IsumT + "\",Count:" + sumN + "b}}"));
					}
				}
				else {
					while (sumN >= 127) {
						if (world instanceof ServerWorld) {
						((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
						new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),("/summon item ~ ~ ~ {Item:{id:\"" + IsumT + "\",Count:127b}}"));
						}
						sumN = sumN - 127;
					}
					if (world instanceof ServerWorld) {
					((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
					new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),("/summon item ~ ~ ~ {Item:{id:\"" + IsumT + "\",Count:" + sumN + "b}}"));
					}
				}
			}
			((itemstack)).setCount((int) 0);
		}
		else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("§4Black Hole §ris too big to drop"), (true));
			}
		}
	}
}
