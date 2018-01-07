package com.picatrix1899.steampunk.lists;

import com.picatrix1899.steampunk.blocks.BlockWitherFarm;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BlockList
{
	public static void registerItems()
	{
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
