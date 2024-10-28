package com.ytgld.moonbetween;

import com.ytgld.moonbetween.Block.*;
import com.ytgld.moonbetween.Block.PickBlock.*;
import com.ytgld.moonbetween.Items.Food.amout_food;
import com.ytgld.moonbetween.Items.IdolHeadStatue.*;
import com.ytgld.moonbetween.Items.MS.*;
import com.ytgld.moonbetween.Items.Tower.*;
import com.ytgld.moonbetween.Items.misc.fire;
import com.ytgld.moonbetween.Items.misc.monsterstone;
import com.ytgld.moonbetween.Items.misc.snailpearl;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thebetweenlands.common.registries.ItemRegistry;

@Mod(modid=MoonBetween.MODID,
        name=MoonBetween.NAME,
        acceptedMinecraftVersions=MoonBetween.MC_VERSION)

@EventBusSubscriber
public class MoonBetween
{
    public static final String MODID = "moonbetween";
    public static final String NAME = "Moon Between";

    public static final String MC_VERSION = "1.12";
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Evt());
        ItemRegistry.ITEMS.add(EdgeShroom);
    }
    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        Loot.registerLootTables();
        MinecraftForge.EVENT_BUS.register(Recipe.class);
    }
    @ObjectHolder(MODID + ":sap_ball")
    public static final Item Sap = null;
    @ObjectHolder(MODID + ":sap_dirt_ball")
    public static final Item SapDirt = null;
    @ObjectHolder(MODID+":yellow_dotted_fungus")
    public static final Item YellowDottedFungus = null;
    @ObjectHolder(MODID+":rotbulb_ball")
    public static final Item RotbulbBall = null;
    @ObjectHolder(MODID+":brick_flower")
    public static final Item BrickFlower = null;
    @ObjectHolder(MODID+":rotbulb_head")
    public static final Item RotbulbHead = null;
    @ObjectHolder(MODID+":edge_shroom")
    public static final Item EdgeShroom = null;
    @ObjectHolder(MODID+":between_soul_stone")
    public static final Item BetweenSoulStone = null;
    @ObjectHolder(MODID+":soul_eye")
    public static final Item SoulEye = null;
    @ObjectHolder(MODID+":crown")
    public static final Item Crown = null;
    @ObjectHolder(MODID+":soul_cube")
    public static final Item SoulCube = null;
    @ObjectHolder(MODID+":between_book")
    public static final Item BetweenBook = null;
    @ObjectHolder(MODID+":between_soul")
    public static final Item BetweenSoul= null;
    @ObjectHolder(MODID+":between_crystal")
    public static final Item BetweenCrystal= null;
    @ObjectHolder(MODID+":lightning")
    public static final Item Lightning= null;
    @ObjectHolder(MODID+":bone")
    public static final Item Bone= null;
    @ObjectHolder(MODID+":talisman")
    public static final Item Talisman= null;
    @ObjectHolder(MODID+":fruit")
    public static final Item Fruit= null;
    @ObjectHolder(MODID+":apple")
    public static final Item Apple= null;
    @ObjectHolder(MODID+":swamphead")
    public static final Item SwampHead= null;
    @ObjectHolder(MODID+":seed")
    public static final Item Seed= null;
    @ObjectHolder(MODID+":monsterstone")
    public static final Item monsterstone= null;
    @ObjectHolder(MODID+":snailpearl")
    public static final Item snailpearl= null;
    @ObjectHolder(MODID+":fire")
    public static final Item fire= null;
    @ObjectHolder(MODID+":m_seed")
    public static final Item m_seed= null;
    @ObjectHolder(MODID+":app_seed")
    public static final Item app_seed= null;
    @ObjectHolder(MODID+":compost_seed")
    public static final Item compost_seed= null;
    @ObjectHolder(MODID+":sap_seed")
    public static final Item sap_seed= null;
    @ObjectHolder(MODID+":furit")
    public static final Item furit= null;
    @ObjectHolder(MODID+":moss")
    public static final Item moss= null;
    @ObjectHolder(MODID+":obelisk")
    public static final Item obelisk= null;
    @ObjectHolder(MODID+":fire_orb")
    public static final Item fire_orb= null;
    @ObjectHolder(MODID+":fangs")
    public static final Item fangs= null;
    @ObjectHolder(MODID+":frog")
    public static final Item frog= null;
    @ObjectHolder(MODID+":amout_food")
    public static final Item amout_food= null;
    @ObjectHolder(MODID+":cheese")
    public static final Item cheese= null;
    @ObjectHolder(MODID+":luck_amout")
    public static final Item luck_amout= null;

    @ObjectHolder(MODID+":bone_ball")
    public static final Item bone_ball= null;
    @ObjectHolder(MODID+":die_bone")
    public static final Item die_bone= null;

    @ObjectHolder(MODID+":rage")
    public static final Item rage= null;
    @ObjectHolder(MODID+":bright_green")
    public static final Item bright_green= null;


    @ObjectHolder(MODID+":compost_bush")
    public static final Block compost_bush= null;
    @ObjectHolder(MODID+":shroom")
    public static final Block shroom= null;
    @ObjectHolder(MODID+":soulseed")
    public static final Block soulseed= null;
    @ObjectHolder(MODID+":appseed")
    public static final Block appseed= null;
    @ObjectHolder(MODID+":sap_egg")
    public static final Block sap_egg= null;
    @ObjectHolder(MODID+":furit_seed")
    public static final Block furit_seed= null;




    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event){
        event.getRegistry().register(new compost_bush());
        event.getRegistry().register(new soulseed());
        event.getRegistry().register(new shroom());
        event.getRegistry().register(new appseed());
        event.getRegistry().register(new sap_egg());
        event.getRegistry().register(new furit_seed());

    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new compost_seed());
        event.getRegistry().register(new sap_seed());
        event.getRegistry().register(new furit());
        event.getRegistry().register(new moss());
        event.getRegistry().register(new obelisk());
        event.getRegistry().register(new fire_orb());
        event.getRegistry().register(new fangs());
        event.getRegistry().register(new frog());
        event.getRegistry().register(new amout_food());


        event.getRegistry().register(new sap_ball());
        event.getRegistry().register(new sap_dirt_ball());
        event.getRegistry().register(new yellow_dotted_fungus());
        event.getRegistry().register(new rotbulb_ball());
        event.getRegistry().register(new brick_flower());
        event.getRegistry().register(new rotbulb_head());
        event.getRegistry().register(new edge_shroom());
        event.getRegistry().register(new between_soul_stone());
        event.getRegistry().register(new soul_eye());
        event.getRegistry().register(new crown());
        event.getRegistry().register(new soul_cube());
        event.getRegistry().register(new between_book());
        event.getRegistry().register(new between_soul());
        event.getRegistry().register(new between_crystal());
        event.getRegistry().register(new Lightning());
        event.getRegistry().register(new m_seed());
        event.getRegistry().register(new app_seed());
        event.getRegistry().register(new cheese());
        event.getRegistry().register(new luck_amout());
        event.getRegistry().register(new bone_ball());
        event.getRegistry().register(new die_bone());
        event.getRegistry().register(new rage());
        event.getRegistry().register(new bright_green());






    }
    @SubscribeEvent
    public static void reg_item(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new Bone());
        event.getRegistry().register(new Talisman());
        event.getRegistry().register(new Fruit());
        event.getRegistry().register(new Apple());
        event.getRegistry().register(new SwampHead());
        event.getRegistry().register(new Seed());
        event.getRegistry().register(new monsterstone());
        event.getRegistry().register(new snailpearl());
        event.getRegistry().register(new fire());



    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(Sap, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "sap_ball"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(SapDirt, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "sap_dirt_ball"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(YellowDottedFungus, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "yellow_dotted_fungus"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(RotbulbBall, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "rotbulb_ball"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(BrickFlower, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "brick_flower"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(RotbulbHead, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "rotbulb_head"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(EdgeShroom, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "edge_shroom"), "inventory"));

        ModelLoader.setCustomModelResourceLocation(BetweenSoulStone, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "between_soul_stone"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(SoulEye, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "soul_eye"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Crown, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "crown"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(SoulCube, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "soul_cube"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(BetweenBook, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "between_book"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(BetweenSoul, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "between_soul"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(BetweenCrystal, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "between_crystal"), "inventory"));

        ModelLoader.setCustomModelResourceLocation(Lightning, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "lightning"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Bone, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "bone"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Talisman, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "talisman"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Fruit, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "fruit"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Apple, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "apple"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(SwampHead, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "swamphead"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Seed, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "seed"), "inventory"));

        ModelLoader.setCustomModelResourceLocation(monsterstone, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "monsterstone"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(snailpearl, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "snailpearl"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(fire, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "fire"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(m_seed, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "m_seed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(app_seed, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "app_seed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(sap_seed, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "sap_seed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(furit, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "furit"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(moss, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "moss"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(obelisk, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "obelisk"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(fire_orb, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "fire_orb"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(fangs, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "fangs"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(frog, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "frog"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(amout_food, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "amout_food"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(cheese, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "cheese"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(luck_amout, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "luck_amout"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(bone_ball, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "bone_ball"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(die_bone, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "die_bone"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(rage, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "rage"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(bright_green, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "bright_green"), "inventory"));

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(compost_bush), 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "compost_bush"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(compost_seed, 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "compost_seed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(soulseed), 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "soulseed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(shroom), 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "shroom"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(appseed), 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "appseed"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(sap_egg), 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "sap_egg"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(furit_seed), 0, new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "furit_seed"), "inventory"));


        /*


        ModelLoader.setCustomStateMapper(compost_bush,(a)->{
            Map<IBlockState, ModelResourceLocation> map = new HashMap<>();
            map.put(compost_bush.getDefaultState(),new ModelResourceLocation(new ResourceLocation(MoonBetween.MODID, "compost_bush"), "inventory"));
            return map;
        } );
*/
    }
}
