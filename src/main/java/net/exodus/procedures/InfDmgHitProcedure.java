package net.exodus.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.exodus.ExodusMod;
import net.exodus.item.ExodusArmorItem;

import java.util.Map;

public class InfDmgHitProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ExodusMod.LOGGER.warn("Failed to load dependency entity for procedure InfDmgHit!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (!(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET) : ItemStack.EMPTY)
				.getItem() == ExodusArmorItem.boots
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS) : ItemStack.EMPTY)
						.getItem() == ExodusArmorItem.legs
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST) : ItemStack.EMPTY)
						.getItem() == ExodusArmorItem.body
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD) : ItemStack.EMPTY)
						.getItem() == ExodusArmorItem.helmet)) {
			if (entity instanceof LivingEntity)
			{
				((LivingEntity) entity).setHealth((float) 1);
				for (int i = 0; i < 10; i++)
				{
					entity.attackEntityFrom(new DamageSource("none").setDamageBypassesArmor(), Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.ANVIL, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.CACTUS, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.CRAMMING, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.DRAGON_BREATH, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.DROWN, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.DROWN, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.DRYOUT, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.FALL, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.FALLING_BLOCK, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.FLY_INTO_WALL, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.GENERIC, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.HOT_FLOOR, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.IN_FIRE, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.IN_WALL, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.LAVA, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.MAGIC, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.ON_FIRE, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.STARVE, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.SWEET_BERRY_BUSH, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.WITHER, Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(DamageSource.func_233546_a_(), Float.POSITIVE_INFINITY);
					entity.attackEntityFrom(new DamageSource("none").setDamageBypassesArmor(), Float.POSITIVE_INFINITY);
				}
			}
		} else if (1 < ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).setHealth((float) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) - 1));
		} else {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).setHealth((float) ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getMaxHealth() : -1));
		}
	}
}
