package net.exodus.procedures;

import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.IInventory;
import net.minecraft.block.Blocks;

import net.exodus.ExodusMod;
import net.exodus.ExodusModVariables;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;


public class InputProcProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ExodusMod.LOGGER.warn("Failed to load dependency world for procedure InputProc!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ExodusMod.LOGGER.warn("Failed to load dependency x for procedure InputProc!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ExodusMod.LOGGER.warn("Failed to load dependency y for procedure InputProc!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ExodusMod.LOGGER.warn("Failed to load dependency z for procedure InputProc!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		double slots = 0;
		TileEntity inv = world.getTileEntity(new BlockPos(x, y, z));
		String slotStr = inv.getTileData().getString("slots");
		List<String> slot = new LinkedList<String>(Arrays.asList("-1"));
		int slotint = 0;
		try {
			slot = new LinkedList<String>(Arrays.asList(slotStr.split(",", -1)));
		}
		catch (NullPointerException n) {
			slot = new LinkedList<String>(Arrays.asList("-1"));
		}
		for (String curslot : slot) {
			try {
				slotint = Integer.parseInt(curslot);
			}
			catch (NumberFormatException n) {
				slot.remove(curslot);
			}
		}
		for (int index0 = -1; index0 < (int) (2); index0++) {
			inv = world.getTileEntity(new BlockPos(x + index0, y, z));
			if (inv instanceof IInventory) {
					slots = (double) ((IInventory) inv).getSizeInventory();
				} else {
					slots = -1;
				};
			if (slot.contains("-1")) {
				for (int index1 = 0; index1 < (int) (slots); index1++) {
					Tile(index1, x + index0, y, z, world);
				}
			}
			else {
				for (String curslot : slot) {
					slotint = Integer.parseInt(curslot);
					if (slotint < slots) Tile(slotint, x + index0, y, z, world);
				}
			}
			index0++;
		}
		for (int index2 = -1; index2 < (int) (2); index2++) {
			inv = world.getTileEntity(new BlockPos(x, y + index2, z));
			if (inv instanceof IInventory) {
					slots = (double) ((IInventory) inv).getSizeInventory();
				} else {
					slots = -1;
				};
			if (slot.contains("-1")) {
				for (int index3 = 0; index3 < (int) (slots); index3++) {
					Tile(index3, x, y + index2, z, world);
				}
			}
			else {
				for (String curslot : slot) {
					slotint = Integer.parseInt(curslot);
					if (slotint < slots) Tile(slotint, x, y + index2, z, world);
				}
			}
			index2++;
		}
		for (int index4 = -1; index4 < (int) (2); index4++) {
			inv = world.getTileEntity(new BlockPos(x, y, z + index4));
			if (inv instanceof IInventory) {
					slots = (double) ((IInventory) inv).getSizeInventory();
				} else {
					slots = -1;
				};
			if (slot.contains("-1")) {
				for (int index5 = 0; index5 < (int) (slots); index5++) {
					Tile(index5, x, y, z + index4, world);
				}
			}
			else {
				for (String curslot : slot) {
					slotint = Integer.parseInt(curslot);
					if (slotint < slots) Tile(slotint, x, y, z + index4, world);
				}
			}
			index4++;
		}

		String settext = "-1";
		for (String curslot : slot) {
			if (settext.equals("-1")) {
				settext = curslot;
			}
			else {
				settext = settext + "," + curslot;
			}
			if (curslot.equals("-1"));
		}
		world.getTileEntity(new BlockPos(x, y, z)).getTileData().putString("slots", settext);
	}
	
	private static void Tile(int slot, double x, double y, double z, IWorld world) {
		int id = 0;
		int newnum = 0;
		String amount = "";
		String numstr = "";
		String id2 = "";
		ItemStack item = (new Object() {
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
		}.getItemStack(new BlockPos((int) (x), (int) y, (int) z), (int) (slot)));
		id = Item.getIdFromItem(item.getItem());
		String nbt = null;
		if (item.getTag() != null) nbt = item.getTag().toString();
		else nbt = "{}";
		String global = ExodusModVariables.MapVariables.get(world).items;
		if (id != 0) {
			numstr = ("(" + (new Object() {
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
			}.getAmount(world, new BlockPos((int) (x), (int) y, (int) z), (int) (slot))) + ")");
			
			if (global.contains(id + "(")) {
				amount = global.substring(global.indexOf(id + "("));
				amount = amount.substring(0, amount.indexOf(")"));
				id2 = amount.substring(0, amount.indexOf("(") + 1);
				amount = amount.replace(id2, "");
				amount = amount.replace(")", "");
				newnum = (Integer.parseInt(amount) + Integer.parseInt(numstr.replaceAll("[^0-9]", "")));
				
				if (global.contains(id + "(" + amount + ")" + nbt)) {
					ExodusModVariables.MapVariables.get(world).items = global.replace(id2 + amount + ")" + nbt, id + "(" + newnum + ")" + nbt);
				}
				else {
					ExodusModVariables.MapVariables.get(world).items = (global + id + numstr + nbt);
				}
			}
			else {
				ExodusModVariables.MapVariables.get(world).items = (global + id + numstr + nbt);
			}			
			TileEntity _ent = world.getTileEntity(new BlockPos((int) (x), (int) y, (int) z));
			if (_ent != null) {
				final int _sltid = (int) (slot);
				final ItemStack _setstack = new ItemStack(Blocks.AIR);
				_setstack.setCount((int) 1);
				_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable) {
						((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
					}
				});
			}
		}
	}
}
