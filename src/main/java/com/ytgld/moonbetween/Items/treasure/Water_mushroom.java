package com.ytgld.moonbetween.Items.treasure;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.client.tab.BLCreativeTabs;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;
import thebetweenlands.common.registries.KeyBindRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class Water_mushroom extends TreasureItem {
    public static EnumEquipmentInventory slot = EnumEquipmentInventory.MISC;
    public AttributeModifier speed_at_water = new AttributeModifier(UUID.fromString("2f0d3d8c-afbe-358f-b8f3-786d7b0f9259"),
            "water_mushroom",0.7f, 1);

    public Water_mushroom(){
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "water_mushroom"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setUnlocalizedName(MoonBetween.MODID + ".water_mushroom");
        this.setMaxStackSize(1);
    }
    @Override
    public void onEquip(ItemStack stack, Entity entity, IInventory inventory) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(speed_at_water)) {
                player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speed_at_water);
            }
        }
    }
    @Override
    public void onUnequip(ItemStack stack, Entity entity, IInventory inventory) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(speed_at_water))
                player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(speed_at_water);
        }
    }

    public EnumEquipmentInventory getEquipmentCategory(ItemStack itemStack) {
        return slot;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.water_mushroom.bonus"), 0));
        if (GuiScreen.isShiftKeyDown()) {
            String toolTip = I18n.format("tooltip.bl.ring.power", KeyBindRegistry.RADIAL_MENU.getDisplayName());

            list.addAll(ItemTooltipHandler.splitTooltip(toolTip, 1));
        } else {
            list.add(I18n.format("tooltip.bl.press.shift", new Object[0]));
        }

    }
}


