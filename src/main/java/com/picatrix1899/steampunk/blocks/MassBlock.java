package com.picatrix1899.steampunk.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MassBlock extends Block
{

	public MassBlock(String unlocalized, Material materialIn, CreativeTabs tab)
	{
		super(materialIn);
		setUnlocalizedName(unlocalized);
		setCreativeTab(tab);
	}

}
