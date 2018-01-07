package com.picatrix1899.steampunk.blocks;

import com.picatrix1899.steampunk.Utils;
import com.picatrix1899.steampunk.tile.TileTest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTest extends BlockContainer implements ITileEntityProvider
{

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockTest()
	{
		super(Material.IRON);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }
    
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

	
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
   	 return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{

		TileTest tile = (TileTest)worldIn.getTileEntity(pos);
		
		if(tile == null) return false;

		int column = -1;
		int row = -1;
		
		if(isInsideGridRange(hitX, 0))
			column = 0;
		else if(isInsideGridRange(hitX, 1))
			column = 1;
		else if(isInsideGridRange(hitX, 2))
			column = 2;
		
		if(isInsideGridRange(hitZ, 0))
			row = 0;
		else if(isInsideGridRange(hitZ, 1))
			row = 1;
		else if(isInsideGridRange(hitZ, 2))
			row = 2;

		if(column == -1 || row == -1) return false;
		
		int slot = row * 3 + column;
		
		Utils.insertIntoTile(tile, playerIn, slot, false, true);
		
		return true;
	}
	
	private boolean isInsideGridRange(float hit, int slot)
	{
		float pixelSize = 1.0f / 16.0f;
		
		float border = pixelSize;
		float field = pixelSize * 4.0f;
		
		float start = (border * (slot + 1.0f)) + (field * slot);
		float end = start + field;
		
		return start <= hit && hit <= end;
	}

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
    
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
    	// TODO Auto-generated method stub
    	return EnumBlockRenderType.MODEL;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileTest();
	}
}
