
package net.complete.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.FishingRodItem;

import net.complete.itemgroup.CompleteItemGroup;
import net.complete.CompleteModElements;

@CompleteModElements.ModElement.Tag
public class CompleteRodItem extends CompleteModElements.ModElement {
	@ObjectHolder("complete:complete_rod")
	public static final Item block = null;

	public CompleteRodItem(CompleteModElements instance) {
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
		}.setRegistryName("complete_rod"));
	}

	private static class ItemToolCustom extends FishingRodItem {
		protected ItemToolCustom() {
			super(new Item.Properties().group(CompleteItemGroup.tab).maxDamage(0).isImmuneToFire());
		}

		@Override
		public int getItemEnchantability() {
			return 128000;
		}
	}
}
