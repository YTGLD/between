package com.ytgld.moonbetween.Items.MS;

import com.ytgld.moonbetween.ItemRing;
import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.common.registries.KeyBindRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class soul_cube extends ItemRing implements IAnimatorRepairable {
    @Override
    public boolean canBeUsed(ItemStack stack) {
        return stack.getItemDamage() == 0;
    }
    AttributeModifier as = new AttributeModifier(UUID.fromString("00000000-0000-300c-9e01-0d4ad8c6caa8"),"moonsoul",10, 0);


    @Override
    public void onEquipmentTick(ItemStack itemStack, Entity entity, IInventory iInventory) {
        if (entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.world.isRemote&&
                    itemStack.getItemDamage() < 100)
            {
                if (player.ticksExisted % 30 == 0) {
                    itemStack.setItemDamage(itemStack.getItemDamage() + 1);
                }
            }

        }
    }

    @Override
    public void onEquip(ItemStack stack, Entity entity, IInventory inventory) {


        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).hasModifier(as))
                player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(as);

        }
    }
    @Override
    public void onUnequip(ItemStack stack, Entity entity, IInventory inventory) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).hasModifier(as))
                player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(as);

        }
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.soul_cube.bonus", new Object[0]), 0));

        if (GuiScreen.isShiftKeyDown()) {
            String toolTip = I18n.format("tooltip.bl.ring.power", new Object[]{
                    KeyBindRegistry.RADIAL_MENU.getDisplayName()
            });

            list.addAll(ItemTooltipHandler.splitTooltip(toolTip, 1));
        } else {
            list.add(I18n.format("tooltip.bl.press.shift", new Object[0]));
        }

    }
    public soul_cube(){
       this  .setRegistryName(new ResourceLocation(MoonBetween.MODID, "soul_cube"))
                .setCreativeTab(CreativeTabs.MISC)
                .setUnlocalizedName(MoonBetween.MODID + ".soul_cube").setMaxDamage(1024);

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

}
