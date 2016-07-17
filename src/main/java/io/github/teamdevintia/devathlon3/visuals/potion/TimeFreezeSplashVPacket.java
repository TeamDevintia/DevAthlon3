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

    private float red = ParticleUtil.floatRGB(0);
    private float green = ParticleUtil.floatRGB(255);
    private float blue = ParticleUtil.floatRGB(255);

    @Override
    public void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs) {
        if (location == null) {
            System.out.println("why is location null?!");
            return;
        }

        final double[] radius = {(Integer) optionalArgs[0] - 1};
        final double[] endRadius = {radius[0] + (Integer) optionalArgs[1] - 1};

        new BukkitRunnable() {
            @Override
            public void run() {
                if (radius[0] > endRadius[0]) {
                    this.cancel();
                }

                radius[0] += 0.5D;
                for (int degree = 0; degree < 360; degree++) {
                    double radians = Math.toRadians(degree);
                    double x = Math.cos(radians) * radius[0];
                    double z = Math.sin(radians) * radius[0];

                    location.add(x, 0, z);
                    ParticleUtil.playColor(location, false, ColoredParticle.MOBSPELL, red, green, blue, 1);
                    location.subtract(x, 0, z);
                }
            }
        }.runTaskTimer(devathlon3, 0, 3);
    }

}
