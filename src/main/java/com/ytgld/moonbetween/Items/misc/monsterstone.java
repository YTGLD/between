package com.ytgld.moonbetween.Items.misc;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.api.capability.IEquipmentCapability;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.api.item.IEquippable;
import thebetweenlands.client.handler.ItemTooltipHandler;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;
import thebetweenlands.common.entity.mobs.EntityChiromawTame;
import thebetweenlands.common.registries.CapabilityRegistry;
import thebetweenlands.common.registries.KeyBindRegistry;
import thebetweenlands.client.tab.BLCreativeTabs;
import javax.annotation.Nullable;
import java.util.List;

public class monsterstone extends Item implements IEquippable , IAnimatorRepairable {
    public monsterstone(){
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "monsterstone"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setUnlocalizedName(MoonBetween.MODID + ".monsterstone");
        this.setMaxStackSize(1);
        this.setMaxDamage(3600);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.monsterstone.bonus", new Object[0]), 0));

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
        return EnumEquipmentInventory.MISC;
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
        }   return true;
    }

    @Override
    public boolean canEquip(ItemStack itemStack, @Nullable EntityPlayer player, Entity entity) {
        IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
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
        if (entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.world.isRemote&&
                    itemStack.getItemDamage() < 100) {


                Vec3d vec3d = player.getPositionVector();
                int r = 10;
                List<EntityLiving> list = player.world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(vec3d.x + r, vec3d.y + r, vec3d.z + r, vec3d.x - r, vec3d.y - r, vec3d.z - r));
                for (EntityLiving living : list) {
                    if (living instanceof EntityChiromawTame) {
                        EntityChiromawTame chiromaw = (EntityChiromawTame) living;
                        if (chiromaw.getOwner() != null && chiromaw.getOwner() == player) {
                            chiromaw.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 100, 1));
                            chiromaw.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 1));
                            chiromaw.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 100, 1));

                            if (player.ticksExisted % 20 == 0) {
                                itemStack.setItemDamage(itemStack.getItemDamage() + 1);
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



