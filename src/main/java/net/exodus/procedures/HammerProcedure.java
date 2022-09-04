package net.exodus.procedures;

import org.spongepowered.asm.util.Counter;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.Direction;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.exodus.ExodusModVariables;
import net.exodus.ExodusMod;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class HammerProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ExodusMod.LOGGER.warn("Failed to load dependency entity for procedure Hammer!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ExodusMod.LOGGER.warn("Failed to load dependency x for procedure Hammer!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ExodusMod.LOGGER.warn("Failed to load dependency y for procedure Hammer!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ExodusMod.LOGGER.warn("Failed to load dependency z for procedure Hammer!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ExodusMod.LOGGER.warn("Failed to load dependency world for procedure Hammer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		BlockState block = Blocks.AIR.getDefaultState();
		List Items = new ArrayList();
		ArrayList<Integer> ItemNumbers = new ArrayList<>();
		double sx = -5;
		double sy = -5;
		double sz = -5;
		double repeat = 11;
		int Counter = 0;
		String blockstr = "";
		String Test = "";
		if ((((entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ExodusModVariables.PlayerVariables())).hammerDir) == Direction.NORTH)) {
			sz = (double) -10;
		}
		else if ((((entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ExodusModVariables.PlayerVariables())).hammerDir) == Direction.SOUTH)) {
			sz = (double) 0;
		}
		else {
			sz = (double) -5;
		}
		for (int index0 = 0; index0 < (int) repeat; index0++) {
			if ((((entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ExodusModVariables.PlayerVariables())).hammerDir) == Direction.DOWN)) {
			sy = (double) -10;
			}
			else if ((((entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ExodusModVariables.PlayerVariables())).hammerDir) == Direction.UP)) {
			sy = (double) 0;
			}
			else {
			sy = (double) -1;
			}
			for (int index1 = 0; index1 < (int) repeat; index1++) {
				if ((((entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ExodusModVariables.PlayerVariables())).hammerDir) == Direction.EAST)) {
				sx = (double) 0;
				}
				else if ((((entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ExodusModVariables.PlayerVariables())).hammerDir) == Direction.WEST)) {
				sx = (double) -10;
				}
				else {
				sx = (double) -5;
				}
				for (int index2 = 0; index2 < (int) repeat; index2++) {
					block = (world.getBlockState(new BlockPos((int) (sx + x), (int) (sy + y), (int) (sz + z))));
					blockstr = (String) ("" + (block));
					blockstr = blockstr.replace("Block", "");
					blockstr = blockstr.substring(blockstr.indexOf("{") + 1);
					blockstr = blockstr.substring(0, blockstr.indexOf("}"));
					if (Items.contains(blockstr)) {
						ItemNumbers.set(Items.lastIndexOf(blockstr), (ItemNumbers.get(Items.lastIndexOf(blockstr)) + 1));
						world.setBlockState(new BlockPos((int) (sx + x), (int) (sy + y), (int) (sz + z)), Blocks.AIR.getDefaultState(), 3);
					} else {
						if ((!(block.getBlock() instanceof FlowingFluidBlock)) && (!(block.getFluidState().isSource())) && block.getMaterial() != net.minecraft.block.material.Material.AIR) {
							Items.add(/* @ItemStack */(blockstr));
							ItemNumbers.add(1);
							world.setBlockState(new BlockPos((int) (sx + x), (int) (sy + y), (int) (sz + z)), Blocks.AIR.getDefaultState(), 3);
						}
					}
					sx = (double) (sx + 1);
				}
				sy = (double) (sy + 1);
			}
			sz = (double) (sz + 1);
		}
		Counter = (int) 0;
		Test = (String) "Items:\"";
		for (int index4 = 0; index4 < (int) (Items.size()); index4++) {
				if ((Counter > 0)) {
					Test = (String) ((Test) + "" + ("}"));
				}
				Test = (String) ((Test) + "" + ("{") + "" + (Items.get(Counter)) + "" + (", ") + "" + (ItemNumbers.get(Counter)));
				Counter = (int) (Counter + 1);
			}
		Test = (String) ((Test) + "" + ("}\""));
		if (!Test.equals("Items:\"}\""))
		if (world instanceof ServerWorld) {
			((World) world).getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z),
					Vector2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
					"/summon item ~ ~ ~ {Item:{id:\"exodus:blackhole\",Count:1b,tag:{" + Test + "}}}");
		}
		ItemNumbers.clear();
		Items.clear();
	}
}
