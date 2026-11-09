package edu.starter;

public class Mob {
    private static final double ATTACK_ABILITY = 10;
    private double max_hp;
    private double hp;

    public Mob(double hp){
        this.max_hp = hp; 
        this.hp = hp;
    }

    public double gethp() {
        if (hp < 0){
            return 0;
        }
        return hp;
    }

    public double getattack_ability() {
        return ATTACK_ABILITY;
    }

    public double getmax_hp() {
        return max_hp;
    }

    public boolean isalive() {
        return (hp > 0);
    }

    public void takedamage(double damage) {
        hp -= damage;
        if (hp < 0){
            hp = 0;
        }
    }

    public void attack(Fighter player) {
        player.takedamage(getattack_ability());
    }
}