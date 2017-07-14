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
	  String[] area;
  
  public void onEnable()
  {
    saveDefaultConfig();

    String Falhanareproducao = getConfig().getString("falha-na-reproducao", "§6[§f§lMuMC§6] Sua criação já atingiu numero maximo de mobs!");
    String AvisoSPAWNER_EGG = getConfig().getString("AvisoSPAWNER-EGG", "§6[§f§lMuMC§6] O invocador não funcionara se o limite de mobs for atingido!");
    String prefixo = getConfig().getString("Prefixo-Do-Plugin", "§f[§cMu§aMobControle§f]");

    List<String> breedLimitList = getConfig().getStringList("limite-de-animais");
    if (breedLimitList.size() == 0)
    	{        
    	 getLogger().info("A lista de criações a serem controladas esta vazia!");
    	}
    else
    	{	
	  	getServer().getConsoleSender().sendMessage("§3Plugin: §6MuMobControle §2- §3Desenvolvedor: §6Mushu949 §2 §3Prefixo: ["+ prefixo +"§3]");
    	getServer().getConsoleSender().sendMessage("§3" + prefixo + " " +"§2Iniciando...");
    	getServer().getConsoleSender().sendMessage("§3" + prefixo + " " +"§eCarregando mobs e seus limites ...");

    		for (String entry : breedLimitList)
      	{
        String[] tempString = entry.split("=");
        int tempLimit = Integer.parseInt(tempString[1].trim());
        EntityType tempAnimal = EntityType.valueOf(tempString[0].toUpperCase());
        this.animalLimits.put(tempAnimal, Integer.valueOf(tempLimit));
      }
    }
    String configArea = getConfig().getString("area-total", "16, 16, 256");
    this.area = configArea.split(", ");
	getServer().getConsoleSender().sendMessage("§3" + prefixo + " " +"§2Carregamento concluido!");
    new Limitador(this, this.animalLimits, Falhanareproducao, this.area);
    new ReguladorDeOvos(this, this.animalLimits, AvisoSPAWNER_EGG, this.area);
	getServer().getConsoleSender().sendMessage("§3" + prefixo + " " +"§2Plugin carregado com sucesso!");
 }
  
  public void onDisable()
  {    	  	
	  	String prefixo = getConfig().getString("Prefixo-Do-Plugin", "§f[§cMu§aMobControle§f]");
	  	getServer().getConsoleSender().sendMessage("§3Plugin: §6MuMobControle §2- §3Desenvolvedor: §6Mushu949 §2 §3Prefixo: ["+ prefixo +"§3]");
		getServer().getConsoleSender().sendMessage("§3" + prefixo + " " +"§aPlugin desabilitado com sucesso!");
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
