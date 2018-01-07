package com.picatrix1899.steampunk.tile;

import java.util.logging.Logger;

import com.picatrix1899.steampunk.SteampunkMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileTest extends TileEntityInventory implements ITickable
{

	public TileTest()
	{
		super(9);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		super.setInventorySlotContents(index, stack);
		markDirty();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		SteampunkMod.LOGGER.info("Write");
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		SteampunkMod.LOGGER.info("Read");
		super.readFromNBT(compound);
	}
}
