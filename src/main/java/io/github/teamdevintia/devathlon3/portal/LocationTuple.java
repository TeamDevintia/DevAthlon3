package io.github.teamdevintia.devathlon3.portal;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Iterator;

/**
 * A small tuple holding two locations
 *
 * @author MiniDigger
 */
public class LocationTuple implements Iterable<Location> {

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

    @Override
    public Iterator<Location> iterator() {
        Iterator<Location> it = new Iterator<Location>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < 2;
            }

            @Override
            public Location next() {
                currentIndex++;
                if ( currentIndex == 1 ) {
                    return loc1;
                } else {
                    return loc2;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
