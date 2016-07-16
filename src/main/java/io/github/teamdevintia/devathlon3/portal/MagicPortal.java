package io.github.teamdevintia.devathlon3.portal;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

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

    private List<Location> placedTorches = new ArrayList<>();
    private List<LocationTuple> connections = new ArrayList<>();
    private BukkitTask particleTask;

    private int torchcount = 0;
    private boolean isFinished = false;
    private Location center;

    private Devathlon3 plugin;

    public MagicPortal( Devathlon3 plugin ) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents( this, plugin );
    }

    @EventHandler
    public void onPlace( BlockPlaceEvent event ) {
        //TODO check if it is the right torch
        if ( event.getBlockPlaced().getType() == Material.REDSTONE_TORCH_ON ) {
            // only 5 torches are placeable, so that it is easier to finish it ^^
            if ( torchcount == 5 ) {
                event.setCancelled( true );
                event.setBuild( false );
                return;
            }

            placedTorches.add( event.getBlockPlaced().getLocation() );
            torchcount++;
            checkForCompletion();
        } else if ( event.getBlock().getType() == Material.SMOOTH_BRICK && event.getBlock().getData() == 3 && isFinished ) {
            // we already has a spawn
            if ( center != null ) {
                event.setCancelled( true );
                event.setBuild( false );
                return;
            }

            Location center = event.getBlock().getLocation().add( 0, 1, 0 );
            // validate...
            // y value
            System.out.println( "y " + center.getY() );
            if ( center.getY() != placedTorches.get( 0 ).getY() ) {
                event.setCancelled( true );
                event.setBuild( false );
                return;
            }
            // distance
            for ( Location loc : placedTorches ) {
                if ( loc.distanceSquared( center ) > 16 ) {
                    event.setCancelled( true );
                    event.setBuild( false );
                    return;
                }
            }

            this.center = center;
            spawnThrone();
        }
    }

    @EventHandler
    public void onBreak( BlockBreakEvent event ) {
        //TODO check if it is the right torch
        if ( event.getBlock().getType() == Material.REDSTONE_TORCH_ON ) {
            // don't destroy what you just created!
            if ( isFinished ) {
                event.setCancelled( true );
                return;
            }

            placedTorches.remove( event.getBlock().getLocation() );
            torchcount--;

            // someone cheated!
            if ( torchcount < 0 ) {
                torchcount = 0;
            }

            checkForCompletion();
        }
    }

    /**
     * Relights all portals
     */
    private void checkForCompletion() {
        if ( particleTask != null ) {
            particleTask.cancel();
        }

        // clear old
        connections = new ArrayList<>();

        // find locations
        for ( Location loc : placedTorches ) {
            Block block = loc.getBlock();

            for ( BlockFace face : new BlockFace[]{ BlockFace.NORTH, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH } ) {
                check( block, face );
            }
        }

        // task to play the particles
        particleTask = new BukkitRunnable() {
            @Override
            public void run() {
                for ( LocationTuple tuple : connections ) {
                    //calc direction
                    Vector diff = tuple.loc2.toVector().subtract( tuple.loc1.toVector() );
                    double distance = diff.length();

                    // calc step size
                    double stepSize = 0.5;
                    double dx = ( diff.getX() / distance ) * stepSize;
                    double dy = ( diff.getY() / distance ) * stepSize;
                    double dz = ( diff.getZ() / distance ) * stepSize;

                    // 0.5 offset to center the particle
                    Location temp = tuple.loc1.clone().add( 0.5, 0.5, 0.5 );
                    // play the line
                    for ( double d = 0; d <= distance; d += stepSize ) {
                        temp.add( dx, dy, dz );

                        temp.getWorld().playEffect( temp, Effect.TILE_BREAK, Material.REDSTONE_BLOCK.getId() );
                    }
                }
            }
        }.runTaskTimer( plugin, 0, 1 );

        // check if portal complete

        // count locations
        Map<Location, Integer> count = new HashMap<>();
        for ( LocationTuple tuple : connections ) {
            tuple.forEach( loc -> {
                Integer c = count.get( loc );
                if ( c == null ) {
                    c = 0;
                }

                count.put( loc, c + 1 );
            } );
        }

        boolean full = false;
        // our structure has 5 torches
        if ( count.size() == 5 ) {
            full = true;
            for ( int i : count.values() ) {
                // every torch can be reached exactly twice
                if ( i != 2 ) {
                    full = false;
                    break;
                }
            }
        }

        // if done, spawn the throne!
        if ( full ) {
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
    private void check( Block block, BlockFace up ) {
        if ( up != BlockFace.NORTH && up != BlockFace.EAST && up != BlockFace.SOUTH && up != BlockFace.WEST ) {
            throw new IllegalArgumentException( "Invalid block face " + up.name() );
        }

        BlockFace down = up.getOppositeFace();
        BlockFace left = DirectionUtil.counterClockwise( up );
        BlockFace right = DirectionUtil.clockwise( up );

        // 1
        // 1 -> 3
        Block pos = block.getRelative( right, 2 ).getRelative( down, 5 );
        checkAndAddToList( block, pos );
        // 1 -> 4
        pos = block.getRelative( left, 2 ).getRelative( down, 5 );
        checkAndAddToList( block, pos );

        // 2
        // 2 -> 4
        pos = block.getRelative( down, 3 ).getRelative( left, 5 );
        checkAndAddToList( block, pos );
        // 2 -> 5
        pos = block.getRelative( left, 6 );
        checkAndAddToList( block, pos );

        // 3
        // 3 -> 1
        pos = block.getRelative( up, 5 ).getRelative( left, 2 );
        checkAndAddToList( block, pos );
        // 3 -> 5
        pos = block.getRelative( left, 5 ).getRelative( up, 3 );
        checkAndAddToList( block, pos );

        // 4
        // 4 -> 1
        pos = block.getRelative( up, 5 ).getRelative( right, 2 );
        checkAndAddToList( block, pos );
        // 4 -> 2
        pos = block.getRelative( right, 5 ).getRelative( up, 3 );
        checkAndAddToList( block, pos );

        //5
        //5 -> 2
        pos = block.getRelative( right, 6 );
        checkAndAddToList( block, pos );
        // 5 ->3
        pos = block.getRelative( down, 3 ).getRelative( left, 5 );
        checkAndAddToList( block, pos );
    }

    /**
     * Checks if pos is a valid torch postion and if thats true it adds the tuple(origin, pos) to the list
     *
     * @param origin the block placed
     * @param pos    the block to check
     */
    private void checkAndAddToList( Block origin, Block pos ) {
        if ( pos.getType() == Material.REDSTONE_TORCH_ON ) {
            if ( placedTorches.contains( pos.getLocation() ) ) {
                LocationTuple tuple = new LocationTuple( origin, pos );
                if ( !connections.contains( tuple ) ) {
                    connections.add( tuple );
                }
            }
        }
    }

    /**
     * starts the thrown spawning animation
     */
    private void spawnThrone() {
        System.out.println( "spawn throne" );
        spawnLayer1( center.getBlock() );
    }

    /**
     * Spawns the first layer of blocks
     *
     * @param center the center pos
     */
    private void spawnLayer1( Block center ) {
        center.setType( Material.SMOOTH_BRICK );
        center.setData( (byte) 3 );

        // stairs
        Block temp = center.getRelative( BlockFace.NORTH );
        temp.setType( Material.SMOOTH_STAIRS );
        temp.setData( (byte) 2 );

        temp = center.getRelative( BlockFace.EAST );
        temp.setType( Material.SMOOTH_STAIRS );
        temp.setData( (byte) 1 );

        temp = center.getRelative( BlockFace.SOUTH );
        temp.setType( Material.SMOOTH_STAIRS );
        temp.setData( (byte) 3 );

        temp = center.getRelative( BlockFace.WEST );
        temp.setType( Material.SMOOTH_STAIRS );
        temp.setData( (byte) 0 );

        // edges
        temp = center.getRelative( BlockFace.NORTH ).getRelative( BlockFace.WEST );
        temp.setType( Material.SMOOTH_BRICK );
        temp.setData( (byte) 0 );

        temp = center.getRelative( BlockFace.NORTH ).getRelative( BlockFace.EAST );
        temp.setType( Material.SMOOTH_BRICK );
        temp.setData( (byte) 1 );

        temp = center.getRelative( BlockFace.SOUTH ).getRelative( BlockFace.EAST );
        temp.setType( Material.SMOOTH_BRICK );
        temp.setData( (byte) 0 );

        temp = center.getRelative( BlockFace.SOUTH ).getRelative( BlockFace.WEST );
        temp.setType( Material.SMOOTH_BRICK );
        temp.setData( (byte) 2 );

        // slaps
        temp = center.getRelative( BlockFace.NORTH, 2 ).getRelative( BlockFace.WEST );
        temp.setType( Material.STEP );
        temp.setData( (byte) 5 );
        temp = center.getRelative( BlockFace.NORTH, 2 ).getRelative( BlockFace.EAST );
        temp.setType( Material.STEP );
        temp.setData( (byte) 5 );

        temp = center.getRelative( BlockFace.EAST, 2 ).getRelative( BlockFace.NORTH );
        temp.setType( Material.STEP );
        temp.setData( (byte) 5 );
        temp = center.getRelative( BlockFace.EAST, 2 ).getRelative( BlockFace.SOUTH );
        temp.setType( Material.STEP );
        temp.setData( (byte) 5 );

        temp = center.getRelative( BlockFace.SOUTH, 2 ).getRelative( BlockFace.EAST );
        temp.setType( Material.STEP );
        temp.setData( (byte) 5 );
        temp = center.getRelative( BlockFace.SOUTH, 2 ).getRelative( BlockFace.WEST );
        temp.setType( Material.STEP );
        temp.setData( (byte) 5 );

        temp = center.getRelative( BlockFace.WEST, 2 ).getRelative( BlockFace.NORTH );
        temp.setType( Material.STEP );
        temp.setData( (byte) 5 );
        temp = center.getRelative( BlockFace.WEST, 2 ).getRelative( BlockFace.SOUTH );
        temp.setType( Material.STEP );
        temp.setData( (byte) 5 );
    }

    private void spawnLayer2( Block center ) {

    }
}