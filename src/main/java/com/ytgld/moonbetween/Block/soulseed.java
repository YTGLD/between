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
import thebetweenlands.common.registries.ItemRegistry;

import java.util.Random;

public class soulseed extends BlockCrops implements BlockRegistry.ICustomItemBlock {



    public soulseed() {
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "soulseed"))
                .setUnlocalizedName(MoonBetween.MODID + ".soulseed");

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
        return MoonBetween.Seed;
    }

    protected Item getCrop()
    {
        return ItemRegistry.WIGHT_HEART;
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

