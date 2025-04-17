package com.ytgld.moonbetween.Items.dung;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.capability.IEquipmentCapability;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.api.item.IEquippable;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.client.tab.BLCreativeTabs;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;
import thebetweenlands.common.item.equipment.ItemRing;
import thebetweenlands.common.registries.CapabilityRegistry;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.KeyBindRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class stone_mushroom extends Item implements IEquippable, IAnimatorRepairable {
    public stone_mushroom() {
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "stone_mushroom"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setUnlocalizedName(MoonBetween.MODID + ".stone_mushroom");
        this.setMaxStackSize(1);

        this.setMaxDamage(1024);
    }

    public static void Finish(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.stone_mushroom) {
                        if (event.getItem().getItem() == ItemRegistry.YELLOW_DOTTED_FUNGUS){
                            player.heal(10);
                            player.getFoodStats().addStats(4,6);

                            ItemRing.removeXp(player,30);
                        }
                    }
                }
            }
        }
    }
    public static void RightClickItem(PlayerInteractEvent.RightClickItem event){
        if (event.getEntity() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.stone_mushroom) {
                        if (event.getItemStack().getItem()  == ItemRegistry.YELLOW_DOTTED_FUNGUS){
                            player.setActiveHand(event.getHand());
                        }
                    }
                }
            }

        }
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.stone_mushroom.bonus", new Object[0]), 0));
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.stone_mushroom.bonus.a", new Object[0]), 0));
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.stone_mushroom.bonus.b", new Object[0]), 0));



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
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    @Override
    public EnumEquipmentInventory getEquipmentCategory(ItemStack itemStack) {
        return EnumEquipmentInventory.AMULET;
    }

    @Override
    public boolean canEquipOnRightClick(ItemStack itemStack, EntityPlayer entityPlayer, Entity entity) {
        IEquipmentCapability cap = entityPlayer.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
        if (cap != null) {
            IInventory inv = cap.getInventory(EnumEquipmentInventory.MISC);
            for (int i = 0; i < inv.getSizeInventory(); i++){
                ItemStack stack = inv.getStackInSlot(i);
                if (!stack.isEmpty()&&stack.getItem() == this){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean canEquip(ItemStack itemStack, @Nullable EntityPlayer entityPlayer, Entity entity) {
        IEquipmentCapability cap = entityPlayer.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
        if (cap != null) {
            IInventory inv = cap.getInventory(EnumEquipmentInventory.MISC);
            for (int i = 0; i < inv.getSizeInventory(); i++){
                ItemStack stack = inv.getStackInSlot(i);
                if (!stack.isEmpty()&&stack.getItem() == this){
                    return false;
                }
            }
        }

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



