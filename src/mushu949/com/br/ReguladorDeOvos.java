package mushu949.com.br;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class ReguladorDeOvos implements Listener
{
private Main plugin;
private HashMap<EntityType, Integer> animalLimits;
@SuppressWarnings("unused")
private String breedingFailedMsg;
private String[] area;

public ReguladorDeOvos(Main instance, HashMap<EntityType, Integer> animalLimits,String breedingFailedMsg, String[] area)
{
  this.plugin = instance;
  this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
  this.breedingFailedMsg = breedingFailedMsg;
  this.animalLimits = animalLimits;
  this.area = area;
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