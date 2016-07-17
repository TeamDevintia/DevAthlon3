package io.github.teamdevintia.devathlon3.visuals.potion;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.util.ParticleUtil;
import io.github.teamdevintia.devathlon3.util.ParticleUtil.ColoredParticle;
import io.github.teamdevintia.devathlon3.visuals.VPacket;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author Shad0wCore
 */
public class FireSplashVPacket implements VPacket {

    private float[] trailFirst = new float[]{ParticleUtil.floatRGB(241), ParticleUtil.floatRGB(196), ParticleUtil.floatRGB(15)};
    private float[] trailSecond = new float[]{ParticleUtil.floatRGB(231), ParticleUtil.floatRGB(76), ParticleUtil.floatRGB(60)};
    private float[] trailThird = new float[]{ParticleUtil.floatRGB(236), ParticleUtil.floatRGB(240), ParticleUtil.floatRGB(241)};

    @Override
    public void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs) {
        int range = (int) optionalArgs[0] + 2;
        int fireTicks = (int) optionalArgs[1];

        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().getNearbyEntities(location, range, range, range).forEach(entity -> {
                    Location modified = entity.getLocation().clone();
                    modified.add(0.5, 0.5, 0.5);


                    for (int a = 0; a < 1; a++) {
                        for (int i = 0; i < 3; i++) {
                            switch (i) {
                                case 0:
                                    for (int ii = 0; ii < 5; ii++) {
                                        ParticleUtil.playColor(entity.getLocation(), false, ColoredParticle.MOBSPELL, trailFirst[0], trailFirst[1], trailFirst[2], 1.F);
                                    }
                                    break;
                                case 1:
                                    for (int ii = 0; ii < 5; ii++) {
                                        ParticleUtil.playColor(entity.getLocation(), false, ColoredParticle.MOBSPELL, trailSecond[0], trailSecond[1], trailSecond[2], 1.F);
                                    }
                                    break;
                                case 2:
                                    for (int ii = 0; ii < 5; ii++) {
                                        ParticleUtil.playColor(entity.getLocation(), false, ColoredParticle.MOBSPELL, trailThird[0], trailThird[1], trailThird[2], 1.F);

                                    }
                                    break;
                            }
                        }
                    }
                });
            }
        }.runTaskTimer(devathlon3, 0, 2);

        new BukkitRunnable() {
            @Override
            public void run() {
                bukkitTask.cancel();
            }
        }.runTaskLater(devathlon3, fireTicks);
    }

}
