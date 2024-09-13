package com.ytgld.moonbetween.Items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.api.item.IEquippable;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;

import javax.annotation.Nullable;

public class IdolHead extends Item implements IEquippable, IAnimatorRepairable {
    @Override
    public int getMinRepairFuelCost(ItemStack itemStack) {
        return 4;
    }

    @Override
    public int getFullRepairFuelCost(ItemStack itemStack) {
        return 32;
    }

    @Override
    public int getMinRepairLifeCost(ItemStack itemStack) {
        return 15;
    }

    @Override
    public int getFullRepairLifeCost(ItemStack itemStack) {
        return 45;
    }
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    @Override
    public EnumEquipmentInventory getEquipmentCategory(ItemStack itemStack) {
        return EnumEquipmentInventory.AMULET;
    }

    @Override
    public boolean canEquipOnRightClick(ItemStack itemStack, EntityPlayer entityPlayer, Entity entity) {
        return false;
    }

    @Override
    public boolean canEquip(ItemStack itemStack, @Nullable EntityPlayer entityPlayer, Entity entity) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, @Nullable EntityPlayer entityPlayer, Entity entity, IInventory iInventory) {
        return true;
    }

    @Override
    public boolean canDrop(ItemStack itemStack, Entity entity, IInventory iInventory) {
        return true;
    }

    @Override
    public void onEquip(ItemStack itemStack, Entity entity, IInventory iInventory) {

    }

    @Override
    public void onUnequip(ItemStack itemStack, Entity entity, IInventory iInventory) {

    }

    @Override
    public void onEquipmentTick(ItemStack itemStack, Entity entity, IInventory iInventory) {

    }
}
