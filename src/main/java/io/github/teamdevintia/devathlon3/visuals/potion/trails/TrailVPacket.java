package io.github.teamdevintia.devathlon3.visuals.potion.trails;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.enums.SoundSource;
import io.github.teamdevintia.devathlon3.util.ParticleUtil;
import io.github.teamdevintia.devathlon3.util.SoundUtil;
import io.github.teamdevintia.devathlon3.visuals.VPacket;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Simple particle trail, plays some particles and a death sound
 * <p>
 * params: Sound, Potion, Effect,R,G,B
 */
public class TrailVPacket implements VPacket {
    @Override
    public void play(Devathlon3 devathlon3, Location location, Player toPlayer, Object... optionalArgs) {
        Sound sound = (Sound) optionalArgs[0];
        ThrownPotion thrownPotion = (ThrownPotion) optionalArgs[1];
        Effect effect = (Effect) optionalArgs[2];
        float red = (float) optionalArgs[3];
        float green = (float) optionalArgs[4];
        float blue = (float) optionalArgs[5];

        new BukkitRunnable() {
            @Override
            public void run() {
                // if dead, play sound
                if (thrownPotion.isDead()) {
                    this.cancel();
                    SoundUtil.playSound(sound, SoundSource.HOSTILE, 1.F, 1.F, thrownPotion.getLocation());
                }

                // else play some particles!
                for (int i = 0; i < 5; i++) {
                    if (red != -1 && green != -1 && blue == -1) {
                        ParticleUtil.playColor(thrownPotion.getLocation(), false, ParticleUtil.ColoredParticle.MOBSPELL, red, green, blue, 1.F);
                    }
                    ParticleUtil.play(thrownPotion.getLocation(), effect, 0, 0, 0, 0, 0, 0.1F, 1, 16);
                }
            }
        }.runTaskTimer(devathlon3, 0, 1);
    }
}
