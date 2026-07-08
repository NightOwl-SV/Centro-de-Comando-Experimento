package centrodemando;

import arc.util.Log;
import mindustry.mod.Mod;
import mindustry.content.Blocks;
import mindustry.type.ItemStack;

public class CentroDeMandoMod extends Mod {

    public CentroDeMandoMod() {
        Log.info("Loaded Centro de Mando mod.");
    }

    @Override
    public void loadContent() {
        Log.info("Loading Centro de Mando content...");
        
        // Register custom commands
        ModCommands.load();
        
        // Register the Command Center block
        new CommandCenterBlock("command-center");
        
        Log.info("Centro de Mando mod loaded successfully!");
    }
}
