package com.picatrix1899.steampunk.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.picatrix1899.steampunk.RegItem;
import com.picatrix1899.steampunk.RegistryUtils;
import com.picatrix1899.steampunk.blocks.MassBlock;
import com.picatrix1899.steampunk.items.MassItem;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BlockIngotList
{
	private static HashMap<String,Block> ingotBlocks = new HashMap<String,Block>();
	
	public static void generateBlocks()
	{
		ArrayList<String> materials = new ArrayList<String>();
		ArrayList<String> versions = new ArrayList<String>();
		
//		materials.add("Aluminium");
//		materials.add("Copper");
//		materials.add("Tin");
//		materials.add("Zinc");
//		materials.add("Brass");
//		materials.add("Bronze");
//		materials.add("Steel");
//		materials.add("Silver");
//		materials.add("Lead");
//		
//		versions.add("");
//		
//		generateItems(materials, versions);
		
		versions.clear();
		
		materials.add("Iron");
		materials.add("Gold");
	
		versions.add("Decade");
		versions.add("Centennium");
		versions.add("Millennium");
		
		generateItems(materials, versions);
	}
	
	public static Block getIngotBlock(String name)
	{
		return ingotBlocks.get("block" + name);
	}
	
	private static void generateItems(List<String> materials, List<String> versions)
	{
		String fetched = "";
		
		for(String version : versions)
		{
			for(String material : materials)
			{
				fetched = "block" + version + material;
				
				ingotBlocks.put(fetched, new MassBlock(fetched, Material.IRON, TabList.MATERIALS));
			}
		}
	}
	
	public static void registerBlocks()
	{
		for(Block b : ingotBlocks.values())
		{
			ItemBlock b2 = RegistryUtils.registerBlock(b);
			RegItem reg = new RegItem(b2);
			
			RegistryUtils.registerItemModelVariant(reg);
		}
	}
}
