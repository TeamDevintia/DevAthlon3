package io.github.teamdevintia.devathlon3.visuals.potion;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.util.ParticleUtil;
import io.github.teamdevintia.devathlon3.util.ParticleUtil.ColoredParticle;
import io.github.teamdevintia.devathlon3.visuals.VPacket;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Shad0wCore
 */
public class TimeFreezeSplashVPacket implements VPacket {

    private float red = ParticleUtil.floatRGB(26);
    private float green = ParticleUtil.floatRGB(188);
    private float blue = ParticleUtil.floatRGB(156);

    @Override
    public void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs) {
        final double[] radius = {0};
        final double[] endRadius = {(double) (int) optionalArgs[0]};
        final int[] switcher = {0};

        new BukkitRunnable() {
            @Override
            public void run() {
                if (radius[0] > endRadius[0]) {
                    this.cancel();
                }

                radius[0] += 0.5D;

                switch (switcher[0]) {
                    case 0:
                        switcher[0] = 1;
                        for (int degree = 0; degree < 360; degree++) {
                            double radians = Math.toRadians(degree);
                            double x = Math.cos(radians) * radius[0];
                            double z = Math.sin(radians) * radius[0];

                            location.add(x, 0, z);
                            ParticleUtil.playColor(location, false, ColoredParticle.MOBSPELL, red, green, blue, 1);
                            location.subtract(x, 0, z);
                        }
                        break;
                    case 1:
                        switcher[0] = 0;
                        break;
                }

            }
        }.runTaskTimer(devathlon3, 0, 4);
    }

}
