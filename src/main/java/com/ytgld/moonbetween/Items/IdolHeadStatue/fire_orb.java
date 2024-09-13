package com.ytgld.moonbetween.Items.IdolHeadStatue;

import com.ytgld.moonbetween.Items.IdolHead;
import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.common.registries.KeyBindRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class fire_orb extends IdolHead {
    public fire_orb(){
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "fire_orb"))
                .setCreativeTab(CreativeTabs.MISC)
                .setUnlocalizedName(MoonBetween.MODID + ".fire_orb");
        this.setMaxStackSize(1);

        this.setMaxDamage(250);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.fire_orb.bonus", new Object[0]), 0));

        if (GuiScreen.isShiftKeyDown()) {
            String toolTip = I18n.format("tooltip.bl.ring.power", new Object[]{
                    KeyBindRegistry.RADIAL_MENU.getDisplayName()
            });

            list.addAll(ItemTooltipHandler.splitTooltip(toolTip, 1));
        } else {
            list.add(I18n.format("tooltip.bl.press.shift", new Object[0]));
        }

    }
}
