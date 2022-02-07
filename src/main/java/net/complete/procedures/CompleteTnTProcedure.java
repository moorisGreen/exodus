package net.complete.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.complete.item.CompleteArmorItem;
import net.complete.CompleteMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

public class CompleteTnTProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CompleteMod.LOGGER.warn("Failed to load dependency world for procedure CompleteTnT!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CompleteMod.LOGGER.warn("Failed to load dependency x for procedure CompleteTnT!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CompleteMod.LOGGER.warn("Failed to load dependency y for procedure CompleteTnT!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CompleteMod.LOGGER.warn("Failed to load dependency z for procedure CompleteTnT!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CompleteMod.LOGGER.warn("Failed to load dependency entity for procedure CompleteTnT!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.getPersistentData().getDouble("Size") <= 200) {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vector3d(x, y, z), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
						("kill @e[distance=.." + entity.getPersistentData().getDouble("Size") + "]"));
			}
			{
				List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(
						x - ((entity.getPersistentData().getDouble("Size") / 2) / 2d), y - ((entity.getPersistentData().getDouble("Size") / 2) / 2d),
						z - ((entity.getPersistentData().getDouble("Size") / 2) / 2d), x + ((entity.getPersistentData().getDouble("Size") / 2) / 2d),
						y + ((entity.getPersistentData().getDouble("Size") / 2) / 2d), z + ((entity.getPersistentData().getDouble("Size") / 2) / 2d)),
						null).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if (((entityiterator instanceof LivingEntity)
							? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType.FEET)
							: ItemStack.EMPTY).getItem() == CompleteArmorItem.boots
							&& ((entityiterator instanceof LivingEntity)
									? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType.LEGS)
									: ItemStack.EMPTY).getItem() == CompleteArmorItem.legs
							&& ((entityiterator instanceof LivingEntity)
									? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType.CHEST)
									: ItemStack.EMPTY).getItem() == CompleteArmorItem.body
							&& ((entityiterator instanceof LivingEntity)
									? ((LivingEntity) entityiterator).getItemStackFromSlot(EquipmentSlotType.HEAD)
									: ItemStack.EMPTY).getItem() == CompleteArmorItem.helmet) {
						if (1 < ((entityiterator instanceof LivingEntity) ? ((LivingEntity) entityiterator).getHealth() : -1)) {
							entity.attackEntityFrom(DamageSource.GENERIC, (float) 1);
							if (entityiterator instanceof LivingEntity)
								((LivingEntity) entityiterator).setHealth(
										(float) (((entityiterator instanceof LivingEntity) ? ((LivingEntity) entityiterator).getHealth() : -1) - 1));
						} else {
							entity.attackEntityFrom(DamageSource.GENERIC, (float) 1);
							if (entityiterator instanceof LivingEntity)
								((LivingEntity) entityiterator).setHealth(
										(float) ((entityiterator instanceof LivingEntity) ? ((LivingEntity) entityiterator).getMaxHealth() : -1));
						}
					}
				}
			}
		}
	}
}
