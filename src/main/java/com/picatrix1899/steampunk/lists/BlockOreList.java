package com.picatrix1899.steampunk.lists;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOreList
{
	public static Block oreAluminium;
	public static Block oreCopper;
	public static Block oreTin;
	public static Block oreBronze;
	public static Block oreBrass;
	public static Block oreZinc;
	public static Block oreLead;
	public static Block oreSilver;
	public static Block oreSulphur;
	public static Block oreSaltpeter;
	public static Block orePhosphor;
	public static Block oreQuicksilver;
	
	public static void registerItems()
	{
		oreAluminium = registerBlock("oreAluminium", Material.IRON, TabList.MATERIALS);
		oreCopper = registerBlock("oreCopper", Material.IRON, TabList.MATERIALS);
		oreTin = registerBlock("oreTin", Material.IRON, TabList.MATERIALS);
		oreBronze = registerBlock("oreBronze",Material.IRON, TabList.MATERIALS);
		oreBrass = registerBlock("oreBrass", Material.IRON,TabList.MATERIALS);
		oreZinc = registerBlock("oreZinc", Material.IRON,TabList.MATERIALS);
		oreLead = registerBlock("oreLead", Material.IRON,TabList.MATERIALS);
		oreSilver = registerBlock("oreSilver", Material.IRON,TabList.MATERIALS);
		oreSulphur = registerBlock("oreSulphur", Material.IRON,TabList.MATERIALS);
		oreSaltpeter = registerBlock("oreSaltpeter", Material.IRON,TabList.MATERIALS);
		orePhosphor = registerBlock("orePhosphor", Material.IRON,TabList.MATERIALS);
		oreQuicksilver = registerBlock("oreQuicksilver", Material.IRON,TabList.MATERIALS);
	}
	
	public static Block registerBlock(String unlocalizedName, Material mat, CreativeTabs tab, String... oredict)
	{
		Block block = new Block(mat);

		block.setUnlocalizedName(unlocalizedName);
		block.setCreativeTab(tab);
		
		for(String s : oredict)
		{
			OreDictionary.registerOre(s, block);
		}
		
		GameRegistry.register(block);
		
		return block;
	}
}
