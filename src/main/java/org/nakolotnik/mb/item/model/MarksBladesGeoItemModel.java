package org.nakolotnik.mb.item.model;

import org.nakolotnik.mb.item.MarksBladesGeoItem;
import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;


public class MarksBladesGeoItemModel extends GeoModel<MarksBladesGeoItem> {
    @Override
    public ResourceLocation getAnimationResource(MarksBladesGeoItem animatable) {
        return new ResourceLocation("mb", "animations/marksblades.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(MarksBladesGeoItem animatable) {
        return new ResourceLocation("mb", "geo/marksblades.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MarksBladesGeoItem animatable) {
        return new ResourceLocation("mb", "textures/item/blade.png");
    }
}
