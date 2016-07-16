package io.github.teamdevintia.devathlon3.portal;

import io.github.teamdevintia.devathlon3.util.ReflectionUtil;
import net.minecraft.server.v1_10_R1.*;

import java.util.LinkedHashSet;

/**
 * Wizard entity, automatically looks at the nearest player
 */
public class WizardEntity extends EntityVillager {

    public WizardEntity(World world) {
        super(world);

        // clear pathfinding
        LinkedHashSet goalB = (LinkedHashSet) ReflectionUtil.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
        if (goalB != null) {
            goalB.clear();
        }
        LinkedHashSet goalC = (LinkedHashSet) ReflectionUtil.getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
        if (goalC != null) {
            goalC.clear();
        }
        LinkedHashSet targetB = (LinkedHashSet) ReflectionUtil.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
        if (targetB != null) {
            targetB.clear();
        }
        LinkedHashSet targetC = (LinkedHashSet) ReflectionUtil.getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
        if (targetC != null) {
            targetC.clear();
        }

        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 20.0F, 2F));
    }
}
