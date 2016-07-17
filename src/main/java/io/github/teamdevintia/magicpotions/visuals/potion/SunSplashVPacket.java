package io.github.teamdevintia.magicpotions.visuals.potion;

import io.github.teamdevintia.magicpotions.MagicPotions;
import io.github.teamdevintia.magicpotions.util.ParticleUtil;
import io.github.teamdevintia.magicpotions.visuals.VPacket;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shad0wCore
 */
public class SunSplashVPacket implements VPacket {

    @Override
    public void play(MagicPotions magicPotions, Location location, Player toPlayer, Object... optionalArgs) {
        int range = (int) optionalArgs[0];

        ParticleUtil.play(location, Effect.EXPLOSION_HUGE, 0, 0, 0, 0, 0, 0.1F, 10, 16);

        Bukkit.getScheduler().runTaskTimer(magicPotions, new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().getNearbyEntities(location, range, range, range).stream().filter(entity -> entity instanceof LivingEntity).forEach(entity -> {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.setFireTicks(20);
                    livingEntity.damage(2);
                });
            }
        }, 0, 20);

        Bukkit.getScheduler().runTaskTimer(magicPotions, new BukkitRunnable() {
            @Override
            public void run() {

                int fromX;
                int toX;
                if (location.getX() > 0) {
                    fromX = location.getBlockX() - 1;
                    toX = location.getBlockX() + 1;
                } else {
                    fromX = location.getBlockX() + 1;
                    toX = location.getBlockX() - 1;
                }

                int fromY;
                int toY;
                if (location.getY() > 0) {
                    fromY = location.getBlockY() - 1;
                    toY = location.getBlockY() + 1;
                } else {
                    fromY = location.getBlockY() + 1;
                    toY = location.getBlockY() - 1;
                }

                int fromZ;
                int toZ;
                if (location.getZ() > 0) {
                    fromZ = location.getBlockZ() - 1;
                    toZ = location.getBlockZ() + 1;
                } else {
                    fromZ = location.getBlockZ() + 1;
                    toZ = location.getBlockZ() - 1;
                }


                List<Location> particleLocs = new ArrayList<>();
                for (int x = fromX; x < toX; x++) {
                    for (int y = fromY; y < toY; y++) {
                        for (int z = fromZ; z < toZ; z++) {
                            Block block = location.getWorld().getBlockAt(x, y, z);
                            //TODO more sun particles
                        }
                    }
                }
            }
        }, 0, 10);


    }

}
