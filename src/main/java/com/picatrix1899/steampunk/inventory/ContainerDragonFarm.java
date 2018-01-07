package com.picatrix1899.steampunk.inventory;

import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerDragonFarm extends Container
{

	private IInventory inv;
	private int progressTime;
	private int totalProgressTime;
	
    public ContainerDragonFarm(InventoryPlayer playerInventory, IInventory inv)
    {
    	this.inv = inv;
    	
    	this.addSlotToContainer(new SlotRestricted(inv, 0, 28, 12, 64, new ItemStack(Items.END_CRYSTAL)));
    	this.addSlotToContainer(new SlotRestricted(inv, 1, 8, 32, 64, new ItemStack(Items.END_CRYSTAL)));
    	this.addSlotToContainer(new SlotRestricted(inv, 2, 48, 32, 64, new ItemStack(Items.END_CRYSTAL)));
    	this.addSlotToContainer(new SlotRestricted(inv, 3, 28, 52, 64, new ItemStack(Items.END_CRYSTAL)));
    	this.addSlotToContainer(new SlotUnused(inv, 4, 28, 32));

    	
    	this.addSlotToContainer(new Slot(inv, 5, 73, 56));
    	this.addSlotToContainer(new Slot(inv, 6, 73 + 16 + 2, 56));
    	
    	this.addSlotToContainer(new SlotOutput(inv, 7, 116 + 0 * (16 + 2), 14 + 0 * (16 + 2)));
    	this.addSlotToContainer(new SlotOutput(inv, 8, 116 + 1 * (16 + 2), 14 + 0 * (16 + 2)));
    	this.addSlotToContainer(new SlotOutput(inv, 9, 116 + 2 * (16 + 2), 14 + 0 * (16 + 2)));
    	
    	this.addSlotToContainer(new SlotOutput(inv, 10, 116 + 0 * (16 + 2), 14 + 1 * (16 + 2)));
    	this.addSlotToContainer(new SlotOutput(inv, 11, 116 + 1 * (16 + 2), 14 + 1 * (16 + 2)));
    	this.addSlotToContainer(new SlotOutput(inv, 12, 116 + 2 * (16 + 2), 14 + 1 * (16 + 2)));
    	
    	this.addSlotToContainer(new SlotOutput(inv, 13, 116 + 0 * (16 + 2), 14 + 2 * (16 + 2)));
    	this.addSlotToContainer(new SlotOutput(inv, 14, 116 + 1 * (16 + 2), 14 + 2 * (16 + 2)));
    	this.addSlotToContainer(new SlotOutput(inv, 15, 116 + 2 * (16 + 2), 14 + 2 * (16 + 2)));
    	
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.inv.isUseableByPlayer(playerIn);
    }
	
    /**
     * Take a stack from the specified inventory slot.
     */
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.inv.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.inv.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.inv.getSizeInventory(), false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
    
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.inv);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = (IContainerListener)this.listeners.get(i);

            if(this.progressTime != this.inv.getField(0))
            {
                icontainerlistener.sendProgressBarUpdate(this, 0, this.inv.getField(0));
            }
            
            if(this.totalProgressTime != this.inv.getField(1))
            {
                icontainerlistener.sendProgressBarUpdate(this, 1, this.inv.getField(1));
            }
        }

        this.progressTime = this.inv.getField(0);
        this.totalProgressTime = this.inv.getField(1);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.inv.setField(id, data);
    }
}
