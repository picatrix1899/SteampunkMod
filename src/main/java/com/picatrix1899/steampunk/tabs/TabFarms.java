package com.picatrix1899.steampunk.tabs;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TabFarms extends TabBase
{

	public TabFarms()
	{
		super("steampunk.farms");

		setIcon(Item.getItemFromBlock(Blocks.SAPLING));
	}

}
