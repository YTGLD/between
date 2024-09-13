package com.ytgld.moonbetween;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thebetweenlands.api.item.IAnimatorRepairable;
import thebetweenlands.common.item.misc.ItemMisc;
import thebetweenlands.common.recipe.animator.ToolRepairAnimatorRecipe;
import thebetweenlands.common.recipe.misc.AnimatorRecipe;
import thebetweenlands.common.recipe.misc.CompostRecipe;
import thebetweenlands.common.recipe.mortar.PestleAndMortarRecipe;

import static com.ytgld.moonbetween.MoonBetween.*;

public class Recipe {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event){
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) SoulEye));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) SapDirt));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) Sap));}

//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) EdgeShroom));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) BrickFlower));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) YellowDottedFungus));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) RotbulbHead));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) RotbulbBall));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) SoulCube));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) SoulEye));}


       //        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) BetweenCrystal));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) BetweenSoul));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) BetweenBook));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) Crown));}
//        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) BetweenSoulStone));}

        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) Fruit));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) Lightning));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) fire));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) monsterstone));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) Seed));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) SwampHead));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) Apple));}


        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) Talisman));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) Bone));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) snailpearl));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) cheese));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) luck_amout));}
        if (fire instanceof IAnimatorRepairable) {AnimatorRecipe.addRecipe(new ToolRepairAnimatorRecipe((IAnimatorRepairable) die_bone));}

        AnimatorRecipe.addRecipe(new AnimatorRecipe(new ItemStack(bone_ball),32,50,new ItemStack(die_bone)));


        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(bone_ball));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(cheese));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(Sap));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(SapDirt));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(SoulEye));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(SoulCube));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(RotbulbBall));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(RotbulbHead));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(YellowDottedFungus));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(BrickFlower));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(EdgeShroom));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(BetweenSoulStone));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(Crown));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(BetweenBook));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(BetweenSoul));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(BetweenCrystal));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(Lightning));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(Fruit));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(Bone));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(Talisman));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(Apple));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(SwampHead));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(Seed));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(monsterstone));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(snailpearl));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(fire));
        PestleAndMortarRecipe.addRecipe(ItemMisc.EnumItemMisc.LOOT_SCRAPS.create(1), new ItemStack(luck_amout));


        CompostRecipe.addRecipe(25, 12000, compost_seed);
        CompostRecipe.addRecipe(25, 12000, Seed);

        CompostRecipe.addRecipe(25, 12000, EdgeShroom);
        CompostRecipe.addRecipe(25, 12000, m_seed);

        CompostRecipe.addRecipe(25, 12000, app_seed);
        CompostRecipe.addRecipe(25, 12000, sap_seed);
    }
}