package com.ytgld.moonbetween.Items.MS;

import com.ytgld.moonbetween.ItemRing;
import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.common.registries.KeyBindRegistry;
import thebetweenlands.client.tab.BLCreativeTabs;
import javax.annotation.Nullable;
import java.util.List;

public class soul_eye extends ItemRing implements IAnimatorRepairable {
    @Override
    public boolean canBeUsed(ItemStack stack) {
        return stack.getItemDamage() == 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.soul_eye.bonus", new Object[0]), 0));

        if (GuiScreen.isShiftKeyDown()) {
            String toolTip = I18n.format("tooltip.bl.ring.power", new Object[]{
                    KeyBindRegistry.RADIAL_MENU.getDisplayName()
            });

            list.addAll(ItemTooltipHandler.splitTooltip(toolTip, 1));
        } else {
            list.add(I18n.format("tooltip.bl.press.shift", new Object[0]));
        }

    }
    @Override
    public void onEquipmentTick(ItemStack stack, Entity entity, IInventory inventory) {
        if (entity .ticksExisted % 30 == 0){
            if (entity instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) entity;
                if (player.experienceLevel > 0&&
                        !player.world.isRemote) {

                    player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0, false, false));

                    thebetweenlands.common.item.equipment.ItemRing.removeXp(player, 1);

                }
            }
        }
    }
    public soul_eye(){
     this     .setRegistryName(new ResourceLocation(MoonBetween.MODID, "soul_eye"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setUnlocalizedName(MoonBetween.MODID + ".soul_eye");

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
