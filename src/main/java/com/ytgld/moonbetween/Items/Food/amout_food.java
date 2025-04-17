package com.ytgld.moonbetween.Items.Food;

import com.ytgld.moonbetween.Items.FoodItem;
import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.capability.IEquipmentCapability;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.client.tab.BLCreativeTabs;
import thebetweenlands.common.registries.CapabilityRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class amout_food extends FoodItem {
    public amout_food(){
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "amout_food"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setUnlocalizedName(MoonBetween.MODID + ".amout_food");

    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            IEquipmentCapability cap = entity.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, (EnumFacing) null);
            if (cap != null) {
                if (cap.getAmuletSlots() < 3) {
                    cap.setAmuletSlots(cap.getAmuletSlots() + 1);
                    player.sendStatusMessage(new TextComponentTranslation("chat.amulet.slot.added", new Object[0]), true);
                    stack.shrink(1);
                }else {
                    player.sendStatusMessage(new TextComponentTranslation("chat.amulet.slot.full", new Object[0]), true);
                }
            }
        }
        return stack;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.amout_food.bonus", new Object[0]), 0));
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.amout_food.bonus1", new Object[0]), 0));

    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

}
