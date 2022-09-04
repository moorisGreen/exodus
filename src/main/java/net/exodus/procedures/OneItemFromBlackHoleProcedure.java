package net.exodus.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;

import net.exodus.item.BlackHoleItem;
import net.exodus.ExodusMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

public class OneItemFromBlackHoleProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ExodusMod.LOGGER.warn("Failed to load dependency x for procedure OneItemFromBlackHole!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ExodusMod.LOGGER.warn("Failed to load dependency y for procedure OneItemFromBlackHole!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ExodusMod.LOGGER.warn("Failed to load dependency z for procedure OneItemFromBlackHole!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ExodusMod.LOGGER.warn("Failed to load dependency world for procedure OneItemFromBlackHole!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (!((new Object() {
			public ItemStack getItemStack(BlockPos pos, int sltid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null) {
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
				}
				return _retval.get();
			}
		}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == Blocks.AIR.asItem())) {
			return;
		}
		ItemStack Item = ItemStack.EMPTY;
		String Tag = "";
		String Tag2 = "";
		String Isum = "";
		String IsumT = "";
		String IsumN = "";
		int sumN = 0;
		Item FinalItem;
		if ((BlackHoleItem.block == (Item = new Object() {
			public ItemStack getItemStack(BlockPos pos, int sltid) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				TileEntity _ent = world.getTileEntity(pos);
				if (_ent != null) {
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
				}
				return _retval.get();
			}
		}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem())) {
			if (((new Object() {
				public int getAmount(IWorld world, BlockPos pos, int sltid) {
					AtomicInteger _retval = new AtomicInteger(0);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null) {
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).getCount());
						});
					}
					return _retval.get();
				}
			}.getAmount(world, new BlockPos((int) x, (int) y, (int) z), (int) (1))) == 0)) {
				Tag2 = (String) ((Item).getOrCreateTag().getString("Items"));
				Tag = Tag2.replace(",", "");
				if (Tag.equals("")) {
					TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
					if (_ent != null) {
						final int _sltid = (int) (0);
						final ItemStack _setstack = (new ItemStack(BlackHoleItem.block));
						_setstack.setCount((int) 0);
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable) {
								((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
							}
						});
					}
					return;
				}
				Isum = Tag.substring(Tag.indexOf("{") + 1);
				Isum = Isum.substring(0, Isum.indexOf("}"));
				Tag = Tag.replace("{" + Isum + "}", "");
				IsumT = Isum.substring(0, Isum.lastIndexOf(' '));
				IsumN = Isum.substring(Isum.lastIndexOf(' ') + 1);
				try {
					sumN = Integer.parseInt(IsumN);
				}
				catch (NumberFormatException e)
				{
					sumN = 0;
				}
				TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
				FinalItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(IsumT));
				if (_ent != null) {
					final int _sltid = (int) (1);
					final ItemStack _setstack = (new ItemStack(FinalItem));
					_setstack.setCount((int) sumN);
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
						}
					});
				}
				Tag2 = Tag2.replace("{" + IsumT + ", " + IsumN + "}", "");
				if (!Tag2.replace(",", "").equals("")) {
					(new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							if (_ent != null) {
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid));
								});
							}
							return _retval.get();
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getOrCreateTag().putString("Items", Tag2);
				}
				else {
					if (_ent != null) {
						final int _sltid = (int) (0);
						final ItemStack _setstack = (new ItemStack(BlackHoleItem.block));
						_setstack.setCount((int) 0);
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable) {
								((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
							}
						});
					}

				}
			}
		}
	}
}
