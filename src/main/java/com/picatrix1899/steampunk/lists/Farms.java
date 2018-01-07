package com.picatrix1899.steampunk.lists;

import com.picatrix1899.steampunk.RegItem;
import com.picatrix1899.steampunk.RegistryUtils;
import com.picatrix1899.steampunk.blocks.BlockDragonFarm;
import com.picatrix1899.steampunk.blocks.BlockWitherFarm;
import com.picatrix1899.steampunk.blocks.EnumTier;
import com.picatrix1899.steampunk.tile.TileEntityDragonFarm;
import com.picatrix1899.steampunk.tile.TileEntityWitherFarm;

import net.minecraft.item.ItemBlock;

public class Farms
{
	public static BlockWitherFarm blockWitherFarm;
	public static ItemBlock itemWitherFarm;
	public static BlockDragonFarm blockDragonFarm_t1;
	public static ItemBlock itemDragonFarm_t1;
	public static BlockDragonFarm blockDragonFarm_t2;
	public static ItemBlock itemDragonFarm_t2;
	public static BlockDragonFarm blockDragonFarm_t3;
	public static ItemBlock itemDragonFarm_t3;
	
	public static void registerAll()
	{
		blockWitherFarm = new BlockWitherFarm();
		itemWitherFarm = RegistryUtils.registerBlock(blockWitherFarm);
		RegistryUtils.registerItemModelVariant(new RegItem(itemWitherFarm));
		RegistryUtils.registerTileEntity(TileEntityWitherFarm.class);
		
		blockDragonFarm_t1 = new BlockDragonFarm(EnumTier.TIER_1);
		itemDragonFarm_t1 = RegistryUtils.registerBlock(blockDragonFarm_t1);
		RegistryUtils.registerItemModelVariant(new RegItem(itemDragonFarm_t1));
		
		
		blockDragonFarm_t2 = new BlockDragonFarm(EnumTier.TIER_2);
		itemDragonFarm_t2 = RegistryUtils.registerBlock(blockDragonFarm_t2);
		RegistryUtils.registerItemModelVariant(new RegItem(itemDragonFarm_t2));
		
		
		blockDragonFarm_t3 = new BlockDragonFarm(EnumTier.TIER_3);
		itemDragonFarm_t3 = RegistryUtils.registerBlock(blockDragonFarm_t3);
		RegistryUtils.registerItemModelVariant(new RegItem(itemDragonFarm_t3));
		
		RegistryUtils.registerTileEntity(TileEntityDragonFarm.class);
	}
}
