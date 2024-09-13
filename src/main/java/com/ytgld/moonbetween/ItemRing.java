package com.ytgld.moonbetween;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thebetweenlands.api.item.IEquippable;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;

import javax.annotation.Nullable;

public abstract class ItemRing extends Item implements IEquippable {

    public ItemRing(){
        this.setMaxStackSize(1);
    }

    @Override
    public boolean canEquipOnRightClick(ItemStack itemStack, EntityPlayer entityPlayer, Entity entity) {
        return itemStack.getItemDamage() == 0;
    }
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public EnumEquipmentInventory getEquipmentCategory(ItemStack itemStack) {
        return EnumEquipmentInventory.RING;
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


    public abstract boolean canBeUsed(ItemStack stack);
}
