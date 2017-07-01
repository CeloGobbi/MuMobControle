package mushu949.com.br;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
{
  HashMap<EntityType, Integer> animalLimits = new HashMap<EntityType, Integer>();
  int villagerLimit;
  String[] area;
  
  public void onEnable()
  {
    saveDefaultConfig();
    
    String breedingFailedMsg = getConfig().getString("falha-na-reproducao", "Sua criação já atingiu numero maximo de mobs!");
    
    List<String> breedLimitList = getConfig().getStringList("limite-de-animais");
    if (breedLimitList.size() == 0)
    {
      getLogger().info("A lista de criações a serem controladas esta vazia!");
    }
    else
    {
      getLogger().info("Carregando animais e seus limites ...");
      for (String entry : breedLimitList)
      {
        String[] tempString = entry.split("=");
        int tempLimit = Integer.parseInt(tempString[1].trim());
		EntityType tempAnimal = EntityType.valueOf(tempString[0].toUpperCase());
        this.animalLimits.put(tempAnimal, Integer.valueOf(tempLimit));
      }
    }
    this.villagerLimit = getConfig().getInt("villager-limite", -1);
    
    String configArea = getConfig().getString("area-total", "16, 16, 256");
    this.area = configArea.split(", ");
    
    getLogger().info("Carregamento concluido!");
    
    new Limitador(this, this.animalLimits, this.villagerLimit, breedingFailedMsg, this.area);
    new ReguladorDeOvos(this, this.animalLimits, this.villagerLimit, breedingFailedMsg, this.area);
    getLogger().info("[MuAnimalControle] Plugin carregado com sucesso!");
  }
  
  public void onDisable()
  {
    getLogger().info("[MuAnimalControle] Foi encerrado com sucesso!");
  }
  
  
  public int getEntityCount(EntityType eventEntity, Player player)
  {
    int count = 0;
    
    List<Entity> entities = player.getNearbyEntities(Integer.parseInt(this.area[0]), Integer.parseInt(this.area[2]), Integer.parseInt(this.area[1]));
    for (Entity entity : entities) {
      if (entity.getType() == eventEntity) {
        count++;
      }
    }
    return count;
  }
}
