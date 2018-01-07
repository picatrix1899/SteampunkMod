package com.picatrix1899.steampunk.lists;

import com.picatrix1899.steampunk.tabs.TabFarms;
import com.picatrix1899.steampunk.tabs.TabMaterials;

public class TabList
{
	public static TabMaterials MATERIALS;
	public static TabFarms FARMS;
	
	public static void registerAll()
	{
		MATERIALS = new TabMaterials();
		FARMS = new TabFarms();
	}
}
