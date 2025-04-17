package com.ytgld.moonbetween.Items.dung;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
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
import thebetweenlands.client.render.particle.BLParticles;
import thebetweenlands.client.render.particle.ParticleFactory;
import thebetweenlands.client.tab.BLCreativeTabs;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;
import thebetweenlands.common.entity.mobs.EntityAshSprite;
import thebetweenlands.common.registries.CapabilityRegistry;
import thebetweenlands.common.registries.KeyBindRegistry;
import thebetweenlands.client.tab.BLCreativeTabs;
import javax.annotation.Nullable;
import java.util.List;

public class candle_off extends Item implements IEquippable, IAnimatorRepairable {
    public candle_off() {
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "candle_off"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setUnlocalizedName(MoonBetween.MODID + ".candle_off");
        this.setMaxStackSize(1);

        this.setMaxDamage(1024);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.candle_off.bonus", new Object[0]), 0));
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.candle_off.bonus.a", new Object[0]), 0));



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
        if (entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.world.isRemote
                    && itemStack.getItemDamage() < itemStack.getMaxDamage()-1
                    && !player.getCooldownTracker().hasCooldown(itemStack.getItem())) {
                Vec3d vec3d = player.getPositionVector();
                int r = 6;
                List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(vec3d.x + r, vec3d.y + r, vec3d.z + r, vec3d.x - r, vec3d.y - r, vec3d.z - r));
                for (EntityLivingBase living : list) {
                    if (living!=player) {
                        if (living instanceof EntityAshSprite) {
                            EntityAshSprite sprite = (EntityAshSprite) living;
                            if (sprite.getHealth() > 0) {
                                sprite.attackEntityFrom(DamageSource.IN_FIRE, 1024);
                                itemStack.setItemDamage(itemStack.getItemDamage() + 1);
                            }
                        } else {
                            living.setFire(100);
                            player.getCooldownTracker().setCooldown(itemStack.getItem(), 100);
                            itemStack.setItemDamage(itemStack.getItemDamage() + 1);

                            BLParticles.PORTAL.spawn(living.world, living.posX + living.width / 2.0D, living.posY + living.height / 2.0D + 0.5D, living.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, 0.05D, 0));
                            BLParticles.PORTAL.spawn(living.world, living.posX, living.posY + living.height / 2.0D + 0.5D, living.posZ + living.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, 0.08D));
                            BLParticles.PORTAL.spawn(living.world, living.posX - living.width / 2.0D, living.posY + living.height / 2.0D + 0.5D, living.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, 0.05D, 0));
                            BLParticles.PORTAL.spawn(living.world, living.posX, living.posY + living.height / 2.0D + 0.5D, living.posZ - living.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, -0.08D));
                            BLParticles.PORTAL.spawn(living.world, living.posX + living.width / 2.0D, living.posY + living.height / 2.0D + 0.5D, living.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, -0.05D, 0));
                            BLParticles.PORTAL.spawn(living.world, living.posX, living.posY + living.height / 2.0D + 0.5D, living.posZ + living.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, -0.05D, 0.08D));
                            BLParticles.PORTAL.spawn(living.world, living.posX - living.width / 2.0D, living.posY + living.height / 2.0D + 0.5D, living.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, -0.05D, 0));
                            BLParticles.PORTAL.spawn(living.world, living.posX, living.posY + living.height / 2.0D + 0.5D, living.posZ - living.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, -0.05D, -0.08D));

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

