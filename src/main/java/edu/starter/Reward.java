package edu.starter;

/**
 * Reward enum represents the possible rewards a player can choose after winning an encounter.
 */

public enum Reward {
    RECOVERY,
    POWER;

    /**
     * Applies the reward to the given player, modifying its state according to the reward type.
     *
     * @pre player != null
     * @pre the reward was not already applied in this adventure
     *      (the caller, Main, guarantees this)
     * 
     * @post RECOVERY: hp == min(MAX_HEALTH, old hp + 25);
     * @post POWER: attack == old attack + 2;
     */

    public void applyTo(Fighter player) {
        switch (this) {
            case RECOVERY:
                player.heal(25);
                System.out.println("restored 25 hp (max) to fighter");
                break;
            case POWER:
                player.upgradeattack(2);
                System.out.println("fighter gained +2 to basic attack");
                break;
        }
    }
}