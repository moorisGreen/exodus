
package net.exodus.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.exodus.itemgroup.ExodusItemGroup;
import net.exodus.ExodusModElements;

@ExodusModElements.ModElement.Tag
public class SingularitypileItem extends ExodusModElements.ModElement {
	@ObjectHolder("exodus:singularitypile")
	public static final Item block = null;

	public SingularitypileItem(ExodusModElements instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ExodusItemGroup.tab).maxStackSize(9).isImmuneToFire().rarity(Rarity.COMMON));
			setRegistryName("singularitypile");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
