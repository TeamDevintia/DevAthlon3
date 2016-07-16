package io.github.teamdevintia.devathlon3.potions;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;

/**
 * @author Shad0wCore
 */
public abstract class MagicPotion {


    private Devathlon3 devathlon3;
    private String potionID;

    public MagicPotion(Devathlon3 devathlon3, String potionIdentifier) {
        this.devathlon3 = devathlon3;
        this.potionID = potionIdentifier;
        this.onPotionBuild();
    }

    protected abstract void onPotionBuild();

    protected abstract void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion);

    protected abstract void onPotionHit(Location location, ThrownPotion thrownPotion);

    public String getPotionID() {
        return potionID;
    }

    protected Devathlon3 instance() {
        return this.devathlon3;
    }

}
