package net.complete.procedures;

import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;

import net.complete.item.CompleteShearsItem;
import net.complete.item.CompleteHoeItem;
import net.complete.CompleteModVariables;
import net.complete.CompleteMod;

import java.util.Map;

public class ToolChangeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CompleteMod.LOGGER.warn("Failed to load dependency entity for procedure ToolChange!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CompleteMod.LOGGER.warn("Failed to load dependency itemstack for procedure ToolChange!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (itemstack.getItem() == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()) {
			if (itemstack.getItem() == CompleteShearsItem.block) {
				if (entity instanceof LivingEntity) {
					ItemStack _setstack = new ItemStack(CompleteHoeItem.block);
					_setstack.setCount((int) 1);
					((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
					if (entity instanceof ServerPlayerEntity)
						((ServerPlayerEntity) entity).inventory.markDirty();
				}
				(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
						.addEnchantment(Enchantments.SILK_TOUCH, (int) 100);
			} else if (itemstack.getItem() == CompleteHoeItem.block) {
				if ((entity.getCapability(CompleteModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new CompleteModVariables.PlayerVariables())).hoe_used == false) {
					if (entity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(CompleteShearsItem.block);
						_setstack.setCount((int) 1);
						((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
						if (entity instanceof ServerPlayerEntity)
							((ServerPlayerEntity) entity).inventory.markDirty();
					}
					(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
							.addEnchantment(Enchantments.SILK_TOUCH, (int) 100);
				} else {
					{
						boolean _setval = (false);
						entity.getCapability(CompleteModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hoe_used = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
	}
}
