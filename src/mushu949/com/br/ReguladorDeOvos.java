package mushu949.com.br;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.Herbystar.TTA.TTA_Methods;

public class ReguladorDeOvos
  implements Listener
{
  private Main plugin;
  private HashMap<EntityType, Integer> animalLimits;
  private String AvisoSPAWNER_EGG;
  private String[] area;
  
  public ReguladorDeOvos(Main instance, HashMap<EntityType, Integer> animalLimits, String AvisoSPAWNER_EGG, String[] area)
  {
    this.plugin = instance;
    this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    this.AvisoSPAWNER_EGG = AvisoSPAWNER_EGG;
    this.animalLimits = animalLimits;
    this.area = area;
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent e)
  {
    Player player = e.getPlayer();
    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
    ItemStack item = player.getInventory().getItemInMainHand();
      if (((item != null) && (item.getType() == Material.MONSTER_EGG))){
        TTA_Methods.sendActionBar(player, this.AvisoSPAWNER_EGG, 60);

      }
    }
  }

  @EventHandler
  public void onInteract2(PlayerInteractEvent e)
  {
    Player player = e.getPlayer();
    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
    ItemStack item = player.getInventory().getItemInOffHand();
      if (((item != null) && (item.getType() == Material.MONSTER_EGG))){
        TTA_Methods.sendActionBar(player, this.AvisoSPAWNER_EGG, 60);
      }
    }
  }
  
  @EventHandler
  public void onCreatureSpawnEvent(CreatureSpawnEvent event)
  {
    if (((event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.EGG) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.BUILD_IRONGOLEM) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.BUILD_SNOWMAN) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.BUILD_WITHER) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CHUNK_GEN) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DISPENSE_EGG) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CURED) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.DEFAULT) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.ENDER_PEARL) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.INFECTION) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.JOCKEY) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.LIGHTNING) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.MOUNT) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NETHER_PORTAL) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.OCELOT_BABY) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.REINFORCEMENTS) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SHOULDER_ENTITY) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SLIME_SPLIT) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.TRAP) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.VILLAGE_DEFENSE) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.VILLAGE_INVASION) || 
      (event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)) && 
      (this.animalLimits.containsKey(event.getEntityType())))
    {
      int limit = ((Integer)this.animalLimits.get(event.getEntityType())).intValue();
      if (!canBreed(event.getEntity(), limit)) {
        event.setCancelled(true);
      }
    }
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
