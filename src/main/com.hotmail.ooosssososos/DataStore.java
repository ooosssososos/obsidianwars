import me.sothatsit.selectionapi.SaveSelection;
import me.sothatsit.selectionapi.Selection;
import org.bukkit.Location;

/**
 * Created with IntelliJ IDEA.
 * User: oscar
 * Date: 7/7/13
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
/*
ID: ooossso1
LANG: JAVA
TASK: DataStore
 */
public class DataStore {
    /*
    Tower 1 : 0,1
    Tower 2 : 2,3
    Apothacary : 4,5
    Barracks : 6,7
    Refinery : 8,9
    Storage : 10,11
    Spawn : 12,13
     */
    SaveSelection[] RedTeam = new SaveSelection[7];
    SaveSelection[] BlueTeam = new SaveSelection[7];
    int[] RedTeamO = new int[7];
    int[] BlueTeamO = new int[7];
    boolean[] RedTeamB = {true,true,true,true,true,true,true};
    boolean[] BlueTeamB = {true,true,true,true,true,true,true};
}
