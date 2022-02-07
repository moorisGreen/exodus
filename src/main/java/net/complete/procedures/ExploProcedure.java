package net.complete.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.Items;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.complete.CompleteModVariables;
import net.complete.CompleteMod;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NumberNBT;
import net.minecraft.block.FourWayBlock;
import net.minecraft.block.SixWayBlock;

public class ExploProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CompleteMod.LOGGER.warn("Failed to load dependency entity for procedure Explo!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CompleteMod.LOGGER.warn("Failed to load dependency x for procedure Explo!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CompleteMod.LOGGER.warn("Failed to load dependency y for procedure Explo!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CompleteMod.LOGGER.warn("Failed to load dependency z for procedure Explo!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CompleteMod.LOGGER.warn("Failed to load dependency world for procedure Explo!");
			return;
		}
		if (dependencies.get("SizeX") == null) {
			if (!dependencies.containsKey("SizeX"))
				CompleteMod.LOGGER.warn("Failed to load dependency SizeX for procedure Explo!");
			return;
		}
		if (dependencies.get("SizeZ") == null) {
			if (!dependencies.containsKey("SizeZ"))
				CompleteMod.LOGGER.warn("Failed to load dependency SizeZ for procedure Explo!");
			return;
		}
		if (dependencies.get("Size") == null) {
			if (!dependencies.containsKey("Size"))
				CompleteMod.LOGGER.warn("Failed to load dependency Size for procedure Explo!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double SizeX = (double) dependencies.get("SizeX");
		double SizeY = (double) dependencies.get("SizeY");
		double SizeZ = (double) dependencies.get("SizeZ");
		double Rate = (double) dependencies.get("Rate");
		double Size = entity.getPersistentData().getDouble("Size");
		String Itemnbt = entity.getPersistentData().getString("Items");
		String Itemtemp = "";
		double x2 = 0;
		double y2 = 0;
		double z2 = 0;
		List Items = new ArrayList();
		ArrayList<Integer> ItemNumbers = new ArrayList<>();
		BlockState block = Blocks.AIR.getDefaultState();
		String blockstr = "";
		String ID = "";
		int Itemnum = 0;
		int Index = 0;
		BlockPos position = new BlockPos(0,0,0);
		List PosList = new ArrayList<BlockPos>();
		for(int i = 0; i < Rate; ++i) {
			if (SizeX < Size) {
				SizeX++;
			}
			else if (SizeY < Size) {
				SizeY++;
				SizeX = -Size;
			}
			else if (SizeZ < Size) {
				SizeZ++;
				SizeX = -Size;
				SizeY = -Size;
			}
			position = new BlockPos((x + SizeX), (y + SizeY), (z + SizeZ));
			if (world.getBlockState(position).getMaterial() != net.minecraft.block.material.Material.AIR) {
				if (SizeX*SizeX + SizeY*SizeY + SizeZ*SizeZ <= Size*Size) {
					if (!PosList.contains(position)) {
						PosList.add(position);
					}
				}
			}
		}
		entity.getPersistentData().putDouble("x2", SizeX);
		entity.getPersistentData().putDouble("y2", SizeY);
		entity.getPersistentData().putDouble("z2", SizeZ);


		for (BlockPos i : (List<BlockPos>) PosList) {
			block = (world.getBlockState(i));
			blockstr = (String) ("" + (block));
			blockstr = blockstr.replace("Block", "");
			blockstr = blockstr.substring(blockstr.indexOf("{") + 1);
			blockstr = blockstr.substring(0, blockstr.indexOf("}"));
			if ((!(block.getBlock() instanceof FlowingFluidBlock)) && (!(block.getFluidState().isSource())) && block.getMaterial() != net.minecraft.block.material.Material.AIR) {
				if (Items.contains(blockstr)) {
					ItemNumbers.set(Items.lastIndexOf(blockstr), (ItemNumbers.get(Items.lastIndexOf(blockstr)) + 1));
				} else {
						Items.add(blockstr);
						ItemNumbers.add(1);
				}
			}
			world.setBlockState(i, Blocks.AIR.getDefaultState(), 3);
		}
		
		for (int index = 0; index < (int) (Items.size()); index++) {
			if ((index > 0)) {
				ID = (String) (ID + ",");
			}
			ID = (String) (ID + Items.get(index) + "" + ";" + "" + ItemNumbers.get(index));
		}
		if (!ID.equals("")) {
			if (Itemnbt.equals("")) {
				Itemnbt = ID;
				entity.getPersistentData().putString("Items", ID);
			}
			else {
				Itemnum = ID.length() - ID.replace(",", "").length();
				for (int ItemCounter = 0; ItemCounter < Itemnum; ItemCounter++) {
					Index = ID.indexOf(",");
					Itemtemp = ID.substring(0, Index);
					Itemnbt = toNbt(Itemtemp, Itemnbt);
					entity.getPersistentData().putString("Items", Itemnbt);
				}
				Index = ID.lastIndexOf(",");
				Itemtemp = ID.substring(Index + 1, ID.length());
				Itemnbt = toNbt(Itemtemp, Itemnbt);
				entity.getPersistentData().putString("Items", Itemnbt);
			}
		}
	}

	private static String toNbt(String Item, String nbt) {
		int Itemnum = nbt.length() - nbt.replace(",", "").length() + 1;
		int Index = 0;
		String Itemtemp = "";
		int Numbernbt = 0;
		int Number = 0;
		try {
  			Number = Integer.parseInt(Item.substring(Item.indexOf(";") + 1, Item.length()));
		}
		catch (NumberFormatException e)
		{
   			Number = 0;
		}
		Item = Item.substring(0, Item.indexOf(";"));
		boolean found = false;
		int NewNumber = 0;
		String Newnbt = "";
		for (int ItemCounter = 0; ItemCounter < Itemnum; ItemCounter++) {
			Index = nbt.indexOf(",");
			if (Index == -1) {
				Index = nbt.length();
			}
			if (nbt.lastIndexOf("#") == -1) {
				Itemtemp = nbt.substring(0, Index);
			}
			else {
				Itemtemp = nbt.substring(nbt.lastIndexOf("#") + 1, Index);
			}
			nbt = nbt.replaceFirst(",", "#");
			Index = Itemtemp.indexOf(";");
			try {
  				Numbernbt = Integer.parseInt(Itemtemp.substring(Index + 1, Itemtemp.length()));
			}
			catch (NumberFormatException e)
			{
   				Numbernbt = 0;
			}
			Itemtemp = Itemtemp.substring(0, Index);
			if (Itemtemp.equals(Item)) {
				NewNumber = Numbernbt + Number;
				Newnbt = nbt.replaceAll(Itemtemp + ";" + Numbernbt, Itemtemp + ";" + NewNumber);
				Newnbt = Newnbt.replaceAll("#", ",");
				found = true;
			}
		}
		if (found == false) {
			Newnbt = nbt + "," + Item + ";" + Number;
			Newnbt = Newnbt.replaceAll("#", ",");
		}
		return Newnbt;
	}
}
