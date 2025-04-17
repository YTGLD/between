package com.ytgld.moonbetween.Items.ghost;

import com.ytgld.moonbetween.MoonBetween;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
import thebetweenlands.common.entity.mobs.EntityVolatileSoul;
import thebetweenlands.common.item.equipment.ItemRing;
import thebetweenlands.common.registries.CapabilityRegistry;
import thebetweenlands.common.registries.KeyBindRegistry;
import thebetweenlands.common.registries.SoundRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ghost_cage  extends Item implements IEquippable, IAnimatorRepairable {
    public ghost_cage() {
        this.setRegistryName(new ResourceLocation(MoonBetween.MODID, "ghost_cage"))
                .setCreativeTab(BLCreativeTabs.ITEMS)
                .setUnlocalizedName(MoonBetween.MODID + ".ghost_cage");
        this.setMaxStackSize(1);

        this.setMaxDamage(1024);
    }
    public static void die(LivingDeathEvent event){
        if (event.getSource().getTrueSource() instanceof EntityPlayer ){
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.ghost_cage) {

                        Vec3d vec3d = player.getPositionVector();
                        int r = 4;
                        List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(vec3d.x + r, vec3d.y + r, vec3d.z + r, vec3d.x - r, vec3d.y - r, vec3d.z - r));
                        for (EntityLivingBase living : list){
                            if (living != player){
                                living.attackEntityFrom(DamageSource.ANVIL,4);
                            }
                        }

                        ItemRing.removeXp(player,6);

                        BLParticles.PORTAL.spawn(event.getEntity().world, event.getEntity().posX + event.getEntity().width / 2.0D, event.getEntity().posY + event.getEntity().height / 2.0D + 0.5D, event.getEntity().posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, 0.05D, 0));
                        BLParticles.PORTAL.spawn(event.getEntity().world, event.getEntity().posX, event.getEntity().posY + event.getEntity().height / 2.0D + 0.5D, event.getEntity().posZ + event.getEntity().width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, 0.08D));
                        BLParticles.PORTAL.spawn(event.getEntity().world, event.getEntity().posX - event.getEntity().width / 2.0D, event.getEntity().posY + event.getEntity().height / 2.0D + 0.5D, event.getEntity().posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, 0.05D, 0));
                        BLParticles.PORTAL.spawn(event.getEntity().world, event.getEntity().posX, event.getEntity().posY + event.getEntity().height / 2.0D + 0.5D, event.getEntity().posZ - event.getEntity().width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, -0.08D));
                        BLParticles.PORTAL.spawn(event.getEntity().world, event.getEntity().posX + event.getEntity().width / 2.0D, event.getEntity().posY + event.getEntity().height / 2.0D + 0.5D, event.getEntity().posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, -0.05D, 0));
                        BLParticles.PORTAL.spawn(event.getEntity().world, event.getEntity().posX, event.getEntity().posY + event.getEntity().height / 2.0D + 0.5D, event.getEntity().posZ + event.getEntity().width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, -0.05D, 0.08D));
                        BLParticles.PORTAL.spawn(event.getEntity().world, event.getEntity().posX - event.getEntity().width / 2.0D, event.getEntity().posY + event.getEntity().height / 2.0D + 0.5D, event.getEntity().posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, -0.05D, 0));
                        BLParticles.PORTAL.spawn(event.getEntity().world, event.getEntity().posX, event.getEntity().posY + event.getEntity().height / 2.0D + 0.5D, event.getEntity().posZ - event.getEntity().width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, -0.05D, -0.08D));
                        player.world.playSound(null, player.posX, player.posY, player.posZ, SoundRegistry.FORTRESS_BOSS_HURT, SoundCategory.NEUTRAL, 1, 1);

                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        list.addAll(ItemTooltipHandler.splitTooltip(I18n.format("tooltip.moon.ghost_cage.bonus", new Object[0]), 0));
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


