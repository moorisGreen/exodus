
package net.exodus.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.FishingRodItem;

import net.exodus.itemgroup.ExodusItemGroup;
import net.exodus.ExodusModElements;

@ExodusModElements.ModElement.Tag
public class ExodusRodItem extends ExodusModElements.ModElement {
	@ObjectHolder("exodus:exodus_rod")
	public static final Item block = null;

	public ExodusRodItem(ExodusModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemToolCustom() {
			@Override
			@OnlyIn(Dist.CLIENT)
			public boolean hasEffect(ItemStack itemstack) {
				return true;
			}
		}.setRegistryName("exodus_rod"));
	}

	private static class ItemToolCustom extends FishingRodItem {
		protected ItemToolCustom() {
			super(new Item.Properties().group(ExodusItemGroup.tab).maxDamage(0).isImmuneToFire());
		}

		@Override
		public int getItemEnchantability() {
			return 128000;
		}
	}
}
