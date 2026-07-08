package centrodemando;

import arc.func.Func;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.AIController;
import mindustry.gen.Unit;

public class ModCommands {

    public static UnitCommand attackCommand;
    public static UnitCommand defendCommand;
    public static UnitCommand retreatCommand;

    public static void load() {
        attackCommand = new UnitCommand("cc-attack", "right", (Func<Unit, AIController>) unit -> new AttackAI());
        
        defendCommand = new UnitCommand("cc-defend", "modeSurvival", (Func<Unit, AIController>) unit -> new DefendAI());
        
        retreatCommand = new UnitCommand("cc-retreat", "up", (Func<Unit, AIController>) unit -> new RetreatAI());
    }
}
