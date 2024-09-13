package com.ytgld.moonbetween.Items.Tower;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.api.item.IEquippable;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;
import thebetweenlands.common.item.farming.ItemPlantableSeeds;
import thebetweenlands.common.registries.KeyBindRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class Seed extends ItemPlantableSeeds implements IEquippable , IAnimatorRepairable {
    public Seed(){
        super(()->{
            return MoonBetween.soulseed.getDefaultState();
        });
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "seed"))
                .setCreativeTab(CreativeTabs.MISC)
                .setUnlocalizedName(MoonBetween.MODID + ".seed");
        this.setMaxStackSize(1);

        this.setMaxDamage(256);
    }@Override
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

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.seed.bonus", new Object[0]), 0));

        if (GuiScreen.isShiftKeyDown()) {
            String toolTip = I18n.format("tooltip.bl.ring.power", new Object[]{
                    KeyBindRegistry.RADIAL_MENU.getDisplayName()
            });

            list.addAll(ItemTooltipHandler.splitTooltip(toolTip, 1));
        } else {
            list.add(I18n.format("tooltip.bl.press.shift", new Object[0]));
        }
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.compost_seed.bonus", new Object[0]), 0));

    }
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
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



