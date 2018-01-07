package com.picatrix1899.steampunk.proxy;

import com.picatrix1899.steampunk.SteampunkMod;
import com.picatrix1899.steampunk.blocks.BlockTest;
import com.picatrix1899.steampunk.lists.BlockIngotList;
import com.picatrix1899.steampunk.lists.BlockOreList;
import com.picatrix1899.steampunk.lists.ItemIngotList;
import com.picatrix1899.steampunk.lists.TabList;
import com.picatrix1899.steampunk.render.TileRendererTest;
import com.picatrix1899.steampunk.tile.TileTest;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy
{
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
	}  
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileTest.class, new TileRendererTest());
	}
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
}
