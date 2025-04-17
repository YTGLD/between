package com.ytgld.moonbetween;

import com.ytgld.moonbetween.Items.treasure.Luminous_mushroom;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NewEvt {
    @SubscribeEvent
    public void Fruit(LivingEntityUseItemEvent.Finish event){
        Luminous_mushroom.eatBULB_CAPPED_MUSHROOM_ITEM(event);
    }
}
