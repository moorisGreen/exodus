package net.complete.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.complete.item.CompleteArmorItem;
import net.complete.CompleteMod;

import java.util.Map;

public class CompleteSwordLivingEntityIsHitProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CompleteMod.LOGGER.warn("Failed to load dependency entity for procedure CompleteSwordLivingEntityIsHit!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (!(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET) : ItemStack.EMPTY)
				.getItem() == CompleteArmorItem.boots
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.LEGS) : ItemStack.EMPTY)
						.getItem() == CompleteArmorItem.legs
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.CHEST) : ItemStack.EMPTY)
						.getItem() == CompleteArmorItem.body
				&& ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.HEAD) : ItemStack.EMPTY)
						.getItem() == CompleteArmorItem.helmet)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).setHealth((float) 1);
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).attackEntityFrom(new DamageSource("none").setDamageBypassesArmor(), (float) 10000);
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
