package com.ytgld.moonbetween;

import com.ytgld.moonbetween.Items.MS.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thebetweenlands.api.capability.IDecayCapability;
import thebetweenlands.api.capability.IEquipmentCapability;
import thebetweenlands.client.render.particle.BLParticles;
import thebetweenlands.client.render.particle.ParticleFactory;
import thebetweenlands.common.capability.decay.DecayStats;
import thebetweenlands.common.capability.equipment.EnumEquipmentInventory;
import thebetweenlands.common.entity.EntityBLLightningBolt;
import thebetweenlands.common.entity.mobs.*;
import thebetweenlands.common.item.equipment.ItemRing;
import thebetweenlands.common.item.food.ItemBLFood;
import thebetweenlands.common.item.misc.ItemMisc;
import thebetweenlands.common.registries.CapabilityRegistry;
import thebetweenlands.common.registries.ItemRegistry;
import thebetweenlands.common.registries.LootTableRegistry;
import thebetweenlands.common.registries.SoundRegistry;

import java.util.List;
import java.util.Random;

public class Evt {
    @SubscribeEvent
    public void asWeCan(LootTableLoadEvent event) {
        if (event.getName().equals(LootTableRegistry.SLUDGE_WORM_DUNGEON_CHEST)) {
            LootEntryTable lootEntryTable = new LootEntryTable(Loot.LOOT_TABLE, 1, 0, new LootCondition[0], Loot.LOOT_TABLE.toString());
            RandomValueRange rolls = new RandomValueRange(1, 1);
            LootPool pool = new LootPool(new LootEntry[]{lootEntryTable}, new LootCondition[0], rolls, rolls, Loot.LOOT_TABLE.toString());
            event.getTable().addPool(pool);
        }
        if (event.getName().equals(LootTableRegistry.WIGHT_FORTRESS_CHEST)) {
            LootEntryTable lootEntryTable = new LootEntryTable(Loot.LOOT_WIGHT, 1, 0, new LootCondition[0], Loot.LOOT_WIGHT.toString());
            RandomValueRange rolls = new RandomValueRange(1, 1);
            LootPool pool = new LootPool(new LootEntry[]{lootEntryTable}, new LootCondition[0], rolls, rolls, Loot.LOOT_WIGHT.toString());
            event.getTable().addPool(pool);
        }
        if (event.getName().equals(LootTableRegistry.CRAGROCK_TOWER_CHEST)) {
            LootEntryTable lootEntryTable = new LootEntryTable(Loot.CIL, 1, 0, new LootCondition[0], Loot.CIL.toString());
            RandomValueRange rolls = new RandomValueRange(1, 1);
            LootPool pool = new LootPool(new LootEntry[]{lootEntryTable}, new LootCondition[0], rolls, rolls, Loot.CIL.toString());
            event.getTable().addPool(pool);
        }
        if (event.getName().equals(LootTableRegistry.SPAWNER_CHEST)) {
            LootEntryTable lootEntryTable = new LootEntryTable(Loot.monster, 1, 0, new LootCondition[0], Loot.monster.toString());
            RandomValueRange rolls = new RandomValueRange(1, 1);
            LootPool pool = new LootPool(new LootEntry[]{lootEntryTable}, new LootCondition[0], rolls, rolls, Loot.monster.toString());
            event.getTable().addPool(pool);
        }

        if (event.getName().equals(LootTableRegistry.IDOL_HEADS_CHEST)) {
            LootEntryTable lootEntryTable = new LootEntryTable(Loot.idol, 1, 0, new LootCondition[0], Loot.idol.toString());
            RandomValueRange rolls = new RandomValueRange(1, 1);
            LootPool pool = new LootPool(new LootEntry[]{lootEntryTable}, new LootCondition[0], rolls, rolls, Loot.idol.toString());
            event.getTable().addPool(pool);
        }
    }

