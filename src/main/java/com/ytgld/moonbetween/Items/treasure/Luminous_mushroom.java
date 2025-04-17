package com.ytgld.moonbetween.Items.treasure;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.capability.IEquipmentCapability;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.client.tab.BLCreativeTabs;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;
import thebetweenlands.common.registries.CapabilityRegistry;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.KeyBindRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class Luminous_mushroom extends TreasureItem {
    public static int damage = 1800;
    public static EnumEquipmentInventory slot = EnumEquipmentInventory.AMULET;

    public Luminous_mushroom(){
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "luminous_mushroom"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setMaxDamage(damage)
                .setUnlocalizedName(MoonBetween.MODID + ".luminous_mushroom");
        this.setMaxStackSize(1);
    }

    public static void eatBULB_CAPPED_MUSHROOM_ITEM(LivingEntityUseItemEvent event){
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(slot);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.luminous_mushroom) {
                        if (stack.getItemDamage() > 0) {
                            if (event.getItem().getItemUseAction() == EnumAction.EAT) {
                                if (event.getItem().getItem() == ItemRegistry.BULB_CAPPED_MUSHROOM_ITEM){
                                    stack.setItemDamage(stack.getItemDamage()-damage/10);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @Override
    public void onEquipmentTick(ItemStack stack, Entity entity, IInventory iInventory) {
        if (stack.getItemDamage()<damage-1){
            if (entity.ticksExisted%20==1) {
                stack.setItemDamage(stack.getItemDamage() + 1);
            }
            if (entity instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) entity;
                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,600,0,false,false));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.luminous_mushroom.bonus"), 0));
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.luminous_mushroom.bonus.a"), 0));



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



