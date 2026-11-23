package edu.starter;

/** fighter class represents a player character in the game.
 * It has attributes for health, resources, attack ability, and defense ability.
 * 
 * The class provides methods for taking damage, attacking enemies, defending, healing, upgrading attack, recovering resources, and performing special attacks.
 * Invariants:
 *   0 <= hp <= max_health
 *   0 <= resources <= max_resources
 *   0 <= pending_defense
*/

public abstract class Fighter {
    private double max_health = 100;
    private double max_resources = 10;
    private double recover_ability = 3;
    private double special_cost = 3;
    private double special_ability = 15;
    private double defense_ability = 5;

    private double attack_ability = 10;
    private double pending_defense;
    private double hp;
    private double resources;

    public Fighter(double hp, double resources){
        this.hp = hp;
        this.resources = resources;
        this.pending_defense = 0;
    }

    public double gethp() {
        return hp;
    }

    public double getresources() {
        return resources;
    }

    public double getattack_ability() {
        return attack_ability;
    }

    public double getmax_health() {
        return max_health;
    }

    public double getmax_resources() {
        return max_resources;
    }

    public double getpending_defense() {
        return pending_defense;
    }

    public double getrecover_abilty() {
        return recover_ability;
    }

    public double getspecial_ability() {
        return special_ability;
    }

    public boolean isalive() {
        return hp > 0;
    }

    public void takedamage(double damage) {
        damage -= pending_defense;
        if (damage < 0){
            damage = 0;
        }
        pending_defense = 0;
        hp -= damage;
        if (hp < 0){
            hp = 0;
        }
    }

    public void attack(Mob enemy) {
        enemy.takedamage(attack_ability);
    }

    public void defend() {
        pending_defense = defense_ability;
    }

    public void cleardefense() {
        pending_defense = 0;
    }

    /**
     * Restores health
     *
     * @pre amount > 0
     * @post hp == min(max_health, old hp + amount)
     */
    public void heal(double amount) {
        hp += amount;
        if (hp > max_health){
            hp = max_health;
        }
    }

    /**
     * Increases attack damage
     *
     * @pre amount > 0
     * @post attack_ability == old attack_ability + amount
     */
    public void upgradeattack(double amount) {
        attack_ability += amount;
    }

    /**
     * Recovers resources
     *
     * @post if resources < max_resources: returns true and
     *       resources = min(max_resources, (old resources + recover_ability))
     * @post else: returns false and
     *       resources = max_resources
     */
    public boolean recover(){
        if (resources >= max_resources){
            resources = max_resources;
            return false;
        }
        resources += recover_abilty;
        if (resources > max_resources){
            resources = max_resources;
        }
        return true;
    }

    /**
     * Uses the special attack on the given enemy
     *
     * @pre enemy != null
     * @post if resources >= special_cost: returns true, resources decreased by
     *       exactly special_cost, and the enemy took special_ability damage
     * @post else: returns false and
     * no state changed
     */
    public boolean special(Mob enemy) {
        if (resources < special_cost){
            return false;
        }
        resources -= special_cost;
        enemy.takedamage(getspecial_ability());
        return true;
    }

}