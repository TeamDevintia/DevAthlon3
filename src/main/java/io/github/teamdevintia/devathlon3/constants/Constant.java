package io.github.teamdevintia.devathlon3.constants;

import io.github.teamdevintia.devathlon3.Devathlon3;

import java.util.HashMap;

/**
 * @author Shad0wCore
 */
public abstract class Constant<T> {

    private HashMap<String, T> contentHashMap = new HashMap<>();
    protected Devathlon3 instance;

    public Constant(Devathlon3 instance) {
        this.instance = instance;
    }

    public abstract void initializeContent();

    public T get(String identifier) {
        return this.getContentMap().get(identifier);
    }

    public HashMap<String, T> getContentMap() {
        return contentHashMap;
    }

    public void cleanup() {
        contentHashMap.clear();
        contentHashMap = null;
        instance = null;
    }

}
