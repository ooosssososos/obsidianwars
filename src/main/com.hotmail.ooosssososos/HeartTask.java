import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created with IntelliJ IDEA.
 * User: oscar
 * Date: 7/10/13
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
/*
ID: ooossso1
LANG: JAVA
TASK: HeartTask
 */
public class HeartTask extends BukkitRunnable{
    obsidianwars plugin;
    public HeartTask(obsidianwars plugin){
    this.plugin = plugin;
    }
    public void run(){
        plugin.count();
        for(int i = 0; i < 7; i ++){
            if(obsidianwars.data.BlueTeamO[i] <=0)obsidianwars.data.BlueTeamB[i] = false;
            else  obsidianwars.data.BlueTeamB[i] = true;
            if(obsidianwars.data.RedTeamO[i] <=0)obsidianwars.data.RedTeamB[i] = false;
            else  obsidianwars.data.RedTeamB[i] = true;
        }
        plugin.check();
    }
}
