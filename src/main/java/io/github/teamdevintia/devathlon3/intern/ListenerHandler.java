package io.github.teamdevintia.devathlon3.intern;

import io.github.teamdevintia.devathlon3.Devathlon3;
import org.bukkit.event.Listener;

/**
 * @author Shad0wCore
 */
public abstract class ListenerHandler implements Listener {

    private Devathlon3 instance;

    public ListenerHandler(Devathlon3 instance) {
        this.instance = instance;
    }

    protected Devathlon3 instance() {
        return this.instance;
    }

}
