package net.complete.procedures;

import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.IInventory;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.CompoundNBT;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.complete.CompleteMod;
import net.complete.CompleteModVariables;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class OutputProcProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CompleteMod.LOGGER.warn("Failed to load dependency world for procedure OutputProc!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CompleteMod.LOGGER.warn("Failed to load dependency x for procedure OutputProc!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CompleteMod.LOGGER.warn("Failed to load dependency y for procedure OutputProc!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CompleteMod.LOGGER.warn("Failed to load dependency z for procedure OutputProc!");
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
		BlockPos block = new BlockPos((int) x, (int) y, (int) z);
		String global = CompleteModVariables.MapVariables.get(world).items;
		if (global.trim().isEmpty()) {
			return;
		}
		String nbt = global.substring(global.indexOf("{"));
		int Counter = 0;
		int Counterfin = 0;
		boolean Quotes = false;
		char Escape1 = (char) 0;
		char Escape2 = (char) 0;
		char Prev = (char) 0;
		for (int i = 0; i < nbt.length(); i++){
		    char c = nbt.charAt(i);
		    if (i >= 2) {
		    	Escape1 = nbt.charAt(i-1);
		    	Escape2 = nbt.charAt(i-2);
		    }
		    if (Quotes == false) {
		    	if (Character.toString(c).equals("'")) {
			    	if (!Character.toString(Escape1).equals("\\")) {
			    		Quotes = true;
			    		Prev = c;
			    	}
			    }
			    else if (c == '"') {
				    if (!Character.toString(Escape1).equals("\\") && !Character.toString(Escape2).equals("\\")) {
				    	Quotes = true;
				    	Prev = c;
				    }
			    }        
			    if (Character.toString(c).equals("{")) Counter += 1;
			    if (Character.toString(c).equals("}")) Counter -= 1;
			    if (Counter <= 0) {
			    	Counterfin = i + 1;
			    	break;
			    }
		    }
		    else if (Quotes == true) {
		    	if (Character.toString(c).equals("'")) {
		    		if (Character.toString(c).equals(Character.toString(Prev))) {
		    			if (!Character.toString(Escape1).equals("\\")) {
		    				Quotes = false;
		    			}
		    		}
		    	}
		    	else if (c == '"') {
		    		if (Character.toString(c).equals(Character.toString(Prev))) {
			    		if (!Character.toString(Escape1).equals("\\") && !Character.toString(Escape2).equals("\\")) {
			    			Quotes = false;
			    		}
			    	}
		   		}
		    }
		}
		nbt = nbt.substring(0, Counterfin);

		String id = global.substring(0, global.indexOf("("));
		ItemStack curstack = new Object() {
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
		}.getItemStack(block, (int) (slot));
		
		Item cur = curstack.getItem();
		int max = new ItemStack(Item.getItemById(Integer.parseInt(id))).getMaxStackSize();
		if (cur == Blocks.AIR.asItem()) {
			/*world.getTileEntity(block).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					System.out.println(((IItemHandlerModifiable) capability).isItemValidForSlot(new ItemStack(Item.getItemById(Integer.parseInt(id))), slot));
				}
			});*/
			if (((IInventory) world.getTileEntity(block)).isItemValidForSlot(slot, new ItemStack(Item.getItemById(Integer.parseInt(id))))) ChangeItem(max, 0, global, id, block, slot, world, max, nbt);
			return;
		}
		String nbt2 = "{}";
		if (curstack.getTag() != null) nbt2 = (String) curstack.getTag().toString();
		if (Item.getIdFromItem(cur) == Integer.parseInt(id) && nbt.equals(nbt2)) {
			int curnum = new Object() {
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
			}.getAmount(world, block, (int) (slot));
			if (curnum >= max) {
				return;
			}
			if (((IInventory) world.getTileEntity(block)).isItemValidForSlot(slot, new ItemStack(Item.getItemById(Integer.parseInt(id))))) ChangeItem((max - curnum), curnum, global, id, block, slot, world, max, nbt);
		}
	}
	
	private static void ChangeItem(int free, int add, String global, String id, BlockPos pos, int slot, IWorld world, int max, String nbt) {
		String number = global.substring(global.indexOf("(") + 1, global.indexOf(")"));
		if (Integer.parseInt(number) <= free) {
			TileEntity _ent = world.getTileEntity(pos);
			if (_ent != null) {
				final int _sltid = (int) (slot);
				final ItemStack _setstack = new ItemStack(Item.getItemById(Integer.parseInt(id)));
				_setstack.setCount((int) (Integer.parseInt(number) + add));
				try {
					_setstack.setTag(JsonToNBT.getTagFromJson(nbt));
				} catch (CommandSyntaxException e) {
					System.out.println(e);
				}
				_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable) {
						((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
					}
				});
			}
			CompleteModVariables.MapVariables.get(world).items = global.replace(id + "(" + number + ")" + nbt, "");
		}
		else {
			TileEntity _ent = world.getTileEntity(pos);
			if (_ent != null) {
				final int _sltid = (int) (slot);
				final ItemStack _setstack = new ItemStack(Item.getItemById(Integer.parseInt(id)));
				_setstack.setCount((int) max);
				try {
					_setstack.setTag(JsonToNBT.getTagFromJson(nbt));
				} catch (CommandSyntaxException e) {
					System.out.println(e);
				}
				_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable) {
						((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
					}
				});
			}
			String newnumber = Integer.toString(Integer.parseInt(number) - free);
			CompleteModVariables.MapVariables.get(world).items = global.replace(id + "(" + number + ")" + nbt, id + "(" + newnumber + ")" + nbt);
		}
	}
}
