package io.github.teamdevintia.devathlon3.visuals.potion;

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
public class TimeFreezeTrailVPacket implements VPacket {

    private float red = ParticleUtil.floatRGB(0);
    private float green = ParticleUtil.floatRGB(255);
    private float blue = ParticleUtil.floatRGB(255);

    @Override
    public void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs) {
        ThrownPotion thrownPotion = (ThrownPotion) optionalArgs[0];
        new BukkitRunnable() {
            @Override
            public void run() {
                if (thrownPotion.isDead()) {
                    this.cancel();
                    SoundUtil.playSound(Sound.ENTITY_ZOMBIE_INFECT, SoundSource.HOSTILE, 2.F, 1.F, thrownPotion.getLocation());
                }

                // basically you also can use 0.0, 1.0, 1.0 at red, green, blue
                ParticleUtil.playColor(thrownPotion.getLocation(), false, ColoredParticle.MOBSPELL, red, green, blue, 1.F);
                ParticleUtil.play(thrownPotion.getLocation(), Effect.SNOW_SHOVEL, 0, 0, 0, 0, 0, 0.1F, 5, 16);
            }
        }.runTaskTimer(devathlon3, 0, 2);
    }

}
