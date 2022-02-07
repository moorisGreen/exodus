
package net.complete.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.complete.procedures.DropBlackHoleProcedure;
import net.complete.itemgroup.CompleteItemGroup;
import net.complete.CompleteModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@CompleteModElements.ModElement.Tag
public class BlackHoleItem extends CompleteModElements.ModElement {
	@ObjectHolder("complete:black_hole")
	public static final Item block = null;
	public BlackHoleItem(CompleteModElements instance) {
		super(instance, 24);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(CompleteItemGroup.tab).maxStackSize(1).isImmuneToFire().rarity(Rarity.COMMON));
			setRegistryName("black_hole");
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

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			String ToolTip1 = "";
			String ToolTip2 = "";
			String ToolTipText = "";
			String ToolTipNumber = "";
			super.addInformation(itemstack, world, list, flag);
			ToolTip1 = (String) ((itemstack).getOrCreateTag().getString("Items"));
			ToolTip1 = ToolTip1.replace(",", "");
			int lines = ToolTip1.length() - ToolTip1.replace("{", "").length();
			for (int LineCounter = 0; LineCounter < lines; LineCounter++) {
				ToolTip2 = ToolTip1.substring(ToolTip1.indexOf("{") + 1);
				ToolTip2 = ToolTip2.substring(0, ToolTip2.indexOf("}"));
				ToolTip1 = ToolTip1.replace("{" + ToolTip2 + "}", "");
				ToolTipText = ToolTip2.substring(ToolTip2.lastIndexOf(':') + 1);
				ToolTipNumber = ToolTipText.substring(ToolTipText.lastIndexOf(" ") + 1);
				ToolTipText = ToolTipText.substring(0, ToolTipText.lastIndexOf(" ") + 1);
				int Textnum = ToolTipText.length() - ToolTipText.replace("_", "").length();
				for (int TextCounter = 0; TextCounter < Textnum; TextCounter++) {
					int Index = ToolTipText.indexOf("_");
					ToolTipText = ToolTipText.substring(0, Index + 1) + ToolTipText.substring(Index + 1, Index + 2).toUpperCase() + ToolTipText.substring(Index + 2);
					ToolTipText = ToolTipText.replaceFirst("_", " ");
				}
				ToolTipText = ToolTipText.substring(0, 1).toUpperCase() + ToolTipText.substring(1);
				list.add(new StringTextComponent(("\u00A77") + "x" + ToolTipNumber + " " + ToolTipText));
			}
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("itemstack", itemstack);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				$_dependencies.put("entity", entity);
				DropBlackHoleProcedure.executeProcedure($_dependencies);
			}
			return ar;
		}
	}
}
