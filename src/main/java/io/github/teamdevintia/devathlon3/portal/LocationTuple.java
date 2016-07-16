package io.github.teamdevintia.devathlon3.portal;

import org.bukkit.Location;
import org.bukkit.block.Block;

/**
 * A small tuple holding two locations
 *
 * @author MiniDigger
 */
public class LocationTuple {

    public Location loc1;
    public Location loc2;

    public LocationTuple( Block loc1, Block loc2 ) {
        this.loc1 = loc1.getLocation();
        this.loc2 = loc2.getLocation();
    }

    @Override
    public boolean equals( Object obj ) {
        if ( !( obj instanceof LocationTuple ) ) {
            return false;
        }

        LocationTuple tuple = (LocationTuple) obj;
        return ( loc1.equals( tuple.loc1 ) || loc1.equals( tuple.loc2 ) ) && ( loc2.equals( tuple.loc1 ) || loc2.equals( tuple.loc2 ) );
    }
}