    @SubscribeEvent
    public void PlayerInteractEvent(PlayerInteractEvent.RightClickItem event){
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            ItemStack stack = event.getItemStack();
            if (stack.getItem() instanceof between_book ||
                    stack.getItem() instanceof between_crystal ||
                    stack.getItem() instanceof between_soul ||
                    stack.getItem() instanceof between_soul_stone ||
                    stack.getItem() instanceof brick_flower ||
                    stack.getItem() instanceof crown ||
                    stack.getItem() instanceof edge_shroom ||
                    stack.getItem() instanceof rotbulb_ball ||
                    stack.getItem() instanceof rotbulb_head ||
                    stack.getItem() instanceof sap_ball ||
                    stack.getItem() instanceof sap_dirt_ball ||
                    stack.getItem() instanceof soul_cube ||
                    stack.getItem() instanceof soul_eye ||
                    stack.getItem() instanceof yellow_dotted_fungus ||
                    stack.getItem() instanceof cheese)
            {
                if (stack.getItemDamage()!= 0){
                    if (player.experienceLevel > 0) {
                        stack.setItemDamage(stack.getItemDamage()- (stack.getMaxDamage() / 16) - 1);
                        ItemRing.removeXp(player, (stack.getItemDamage() / 33) + 5);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void AttAck(LivingHurtEvent event) {
        if (event.getSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = entity.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.SapDirt) {


                        if (entity.experienceLevel > 0) {
                            if (event.getEntity() instanceof EntityLiving) {
                                EntityLiving entityHit = (EntityLiving) event.getEntity();
                                entityHit.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 1));
                                BLParticles.DIRT_DECAY.spawn(entityHit.world, entityHit.posX + entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, 0.05D, 0));
                                BLParticles.DIRT_DECAY.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ + entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, 0.08D));
                                BLParticles.DIRT_DECAY.spawn(entityHit.world, entityHit.posX - entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, 0.05D, 0));
                                BLParticles.DIRT_DECAY.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ - entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, -0.08D));
                                BLParticles.DIRT_DECAY.spawn(entityHit.world, entityHit.posX + entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, -0.05D, 0));
                                BLParticles.DIRT_DECAY.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ + entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, -0.05D, 0.08D));
                                BLParticles.DIRT_DECAY.spawn(entityHit.world, entityHit.posX - entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, -0.05D, 0));
                                BLParticles.DIRT_DECAY.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ - entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, -0.05D, -0.08D));
                                ItemRing.removeXp(entity, 2);


                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void BetweenBook(LivingHurtEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = entity.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.BetweenBook) {
                        if (entity.experienceLevel > 0) {
                            if (event.getSource()!= null&&event.getSource().getTrueSource() != null) {
                                event.setAmount(event.getAmount() * 0.65f);
                                ItemRing.removeXp(entity,7);
                                entity.world.playSound(null, entity.posX, entity.posY, entity.posZ, SoundRegistry.FORTRESS_BOSS_HURT, SoundCategory.NEUTRAL, 1, 1);

                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void fire_orb(LivingHurtEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = entity.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.fire_orb) {
                        if (stack.getItemDamage() < 250){
                            if (event.getSource() != null&& event.getSource().isFireDamage()){
                                event.setAmount(event.getAmount() * 0.5f);
                                stack.setItemDamage(stack.getItemDamage() + 1);
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void fangs(LivingHurtEvent event) {
        if (event.getSource()!= null &&event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = entity.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.fangs) {
                        if (entity.experienceLevel > 0){

                            entity.heal(event.getAmount() / 10);
                            ItemRing.removeXp(entity,1);

                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void frog(LivingHurtEvent event) {
        if (event.getSource()!= null &&event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = entity.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.frog) {
                        event.setAmount(event.getAmount() +1);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void Lightning(LivingHurtEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer entity = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = entity.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.Lightning) {
                        if (stack.getItemDamage() < 100) {
                            if (((int) (MathHelper.nextFloat(new Random(), 1f, 7f))) == 2) {
                                if (event.getEntity() instanceof EntityLiving) {
                                    EntityLiving living = (EntityLiving) event.getEntity();
                                    EntityBLLightningBolt lightningBolt = new EntityBLLightningBolt(living.world, living.posX, living.posY, living.posZ, 1, true, false);
                                    living.world.spawnEntity(lightningBolt);
                                    stack.setItemDamage(stack.getItemDamage() + 1);

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void RotbulbBall(LivingHurtEvent event) {
        if (event.getSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.RotbulbBall) {
                        if (stack.getItemDamage() < 128) {
                            if (event.getEntity() instanceof EntityLiving) {
                                EntityLiving entityHit = (EntityLiving) event.getEntity();
                                if (entityHit instanceof EntitySludge ||
                                        entityHit instanceof EntityLargeSludgeWorm) {
                                    event.setAmount(event.getAmount() * 1.7f);

                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX + entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, 0.05D, 0));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ + entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, 0.08D));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX - entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, 0.05D, 0));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ - entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, -0.08D));
                                    stack.setItemDamage(stack.getItemDamage() + 1);

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void SwampHead(LivingHurtEvent event) {
        if (event.getSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.SwampHead) {
                        if (stack.getItemDamage() < 256) {

                            if (event.getEntity() instanceof EntityLiving) {
                                EntityLiving entityHit = (EntityLiving) event.getEntity();
                                if (entityHit instanceof EntitySwampHag) {
                                    event.setAmount(event.getAmount() * 1.5f);

                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX + entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, 0.05D, 0));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ + entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, 0.08D));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX - entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, 0.05D, 0));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ - entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, -0.08D));
                                    stack.setItemDamage(stack.getItemDamage() + 1);

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void Seed(LivingHurtEvent event) {
        if (event.getSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.Seed) {
                        if (stack.getItemDamage() < 256) {

                            if (event.getEntity() instanceof EntityLiving) {
                                EntityLiving entityHit = (EntityLiving) event.getEntity();
                                if (entityHit instanceof EntityWight) {
                                    event.setAmount(event.getAmount() * 1.7f);

                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX + entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, 0.05D, 0));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ + entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, 0.08D));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX - entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, 0.05D, 0));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ - entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, -0.08D));
                                    stack.setItemDamage(stack.getItemDamage() + 1);

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void Apple(LivingHurtEvent event) {
        if (event.getSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.Apple) {
                        if (stack.getItemDamage() < 256) {
                            if (event.getEntity() instanceof EntityLiving) {
                                EntityLiving entityHit = (EntityLiving) event.getEntity();
                                if (entityHit instanceof EntityPyrad) {
                                    event.setAmount(event.getAmount() * 1.6f);

                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX + entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, 0.05D, 0));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ + entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, 0.08D));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX - entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, 0.05D, 0));
                                    BLParticles.SMOKE.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ - entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, -0.08D));
                                    stack.setItemDamage(stack.getItemDamage() + 1);

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void BrickFlower(LivingHealEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.BrickFlower) {
                        if (player.experienceLevel > 0) {
                            event.setAmount(event.getAmount() * 1.45F);
                            ItemRing.removeXp(player,1);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void moss(LivingHealEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.moss) {
                        if (stack.getItemDamage() < 500){
                            event.setAmount(event.getAmount() * 1.1f);
                            stack.setItemDamage(stack.getItemDamage() + 1);
                        }

                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void obelisk(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.obelisk) {
                        if (stack.getItemDamage() < 500){
                            event.setStrength(event.getStrength() * 0.5f);
                            stack.setItemDamage(stack.getItemDamage() + 1);
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void RotbulbHead(LivingHurtEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.RotbulbHead) {
                        if (stack.getItemDamage() < 32){
                            if (event.getSource()!= null&&event.getSource().getTrueSource()!= null) {
                                if (!player.getCooldownTracker().hasCooldown(stack.getItem())) {
                                    event.setAmount(0);
                                    player.getCooldownTracker().setCooldown(stack.getItem(), 300);
                                    stack.setItemDamage(stack.getItemDamage() + 1);

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void Crown(LootingLevelEvent event) {
        if (event.getDamageSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getDamageSource().getTrueSource();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.Crown) {
                        if (stack.getItemDamage() < 96) {

                            event.setLootingLevel(event.getLootingLevel() + 2);
                            stack.setItemDamage(stack.getItemDamage() + 1);

                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void RotbulbHead(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.EdgeShroom) {

                        IDecayCapability capability = player.getCapability(CapabilityRegistry.CAPABILITY_DECAY, null);
                        if (capability != null) {
                            DecayStats decayStats = capability.getDecayStats();

                            if (event.getItem().getItemUseAction() == EnumAction.EAT) {
                                if (decayStats.getDecayLevel() > 0) {
                                    decayStats.setDecayLevel(decayStats.getDecayLevel() - 1);
                                    player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 200, 1));

                                }
                            }
                        }

                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void Talisman(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() != null) {
            EntityPlayer player = event.getAttackingPlayer();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.Talisman) {

                        event.setDroppedExperience((int) (event.getDroppedExperience() * 1.15));

                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void cheese(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() != null) {
            EntityPlayer player = event.getAttackingPlayer();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.cheese) {

                        event.setDroppedExperience((int) (event.getDroppedExperience() * 0.3));

                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void cheese(LivingDropsEvent event) {
        if (event.getSource().getTrueSource() != null) {

            if (event.getSource().getTrueSource() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
                IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
                if (cap != null) {
                    IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                    for (int i = 0; i < inv.getSizeInventory(); i++) {
                        ItemStack stack = inv.getStackInSlot(i);
                        if (stack.getItem() == MoonBetween.cheese) {
                            for (EntityItem item : event.getDrops()) {
                                if (item.getItem().getItem() instanceof ItemBLFood) {
                                    ItemStack ite = item.getItem();
                                    if (item.getItem().getMaxStackSize() != 1) {
                                        ite.setCount(ite.getCount() * 2);
                                        item.setItem(ite);
                                        stack.setItemDamage(stack.getItemDamage() + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void bone_ball(CriticalHitEvent event) {
        if (event.getEntityPlayer() != null) {
            if (event.getEntityPlayer() instanceof EntityPlayer) {
                EntityPlayer player = event.getEntityPlayer();
                IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
                if (cap != null) {
                    IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                    for (int i = 0; i < inv.getSizeInventory(); i++) {
                        ItemStack stack = inv.getStackInSlot(i);
                        if (stack.getItem() == MoonBetween.bone_ball) {
                            event.setDamageModifier(event.getDamageModifier()*1.1f);
                            stack.setItemDamage(stack.getItemDamage() + 4);
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void die_bone(CriticalHitEvent event) {
        if (event.getEntityPlayer() != null) {
            if (event.getEntityPlayer() instanceof EntityPlayer) {
                EntityPlayer player = event.getEntityPlayer();
                IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
                if (cap != null) {
                    IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                    for (int i = 0; i < inv.getSizeInventory(); i++) {
                        ItemStack stack = inv.getStackInSlot(i);
                        if (stack.getItem() == MoonBetween.die_bone) {
                            event.setDamageModifier(event.getDamageModifier()*1.175f);
                            stack.setItemDamage(stack.getItemDamage() + 4);
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void luck_amout(LivingDropsEvent event) {
        if (event.getSource().getTrueSource() != null) {

            if (event.getSource().getTrueSource() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
                IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
                if (cap != null) {
                    IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                    for (int i = 0; i < inv.getSizeInventory(); i++) {
                        ItemStack stack = inv.getStackInSlot(i);
                        if (stack.getItem() == MoonBetween.luck_amout) {
                            event.getDrops().add(new EntityItem(player.world,event.getEntity().getPosition().getX(),event.getEntity().getPosition().getY(),event.getEntity().getPosition().getZ(), ItemMisc.EnumItemMisc.DRIED_SWAMP_REED.create(1)));
                            stack.setItemDamage(stack.getItemDamage() + 1);
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void luck_amout(LivingExperienceDropEvent event) {
        if (event.getAttackingPlayer() != null) {
            EntityPlayer player = event.getAttackingPlayer();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.luck_amout) {
                        event.setDroppedExperience(0);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void Bone(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.Bone) {
                        if (stack.getItemDamage() < 256) {
                            if (event.getItem().getItemUseAction() == EnumAction.EAT) {
                                if (player.getFoodStats().getFoodLevel() < 20) {
                                    player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
                                    stack.setItemDamage(stack.getItemDamage() + 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void Fruit(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.AMULET);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.Fruit) {
                        if (stack.getItemDamage() < 256) {
                            if (event.getItem().getItemUseAction() == EnumAction.EAT) {
                                if (player.getFoodStats().getSaturationLevel() < 20.0f) {
                                    player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel() + 1.5f);
                                    stack.setItemDamage(stack.getItemDamage() + 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void fire(PlayerEvent.BreakSpeed event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.MISC);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.fire) {
                        if (stack.getItemDamage() < 1024) {
                            event.setNewSpeed(event.getNewSpeed() * 1.25f);
                            Block block = event.getState().getBlock();
                            if (block instanceof BlockLog) {
                                event.setNewSpeed(event.getNewSpeed() * 1.5f);
                                if (!player.world.isRemote&&player.ticksExisted % 10 == 0) {
                                    stack.setItemDamage(stack.getItemDamage() + 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void AttYellow_dotted_fungus(LivingHurtEvent event) {
        if (event.getSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.RING);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.YellowDottedFungus) {


                        IDecayCapability capability = player.getCapability(CapabilityRegistry.CAPABILITY_DECAY, null);
                        if (capability != null) {
                            DecayStats decayStats = capability.getDecayStats();
                            if (decayStats.getDecayLevel() < 10) {
                                if (event.getEntity() instanceof EntityLiving) {
                                    EntityLiving entityHit = (EntityLiving) event.getEntity();
                                    event.setAmount(event.getAmount() * 1.5f);
                                    decayStats.setDecayLevel(decayStats.getDecayLevel() + 1);

                                    BLParticles.PORTAL.spawn(entityHit.world, entityHit.posX + entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, 0.05D, 0));
                                    BLParticles.PORTAL.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ + entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, 0.08D));
                                    BLParticles.PORTAL.spawn(entityHit.world, entityHit.posX - entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, 0.05D, 0));
                                    BLParticles.PORTAL.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ - entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, 0.05D, -0.08D));
                                    BLParticles.PORTAL.spawn(entityHit.world, entityHit.posX + entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(0.08D, -0.05D, 0));
                                    BLParticles.PORTAL.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ + entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, -0.05D, 0.08D));
                                    BLParticles.PORTAL.spawn(entityHit.world, entityHit.posX - entityHit.width / 2.0D, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ, ParticleFactory.ParticleArgs.get().withMotion(-0.08D, -0.05D, 0));
                                    BLParticles.PORTAL.spawn(entityHit.world, entityHit.posX, entityHit.posY + entityHit.height / 2.0D + 0.5D, entityHit.posZ - entityHit.width / 2.0D, ParticleFactory.ParticleArgs.get().withMotion(0, -0.05D, -0.08D));
                                }


                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public void snailpearl(LivingDropsEvent event) {
        if (event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            IEquipmentCapability cap = player.getCapability(CapabilityRegistry.CAPABILITY_EQUIPMENT, null);
            if (cap != null) {
                IInventory inv = cap.getInventory(EnumEquipmentInventory.MISC);
                for (int i = 0; i < inv.getSizeInventory(); i++) {
                    ItemStack stack = inv.getStackInSlot(i);
                    if (stack.getItem() == MoonBetween.snailpearl) {
                        if (stack.getItemDamage() < 256) {

                            if (event.getEntity() instanceof EntityBloodSnail
                                    || event.getEntity() instanceof EntityMireSnail) {
                                List<EntityItem> list = event.getDrops();
                                for (EntityItem item : list) {
                                    ItemStack ite = item.getItem();
                                    if (item.getItem().getMaxStackSize() != 1 &&
                                            item.getItem().getCount() * 3 < 63) {
                                        ite.setCount(ite.getCount() * 3);
                                        item.setItem(ite);
                                        stack.setItemDamage(stack.getItemDamage() + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
