package org.nakolotnik.mb.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.nakolotnik.mb.MbMod;
import org.nakolotnik.mb.item.MarksBladesGeoItem;

public class MbModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MbMod.MODID);
    public static final RegistryObject<Item> MARKS_BLADES_GEO = REGISTRY.register("marks_blades_geo", () -> new MarksBladesGeoItem());
}
