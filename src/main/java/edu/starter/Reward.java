package edu.starter;

public enum Reward {
    RECOVERY,
    POWER;

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