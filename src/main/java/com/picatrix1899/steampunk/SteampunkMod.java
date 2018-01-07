package com.picatrix1899.steampunk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.picatrix1899.steampunk.lists.TabList;
import com.picatrix1899.steampunk.proxy.CommonProxy;
import com.picatrix1899.steampunk.tile.TileTest;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SteampunkMod.MODID, version = SteampunkMod.VERSION, name=SteampunkMod.NAME)
public class SteampunkMod
{
	public static final String MODID = "steampunk";
	public static final String VERSION = "1.0.0";
	public static final String NAME = "picatrix's Steampunk Mod";
	
	public static final Logger LOGGER = LogManager.getLogger("");
	
    @Instance(SteampunkMod.MODID)
    public static SteampunkMod instance;
    
    @SidedProxy(clientSide="com.picatrix1899.steampunk.proxy.ClientProxy", serverSide="com.picatrix1899.steampunk.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.preInit(event);
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    }  
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {	
    	proxy.init(event);
    }   
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	proxy.postInit(event);
    }
}
