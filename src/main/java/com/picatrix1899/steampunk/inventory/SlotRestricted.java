package com.picatrix1899.steampunk.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRestricted extends Slot
{

	private ItemStack[] items;
	private int maxStack;
	
	public SlotRestricted(IInventory inventoryIn, int index, int xPosition, int yPosition, int maxStack, ItemStack... items)
	{
		super(inventoryIn, index, xPosition, yPosition);
		
		this.maxStack = maxStack;
		this.items = items;
	}

	@Override
	public int getSlotStackLimit()
	{
		return this.maxStack;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return this.maxStack;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		if(items.length == 0) return super.isItemValid(stack);
		
		for(ItemStack s : this.items)
		{
			if(stack.getItem() == s.getItem() && stack.getMetadata() == s.getMetadata())
			{
				return true;
			}
		}
		
		return false;
	}
}
