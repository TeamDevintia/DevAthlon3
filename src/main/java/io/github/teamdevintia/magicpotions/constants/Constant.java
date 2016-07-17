package io.github.teamdevintia.magicpotions.constants;

import io.github.teamdevintia.magicpotions.MagicPotions;

import java.util.HashMap;
import java.util.Map;

/**
 * A Constant holds various types of data so that they can be stored in one location and are easily accessible and changeable
 *
 * @author Shad0wCore
 */
public abstract class Constant<T> {

    private Map<String, T> contentHashMap = new HashMap<>();
    protected MagicPotions instance;

    public Constant(MagicPotions instance) {
        this.instance = instance;
    }

    /**
     * Adds the content to the map so that it can be used
     */
    public abstract void initializeContent();

    /**
     * Gets a values that was mapped to they identifier from the map
     *
     * @param identifier the identifier that identifies the value that should be returned
     * @return the values that was mapped to the identifier, may be null if not set
     */
    public T get(String identifier) {
        return this.getContentMap().get(identifier);
    }

    /**
     * @return the map where all constants for this instance are stored in
     */
    public Map<String, T> getContentMap() {
        return contentHashMap;
    }

    /**
     * Releases all fields
     */
    public void cleanup() {
        contentHashMap.clear();
        contentHashMap = null;
        instance = null;
    }

}
