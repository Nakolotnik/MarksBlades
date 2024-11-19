package org.nakolotnik.mb.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.EventPriority;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class MarksBladesRightclickedProcedure {

    private static final Map<Player, Long> vampireEffectMap = new HashMap<>();
    private static final Map<Player, Long> particleEffectStartTime = new HashMap<>();

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getHand() != event.getEntity().getUsedItemHand()) {
            return;
        }

        execute(event.getEntity(), event.getLevel());
    }

    public static void execute(Player player, Level world) {
        long currentTime = System.currentTimeMillis();
        vampireEffectMap.put(player, currentTime + 20000);
        particleEffectStartTime.put(player, currentTime);

        // Воспроизведение звука
        if (!world.isClientSide()) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRE_AMBIENT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            Player player = event.player;
            Level world = player.level();
            Long effectStartTime = particleEffectStartTime.get(player);
            if (effectStartTime != null && System.currentTimeMillis() < effectStartTime + 20000) {
                // Количество частиц
                int particleCount = 20;
                double radius = 1.5; // Радиус круга

                for (int i = 0; i < particleCount; i++) {
                    // Расчет угла для каждой частицы (равномерно распределяем по кругу)
                    double angle = 2 * Math.PI * i / particleCount;

                    // Координаты частиц в круге
                    double xOffset = Math.cos(angle) * radius;
                    double zOffset = Math.sin(angle) * radius;

                    // Генерация частиц в виде круга вокруг игрока
                    world.addParticle(ParticleTypes.FLAME,
                            player.getX() + xOffset,
                            player.getY() + 0.2f, // Немного выше игрока
                            player.getZ() + zOffset,
                            0, 0, 0);
                }
            } else {
                particleEffectStartTime.remove(player);
            }
        }
    }
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            Long effectEndTime = vampireEffectMap.get(player);
            if (effectEndTime != null && System.currentTimeMillis() < effectEndTime) {
                LivingEntity target = event.getEntity();
                float healAmount = event.getAmount() * 1.2f;
                player.heal(healAmount);
            }
        }
    }
}
