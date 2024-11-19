package org.nakolotnik.mb.init;

import org.nakolotnik.mb.item.MarksBladesGeoItem;
import software.bernie.geckolib.animatable.GeoItem;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.item.ItemStack;


@Mod.EventBusSubscriber
public class ItemAnimationFactory {
    @SubscribeEvent
    public static void animatedItems(TickEvent.PlayerTickEvent event) {
        String animation = "";
        ItemStack mainhandItem = event.player.getMainHandItem().copy();
        ItemStack offhandItem = event.player.getOffhandItem().copy();
        if (event.phase == TickEvent.Phase.START && (mainhandItem.getItem() instanceof GeoItem || offhandItem.getItem() instanceof GeoItem)) {
            if (mainhandItem.getItem() instanceof MarksBladesGeoItem animatable) {
                animation = mainhandItem.getOrCreateTag().getString("geckoAnim");
                if (!animation.isEmpty()) {
                    event.player.getMainHandItem().getOrCreateTag().putString("geckoAnim", "");
                    if (event.player.level().isClientSide()) {
                        ((MarksBladesGeoItem) event.player.getMainHandItem().getItem()).animationprocedure = animation;
                    }
                }
            }
            if (offhandItem.getItem() instanceof MarksBladesGeoItem animatable) {
                animation = offhandItem.getOrCreateTag().getString("geckoAnim");
                if (!animation.isEmpty()) {
                    event.player.getOffhandItem().getOrCreateTag().putString("geckoAnim", "");
                    if (event.player.level().isClientSide()) {
                        ((MarksBladesGeoItem) event.player.getOffhandItem().getItem()).animationprocedure = animation;
                    }
                }
            }
        }
    }
}