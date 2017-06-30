package mushu949.com.br;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Raiz
  implements Listener
{
  private Main plugin;
  private HashMap<EntityType, Integer> animalLimits;
  private int villagerLimit;
  private String breedingFailedMsg;
  private String[] area;
  
  public Raiz(Main instance, HashMap<EntityType, Integer> animalLimits, int villagerLimit, String breedingFailedMsg, String[] area)
  {
    this.plugin = instance;
    this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    this.breedingFailedMsg = breedingFailedMsg;
    this.animalLimits = animalLimits;
    this.villagerLimit = villagerLimit;
    this.area = area;
  }
 
  @EventHandler(priority=EventPriority.HIGH)
  public void mao_esquerda_ovo(PlayerInteractEvent event){
      Player player = event.getPlayer();
     
      ItemStack item = player.getInventory().getItemInOffHand();
      if(item != null && item.getType() == Material.MONSTER_EGG) 
    {
		@SuppressWarnings("deprecation")
		int spawnData = event.getItem().getData().getData();
      if (spawnData != 0)
      {
		@SuppressWarnings("deprecation")
		EntityType eventEntity = EntityType.fromId(spawnData);
        if (this.animalLimits.containsKey(eventEntity)) 
        {
          int limit = ((Integer)this.animalLimits.get(eventEntity)).intValue();
          event.setCancelled(!canBreed(eventEntity, limit, player));
        }
      }
    }
  } 
  
  @EventHandler(priority=EventPriority.HIGH)
  public void mao_direia_ovo(PlayerInteractEvent event){
      Player player = event.getPlayer();
     
      ItemStack item = player.getInventory().getItemInMainHand();
      if(item != null && item.getType() == Material.MONSTER_EGG) 
    {
	@SuppressWarnings("deprecation")
	int spawnData = event.getItem().getData().getData();
      if (spawnData != 0)
      {
		@SuppressWarnings("deprecation")
		EntityType eventEntity = EntityType.fromId(spawnData);
        if (this.animalLimits.containsKey(eventEntity))
        {
          int limit = ((Integer)this.animalLimits.get(eventEntity)).intValue();
          event.setCancelled(!canBreed(eventEntity, limit, player));
        }
      }
    }
  }  
  
  @EventHandler(priority=EventPriority.HIGH)
  public void mao_direia(PlayerInteractEntityEvent e){
      Player player = e.getPlayer();
     
      ItemStack item = player.getInventory().getItemInMainHand();
      if((item != null && item.getType() == Material.BEETROOT) ||
    	 (item != null && item.getType() == Material.RAW_FISH) ||
    	 (item != null && item.getType() == Material.APPLE) ||
    	 (item != null && item.getType() == Material.NETHER_STALK) ||
    	 (item != null && item.getType() == Material.ROTTEN_FLESH) ||
    	 (item != null && item.getType() == Material.GRILLED_PORK) ||
    	 (item != null && item.getType() == Material.PORK) ||
    	 (item != null && item.getType() == Material.COOKED_CHICKEN) ||
    	 (item != null && item.getType() == Material.RAW_CHICKEN) ||
    	 (item != null && item.getType() == Material.COOKED_BEEF) ||
    	 (item != null && item.getType() == Material.RAW_BEEF) ||
    	 (item != null && item.getType() == Material.GOLDEN_APPLE) ||
    	 (item != null && item.getType() == Material.GOLDEN_CARROT) ||
    	 (item != null && item.getType() == Material.POTATO_ITEM) ||
    	 (item != null && item.getType() == Material.CARROT_ITEM) ||
    	 (item != null && item.getType() == Material.PUMPKIN_SEEDS) ||
    	 (item != null && item.getType() == Material.MELON_SEEDS) ||
    	 (item != null && item.getType() == Material.SEEDS) ||
     	 (item != null && item.getType() == Material.WHEAT)) 
      {
          EntityType eventEntity = e.getRightClicked().getType();
          if (this.animalLimits.containsKey(eventEntity))
          {
            int limit = ((Integer)this.animalLimits.get(eventEntity)).intValue();
            boolean canBreed = canBreed(eventEntity, limit, player);
            if (!canBreed)
            {
              player.sendMessage(ChatColor.RED + this.breedingFailedMsg);
              e.setCancelled(true);
            }
          }
        }
      }
      

  @EventHandler(priority=EventPriority.HIGH)
  public void mao_esquerda(PlayerInteractEntityEvent e){
      Player player = e.getPlayer();
     
      ItemStack item = player.getInventory().getItemInOffHand();
      if((item != null && item.getType() == Material.BEETROOT) ||
    	 (item != null && item.getType() == Material.RAW_FISH) ||
    	 (item != null && item.getType() == Material.APPLE) ||
    	 (item != null && item.getType() == Material.NETHER_STALK) ||
    	 (item != null && item.getType() == Material.ROTTEN_FLESH) ||
    	 (item != null && item.getType() == Material.GRILLED_PORK) ||
    	 (item != null && item.getType() == Material.PORK) ||
    	 (item != null && item.getType() == Material.COOKED_CHICKEN) ||
    	 (item != null && item.getType() == Material.RAW_CHICKEN) ||
    	 (item != null && item.getType() == Material.COOKED_BEEF) ||
    	 (item != null && item.getType() == Material.RAW_BEEF) ||
    	 (item != null && item.getType() == Material.GOLDEN_APPLE) ||
    	 (item != null && item.getType() == Material.GOLDEN_CARROT) ||
    	 (item != null && item.getType() == Material.POTATO_ITEM) ||
    	 (item != null && item.getType() == Material.CARROT_ITEM) ||
    	 (item != null && item.getType() == Material.PUMPKIN_SEEDS) ||
    	 (item != null && item.getType() == Material.MELON_SEEDS) ||
    	 (item != null && item.getType() == Material.SEEDS) ||
     	 (item != null && item.getType() == Material.WHEAT)) 
      {
          EntityType eventEntity = e.getRightClicked().getType();
          if (this.animalLimits.containsKey(eventEntity))
          {
            int limit = ((Integer)this.animalLimits.get(eventEntity)).intValue();
            boolean canBreed = canBreed(eventEntity, limit, player);
            if (!canBreed)
            {
                player.sendMessage(ChatColor.RED + this.breedingFailedMsg);
                e.setCancelled(true);
                
            }
          }
          }
      }
  
  @EventHandler
  public void onCreatureSpawnEvent(CreatureSpawnEvent event)
  {
    if ((event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) {
      if (this.animalLimits.containsKey(event.getEntityType()))
      {
        int limit = ((Integer)this.animalLimits.get(event.getEntityType())).intValue();
        if (!canBreed(event.getEntity(), limit)) {
          event.setCancelled(true);
          
        }
      }
    }
  }
  
  @EventHandler
  public void onVillagerSpawnEvent(CreatureSpawnEvent event)
  {
    if (this.villagerLimit > -1) {
      if ((event.getEntity() instanceof Villager)) {
        if (!canBreed(event.getEntity(), this.villagerLimit)) {
          event.setCancelled(true);
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
  
  public boolean canBreed(Entity eventEntity, int limit)
  {
    int count = 0;
    List<Entity> entities = eventEntity.getNearbyEntities(Integer.parseInt(this.area[0]), Integer.parseInt(this.area[2]), Integer.parseInt(this.area[1]));
    for (Entity entity : entities) {
      if (entity.getType() == eventEntity.getType()) {
        count++;
      }
    }
    if (count >= limit) {
      return false;
    }
    return true;
  }
}
