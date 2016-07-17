package io.github.teamdevintia.devathlon3.intern.listeners;

import io.github.teamdevintia.devathlon3.entities.EvilWitch;
import net.minecraft.server.v1_10_R1.WorldServer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * changes the spawn mechanics after the magic was released
 *
 * @author MiniDigger
 */
public class MobSpawnListener implements Listener {

    //TODO change spawn mechanics

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.ENDERMAN || event.getEntityType() == EntityType.CREEPER || event.getEntityType() == EntityType.WITCH) {
            if (((CraftEntity) event.getEntity()).getHandle() instanceof EvilWitch) {
                return;
            }

            event.setCancelled(true);

            // replace with evil witch
            spawnWitch(event.getLocation());
        }
    }

    private void spawnWitch(Location loc) {
        WorldServer nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        EvilWitch entity = new EvilWitch(nmsWorld);
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
        nmsWorld.addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }
}
