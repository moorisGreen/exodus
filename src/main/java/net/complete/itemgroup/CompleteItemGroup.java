
package net.complete.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.complete.item.CompleteIngotItem;
import net.complete.CompleteModElements;

@CompleteModElements.ModElement.Tag
public class CompleteItemGroup extends CompleteModElements.ModElement {
	public CompleteItemGroup(CompleteModElements instance) {
		super(instance, 94);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabcomplete") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CompleteIngotItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
