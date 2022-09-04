package net.exodus.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;

import net.exodus.item.ExodusRodItem;
import net.exodus.item.ExodusPickaxeItem;
import net.exodus.item.ExodusAxeItem;
import net.exodus.item.ExodusShovelItem;
import net.exodus.item.ExodusSwordItem;
import net.exodus.item.ExodusBowItem;
import net.exodus.item.ExodusArmorItem;
import net.exodus.ExodusMod;

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
				ExodusMod.LOGGER.warn("Failed to load dependency itemstack for procedure Crafting!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (ExodusArmorItem.helmet == itemstack.getItem() || ExodusArmorItem.body == itemstack.getItem()
				|| ExodusArmorItem.legs == itemstack.getItem() || ExodusArmorItem.boots == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.PROTECTION, (int) 100);
		} else if (ExodusSwordItem.block == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.SHARPNESS, (int) 100);
		} else if (ExodusPickaxeItem.block == itemstack.getItem() || ExodusAxeItem.block == itemstack.getItem()
				|| ExodusShovelItem.block == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.FORTUNE, (int) 100);
		} else if (ExodusBowItem.block == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.POWER, (int) 100);
		} else if (ExodusRodItem.block == itemstack.getItem()) {
			(itemstack).addEnchantment(Enchantments.LUCK_OF_THE_SEA, (int) 100);
		}
	}
}
