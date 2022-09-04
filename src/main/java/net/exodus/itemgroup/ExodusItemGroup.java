
package net.exodus.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.exodus.item.ExodusIngotItem;
import net.exodus.ExodusModElements;

@ExodusModElements.ModElement.Tag
public class ExodusItemGroup extends ExodusModElements.ModElement {
	public ExodusItemGroup(ExodusModElements instance) {
		super(instance, 96);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabexodus") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(ExodusIngotItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
