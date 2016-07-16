package io.github.teamdevintia.devathlon3.potions;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownPotion;

/**
 * @author Shad0wCore
 */
public final class FreezePotion extends MagicPotion {

    public FreezePotion(Devathlon3 devathlon3, String potionIdentifier) {
        super(devathlon3, potionIdentifier);
    }

    @Override
    protected void onPotionBuild() {



    }

    @Override
    protected void onPotionLaunch(Entity thrower, ThrownPotion thrownPotion) {

    }

    @Override
    protected void onPotionHit(Location location, ThrownPotion thrownPotion) {

    }

}
