package centrodemando;

import arc.math.geom.Position;
import mindustry.ai.types.AIController;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.world.blocks.storage.CoreBlock;

public class RetreatAI extends AIController {

    @Override
    public void updateUnit() {
        if (unit == null) return;

        updateVisuals();
        
        // Find the nearest core
        Building core = findNearestCore();
        
        if (core != null) {
            // Move to the core
            float dist = unit.dst(core);
            if (dist > core.block.size * 4f) {
                // Still far, move closer
                moveTo(core, core.block.size * 2f);
            } else {
                // Close enough, stop and defend if needed
                unit.movePref(0, 0);
                
                // Look for nearby enemies while retreating
                mindustry.gen.Teamc enemy = mindustry.entities.Units.closestEnemy(unit.team, unit.x, unit.y, unit.range() * 2f, 
                    u -> u.checkTarget(unit.type.targetAir, unit.type.targetGround));
                if (enemy != null) {
                    commandTarget(enemy);
                }
            }
        }
        
        faceTarget();
    }

    private Building findNearestCore() {
        Building closest = null;
        float closestDist = Float.MAX_VALUE;
        
        for (Building build : unit.team.data().buildings) {
            if (build == null || !build.isValid() || !(build.block instanceof CoreBlock)) continue;
            
            float dist = unit.dst(build);
            if (dist < closestDist) {
                closest = build;
                closestDist = dist;
            }
        }
        
        return closest;
    }

    @Override
    public boolean shouldFire() {
        return target != null && unit.within(target, unit.range());
    }
}
