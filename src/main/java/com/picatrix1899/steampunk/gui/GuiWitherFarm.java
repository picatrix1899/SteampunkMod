package com.picatrix1899.steampunk.gui;

import com.picatrix1899.steampunk.Utils;
import com.picatrix1899.steampunk.inventory.ContainerWitherFarm;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class GuiWitherFarm extends GuiContainer
{
    private static final ResourceLocation GUI_BACKGROUND = new ResourceLocation("steampunk:textures/gui/containers/witherFarm.png");
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final IInventory inv;

    public GuiWitherFarm(InventoryPlayer playerInv, IInventory inv)
    {
        super(new ContainerWitherFarm(playerInv, inv));
        this.playerInventory = playerInv;
        this.inv = inv;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI_BACKGROUND);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int l = this.getCookProgressScaled(24);
        
        this.drawTexturedModalRect(i + 94, j + 33, 176, 14, l + 1, 16);
        
    }
    
    private int getCookProgressScaled(int pixels)
    {
        int i = this.inv.getField(0);
        int j = this.inv.getField(1);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}
