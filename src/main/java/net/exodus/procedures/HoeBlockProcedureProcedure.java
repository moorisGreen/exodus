package net.exodus.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.state.Property;
import net.minecraft.state.IntegerProperty;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BoneMealItem;
import net.minecraft.entity.Entity;
import net.minecraft.block.IGrowable;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.exodus.ExodusModVariables;
import net.exodus.ExodusMod;

import java.util.Map;

public class HoeBlockProcedureProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ExodusMod.LOGGER.warn("Failed to load dependency world for procedure HoeBlockProcedure!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ExodusMod.LOGGER.warn("Failed to load dependency x for procedure HoeBlockProcedure!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ExodusMod.LOGGER.warn("Failed to load dependency y for procedure HoeBlockProcedure!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ExodusMod.LOGGER.warn("Failed to load dependency z for procedure HoeBlockProcedure!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ExodusMod.LOGGER.warn("Failed to load dependency entity for procedure HoeBlockProcedure!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double sx = 0;
		double sz = 0;
		{
			boolean _setval = (true);
			entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.hoe_used = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		sx = (-3);
		for (int index0 = 0; index0 < (int) (6); index0++) {
			sz = (-3);
			for (int index1 = 0; index1 < (int) (6); index1++) {
				if ((world.getBlockState(new BlockPos((int) (x + sx), (int) y, (int) (z + sz))))
						.getMaterial() == net.minecraft.block.material.Material.EARTH
						|| (world.getBlockState(new BlockPos((int) (x + sx), (int) y, (int) (z + sz))))
								.getMaterial() == net.minecraft.block.material.Material.ORGANIC) {
					if ((world.getBlockState(new BlockPos((int) (x + sx), (int) (y + 1), (int) (z + sz))))
							.getMaterial() == net.minecraft.block.material.Material.AIR) {
						{
							BlockPos _bp = new BlockPos((int) (x + sx), (int) y, (int) (z + sz));
							BlockState _bs = Blocks.FARMLAND.getDefaultState();
							BlockState _bso = world.getBlockState(_bp);
							for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
								Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
								if (_property != null && _bs.get(_property) != null)
									try {
										_bs = _bs.with(_property, (Comparable) entry.getValue());
									} catch (Exception e) {
									}
							}
							world.setBlockState(_bp, _bs, 3);
						}
						{
							int _value = 7;
							BlockPos _pos = new BlockPos((int) (x + sx), (int) y, (int) (z + sz));
							BlockState _bs = world.getBlockState(_pos);
							Property<?> _property = _bs.getBlock().getStateContainer().getProperty("moisture");
							if (_property instanceof IntegerProperty && _property.getAllowedValues().contains(_value))
								world.setBlockState(_pos, _bs.with((IntegerProperty) _property, _value), 3);
						}
					}
				}
				if ((world.getBlockState(new BlockPos((int) (x + sx), (int) y, (int) (z + sz)))).getBlock() instanceof IGrowable) {
					if (world instanceof World) {
						if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), (World) world,
								new BlockPos((int) (x + sx), (int) y, (int) (z + sz)))
								|| BoneMealItem.growSeagrass(new ItemStack(Items.BONE_MEAL), (World) world,
										new BlockPos((int) (x + sx), (int) y, (int) (z + sz)), (Direction) null)) {
							if (!world.isRemote())
								((World) world).playEvent(2005, new BlockPos((int) (x + sx), (int) y, (int) (z + sz)), 0);
						}
					}
				}
				sz = (sz + 1);
			}
			sx = (sx + 1);
		}
	}
}
