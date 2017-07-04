package mushu949.com.br;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import de.Herbystar.TTA.TTA_Methods;

public class Limitador
  implements Listener
{
  private Main plugin;
  private HashMap<EntityType, Integer> animalLimits;
  private String Falhanareproducao;
  private String[] area;
  
  public Limitador(Main instance, HashMap<EntityType, Integer> animalLimits, String Falhanareproducao, String[] area)
  {
    this.plugin = instance;
    this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    this.Falhanareproducao = Falhanareproducao;
    this.animalLimits = animalLimits;
    this.area = area;
  }

  @EventHandler(priority=EventPriority.HIGH)
  public void mao_direia(PlayerInteractEntityEvent e)
  {
    Player player = e.getPlayer();
    
    ItemStack item = player.getInventory().getItemInMainHand();
    if (((item != null) && (item.getType() == Material.BEETROOT)) || 
      ((item != null) && (item.getType() == Material.EGG)) || 
      ((item != null) && (item.getType() == Material.MOB_SPAWNER)) || 
      ((item != null) && (item.getType() == Material.MONSTER_EGG)) || 
      ((item != null) && (item.getType() == Material.RAW_FISH)) || 
      ((item != null) && (item.getType() == Material.APPLE)) || 
      ((item != null) && (item.getType() == Material.NETHER_STALK)) || 
      ((item != null) && (item.getType() == Material.ROTTEN_FLESH)) || 
      ((item != null) && (item.getType() == Material.GRILLED_PORK)) || 
      ((item != null) && (item.getType() == Material.PORK)) || 
      ((item != null) && (item.getType() == Material.COOKED_CHICKEN)) || 
      ((item != null) && (item.getType() == Material.RAW_CHICKEN)) || 
      ((item != null) && (item.getType() == Material.COOKED_BEEF)) || 
      ((item != null) && (item.getType() == Material.RAW_BEEF)) || 
      ((item != null) && (item.getType() == Material.GOLDEN_APPLE)) || 
      ((item != null) && (item.getType() == Material.GOLDEN_CARROT)) || 
      ((item != null) && (item.getType() == Material.POTATO_ITEM)) || 
      ((item != null) && (item.getType() == Material.CARROT_ITEM)) || 
      ((item != null) && (item.getType() == Material.PUMPKIN_SEEDS)) || 
      ((item != null) && (item.getType() == Material.MELON_SEEDS)) || 
      ((item != null) && (item.getType() == Material.SEEDS)) || (
      (item != null) && (item.getType() == Material.WHEAT)))
    {
      EntityType eventEntity = e.getRightClicked().getType();
      if (this.animalLimits.containsKey(eventEntity))
      {
        int limit = ((Integer)this.animalLimits.get(eventEntity)).intValue();
        boolean canBreed = canBreed(eventEntity, limit, player);
        if (!canBreed)
        {
          ActionBarAPI.sendActionBar(player, this.Falhanareproducao, 60);
          TTA_Methods.sendActionBar(player, this.Falhanareproducao, 60);
          e.setCancelled(true);
        }
      }
    }
  }

  @EventHandler(priority=EventPriority.HIGH)
  public void mao_Esquerda(PlayerInteractEntityEvent e)
  {
    Player player = e.getPlayer();
    
    ItemStack item = player.getInventory().getItemInOffHand();
    if (((item != null) && (item.getType() == Material.BEETROOT)) || 
      ((item != null) && (item.getType() == Material.EGG)) || 
      ((item != null) && (item.getType() == Material.MOB_SPAWNER)) || 
      ((item != null) && (item.getType() == Material.MONSTER_EGG)) || 
      ((item != null) && (item.getType() == Material.RAW_FISH)) || 
      ((item != null) && (item.getType() == Material.APPLE)) || 
      ((item != null) && (item.getType() == Material.NETHER_STALK)) || 
      ((item != null) && (item.getType() == Material.ROTTEN_FLESH)) || 
      ((item != null) && (item.getType() == Material.GRILLED_PORK)) || 
      ((item != null) && (item.getType() == Material.PORK)) || 
      ((item != null) && (item.getType() == Material.COOKED_CHICKEN)) || 
      ((item != null) && (item.getType() == Material.RAW_CHICKEN)) || 
      ((item != null) && (item.getType() == Material.COOKED_BEEF)) || 
      ((item != null) && (item.getType() == Material.RAW_BEEF)) || 
      ((item != null) && (item.getType() == Material.GOLDEN_APPLE)) || 
      ((item != null) && (item.getType() == Material.GOLDEN_CARROT)) || 
      ((item != null) && (item.getType() == Material.POTATO_ITEM)) || 
      ((item != null) && (item.getType() == Material.CARROT_ITEM)) || 
      ((item != null) && (item.getType() == Material.PUMPKIN_SEEDS)) || 
      ((item != null) && (item.getType() == Material.MELON_SEEDS)) || 
      ((item != null) && (item.getType() == Material.SEEDS)) || (
      (item != null) && (item.getType() == Material.WHEAT)))
    {
      EntityType eventEntity = e.getRightClicked().getType();
      if (this.animalLimits.containsKey(eventEntity))
      {
        int limit = ((Integer)this.animalLimits.get(eventEntity)).intValue();
        boolean canBreed = canBreed(eventEntity, limit, player);
        if (!canBreed)
        {
          TTA_Methods.sendActionBar(player, this.Falhanareproducao, 60);
          e.setCancelled(true);
        }
      }
    }
  }
  
  public boolean canBreed(EntityType animalType, int limit, Player player)
  {
    int count = 0;
    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(this.area[0]), Integer.parseInt(this.area[2]), Integer.parseInt(this.area[1]));
    for (Entity entity : entities) {
      if (entity.getType() == animalType) {
        count++;
      }
    }
    if (count >= limit) {
      return false;
    }
    return true;
  }
}
