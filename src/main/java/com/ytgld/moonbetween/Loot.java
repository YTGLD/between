package com.ytgld.moonbetween;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.HashSet;
import java.util.Set;

public class Loot {
    public static final ResourceLocation LOOT_TABLE = RegistrationHandler.create("loot_table");
    public static final ResourceLocation LOOT_WIGHT = RegistrationHandler.create("loot_wight");
    public static final ResourceLocation CIL = RegistrationHandler.create("cil");
    public static final ResourceLocation monster = RegistrationHandler.create("monster");
    public static final ResourceLocation idol = RegistrationHandler.create("idol");
    public static void registerLootTables() {
        RegistrationHandler.LOOT_TABLES.forEach(LootTableList::register);
    }

    public static class RegistrationHandler {
        private static final Set<ResourceLocation> LOOT_TABLES = new HashSet<>();

        protected static ResourceLocation create(String id) {
            ResourceLocation lootTable = new ResourceLocation(MoonBetween.MODID, id);
            LOOT_TABLES.add(lootTable);
            return lootTable;
        }
    }
}
