package com.ytgld.moonbetween.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;

public class FoodItem extends Item {
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }
}
