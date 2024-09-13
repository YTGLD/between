package com.ytgld.moonbetween.Items.MS;

import com.ytgld.moonbetween.ItemRing;
import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.capability.IDecayCapability;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.common.capability.decay.DecayStats;
import thebetweenlands.common.registries.CapabilityRegistry;
import thebetweenlands.common.registries.KeyBindRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class sap_ball extends ItemRing implements IAnimatorRepairable {
    public sap_ball(){
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "sap_ball"))
                .setCreativeTab(CreativeTabs.MISC)
                .setUnlocalizedName(MoonBetween.MODID + ".sap_ball");
    }
    @Override
    public boolean canBeUsed(ItemStack stack) {
        return stack.getItemDamage() == 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.charm.bonus", new Object[0]), 0));

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
        if (entity .ticksExisted % 45 == 0){
            if (entity instanceof EntityPlayer){
                EntityPlayer  player = (EntityPlayer) entity;
                IDecayCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_DECAY, null);
                if (player.experienceLevel > 0){
                    if (cap != null) {
                        if (stack.getItemDamage() < 256) {
                            DecayStats decayStats = cap.getDecayStats();
                            if (decayStats.getDecayLevel() > 0) {
                               thebetweenlands.common.item.equipment.ItemRing.removeXp(player,1);
                                decayStats.setDecayLevel(decayStats.getDecayLevel() - 1);

                            }
                        }
                    }
                }
            }
        }
    }
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

}
