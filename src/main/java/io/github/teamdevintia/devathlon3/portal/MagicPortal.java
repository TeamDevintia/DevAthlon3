package io.github.teamdevintia.devathlon3.portal;

import io.github.teamdevintia.devathlon3.Devathlon3;
import io.github.teamdevintia.devathlon3.managers.VFXManager;
import io.github.teamdevintia.devathlon3.util.ChatUtil;
import io.github.teamdevintia.devathlon3.util.DirectionUtil;
import io.github.teamdevintia.devathlon3.visuals.BloodTrailVFXPacket;
import net.minecraft.server.v1_10_R1.PacketPlayOutGameStateChange;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlls all magic portals in the world
 *
 * @author MiniDigger
 */
public class MagicPortal implements Listener {

    public static boolean wizardActive;

    private List<Location> placedTorches = new ArrayList<>();
    private List<LocationTuple> connections = new ArrayList<>();
    private BukkitTask particleTask;

    private int torchCount = 0;
    private boolean isFinished = false;
    private Location center;

    private Devathlon3 plugin;

    public MagicPortal(Devathlon3 plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getItemInHand().equals(this.plugin.getItemConstant().get("item.ritualLantern"))) {
            // Only 5 torches are place able, so that it is easier to finish it ^^
            if (torchCount == 5) {
                event.setCancelled(true);
                event.setBuild(false);
                return;
            }

            event.getBlockPlaced().setMetadata("process.ritualLantern", new FixedMetadataValue(plugin, "yes"));

            placedTorches.add(event.getBlockPlaced().getLocation());
            torchCount++;
            checkForCompletion();
        } else if (event.getBlock().getType() == Material.SMOOTH_BRICK && event.getBlock().getData() == 3 && isFinished) {
            // We already has a spawn
            if (center != null) {
                event.setCancelled(true);
                event.setBuild(false);
                return;
            }

            Location center = event.getBlock().getLocation().add(0, 1, 0);
            // validate...
            // -y value
            if (center.getY() != placedTorches.get(0).getY()) {
                event.setCancelled(true);
                event.setBuild(false);
                return;
            }
            // -distance
            for (Location loc : placedTorches) {
                if (loc.distanceSquared(center) > 16) {
                    event.setCancelled(true);
                    event.setBuild(false);
                    return;
                }
            }

            this.center = center;
            spawnThrone(event.getPlayer());
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.REDSTONE_TORCH_ON) {
            if (!event.getBlock().hasMetadata("process.ritualLantern")) {
                return;
            }

            // don't destroy what you just created!
            if (isFinished) {
                event.setCancelled(true);
                return;
            }

            placedTorches.remove(event.getBlock().getLocation());
            torchCount--;

            // someone cheated!
            if (torchCount < 0) {
                torchCount = 0;
            }

            checkForCompletion();
        }
    }

    /**
     * Relights all portals
     */
    private void checkForCompletion() {
        if (particleTask != null) {
            particleTask.cancel();
        }

        // clear old
        connections = new ArrayList<>();

        // find locations
        for (Location loc : placedTorches) {
            Block block = loc.getBlock();

            for (BlockFace face : new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH}) {
                check(block, face);
            }
        }

        // task to play the particles
        particleTask = new BukkitRunnable() {
            @Override
            public void run() {
                VFXManager.triggerVFXPacket(new BloodTrailVFXPacket(), null, null, connections);
            }
        }.runTaskTimer(plugin, 0, 1);

        // check if portal complete
        // -count locations
        Map<Location, Integer> count = new HashMap<>();
        for (LocationTuple tuple : connections) {
            tuple.forEach(loc -> {
                Integer c = count.get(loc);
                if (c == null) {
                    c = 0;
                }

                count.put(loc, c + 1);
            });
        }

        boolean full = false;
        // -our structure has 5 torches
        if (count.size() == 5) {
            full = true;
            for (int i : count.values()) {
                // -every torch can be reached exactly twice
                if (i != 2) {
                    full = false;
                    break;
                }
            }
        }

        // if done, spawn the throne!
        if (full) {
            isFinished = true;
        }
    }

    /**
     * Checks all valid postions to see if the newly place block belongs into a triangle
     *
     * @param block the newly placed block
     * @param up    the direction the tringle is facing
     */
    // http://bender.minidigger.me/2016/07/mspaint_2016-07-16_14-25-02.png
    private void check(Block block, BlockFace up) {
        if (up != BlockFace.NORTH && up != BlockFace.EAST && up != BlockFace.SOUTH && up != BlockFace.WEST) {
            throw new IllegalArgumentException("Invalid block face " + up.name());
        }

        BlockFace down = up.getOppositeFace();
        BlockFace left = DirectionUtil.counterClockwise(up);
        BlockFace right = DirectionUtil.clockwise(up);

        // 1
        // 1 -> 3
        Block pos = block.getRelative(right, 2).getRelative(down, 5);
        checkAndAddToList(block, pos);
        // 1 -> 4
        pos = block.getRelative(left, 2).getRelative(down, 5);
        checkAndAddToList(block, pos);

        // 2
        // 2 -> 4
        pos = block.getRelative(down, 3).getRelative(left, 5);
        checkAndAddToList(block, pos);
        // 2 -> 5
        pos = block.getRelative(left, 6);
        checkAndAddToList(block, pos);

        // 3
        // 3 -> 1
        pos = block.getRelative(up, 5).getRelative(left, 2);
        checkAndAddToList(block, pos);
        // 3 -> 5
        pos = block.getRelative(left, 5).getRelative(up, 3);
        checkAndAddToList(block, pos);

        // 4
        // 4 -> 1
        pos = block.getRelative(up, 5).getRelative(right, 2);
        checkAndAddToList(block, pos);
        // 4 -> 2
        pos = block.getRelative(right, 5).getRelative(up, 3);
        checkAndAddToList(block, pos);

        //5
        //5 -> 2
        pos = block.getRelative(right, 6);
        checkAndAddToList(block, pos);
        // 5 ->3
        pos = block.getRelative(down, 3).getRelative(left, 5);
        checkAndAddToList(block, pos);
    }

    /**
     * Checks if pos is a valid torch postion and if thats true it adds the tuple(origin, pos) to the list
     *
     * @param origin the block placed
     * @param pos    the block to check
     */
    private void checkAndAddToList(Block origin, Block pos) {
        if (pos.getType() == Material.REDSTONE_TORCH_ON) {
            if (placedTorches.contains(pos.getLocation())) {
                LocationTuple tuple = new LocationTuple(origin, pos);
                if (!connections.contains(tuple)) {
                    connections.add(tuple);
                }
            }
        }
    }

    /**
     * starts the thrown spawning animation
     *
     * @param player the player who spawned it
     */
    private void spawnThrone(Player player) {
        // step 1: layer 1 at y-1
        new BukkitRunnable() {
            @Override
            public void run() {
                spawnLayer1(center.getBlock().getRelative(BlockFace.DOWN));
            }
        }.runTaskLater(plugin, plugin.getTimingConstant().get("spawn.throne.step1.delay"));
        // step 2: layer 1 at y0, layer 2 at y-1
        new BukkitRunnable() {
            @Override
            public void run() {
                spawnLayer1(center.getBlock());
                spawnLayer2(center.getBlock().getRelative(BlockFace.DOWN));
            }
        }.runTaskLater(plugin, plugin.getTimingConstant().get("spawn.throne.step2.delay"));
        // step 3: layer 1 at y+1, layer 2 at y0, stop particles,
        new BukkitRunnable() {
            @Override
            public void run() {
                spawnLayer1(center.getBlock().getRelative(BlockFace.UP));
                spawnLayer2(center.getBlock());

                //stop particles
                particleTask.cancel();

                //remove torches
                placedTorches.forEach(loc -> loc.getBlock().setType(Material.AIR));
            }
        }.runTaskLater(plugin, plugin.getTimingConstant().get("spawn.throne.step3.delay"));
        // step 4: mob appearance effect plus spawn of wizard
        new BukkitRunnable() {
            @Override
            public void run() {
                // 10 = mob apearance effect
                PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(10, 0);
                Bukkit.getOnlinePlayers().forEach(player -> ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet));

                //TODO spawn wizard (talk to entites in range)
                wizardActive = true;

                Villager wizard = (Villager) center.getWorld().spawnEntity(center.clone().add(0.5, 2, 0.5), EntityType.VILLAGER);
                wizard.setCustomName(plugin.getNameConstant().get("wizard.displayname"));
                wizard.setCustomNameVisible(true);
                wizard.setAI(false);
                wizard.setCollidable(false);
                Bukkit.getOnlinePlayers().forEach(player -> player.setCollidable(false));
                // TODO make him look at player

                Bukkit.getScheduler().runTaskLater(plugin, () -> ChatUtil.sendToPlayersInRange(plugin.getMessageConstant().get("wizard.spawn.1"), 30, center),
                        plugin.getTimingConstant().get("spawn.throne.step4.message1.delay"));
                Bukkit.getScheduler().runTaskLater(plugin, () -> ChatUtil.sendToPlayersInRange(plugin.getMessageConstant().get("wizard.spawn.2"), 30, center),
                        plugin.getTimingConstant().get("spawn.throne.step4.message2.delay"));
                Bukkit.getScheduler().runTaskLater(plugin, () -> ChatUtil.sendToPlayersInRange(plugin.getMessageConstant().get("wizard.spawn.3"), 30, center),
                        plugin.getTimingConstant().get("spawn.throne.step4.message3.delay"));
                Bukkit.getScheduler().runTaskLater(plugin, () -> ChatUtil.sendToPlayersInRange(plugin.getMessageConstant().get("wizard.spawn.4"), 30, center),
                        plugin.getTimingConstant().get("spawn.throne.step4.message4.delay"));
                Bukkit.getScheduler().runTaskLater(plugin, () -> ChatUtil.sendToPlayersInRange(plugin.getMessageConstant().get("wizard.spawn.5"), 30, center),
                        plugin.getTimingConstant().get("spawn.throne.step4.message5.delay"));
                Bukkit.getScheduler().runTaskLater(plugin, () -> ChatUtil.sendToPlayersInRange(plugin.getMessageConstant().get("wizard.spawn.6"), 30, center),
                        plugin.getTimingConstant().get("spawn.throne.step4.message6.delay"));
            }
        }.runTaskLater(plugin, plugin.getTimingConstant().get("spawn.throne.step4.delay"));
    }

    /**
     * Spawns the first layer of blocks
     *
     * @param center the center pos
     */
    private void spawnLayer1(Block center) {
        //center
        center.setType(Material.SMOOTH_BRICK);
        center.setData((byte) 3);

        // stairs
        Block temp = center.getRelative(BlockFace.NORTH);
        temp.setType(Material.SMOOTH_STAIRS);
        temp.setData((byte) 2);

        temp = center.getRelative(BlockFace.EAST);
        temp.setType(Material.SMOOTH_STAIRS);
        temp.setData((byte) 1);

        temp = center.getRelative(BlockFace.SOUTH);
        temp.setType(Material.SMOOTH_STAIRS);
        temp.setData((byte) 3);

        temp = center.getRelative(BlockFace.WEST);
        temp.setType(Material.SMOOTH_STAIRS);
        temp.setData((byte) 0);

        // edges
        temp = center.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 0);

        temp = center.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 1);

        temp = center.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 0);

        temp = center.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 2);

        // slaps
        temp = center.getRelative(BlockFace.NORTH, 2).getRelative(BlockFace.WEST);
        temp.setType(Material.STEP);
        temp.setData((byte) 5);
        temp = center.getRelative(BlockFace.NORTH, 2).getRelative(BlockFace.EAST);
        temp.setType(Material.STEP);
        temp.setData((byte) 5);

        temp = center.getRelative(BlockFace.EAST, 2).getRelative(BlockFace.NORTH);
        temp.setType(Material.STEP);
        temp.setData((byte) 5);
        temp = center.getRelative(BlockFace.EAST, 2).getRelative(BlockFace.SOUTH);
        temp.setType(Material.STEP);
        temp.setData((byte) 5);

        temp = center.getRelative(BlockFace.SOUTH, 2).getRelative(BlockFace.EAST);
        temp.setType(Material.STEP);
        temp.setData((byte) 5);
        temp = center.getRelative(BlockFace.SOUTH, 2).getRelative(BlockFace.WEST);
        temp.setType(Material.STEP);
        temp.setData((byte) 5);

        temp = center.getRelative(BlockFace.WEST, 2).getRelative(BlockFace.NORTH);
        temp.setType(Material.STEP);
        temp.setData((byte) 5);
        temp = center.getRelative(BlockFace.WEST, 2).getRelative(BlockFace.SOUTH);
        temp.setType(Material.STEP);
        temp.setData((byte) 5);
    }

    /**
     * Spawns the second layer of blocks
     *
     * @param center the center pos
     */
    private void spawnLayer2(Block center) {
        //center
        center.setType(Material.SMOOTH_BRICK);
        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.UP || face == BlockFace.DOWN) {
                continue;
            }
            center.getRelative(face).setType(Material.SMOOTH_BRICK);
        }

        // stairs
        Block temp = center.getRelative(BlockFace.NORTH, 2);
        temp.setType(Material.SMOOTH_STAIRS);
        temp.setData((byte) 2);

        temp = center.getRelative(BlockFace.EAST, 2);
        temp.setType(Material.SMOOTH_STAIRS);
        temp.setData((byte) 1);

        temp = center.getRelative(BlockFace.SOUTH, 2);
        temp.setType(Material.SMOOTH_STAIRS);
        temp.setData((byte) 3);

        temp = center.getRelative(BlockFace.WEST, 2);
        temp.setType(Material.SMOOTH_STAIRS);
        temp.setData((byte) 0);

        // edges
        temp = center.getRelative(BlockFace.NORTH, 2).getRelative(BlockFace.WEST);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 0);
        temp = center.getRelative(BlockFace.NORTH, 2).getRelative(BlockFace.EAST);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 1);

        temp = center.getRelative(BlockFace.EAST, 2).getRelative(BlockFace.NORTH);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 2);
        temp = center.getRelative(BlockFace.EAST, 2).getRelative(BlockFace.SOUTH);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 0);

        temp = center.getRelative(BlockFace.SOUTH, 2).getRelative(BlockFace.EAST);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 1);
        temp = center.getRelative(BlockFace.SOUTH, 2).getRelative(BlockFace.WEST);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 0);

        temp = center.getRelative(BlockFace.WEST, 2).getRelative(BlockFace.NORTH);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 2);
        temp = center.getRelative(BlockFace.WEST, 2).getRelative(BlockFace.SOUTH);
        temp.setType(Material.SMOOTH_BRICK);
        temp.setData((byte) 0);
    }
}