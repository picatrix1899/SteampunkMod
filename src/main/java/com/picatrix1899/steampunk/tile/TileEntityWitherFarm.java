package com.picatrix1899.steampunk.tile;

import javax.annotation.Nullable;

import com.picatrix1899.steampunk.Utils;
import com.picatrix1899.steampunk.inventory.ContainerWitherFarm;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityWitherFarm extends TileEntityInventory implements ITickable
{

	private int totalProgressTime;
	private int passedProgressTime;
	
	public TileEntityWitherFarm()
	{
		super(10);
	}

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canProgress()
    {
    	for(int i = 0; i < 3; i++)
    	{
    		if(this.inventory[i] == null)
    		{
    			return false;
    		}
    	}
    	
    	for(int i = 3; i < 7; i++)
    	{
    		if(this.inventory[i] == null)
    		{
    			return false;
    		}
    	}
    	
    	if(this.inventory[7] == null) return false;
    	if(this.inventory[8] == null) return false;
    	
    	return true;
    }
	
    public boolean isProcessing()
    {
    	return passedProgressTime > 0;
    }
    
    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        boolean markAsDirty = false;
        boolean isProcessing = isProcessing();
        
        if (!this.worldObj.isRemote)
        {
            if (this.canProgress())
            {
            	if(!isProcessing())
            	{
            		this.totalProgressTime = getProcessTime();
            	}
            	
                ++this.passedProgressTime;
                
                if (this.passedProgressTime == this.totalProgressTime)
                {
                    this.passedProgressTime = 0;
                    this.processFinished();
                    markAsDirty = true;
                }
            }
            else
            {
                this.passedProgressTime = 0;
            }

            if (isProcessing != isProcessing())
            {
            	markAsDirty = true;
            }
        }

        if (markAsDirty)
        {
            this.markDirty();
        }
    }

    public void processFinished()
    {
            ItemStack itemstack = new ItemStack(Items.NETHER_STAR);

            if (this.inventory[9] == null)
            {
                this.inventory[9] = itemstack.copy();
            }
            else
            {
                this.inventory[9].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            for(int i = 0; i < 7; i++)
            {
                this.inventory[i].stackSize--;

                if (this.inventory[i].stackSize <= 0)
                {
                    this.inventory[i] = null;
                }
            }
    }
    
    public int getProcessTime()
    {
        return 200;
    }
	
    @Override
    public int getField(int id)
    {
    	switch(id)
    	{
    		case 0: return this.passedProgressTime;
    		case 1: return this.totalProgressTime;
    	}
    	
    	return 0;
    }
    
    @Override
    public int getFieldCount()
    {
    	return 2;
    }
    
    @Override
    public void setField(int id, int value)
    {
    	switch(id)
    	{
    	case 0: this.passedProgressTime = value; break;
    	case 1: this.totalProgressTime = value; break;
    	}
    }
}
