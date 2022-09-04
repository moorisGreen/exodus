package net.exodus.procedures;

import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;

import net.exodus.ExodusModVariables;
import net.exodus.ExodusMod;
import net.exodus.item.ExodusShearsItem;
import net.exodus.item.ExodusHoeItem;

import java.util.Map;

public class ToolChangeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ExodusMod.LOGGER.warn("Failed to load dependency entity for procedure ToolChange!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				ExodusMod.LOGGER.warn("Failed to load dependency itemstack for procedure ToolChange!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (itemstack.getItem() == ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()) {
			if (itemstack.getItem() == ExodusShearsItem.block) {
				if (entity instanceof LivingEntity) {
					ItemStack _setstack = new ItemStack(ExodusHoeItem.block);
					_setstack.setCount((int) 1);
					((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
					if (entity instanceof ServerPlayerEntity)
						((ServerPlayerEntity) entity).inventory.markDirty();
				}
				(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))
						.addEnchantment(Enchantments.SILK_TOUCH, (int) 100);
			} else if (itemstack.getItem() == ExodusHoeItem.block) {
				if ((entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ExodusModVariables.PlayerVariables())).hoe_used == false) {
					if (entity instanceof LivingEntity) {
						ItemStack _setstack = new ItemStack(ExodusShearsItem.block);
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
						entity.getCapability(ExodusModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.hoe_used = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
	}
}
