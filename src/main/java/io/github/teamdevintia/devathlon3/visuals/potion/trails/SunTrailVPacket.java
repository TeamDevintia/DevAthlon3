package io.github.teamdevintia.devathlon3.visuals.potion.trails;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.enums.SoundSource;
import io.github.teamdevintia.devathlon3.util.ParticleUtil;
import io.github.teamdevintia.devathlon3.util.ParticleUtil.ColoredParticle;
import io.github.teamdevintia.devathlon3.util.SoundUtil;
import io.github.teamdevintia.devathlon3.visuals.VPacket;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Shad0wCore
 */
public class SunTrailVPacket implements VPacket {

    private float[] trailSecond = new float[]{ParticleUtil.floatRGB(192), ParticleUtil.floatRGB(57), ParticleUtil.floatRGB(43)};
    private float[] trailFirst = new float[]{ParticleUtil.floatRGB(231), ParticleUtil.floatRGB(76), ParticleUtil.floatRGB(60)};
    private float[] trailThird = new float[]{ParticleUtil.floatRGB(211), ParticleUtil.floatRGB(84), ParticleUtil.floatRGB(0)};

    @Override
    public void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs) {
        ThrownPotion thrownPotion = (ThrownPotion) optionalArgs[0];

        new BukkitRunnable() {
            @Override
            public void run() {
                if (thrownPotion.isDead()) {
                    this.cancel();
                    SoundUtil.playSound(Sound.ENTITY_WITHER_SHOOT, SoundSource.HOSTILE, 1.F, 1.F, thrownPotion.getLocation());
                }

                ParticleUtil.play(thrownPotion.getLocation(), Effect.CLOUD, 0, 0, 0, 0,0, 0.1F, 10, 16);

                for (int i = 0; i < 3; i++) {
                    switch (i) {
                        case 0:
                            for (int ii = 0; ii < 5; ii++) {
                                ParticleUtil.playColor(thrownPotion.getLocation(), false, ColoredParticle.MOBSPELL, trailFirst[0], trailFirst[1], trailFirst[2], 1.F);
                            }
                            break;
                        case 1:
                            for (int ii = 0; ii < 5; ii++) {
                                ParticleUtil.playColor(thrownPotion.getLocation(), false, ColoredParticle.MOBSPELL, trailSecond[0], trailSecond[1], trailSecond[2], 1.F);
                            }
                            break;
                        case 2:
                            for (int ii = 0; ii < 5; ii++) {
                                ParticleUtil.playColor(thrownPotion.getLocation(), false, ColoredParticle.MOBSPELL, trailThird[0], trailThird[1], trailThird[2], 1.F);

                            }
                            break;
                    }
                }
            }
        }.runTaskTimer(devathlon3, 0, 1);
    }

}
