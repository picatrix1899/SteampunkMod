package com.picatrix1899.steampunk.tile;

import javax.annotation.Nullable;

import com.picatrix1899.steampunk.Utils;
import com.picatrix1899.steampunk.blocks.EnumTier;
import com.picatrix1899.steampunk.inventory.ContainerWitherFarm;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityDragonFarm extends TileEntityInventory implements ITickable
{

	private int totalProgressTime;
	private int passedProgressTime;
	
	private EnumTier tier;
	
	public TileEntityDragonFarm()
	{
		super(16);
	}
	
	public TileEntityDragonFarm(EnumTier tier)
	{
		super(16);
		this.tier = tier;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		int t = (compound.getByte("Tier") & 0xFF) - 1;
		
		if(t > 0 && t < EnumTier.values().length)
			this.tier = EnumTier.values()[t];
		else
			this.tier = EnumTier.TIER_1;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setByte("Tier", (byte)(this.tier.getIndex() + 1));
		
		return compound;
	}
	
    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canProgress()
    {
    	for(int i = 0; i < 4; i++)
    	{
    		if(this.inventory[i] == null)
    		{
    			return false;
    		}
    	}
    	
    	if(this.inventory[5] == null) return false;
    	if(this.inventory[6] == null) return false;
    	
    	return true;
    }
	
    public boolean isProcessing()
    {
    	return passedProgressTime > 0;
    }
    
    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        boolean markAsDirty = false;
        boolean isProcessing = isProcessing();
        
        if (!this.worldObj.isRemote)
        {
            if (this.canProgress())
            {
            	if(!isProcessing())
            	{
            		this.totalProgressTime = getProcessTime();
            	}
            	
                ++this.passedProgressTime;
                
                if (this.passedProgressTime == this.totalProgressTime)
                {
                    this.passedProgressTime = 0;
                    this.processFinished();
                    markAsDirty = true;
                }
            }
            else
            {
                this.passedProgressTime = 0;
            }

            if (isProcessing != isProcessing())
            {
            	markAsDirty = true;
            }
        }

        if (markAsDirty)
        {
            this.markDirty();
        }
    }

    public void processFinished()
    {
            ItemStack itemstack = new ItemStack(Blocks.DRAGON_EGG);

            if (this.inventory[7] == null)
            {
                this.inventory[7] = itemstack.copy();
            }
            else
            {
                this.inventory[7].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            for(int i = 0; i < 4; i++)
            {
                this.inventory[i].stackSize--;

                if (this.inventory[i].stackSize <= 0)
                {
                    this.inventory[i] = null;
                }
            }
    }
    public int getProcessTime()
    {
    
    	float damage = 1;
    	
    	if(this.inventory[5].getItem() instanceof ItemSword)
    	{
    		damage = ((ItemSword)this.inventory[5].getItem()).getDamageVsEntity() + 4;
    	}
    	
    	float damageAdditional = EnchantmentHelper.getModifierForCreature(this.inventory[5], EnumCreatureAttribute.UNDEFINED);

    	damage += damageAdditional;
    	
    	float health = 200;
    	
    	float steps = health / damage;
    	
    	float ticksPerStep = 20 * 10;
    	
    	float tierMultiplier = Math.max(this.tier.getIndex() * 5, 1);
    	
        return MathHelper.ceiling_float_int((steps * ticksPerStep) / tierMultiplier);
    }
	
    @Override
    public int getField(int id)
    {
    	switch(id)
    	{
    		case 0: return this.passedProgressTime;
    		case 1: return this.totalProgressTime;
    		case 2: return this.tier.getIndex();
    	}
    	
    	return 0;
    }
    
    @Override
    public int getFieldCount()
    {
    	return 3;
    }
    
    @Override
    public void setField(int id, int value)
    {
    	switch(id)
    	{
    		case 0: this.passedProgressTime = value; break;
    		case 1: this.totalProgressTime = value; break;
    		case 2: this.tier = EnumTier.values()[value]; break;
    	}
    }
}
