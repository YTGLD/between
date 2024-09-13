package com.ytgld.moonbetween.Block;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import thebetweenlands.common.block.farming.BlockGenericDugSoil;
import thebetweenlands.common.registries.BlockRegistry;

import java.util.Random;

public class compost_bush extends BlockCrops implements BlockRegistry.ICustomItemBlock {



    public compost_bush() {
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "compost_bush"))
                .setUnlocalizedName(MoonBetween.MODID + ".compost_bush");

    }
    protected int getBonemealAgeIncrease(World worldIn)
    {
        return MathHelper.getInt(worldIn.rand, 0, 1);
    }
    @Override
    public int getMaxAge() {
        return 3;
    }

    protected Item getSeed()
    {
        return MoonBetween.compost_seed;
    }

    protected Item getCrop()
    {
        return Item.getItemFromBlock(BlockRegistry.COMPOST_BLOCK);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return this.isMaxAge(state) ? this.getCrop() : this.getSeed();
    }
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return !this.isMaxAge(state);
    }
    @Override
    protected boolean canSustainBush(IBlockState state) {
        if (state.getBlock() instanceof BlockGenericDugSoil){
            if (state.getValue(BlockGenericDugSoil.COMPOSTED)){
                return true;
            }
        }
        return false;
    }

}
