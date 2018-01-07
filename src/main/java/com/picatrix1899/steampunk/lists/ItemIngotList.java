package com.picatrix1899.steampunk.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.picatrix1899.steampunk.RegItem;
import com.picatrix1899.steampunk.RegistryUtils;
import com.picatrix1899.steampunk.SteampunkMod;
import com.picatrix1899.steampunk.items.MassItem;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ItemIngotList
{

	private static HashMap<String,Item> ingots = new HashMap<String,Item>();
	
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
		
		generateIngots(materials, versions);
		
		versions.clear();
		
		materials.add("Iron");
		materials.add("Gold");
	
		versions.add("Decade");
		versions.add("Centennium");
		versions.add("Millennium");
		
		generateIngots(materials, versions);
		
	}
	
	public static Item getIngot(String name)
	{
		return ingots.get("ingot" + name);
	}
	
	private static void generateIngots(List<String> materials, List<String> versions)
	{
		String fetched = "";
		
		for(String version : versions)
		{
			for(String material : materials)
			{
				fetched = "ingot" + version + material;
				
				ingots.put(fetched, new MassItem(fetched, TabList.MATERIALS));
			}
		}
	}
	
	public static void registerItems()
	{
		for(Item i : ingots.values())
		{
			RegistryUtils.registerItem(i);
			
			RegItem reg = new RegItem(i);
			reg.path("ingots").hasPath(true);
			RegistryUtils.registerItemModelVariant(reg);
		}
		
//		for(Item i : screws.values())
//		{
//			RegistryUtils.registerItem(i);
//			RegistryUtils.registerItemModelInventoryVariant(i, 0);
//		}
	}
}
