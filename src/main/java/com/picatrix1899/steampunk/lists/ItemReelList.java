package com.picatrix1899.steampunk.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.picatrix1899.steampunk.RegItem;
import com.picatrix1899.steampunk.RegistryUtils;
import com.picatrix1899.steampunk.items.MassItem;

import net.minecraft.item.Item;

public class ItemReelList
{

	private static HashMap<String,Item> reels = new HashMap<String,Item>();
	
	public static void generateItems()
	{
		ArrayList<String> materials = new ArrayList<String>();
		ArrayList<String> versions = new ArrayList<String>();
		
		materials.add("Aluminium");
		materials.add("Copper");
		materials.add("Tin");
		materials.add("Zinc");
		materials.add("Brass");
		materials.add("Bronze");
		materials.add("Steel");
		materials.add("Silver");
		materials.add("Lead");
		
		versions.add("");
		
		generateReels(materials, versions);
		
		versions.clear();
		
		materials.add("Iron");
		materials.add("Gold");
	
		versions.add("Decade");
		versions.add("Centennium");
		versions.add("Millennium");
		
		generateReels(materials, versions);
		
	}
	
	public static Item getReel(String name)
	{
		return reels.get("reel" + name);
	}
	
	private static void generateReels(List<String> materials, List<String> versions)
	{
		String fetched = "";
		
		for(String version : versions)
		{
			for(String material : materials)
			{
				fetched = "reel" + version + material;
				
				reels.put(fetched, new MassItem(fetched, TabList.MATERIALS));
			}
		}
	}
	
	public static void registerItems()
	{
		for(Item i : reels.values())
		{
			RegistryUtils.registerItem(i);
			
			RegItem reg = new RegItem(i);
			reg.path("reels").hasPath(true);
			RegistryUtils.registerItemModelVariant(reg);
		}
		
//		for(Item i : screws.values())
//		{
//			RegistryUtils.registerItem(i);
//			RegistryUtils.registerItemModelInventoryVariant(i, 0);
//		}
	}
}
