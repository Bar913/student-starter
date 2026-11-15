package edu.starter;

public enum Reward {
    RECOVERY,
    POWER;

    public void applyTo(Fighter player) {
        switch (this) {
            case RECOVERY:
                player.sethp(player.gethp() + 25);
                System.out.println("Restored 20 HP!");
                break;
            case POWER:
                player.setResources(player.getResources() + 2);
                System.out.println("Gained +2 Resources!");
                break;
        }
    }
}