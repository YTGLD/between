package com.ytgld.moonbetween.Block;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thebetweenlands.common.block.farming.BlockGenericDugSoil;
import thebetweenlands.common.registries.BlockRegistry;
import thebetweenlands.common.registries.ItemRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class sap_egg extends BlockCrops implements BlockRegistry.ICustomItemBlock {



    public sap_egg() {
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "sap_egg"))
                .setUnlocalizedName(MoonBetween.MODID + ".sap_egg");

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
        return MoonBetween.sap_seed;
    }
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
        List<ItemStack> stacks = new ArrayList<>();
        if (this.getAge(state)>= 3) {
            stacks.add(ItemRegistry.SAP_BALL.getDefaultInstance());
            stacks.add(ItemRegistry.SAP_BALL.getDefaultInstance());
            stacks.add(ItemRegistry.SAP_BALL.getDefaultInstance());

            stacks.add(ItemRegistry.TANGLED_ROOT.getDefaultInstance());
            stacks.add(ItemRegistry.TANGLED_ROOT.getDefaultInstance());

            stacks.add(MoonBetween.sap_seed.getDefaultInstance());
        }

        return stacks;
    }

    protected Item getCrop()
    {
        return ItemRegistry.SAP_BALL;
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


