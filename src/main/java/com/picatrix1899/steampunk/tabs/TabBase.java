package com.picatrix1899.steampunk.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabBase extends CreativeTabs
{
	private Item icon;
	
	public TabBase(String unlocalized)
	{
		super(CreativeTabs.getNextID(), unlocalized);
	}

	public TabBase setIcon(Item icon)
	{
		this.icon = icon;
		
		return this;
	}
	
	@Override
	public Item getTabIconItem()
	{
		return icon;
	}

}
