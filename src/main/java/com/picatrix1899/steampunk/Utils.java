package com.picatrix1899.steampunk;

import javax.annotation.Nullable;

import com.picatrix1899.steampunk.tile.TileEntityInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class Utils
{
	public static void insertIntoTile(TileEntityInventory tile, EntityPlayer player, int slot, boolean clone, boolean force)
	{
		
		ItemStack stack = player.getHeldItemMainhand();
		
		if(stack == null) return;
		if(slot >= tile.getSizeInventory()) return;
		if(!tile.isItemValidForSlot(slot, stack)) return;
		
		if(clone)
		{
			stack = stack.copy();
		}
	
		stack = stack.splitStack(tile.getInventoryStackLimit());
		
		if(!force)
		{
			ItemStack sl = tile.getStackInSlot(slot);
			
			if(sl != null) return;
		}
		
		tile.setInventorySlotContents(slot, stack);
	}

	public static void sendMessage(EntityPlayer player, String msg)
	{
		player.addChatMessage(new TextComponentString(msg));
	}
}
