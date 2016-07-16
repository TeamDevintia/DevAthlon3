package io.github.teamdevintia.devathlon3;

import io.github.teamdevintia.devathlon3.portal.MagicPortal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Devathlon3 extends JavaPlugin {

    @Override
    public void onEnable() {
        // init portals
        new MagicPortal( this );
        // blood drop
        new Blood( this );
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args ) {
        System.out.println( "test" );
        return true;
    }
}
