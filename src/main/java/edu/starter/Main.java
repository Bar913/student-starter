package edu.starter;
import java.util.Scanner;

public final class Main{

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

        Mob enemy2 = new Mob(45);
        boolean won_encounter2 = encounter(player, enemy2, 2, scanner);

        if (won_encounter2 == true){
            System.out.println("game won");
        }
        else{
            System.out.println("game won");
        }

        scanner.close();
    }

        public static boolean encounter(Fighter player, Mob enemy, int number, Scanner scanner){

        player.cleardefense();

        while (enemy.isalive() && player.isalive()){

                    System.out.println("num encounter " + number);
            System.out.println("player hp=" + player.gethp());
            System.out.println("player resources=" + player.getresources());
            System.out.println("enemy hp=" + enemy.gethp());

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