package com.picatrix1899.steampunk.render;

import java.util.logging.Logger;

import com.picatrix1899.steampunk.lists.ItemIngotList;
import com.picatrix1899.steampunk.tile.TileTest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TileRendererTest extends TileEntitySpecialRenderer<TileTest>
{
    public static Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void renderTileEntityAt(TileTest te, double x, double y, double z, float partialTicks, int destroyStage)
	{
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        
        renderItem(te.getWorld(), te.getStackInSlot(0), 0.25, 0.25);
        renderItem(te.getWorld(), te.getStackInSlot(1), 0.5, 0.25);
        renderItem(te.getWorld(), te.getStackInSlot(2), 0.75, 0.25);
        
        renderItem(te.getWorld(), te.getStackInSlot(3), 0.25, 0.5);
        renderItem(te.getWorld(), te.getStackInSlot(4), 0.5, 0.5);
        renderItem(te.getWorld(), te.getStackInSlot(5), 0.75, 0.5);
        
        renderItem(te.getWorld(), te.getStackInSlot(6), 0.25, 0.75);
        renderItem(te.getWorld(), te.getStackInSlot(7), 0.5, 0.75);
        renderItem(te.getWorld(), te.getStackInSlot(8), 0.75, 0.75);
		
        GlStateManager.popMatrix();
	}
	
    private void renderItem(World world, ItemStack stack, double rX, double rZ)
    {
        RenderItem itemRenderer = mc.getRenderItem();
        if (stack != null)
        {

            EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
            entityitem.getEntityItem().stackSize = 1;
            entityitem.hoverStart = 0.0F;
 
            float rotation = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
            
            GlStateManager.pushMatrix();
            {
            	GlStateManager.translate(rX, 1.5, rZ);
            	
            	GlStateManager.disableLighting();

            	GlStateManager.rotate(rotation, 0.0F, 1.0F, 0);
            	GlStateManager.scale(0.25F, 0.25F, 0.25F);
            
            	GlStateManager.pushAttrib();
            	{
            		RenderHelper.enableStandardItemLighting();
            		itemRenderer.renderItem(entityitem.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
            		RenderHelper.disableStandardItemLighting();
            	}
            	GlStateManager.popAttrib();

            	GlStateManager.enableLighting();
            }
            GlStateManager.popMatrix();
        }
    }
}
