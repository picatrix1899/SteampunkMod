package com.picatrix1899.steampunk.proxy;

import com.picatrix1899.steampunk.RegItem;
import com.picatrix1899.steampunk.RegistryUtils;
import com.picatrix1899.steampunk.SteampunkMod;
import com.picatrix1899.steampunk.Utils;
import com.picatrix1899.steampunk.blocks.BlockTest;
import com.picatrix1899.steampunk.blocks.BlockWitherFarm;
import com.picatrix1899.steampunk.gui.GuiDragonFarm;
import com.picatrix1899.steampunk.gui.GuiWitherFarm;
import com.picatrix1899.steampunk.inventory.ContainerDragonFarm;
import com.picatrix1899.steampunk.inventory.ContainerWitherFarm;
import com.picatrix1899.steampunk.lists.BlockIngotList;
import com.picatrix1899.steampunk.lists.BlockOreList;
import com.picatrix1899.steampunk.lists.Farms;
import com.picatrix1899.steampunk.lists.ItemIngotList;
import com.picatrix1899.steampunk.lists.ItemReelList;
import com.picatrix1899.steampunk.lists.TabList;
import com.picatrix1899.steampunk.tile.TileEntityDragonFarm;
import com.picatrix1899.steampunk.tile.TileEntityWitherFarm;
import com.picatrix1899.steampunk.tile.TileTest;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.math.Integral.IntegralOps;

public class CommonProxy implements IGuiHandler
{
	
	Block testBlock;
	ItemBlock itemTestBlock;
	
	public void preInit(FMLPreInitializationEvent event)
	{
    	TabList.registerAll();
    	
		ItemIngotList.generateItems();
		ItemIngotList.registerItems();
		ItemReelList.generateItems();
		ItemReelList.registerItems();
		BlockIngotList.generateBlocks();
		BlockIngotList.registerBlocks();
		Farms.registerAll();
		
		testBlock = new BlockTest();
		testBlock.setUnlocalizedName("testBlock");
		testBlock.setCreativeTab(TabList.MATERIALS);
		itemTestBlock = RegistryUtils.registerBlock(testBlock);
		RegistryUtils.registerItemModelVariant(new RegItem(itemTestBlock));
		RegistryUtils.registerTileEntity(TileTest.class);
	}  
	public void init(FMLInitializationEvent event)
	{

	}
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	  
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tileentity = world.getTileEntity(pos);
		
		switch(ID)
		{
			case 0:
			{
				if ((tileentity instanceof TileEntityWitherFarm))
				{
					return new ContainerWitherFarm(player.inventory, (TileEntityWitherFarm)tileentity);
				}
				break;
			}
			case 1:
			{
				if ((tileentity instanceof TileEntityDragonFarm))
				{
					return new ContainerDragonFarm(player.inventory, (TileEntityDragonFarm)tileentity);
				}
				break;
			}
		}

		return null;
	}
	  
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity tileentity = world.getTileEntity(pos);
		
		switch(ID)
		{
			case 0:
			{
				if ((tileentity instanceof TileEntityWitherFarm))
				{
					return new GuiWitherFarm(player.inventory, (TileEntityWitherFarm)tileentity);
				}
				break;
			}
			case 1:
			{
				if ((tileentity instanceof TileEntityDragonFarm))
				{
					return new GuiDragonFarm(player.inventory, (TileEntityDragonFarm)tileentity);
				}
				break;
			}
		}
		return null;
	}
}
