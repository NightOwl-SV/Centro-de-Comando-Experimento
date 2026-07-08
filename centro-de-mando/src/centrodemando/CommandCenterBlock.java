package centrodemando;

import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Building;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.meta.BuildVisibility;

public class CommandCenterBlock extends Block {

    public CommandCenterBlock(String name) {
        super(name);
        
        // Basic block properties
        size = 2;
        health = 220;
        solid = true;
        update = true;
        sync = true;
        hasItems = false;
        hasLiquids = false;
        hasPower = false;
        configurable = true;
        buildVisibility = BuildVisibility.shown;
        category = mindustry.type.Category.effect;
        
        // Build costs (same as original Command Center)
        requirements(mindustry.type.Category.effect, ItemStack.with(
            mindustry.content.Items.copper, 200,
            mindustry.content.Items.lead, 250,
            mindustry.content.Items.graphite, 100,
            mindustry.content.Items.silicon, 250
        ));
        
        // Tech tree research
        research = "ground-factory";
    }

    @Override
    public boolean canPlaceOn(mindustry.world.Tile tile, mindustry.game.Team team, int rotation) {
        return true;
    }

    @Override
    public void placed() {
        super.placed();
    }

    public class CommandCenterBuild extends Building {
        
        @Override
        public void update() {
            super.update();
            // The block itself doesn't need to do much
            // The AI controllers handle unit behavior
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.b(0); // version
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            read.b(); // version
        }
    }
}
