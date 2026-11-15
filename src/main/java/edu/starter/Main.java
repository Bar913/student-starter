package edu.starter;
import java.util.Scanner;

public final class Main{

    /**
     * Runs the game with two encounters against mobs, allowing the player to choose actions and rewards.
     *
     * @post if encounter 1 is lost: prints "game lost", no reward offered,
     *       no encounter 2
     * @post if encounter 1 is won: exactly one valid reward is applied to the
     *       same player object, then encounter 2 starts against a fresh 45 HP Mob
     * @post invalid reward input changes no state and is asked again
     */

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Fighter player = new Fighter(100, 5);

        Mob enemy1 = new Mob(30);
        boolean won_encounter1 = encounter(player, enemy1, 1, scanner);

        if (won_encounter1 == false){
            System.out.println("game lost");
            scanner.close();
            return;
        }

        Reward chosen = null;
        while (chosen == null){
            System.out.println("choose one reward: recovery, power");
            String input = scanner.nextLine();

            if (input.equals("recovery")){
                chosen = Reward.RECOVERY;
            }
            else if (input.equals("power")){
                chosen = Reward.POWER;
            }
            else{
                System.out.println("invalid reward");
            }
        }
        chosen.applyTo(player);

        Mob enemy2 = new Mob(45);
        boolean won_encounter2 = encounter(player, enemy2, 2, scanner);

        if (won_encounter2 == true){
            System.out.println("game won");
        }
        else{
            System.out.println("game lost");
        }

        scanner.close();
    }

    /**
     * Runs an encounter between the player and the enemy, allowing the player to choose actions until one of them is defeated.
     *
     * @pre player != null && enemy != null && scanner != null
     * @pre player.isalive() && enemy.isalive()
     * 
     * @post an illegal action prints "invalid action", consumes no turn and
     *       triggers no enemy counterattack
     * @post pending defense is equal to 0 when the encounter starts
     */

        public static boolean encounter(Fighter player, Mob enemy, int number, Scanner scanner){

        player.cleardefense();

        while (enemy.isalive() && player.isalive()){

            System.out.println("num encounter = " + number);
            System.out.println("player hpsp = " + player.gethp());
            System.out.println("player resources = " + player.getresources());
            System.out.println("player attack = " + player.getattack_ability());
            System.out.println("enemy hp = " + enemy.gethp());

            System.out.println("pls choose: attack, defend, special, recover");
            String action = scanner.nextLine();

            boolean legal = true;

            if (action.equals("attack")){
                player.attack(enemy);
            }

            else if (action.equals("defend")){
                player.defend();
            }

            else if (action.equals("special")){
                legal = player.special(enemy);
            }

            else if (action.equals("recover")){
                legal = player.recover();
            }

            else{
                legal = false;
            }

            if (legal == false){
                System.out.println("invalid action");
                continue;
            }

            if (enemy.isalive()){
                enemy.attack(player);
            }
        }

        if (player.isalive()){
            System.out.println("encounter won");
            return true;
        }
        else{
            System.out.println("encounter lost");
            return false;
        }
    }
}