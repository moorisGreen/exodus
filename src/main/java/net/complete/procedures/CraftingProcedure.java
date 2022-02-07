package net.complete.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;

import net.complete.item.CompleteSwordItem;
import net.complete.item.CompleteShovelItem;
import net.complete.item.CompleteRodItem;
import net.complete.item.CompletePickaxeItem;
import net.complete.item.CompleteBowItem;
import net.complete.item.CompleteAxeItem;
import net.complete.item.CompleteArmorItem;
import net.complete.CompleteMod;

import java.util.Map;
import java.util.HashMap;

public class CraftingProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
			Entity entity = event.getPlayer();
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			ItemStack itemStack = event.getCrafting();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("itemstack", itemStack);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CompleteMod.LOGGER.warn("Failed to load dependency itemstack for procedure Crafting!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (CompleteArmorItem.helmet == itemstack.getItem() || CompleteArmorItem.body == itemstack.getItem()
				|| CompleteArmorItem.legs == itemstack.getItem() || CompleteArmorItem.boots == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.PROTECTION, (int) 100);
		} else if (CompleteSwordItem.block == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.SHARPNESS, (int) 100);
		} else if (CompletePickaxeItem.block == itemstack.getItem() || CompleteAxeItem.block == itemstack.getItem()
				|| CompleteShovelItem.block == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.FORTUNE, (int) 100);
		} else if (CompleteBowItem.block == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.POWER, (int) 100);
		} else if (CompleteRodItem.block == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.LUCK_OF_THE_SEA, (int) 100);
		}
	}
}
