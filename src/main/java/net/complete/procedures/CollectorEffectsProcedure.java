package net.complete.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.state.Property;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.BooleanProperty;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.block.BlockState;

import net.complete.CompleteMod;

import java.util.Map;

public class CollectorEffectsProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CompleteMod.LOGGER.warn("Failed to load dependency world for procedure CollectorEffects!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CompleteMod.LOGGER.warn("Failed to load dependency x for procedure CollectorEffects!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CompleteMod.LOGGER.warn("Failed to load dependency y for procedure CollectorEffects!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CompleteMod.LOGGER.warn("Failed to load dependency z for procedure CollectorEffects!");
			return;
		}
		if (dependencies.get("blockstate") == null) {
			if (!dependencies.containsKey("blockstate"))
				CompleteMod.LOGGER.warn("Failed to load dependency blockstate for procedure CollectorEffects!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		BlockState blockstate = (BlockState) dependencies.get("blockstate");
		if (!(new Object() {
			public boolean get(BlockState _bs, String property) {
				Property<?> _prop = _bs.getBlock().getStateContainer().getProperty(property);
				return _prop instanceof BooleanProperty ? _bs.get((BooleanProperty) _prop) : false;
			}
		}.get(blockstate, "full"))) {
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("complete:collector")),
						SoundCategory.BLOCKS, (float) 1, (float) 0);
			} else {
				((World) world).playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("complete:collector")),
						SoundCategory.BLOCKS, (float) 1, (float) 0, false);
			}
			if ((new Object() {
				public Direction getDirection(BlockState _bs) {
					Property<?> _prop = _bs.getBlock().getStateContainer().getProperty("facing");
					if (_prop instanceof DirectionProperty)
						return _bs.get((DirectionProperty) _prop);
					_prop = _bs.getBlock().getStateContainer().getProperty("axis");
					return _prop instanceof EnumProperty && _prop.getAllowedValues().toArray()[0] instanceof Direction.Axis
							? Direction.getFacingFromAxisDirection(_bs.get((EnumProperty<Direction.Axis>) _prop), Direction.AxisDirection.POSITIVE)
							: Direction.NORTH;
				}
			}.getDirection(blockstate)) == Direction.NORTH) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.SMOKE, (x + 0.5), (y + 0.2), (z - 0.1), (int) 1, 0.15, 0, 0, 0);
				}
			} else if ((new Object() {
				public Direction getDirection(BlockState _bs) {
					Property<?> _prop = _bs.getBlock().getStateContainer().getProperty("facing");
					if (_prop instanceof DirectionProperty)
						return _bs.get((DirectionProperty) _prop);
					_prop = _bs.getBlock().getStateContainer().getProperty("axis");
					return _prop instanceof EnumProperty && _prop.getAllowedValues().toArray()[0] instanceof Direction.Axis
							? Direction.getFacingFromAxisDirection(_bs.get((EnumProperty<Direction.Axis>) _prop), Direction.AxisDirection.POSITIVE)
							: Direction.NORTH;
				}
			}.getDirection(blockstate)) == Direction.SOUTH) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.SMOKE, (x + 0.5), (y + 0.2), (z + 1.1), (int) 1, 0.15, 0, 0, 0);
				}
			} else if ((new Object() {
				public Direction getDirection(BlockState _bs) {
					Property<?> _prop = _bs.getBlock().getStateContainer().getProperty("facing");
					if (_prop instanceof DirectionProperty)
						return _bs.get((DirectionProperty) _prop);
					_prop = _bs.getBlock().getStateContainer().getProperty("axis");
					return _prop instanceof EnumProperty && _prop.getAllowedValues().toArray()[0] instanceof Direction.Axis
							? Direction.getFacingFromAxisDirection(_bs.get((EnumProperty<Direction.Axis>) _prop), Direction.AxisDirection.POSITIVE)
							: Direction.NORTH;
				}
			}.getDirection(blockstate)) == Direction.WEST) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.SMOKE, (x - 0.1), (y + 0.2), (z + 0.5), (int) 1, 0, 0, 0.15, 0);
				}
			} else if ((new Object() {
				public Direction getDirection(BlockState _bs) {
					Property<?> _prop = _bs.getBlock().getStateContainer().getProperty("facing");
					if (_prop instanceof DirectionProperty)
						return _bs.get((DirectionProperty) _prop);
					_prop = _bs.getBlock().getStateContainer().getProperty("axis");
					return _prop instanceof EnumProperty && _prop.getAllowedValues().toArray()[0] instanceof Direction.Axis
							? Direction.getFacingFromAxisDirection(_bs.get((EnumProperty<Direction.Axis>) _prop), Direction.AxisDirection.POSITIVE)
							: Direction.NORTH;
				}
			}.getDirection(blockstate)) == Direction.EAST) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.SMOKE, (x + 1.1), (y + 0.2), (z + 0.5), (int) 1, 0, 0, 0.15, 0);
				}
			}
		}
	}
}
