package net.complete.procedures;

import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;

import net.complete.item.CompleteSwordItem;
import net.complete.item.CompleteBowItem;
import net.complete.CompleteMod;

import java.util.Map;

public class SwordChangeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CompleteMod.LOGGER.warn("Failed to load dependency entity for procedure SwordChange!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CompleteMod.LOGGER.warn("Failed to load dependency itemstack for procedure SwordChange!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (itemstack.getItem() == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()) {
			if (itemstack.getItem() == CompleteSwordItem.block) {
				if (entity instanceof LivingEntity) {
					ItemStack _setstack = new ItemStack(CompleteBowItem.block);
					_setstack.setCount((int) 1);
					((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
					if (entity instanceof ServerPlayerEntity)
						((ServerPlayerEntity) entity).inventory.markDirty();
				}
				(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
						.addEnchantment(Enchantments.POWER, (int) 100);
			} else if (itemstack.getItem() == CompleteBowItem.block) {
				if (entity.isSneaking()) {
					if (entity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(CompleteSwordItem.block);
						_setstack.setCount((int) 1);
						((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
						if (entity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) entity).inventory.markDirty();
					}
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
							.addEnchantment(Enchantments.SHARPNESS, (int) 100);
				}
			}
		}
	}
}
