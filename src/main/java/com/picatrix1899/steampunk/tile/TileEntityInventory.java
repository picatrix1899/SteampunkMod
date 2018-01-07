package com.picatrix1899.steampunk.tile;

import javax.annotation.Nullable;

import com.picatrix1899.steampunk.SteampunkMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityInventory extends TileEntity implements ISidedInventory
{

	protected ItemStack[] inventory;
	
	public TileEntityInventory(int size)
	{
		this.inventory = new ItemStack[size];
	}
	
	@Override
	public int getSizeInventory() { return this.inventory.length; }

	@Override
	public ItemStack getStackInSlot(int index)
	{
			return this.inventory[index];

	}

    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    @Nullable
    public ItemStack decrStackSize(int index, int count)
    {
        return ItemStackHelper.getAndSplit(this.inventory, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    @Nullable
    public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.inventory, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, @Nullable ItemStack stack)
    {
        this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

	@Override
	public int getInventoryStackLimit() { return 64; }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

	@Override
	public void openInventory(EntityPlayer player) { }

	@Override
	public void closeInventory(EntityPlayer player) { }

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) { return true; }

	@Override
	public int getField(int id) { return 0; }

	@Override
	public void setField(int id, int value) { }

	@Override
	public int getFieldCount() { return 0; }

	@Override
	public void clear() { for(int i = 0; i < this.inventory.length; i++) this.inventory[i] = null; }

	@Override
	public String getName() { return ""; }

	@Override
	public boolean hasCustomName() { return false; }

	@Override
	public int[] getSlotsForFace(EnumFacing side) { return new int[0]; }

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) { return false; }

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) { return false; }

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		readFromNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		NBTTagList tags = compound.getTagList("Items", 10);
		this.inventory = new ItemStack[this.inventory.length];
		for(int i = 0; i < tags.tagCount(); i++)
		{
			NBTTagCompound data = tags.getCompoundTagAt(i);
			int j = data.getByte("Slot") & 0xFF;
			if(j >= 0 && j < this.inventory.length)
			{
				this.inventory[j] = ItemStack.loadItemStackFromNBT(data);
				SteampunkMod.LOGGER.info(getClass().getSimpleName() + " " + this.inventory[j]);
			}
		}
	}
	
	@Override
	public NBTTagCompound serializeNBT()
	{
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		NBTTagList tags = new NBTTagList();
		for(int i = 0; i < this.inventory.length; i++)
		{	
			if(this.inventory[i] != null)
			{
				NBTTagCompound data = new NBTTagCompound();
				data.setByte("Slot", (byte)i);

				this.inventory[i].writeToNBT(data);
				tags.appendTag(data);
			}
		}
		
		compound.setTag("Items", tags);
		
		return compound;
	}
}
