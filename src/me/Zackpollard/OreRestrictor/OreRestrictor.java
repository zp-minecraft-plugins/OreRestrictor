package me.Zackpollard.OreRestrictor;
     
import java.util.logging.Logger;
 
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class OreRestrictor extends JavaPlugin {
	
    private static final Logger log = Logger.getLogger("Minecraft");
    
    public void onEnable() {
    	new MyBlockListener(this);
		final FileConfiguration config = this.getConfig();
        config.options().header("OreRestrictor Config");
        config.addDefault("OreRestrictor.Block.ValuableBlocks", true);
        config.addDefault("OreRestrictor.Block.ValuableOres", true);
        config.addDefault("OreRestrictor.Message", "You're not allowed to break");
        config.options().copyDefaults(true);
        saveConfig();
        log.info("OreRestrictor Version 0.1 Enabled");
    }
    public void onDisable() {
        log.info("OreRestrictor Version 0.1 Disabled");
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	boolean op = false;
    	if(sender instanceof Player){
    		Player player = (Player) sender;
    		op = player.isOp();
    	}
    	if(label.equalsIgnoreCase("or")){
    		if(op || sender instanceof ConsoleCommandSender){
    			if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
    				this.reloadConfig();
    				log.info("Reloaded Config File");
    				if(op){
    					sender.sendMessage(ChatColor.GOLD + "OreRestrictor Config Reloaded");
    				}
    				return true;         
    			}
    		}
    	}
    	return false;
    }
}