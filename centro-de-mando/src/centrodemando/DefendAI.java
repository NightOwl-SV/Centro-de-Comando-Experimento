package centrodemando;

import arc.math.geom.Position;
import arc.struct.Seq;
import mindustry.ai.types.AIController;
import mindustry.content.Blocks;
import mindustry.gen.Building;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.turrets.BaseTurret;

public class DefendAI extends AIController {

    @Override
    public void updateUnit() {
        if (unit == null) return;

        updateVisuals();
        
        // Find nearest defensive structure (walls first, then turrets, then any building)
        Building target = findDefensiveStructure();
        
        if (target != null) {
            // Move to the defensive structure
            float dist = unit.dst(target);
            if (dist > unit.type.range * 0.5f) {
                moveTo(target, unit.type.range * 0.3f);
            } else {
                // In position, look for enemies
                Teamc enemy = findNearbyEnemy();
                if (enemy != null) {
                    commandTarget(enemy);
                }
            }
        }
        
        faceTarget();
    }

    private Building findDefensiveStructure() {
        Seq<Building> buildings = unit.team.data().buildings;
        Building closest = null;
        float closestDist = Float.MAX_VALUE;
        
        for (Building build : buildings) {
            if (build == null || !build.isValid()) continue;
            
            float dist = unit.dst(build);
            int priority = getPriority(build);
            int closestPriority = closest != null ? getPriority(closest) : -1;
            
            if (priority > closestPriority || (priority == closestPriority && dist < closestDist)) {
                closest = build;
                closestDist = dist;
            }
        }
        
        return closest;
    }

    private int getPriority(Building build) {
        // Priority: Walls > Turrets > Other buildings
        if (build.block instanceof Wall) {
            return 3;
        } else if (build.block instanceof BaseTurret) {
            return 2;
        } else {
            return 1;
        }
    }

    private Teamc findNearbyEnemy() {
        return mindustry.entities.Units.closestEnemy(unit.team, unit.x, unit.y, unit.range() * 2f, 
            u -> u.checkTarget(unit.type.targetAir, unit.type.targetGround));
    }

    @Override
    public boolean shouldFire() {
        return target != null && unit.within(target, unit.range());
    }
}
