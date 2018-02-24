package me.Zackpollard.OreRestrictor;
     
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
     
public class MyBlockListener implements Listener {
	
	private static Material[] oreList = {Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE};
	private static Material[] blockList = {Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.EMERALD_BLOCK};
	
	public static OreRestrictor plugin;
    public MyBlockListener(OreRestrictor instance){
    	plugin = instance;
        Bukkit.getServer().getPluginManager().registerEvents(this,instance);
    }
    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event){
    	
    	Block block = event.getBlock();
    	Player player = event.getPlayer();
    	
    	if(plugin.getConfig().getBoolean("OreRestrictor.Block.ValuableOres")){
	    	for(Material material : oreList){
	    		if(block.getType().equals(material)){
	    			
	    			String blockname = material.toString().toLowerCase();
	    			if(!player.hasPermission("orerestrictor." + blockname.replace("_ore", "")) || (!player.hasPermission("orerestrictor." + blockname.replace("_block", "")))){
	    				
	    				event.setCancelled(true);
	    				player.sendMessage(ChatColor.RED + plugin.getConfig().getString("OreRestrictor.Message") + " " + blockname.replace("_", " "));
	    				
	    				return;
	    				
	    			}
	    				
	    			if(player.hasPermission("orerestrictor." + blockname.replace("_ore", "")) || (!player.hasPermission("orerestrictor." + blockname.replace("_block", "")))){
	    				
	    				return;
	    				
	    			}
	    		}
	    	}
    	}
    	
    	if(plugin.getConfig().getBoolean("OreRestrictor.Block.ValuableBlocks")){
    	
	    	for(Material material : blockList){
	    		
	    		if(block.getType().equals(material)){
	    			
	    			String blockname = material.toString().toLowerCase();
	    			
	    			if(!player.hasPermission("orerestrictor." + blockname.replace("_ore", "")) || (!player.hasPermission("orerestrictor." + blockname.replace("_block", "")))){
	    				
	    				event.setCancelled(true);
	    				
	    				player.sendMessage(ChatColor.RED + plugin.getConfig().getString("OreRestrictor.Message") + " " + blockname.replace("_", " "));
	    				
	    				return;
	    				
	    			}
	    				
	    			if(player.hasPermission("orerestrictor." + blockname.replace("_ore", "")) || (!player.hasPermission("orerestrictor." + blockname.replace("_block", "")))){
	    				
	    				return;
	    				
	    			}
	    		}
    		}
    	}
    }
}