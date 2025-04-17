package com.ytgld.moonbetween.Block.PickBlock;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.client.tab.BLCreativeTabs;
import thebetweenlands.common.item.farming.ItemPlantableSeeds;

import javax.annotation.Nullable;
import java.util.List;

public class m_seed extends ItemPlantableSeeds {
    public m_seed(){
        super(()->{
            return MoonBetween.shroom.getDefaultState();
        });
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "m_seed"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setUnlocalizedName(MoonBetween.MODID + ".m_seed");
        this.setMaxStackSize(64);
    }
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.UNCOMMON;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.compost_seed.bonus", new Object[0]), 0));

    }
}

