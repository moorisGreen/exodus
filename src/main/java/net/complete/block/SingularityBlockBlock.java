
package net.complete.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.complete.itemgroup.CompleteItemGroup;
import net.complete.CompleteModElements;

import java.util.List;
import java.util.Collections;

@CompleteModElements.ModElement.Tag
public class SingularityBlockBlock extends CompleteModElements.ModElement {
	@ObjectHolder("complete:singularity_block")
	public static final Block block = null;

	public SingularityBlockBlock(CompleteModElements instance) {
		super(instance, 111);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(CompleteItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.ANCIENT_DEBRIS).hardnessAndResistance(-1, 3600000).setLightLevel(s -> 0)
					.harvestLevel(100).harvestTool(ToolType.PICKAXE).setRequiresTool().slipperiness(0f).speedFactor(0f).jumpFactor(0f));
			setRegistryName("singularity_block");
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 150;
		}

	}
}
