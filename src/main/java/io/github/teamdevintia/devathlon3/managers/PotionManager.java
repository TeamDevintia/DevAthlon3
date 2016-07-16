package io.github.teamdevintia.devathlon3.managers;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.potions.MagicPotion;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.HashMap;

/**
 * @author Shad0wCore
 */
public class PotionManager implements Listener {

    private Devathlon3 devathlon3;
    private HashMap<String, MagicPotion> magicPotionMap = new HashMap<>();

    public PotionManager(Devathlon3 devathlon3) {
        this.devathlon3 = devathlon3;
    }

    public void registerMagicPotion(MagicPotion magicPotion) {

    }

    @EventHandler
    public void potionThrow(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof ThrownPotion) {

        }
    }

}
