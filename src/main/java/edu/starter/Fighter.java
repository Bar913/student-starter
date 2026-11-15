package edu.starter;

public class Fighter {
    private static final double MAX_HEALTH = 100;
    private static final double MAX_RESOURCES = 10;
    private static final double RECOVER_ABILITY = 3;
    private static final double SPECIAL_COST = 3;
    private static final double SPECIAL_ABILITY = 15;
    private static final double DEFENSE_ABILITY = 5;

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
        return MAX_HEALTH;
    }

    public double getmax_resources() {
        return MAX_RESOURCES;
    }

    public double getpending_defense() {
        return pending_defense;
    }

    public double getrecover_abilty() {
        return RECOVER_ABILITY;
    }

    public double getspecial_ability() {
        return SPECIAL_ABILITY;
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
        pending_defense = DEFENSE_ABILITY;
    }

    public void cleardefense() {
        pending_defense = 0;
    }

    public void heal(double amount) {
        hp += amount;
        if (hp > MAX_HEALTH){
            hp = MAX_HEALTH;
        }
    }

    public void upgradeattack(double amount) {
        attack_ability += amount;
    }
    public boolean recover(){
        if (resources >= MAX_RESOURCES){
            return false;
        }
        resources += RECOVER_ABILITY;
        if (resources > MAX_RESOURCES){
            resources = MAX_RESOURCES;
        }
        return true;
    }

    public boolean special(Mob enemy) {
        if (resources < SPECIAL_COST){
            return false;
        }
        resources -= SPECIAL_COST;
        enemy.takedamage(getspecial_ability());
        return true;
    }

}