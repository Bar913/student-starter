package edu.starter;

/**
 * Mob class represents an enemy character in the game.
 *
 * Invariants:
 *   0 <= hp <= max_hp
 */

public class Mob {
    private double attack_ability = 10;
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
        return attack_ability;
    }

    public double getmax_hp() {
        return max_hp;
    }

    public boolean isalive() {
        return (hp > 0);
    }

    /**
     * @pre damage >= 0
     * @post hp == max(0, old hp - damage)
     */

    public void takedamage(double damage) {
        hp -= damage;
        if (hp < 0){
            hp = 0;
        }
    }

    /**
     * @pre player != null
     * @post player received attack_ability damage, reduced by its pending defense
     */

    public void attack(Fighter player) {
        player.takedamage(getattack_ability());
    }
}