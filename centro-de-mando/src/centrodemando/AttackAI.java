package centrodemando;

import arc.math.geom.Position;
import arc.util.Time;
import mindustry.ai.types.AIController;
import mindustry.entities.Units;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;

public class AttackAI extends AIController {

    @Override
    public void updateUnit() {
        if (unit == null) return;

        updateVisuals();
        
        // Find the closest enemy target
        Teamc target = findTarget(unit.x, unit.y, unit.range() * 3f, unit.type.targetAir, unit.type.targetGround);
        
        if (target != null) {
            // Attack the target
            commandTarget(target);
            
            // Move towards target if not in range
            if (!unit.within(target, unit.range())) {
                moveTo(target, unit.type.range * 0.8f);
            }
        } else {
            // No target found, move to nearest enemy core
            Teamc enemyCore = findEnemyCore();
            if (enemyCore != null) {
                moveTo(enemyCore, unit.type.range);
            }
        }
        
        faceTarget();
    }

    private Teamc findEnemyCore() {
        return Units.closestEnemy(unit.team, unit.x, unit.y, Float.MAX_VALUE, u -> u instanceof Teamc tc && tc.isBuilding() && tc.block() != null && tc.block().name.contains("core"));
    }

    @Override
    public Teamc findTarget(float x, float y, float range, boolean air, boolean ground) {
        // Find enemy units and buildings
        Teamc enemy = Units.closestEnemy(unit.team, x, y, range, u -> u.checkTarget(air, ground));
        if (enemy != null) return enemy;
        
        // Find enemy buildings
        return Units.closestEnemy(unit.team, x, y, range, u -> u.isBuilding());
    }

    @Override
    public boolean shouldFire() {
        return target != null && unit.within(target, unit.range());
    }
}
