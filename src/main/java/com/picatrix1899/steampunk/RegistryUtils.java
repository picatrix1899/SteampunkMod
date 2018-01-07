package com.picatrix1899.steampunk;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryUtils
{
	public static void registerItemModelVariant(RegItem item)
	{
		ModelLoader.setCustomModelResourceLocation(item.item(), item.meta(), new ModelResourceLocation(item.getCanonicalName(), item.varaint()));
	}
	
	private static String getUnlocalizedName(Item item)
	{
		return item.getUnlocalizedName().substring(5);
	}
	
	private static String getUnlocalizedName(Block block)
	{
		return block.getUnlocalizedName().substring(5);
	}
	
	public static void registerItem(Item item)
	{
		item.setRegistryName(getUnlocalizedName(item));
		
		GameRegistry.register(item);
	}
	
	public static void registerItem(Item item, String uniquename)
	{
		item.setRegistryName(uniquename);
		
		GameRegistry.register(item);
	}

	public static ItemBlock registerBlock(Block block)
	{
		ItemBlock itemBlock = new ItemBlock(block);
		
		block.setRegistryName(getUnlocalizedName(block));
		GameRegistry.register(block);

		itemBlock.setRegistryName(block.getRegistryName());
		GameRegistry.register(itemBlock);
		
		return itemBlock;
	}
	
	public static ItemBlock registerBlock(Block block, String uniquename)
	{
		ItemBlock itemBlock = new ItemBlock(block);
		
		block.setRegistryName(uniquename);
		GameRegistry.register(block);

		itemBlock.setRegistryName(block.getRegistryName());
		GameRegistry.register(itemBlock);
		
		return itemBlock;
	}
	
	public static void registerTileEntity(Class<? extends TileEntity> clazz)
	{
		GameRegistry.registerTileEntity(clazz, SteampunkMod.MODID + ":" + clazz.getSimpleName());
	}
}
