package mmr.maidmodredo.entity.ai;

import mmr.maidmodredo.entity.boss.TrinityEntity;
import mmr.maidmodredo.network.MaidPacketHandler;

public class JumpToAttackTargetGoal extends JumpToTargetGoal {
    private final TrinityEntity entity;

    public JumpToAttackTargetGoal(TrinityEntity creature, int chance, double maxDistance) {
        super(creature, chance, maxDistance);
        this.entity = creature;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return this.entity.getAttackTarget() != null && this.entity.getNavigator().noPath() && super.shouldExecute();
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        MaidPacketHandler.animationModel(this.entity, TrinityEntity.JUMP_ANIMATION);
    }
}