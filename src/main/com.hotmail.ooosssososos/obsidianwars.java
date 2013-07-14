import me.sothatsit.selectionapi.SaveSelection;
import me.sothatsit.selectionapi.Selection;
import me.sothatsit.selectionapi.Selector;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created with IntelliJ IDEA.
 * User: oscar
 * Date: 7/7/13
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
/*
ID: ooossso1
LANG: JAVA
TASK: obsidianwars
 */
public class obsidianwars extends JavaPlugin implements Listener{
    public static DataStore data = new DataStore();
    public Player selector;
    Selector selectorP;
    public static boolean ReadyToStart = false;
    public int stage = 0;
    public int counter;
    public void onEnable(){
        counter = getConfig().getInt("Counter");
        selectorP = new Selector(this, 352);
        getServer().getPluginManager().registerEvents(this,this);

        for(int count = 0;count < 7; count++){
            this.getLogger().severe("DDDDD" + count);
            Location T = new Location(Bukkit.getWorld("world"),
                    getConfig().getDouble("Locations.Chests."  + count + ".T.X"),
                    getConfig().getDouble("Locations.Chests."  + count + ".T.Y"),
                    getConfig().getDouble("Locations.Chests."  + count + ".T.Z"));
            Location B = new Location(Bukkit.getWorld("world"),
                        getConfig().getDouble("Locations.Chests."  + count + ".B.X"),
                        getConfig().getDouble("Locations.Chests."  + count + ".B.Y"),
                        getConfig().getDouble("Locations.Chests."  + count + ".B.Z"));
            Location C = new Location(Bukkit.getWorld("world"),
                    getConfig().getDouble("Locations.Chests."  + count+7 + ".T.X"),
                    getConfig().getDouble("Locations.Chests."  + count+7 + ".T.Y"),
                    getConfig().getDouble("Locations.Chests."  + count+7 + ".T.Z"));
            Location D = new Location(Bukkit.getWorld("world"),
                    getConfig().getDouble("Locations.Chests."  + count+7 + ".B.X"),
                    getConfig().getDouble("Locations.Chests."  + count+7 + ".B.Y"),
                    getConfig().getDouble("Locations.Chests."  + count+7 + ".B.Z"));
            Selection temp;
            temp = new Selection("PH");
            temp.setPoint1NoChecks(T);
            temp.setPoint2NoChecks(B);
            data.BlueTeam[count]= new SaveSelection(temp);
            temp.setPoint1NoChecks(C);
            temp.setPoint2NoChecks(D);
            data.RedTeam[count]= new SaveSelection(temp);
        }
        ReadyToStart = true;
    }
    public void onDisable(){
        save();
        this.saveConfig();
    }
    public void save(){
        for(int counter = 0; counter < 7; counter ++){
        getConfig().set("Locations.Chests."+ counter + ".T.X", data.BlueTeam[counter].maxX );
        getConfig().set("Locations.Chests."+ counter + ".T.Y", data.BlueTeam[counter].maxY );
        getConfig().set("Locations.Chests."+ counter + ".T.Z", data.BlueTeam[counter].maxZ );
        getConfig().set("Locations.Chests."+ counter + ".B.X", data.BlueTeam[counter].minX );
        getConfig().set("Locations.Chests."+ counter + ".B.Y", data.BlueTeam[counter].minY );
        getConfig().set("Locations.Chests."+ counter + ".B.Z", data.BlueTeam[counter].minZ );
        
        getConfig().set("Locations.Chests."+ counter+7 + ".T.X", data.RedTeam[counter].maxX );
        getConfig().set("Locations.Chests."+ counter+7 + ".T.Y", data.RedTeam[counter].maxY );
        getConfig().set("Locations.Chests."+ counter+7 + ".T.Z", data.RedTeam[counter].maxZ );
        getConfig().set("Locations.Chests."+ counter+7 + ".B.X", data.RedTeam[counter].minX );
        getConfig().set("Locations.Chests."+ counter+7 + ".B.Y", data.RedTeam[counter].minY );
        getConfig().set("Locations.Chests."+ counter+7 + ".B.Z", data.RedTeam[counter].minZ );
        }
        getConfig().set("Counter", 7);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args[0].equalsIgnoreCase("setup")){
            selector = p;
            stage = 0;
            p.sendMessage(stage + "");
        }
        if(args[0].equalsIgnoreCase("next")){
            if(stage < 7){
                data.BlueTeam[stage] = new SaveSelection(selectorP.getPlayerSelection(p.getName()));
            }else if(stage < 14){
                data.RedTeam[stage-7] = new SaveSelection(selectorP.getPlayerSelection(p.getName()));
            }else if(stage == 14){
                ReadyToStart = true;
                stage = 0;
                p.sendMessage("Setup complete");
                count();
            }
            stage ++;

        }if(args[0].equalsIgnoreCase("start")){
            if(ReadyToStart == true){
            new HeartTask(this).runTaskTimer(this,20,20);
            }

        }
        return true;
    }


    public void count(){
        for(int i = 0; i < 7; i ++){
            data.BlueTeamO[i]=0;
            data.RedTeamO[i]=0;
            for(int x = data.BlueTeam[i].minX; x <= data.BlueTeam[i].maxX;x++){
                for(int y = data.BlueTeam[i].minY; y <= data.BlueTeam[i].maxY;y++){
                    for(int z = data.BlueTeam[i].minZ; z <= data.BlueTeam[i].maxZ;z++){
                        if(Bukkit.getWorld(data.BlueTeam[0].world).getBlockAt(x,y,z).getType().equals(Material.OBSIDIAN)){
                            data.BlueTeamO[i]++;
                        }
                    }
                }
            }
            for(int x = data.RedTeam[i].minX; x <= data.RedTeam[i].maxX;x++){
                for(int y = data.RedTeam[i].minY; y <= data.RedTeam[i].maxY;y++){
                    for(int z = data.RedTeam[i].minZ; z <= data.RedTeam[i].maxZ;z++){
                        if(Bukkit.getWorld(data.RedTeam[0].world).getBlockAt(x,y,z).getType().equals(Material.OBSIDIAN)){
                            data.RedTeamO[i]++;
                        }
                    }

      }
            }
            Bukkit.broadcastMessage(data.BlueTeamO[i] + "");
            Bukkit.broadcastMessage(data.RedTeamO[i] + "");
        }
    }
    public void check(){
        for(int i = 0; i < 7; i ++){
            if(data.BlueTeamB[i] != false){
                this.getServer().getWorld("world").getBlockAt(data.BlueTeam[i].minX,data.BlueTeam[i].minY-10,data.BlueTeam[i].minZ).setType(Material.BEDROCK);
            }else{
                this.getServer().getWorld("world").getBlockAt(data.BlueTeam[i].minX,data.BlueTeam[i].minY-10,data.BlueTeam[i].minZ).setType(Material.AIR);
            }
            if(data.RedTeamB[i] != false){
                this.getServer().getWorld("world").getBlockAt(data.RedTeam[i].minX,data.RedTeam[i].minY-10,data.RedTeam[i].minZ).setType(Material.BEDROCK);
            }else{
                this.getServer().getWorld("world").getBlockAt(data.RedTeam[i].minX,data.RedTeam[i].minY-10,data.RedTeam[i].minZ).setType(Material.AIR);
            }
        }
    }
}
