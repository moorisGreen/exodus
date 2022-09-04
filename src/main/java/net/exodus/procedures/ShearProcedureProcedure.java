package net.exodus.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;


import net.exodus.ExodusMod;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ShearProcedureProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ExodusMod.LOGGER.warn("Failed to load dependency x for procedure ShearProcedure!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ExodusMod.LOGGER.warn("Failed to load dependency y for procedure ShearProcedure!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ExodusMod.LOGGER.warn("Failed to load dependency z for procedure ShearProcedure!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ExodusMod.LOGGER.warn("Failed to load dependency world for procedure ShearProcedure!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		ArrayList Pos = new ArrayList();
		int Index = 0;
		int Blocknum = 0;
		int Counter2 = 0;
		double x1 = 0;
		double y1 = 0;
		double z1 = 0;
		double x2 = 0;
		double y2 = 0;
		double z2 = 0;
		String newcord = "";
		String get = "";
		String get2 = "";
		String pos = "";
		String ItemStr = "";
		String blockstr = "";
		BlockState block = Blocks.AIR.getDefaultState();
		List Items = new ArrayList();
		ArrayList<Integer> ItemNumbers = new ArrayList<>();
		Pos.add(x+ ":" + y + ":" + z + ":");
		while (true) {
			x2 = 0;
			y2 = 0;
			z2 = 0;
			get = (String) Pos.get(Index);
			for (int Counter = 0; Counter < 3; Counter++) {
				get2 = get;
				newcord = get2.substring(0, get2.indexOf(":"));
				get = get.replaceFirst(newcord + ":", "");
				if (Counter == 0) {
					x2 = Double.parseDouble(newcord);
				}
				else if (Counter == 1) {
					y2 = Double.parseDouble(newcord);
				}
				else if (Counter == 2) {
					z2 = Double.parseDouble(newcord);
				}
			}
			x1 = (double) (-1);
			for (int index1 = 0; index1 < (int) (3); index1++) {
				y1 = (double) (-1);
				for (int index2 = 0; index2 < (int) (3); index2++) {
					z1 = (double) (-1);
					for (int index3 = 0; index3 < (int) (3); index3++) {
						if (((world.getBlockState(new BlockPos((int) (x1 + x2), (int) (y1 + y2), (int) (z1 + z2))))
							.getMaterial() == net.minecraft.block.material.Material.LEAVES)) {
								pos = (x2 + x1) + ":" + (y2 + y1) + ":" + (z2 + z1) + ":";
								if (!Pos.contains(pos)) {
									Pos.add(pos);
									Blocknum += 1;
								}
						}
						z1 = (double) (z1 + 1);
					}
					y1 = (double) (y1 + 1);
				}
				x1 = (double) (x1 + 1);
			}
			Index = (Index + 1);
			if (Pos.size() <= Index) {
				break;
			}
			else if (Blocknum > 10000) {
				break;
			}
		}
		Index = 0;
		while (true) {
			x2 = 0;
			y2 = 0;
			z2 = 0;
			get = (String) Pos.get(Index);
			for (int Counter = 0; Counter < 3; Counter++) {
				get2 = get;
				newcord = get2.substring(0, get2.indexOf(":"));
				get = get.replaceFirst(newcord + ":", "");
				if (Counter == 0) {
					x2 = Double.parseDouble(newcord);
				}
				else if (Counter == 1) {
					y2 = Double.parseDouble(newcord);
				}
				else if (Counter == 2) {
					z2 = Double.parseDouble(newcord);
				}
			}
			block = (world.getBlockState(new BlockPos((int) (x2), (int) (y2), (int) (z2))));
			blockstr = (String) ("" + (block));
			blockstr = blockstr.replace("Block", "");
			blockstr = blockstr.substring(blockstr.indexOf("{") + 1);
			blockstr = blockstr.substring(0, blockstr.indexOf("}"));
			if (Items.contains(blockstr)) {
				ItemNumbers.set(Items.lastIndexOf(blockstr), (ItemNumbers.get(Items.lastIndexOf(blockstr)) + 1));
			}
			else {
				Items.add(blockstr);
				ItemNumbers.add(1);
			}
			{
			BlockPos _bp = new BlockPos((int) x2, (int) y2, (int) z2);
			BlockState _bs = Blocks.AIR.getDefaultState();
			world.setBlockState(_bp, _bs, 3);
			}

			Index++;
			if (Pos.size() <= Index) {
				break;
			}
		}
		Counter2 = (int) 0;
		ItemStr = (String) "Items:\"";
			for (int index4 = 0; index4 < (int) (Items.size()); index4++) {
				if ((Counter2 > 0)) {
					ItemStr = (String) ((ItemStr) + "" + ("}"));
				}
			ItemStr = (String) ((ItemStr) + "" + ("{") + "" + (Items.get(Counter2)) + "" + (", ") + "" + (ItemNumbers.get(Counter2)));
			Counter2 = (int) (Counter2 + 1);
		}
		ItemStr = (String) ((ItemStr) + "" + ("}\""));
		if (!ItemStr.equals("Items:\"}\""))
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z),
					Vector2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						"/summon item ~ ~ ~ {Item:{id:\"complete:black_hole\",Count:1b,tag:{" + ItemStr + "}}}");
			}
		ItemNumbers.clear();
		Items.clear();
	}
}
