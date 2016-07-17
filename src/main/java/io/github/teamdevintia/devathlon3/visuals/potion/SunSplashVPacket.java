package io.github.teamdevintia.devathlon3.visuals.potion;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.util.ParticleUtil;
import io.github.teamdevintia.devathlon3.visuals.VPacket;
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
    public void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs) {
        int range = (int) optionalArgs[0];

        ParticleUtil.play(location, Effect.EXPLOSION_HUGE, 0, 0, 0, 0, 0, 0.1F, 10, 16);

        Bukkit.getScheduler().runTaskTimer(devathlon3, new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().getNearbyEntities(location, range, range, range).forEach(entity -> {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.setFireTicks(20);
                    livingEntity.damage(2);
                });
            }
        }, 0, 20);

        Bukkit.getScheduler().runTaskTimer(devathlon3, new BukkitRunnable() {
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


                        }
                    }
                }
            }
        }, 0, 10);


    }

}
