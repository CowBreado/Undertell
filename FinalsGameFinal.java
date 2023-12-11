

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    //INVENTORY HASHMAP FOR STORING STUFF BOTH STRING AND INT
    static HashMap<String, Integer> inventory = new HashMap();

    //SCANNER WAS SET TO STATIC IN ORDER TO AVOID REDUNDANT DECLARATION ON DIFFERENT METHODS.
    static Scanner input = new Scanner(System.in);

    //SET TWO BOOLEAN VARIABLES AS STATIC IN ORDER TO RETAIN THE VALUE IT BECOMES AFTER THE METHOD.
    static boolean isPrepared;
    static boolean isDefend;

    public static void main(String[] args) {

  
             System.out.println("""
       █    ██  ███▄    █ ▓█████▄ ▓█████  ██▀███  ▄▄▄█████▓▓█████  ██▓     ██▓       
 ██  ▓██▒ ██ ▀█   █ ▒██▀ ██▌▓█   ▀ ▓██ ▒ ██▒▓  ██▒ ▓▒▓█   ▀ ▓██▒    ▓██▒       
▓██  ▒██░▓██  ▀█ ██▒░██   █▌▒███   ▓██ ░▄█ ▒▒ ▓██░ ▒░▒███   ▒██░    ▒██░       
▓▓█  ░██░▓██▒  ▐▌██▒░▓█▄   ▌▒▓█  ▄ ▒██▀▀█▄  ░ ▓██▓ ░ ▒▓█  ▄ ▒██░    ▒██░       
▒▒█████▓ ▒██░   ▓██░░▒████▓ ░▒████▒░██▓ ▒██▒  ▒██▒ ░ ░▒████▒░██████▒░██████▒   
░▒▓▒ ▒ ▒ ░ ▒░   ▒ ▒  ▒▒▓  ▒ ░░ ▒░ ░░ ▒▓ ░▒▓░  ▒ ░░   ░░ ▒░ ░░ ▒░▓  ░░ ▒░▓  ░   
░░▒░ ░ ░ ░ ░░   ░ ▒░ ░ ▒  ▒  ░ ░  ░  ░▒ ░ ▒░    ░     ░ ░  ░░ ░ ▒  ░░ ░ ▒  ░   
 ░░░ ░ ░    ░   ░ ░  ░ ░  ░    ░     ░░   ░   ░         ░     ░ ░     ░ ░      
   ░              ░    ░       ░  ░   ░                 ░  ░    ░  ░    ░  ░   
                     ░                                                         
      """);

        
        //INITIALIZED AN OBJECT OF CLASS HERO NAMED "PLAYER" WHICH WILL BE USED FOR: INVENTORY, IDENTIFYING THE PLAYER, FIGHT SEQUENCES.
        System.out.println("Enter The Name Of Player");
        String name = input.nextLine();
        Hero player = new Hero(name);

        //THIS WILL SERVE AS THE MAIN INTERFACE/MAIN MENU OF THE GAME
        Menu(player);
        System.out.println("You found something on the ground. \npick it up? (1: Yes, 2: No).");
        String ch = input.nextLine();

        if (ch.equalsIgnoreCase("1")) {
            String item = "bean";
            inventory.put(item.toLowerCase(), 1);
            System.out.println("Bean is a consumable item that restores hp when consumed.");
            //INVENTORY.PUT IS USED IN ORDER TO ADD VALUES (TWO VALUES WITH SPECIFIED DATA TYPES FROM HASHMAP INITIALIZATION) 
            //INVENTORY.GET IS USED TO GET THE ASSOCIATED VALUE, FOR EXAMPLE: (STRING, ASSOCIATED VALUE)
            System.out.println("You picked up: " + item + " x" + inventory.get(item));
        } else {
            System.out.println("You decided not to pick it up.");
        }
        System.out.println("You see some apples on a table, pick it up? \npick it up? (1: Yes, 2: No).");
        ch = input.nextLine();
        if (ch.equalsIgnoreCase("1")) {
            String item = "apple";
            inventory.put(item.toLowerCase(), 5);
            System.out.println("You picked up: " + item + " x" + inventory.get(item));
            System.out.println("Apple is a consumable item that restores hp when consumed.");
        } else {
            System.out.println("You decided not to pick it up.");
        }
        Menu(player);
        System.out.println();
        //THIS WILL INITIALIZE THE INTRODUCTION/STORY AND THE FIRST FIGHT SEQUENCE.
        System.out.println("The game will begin now.");
        exploreStart(player);

        //IF DURING THE FIGHT SEQUENCE, THE PLAYER'S HP DROPS TO 0, IT WILL BE RETURNED ALL THE WAY TO MAIN METHOD TO HERE.
        //THIS WILL STOP THE PROGRAM, INITIATING A "GAME OVEr" SCREEN BEFORE PROGRAM TERMINATES.
        if (player.hp < 0) {
            gameOver(player);
            return;
        }

        System.out.println();
        Menu(player);
        System.out.println("Part 2 of the story will now begin...");

        exploreStart2(player);

        if (player.hp < 0) {
            gameOver(player);
            return;
        }

        System.out.println("A final moment of respite before the end.");
        System.out.println();
        Menu(player);
        System.out.println();
        isPrepared = false;
        System.out.println("Part 3 of the story will now begin...");
        exploreStart3(player);

        if (player.hp < 0) {
            gameOver(player);
            return;
        }
        System.out.println("...");
        Enter();
        System.out.println("Did you make it, " + player.name + "?");
        System.out.println("If yes, you truly are a wonderful player.");
        Enter();
        System.out.println("Thank you for playing :)");
        System.out.println("This took years to do");
        Enter();
        System.out.println("Goodbye! :)");

    }

    //MAIN METHOD END
    //GAME OVER METHOD
    public static void gameOver(Hero player) {
        System.out.println(player.name + "'s hp dropped to " + player.hp + " and has fallen. \n" + "GAME OVER");
        Enter();
        System.out.println("\033[H\033[2J");
    System.out.println("""   ▄████  ▄▄▄       ███▄ ▄███▓▓█████     ▒█████   ██▒   █▓▓█████  ██▀███  
 ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀    ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒
▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███      ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒
░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄  
░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒
 ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░
  ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░
░ ░   ░   ░   ▒   ░      ░      ░      ░ ░ ░ ▒       ░░     ░     ░░   ░ 
      ░       ░  ░       ░      ░  ░       ░ ░        ░     ░  ░   ░     
                                                     ░                    """);
        
    }

    //INTRODUCTORY PART OF THE GAME
    public static void exploreStart(Hero player) {
        boolean skipped;
        System.out.println("NOTICE: if you wish to skip the tutorial, write \"skip\" \nPress ENTER to continue the tutorial.");
        String skip = input.nextLine();
        if (skip.equalsIgnoreCase("skip")) {
            System.out.println("You skipped the tutorial.");
            skipped = true;
        } else {
            skipped = false;
        }

        while (!skipped) {
            System.out.println("Tutorial: You are " + player.name + " and was tasked to go gather some lumber.");
            System.out.println("Tutorial: However, as you went to the mountain, you were struck with an unfortunate event.");
            System.out.println("As you stepped on the snowy surface of the mountain. You suddenly fell..?");
            Enter();
            System.out.println("You woke up feeling nauseous. You look at your surroundings, you're in a cavern.");
            System.out.println("You looked up to see where you have fallen from. It's a miracle you somehow survived the fall.");
            Enter();
            System.out.println("...You hear some odd rattling.");
            System.out.println("You begin to shake as you see a deathly figure emerge from within the darkness.");
            Enter();
            System.out.println("You immediately grabbed your wood-cutting axe that fell along with you");
            System.out.println("The figure dashed towards you!");

            Enter();
            System.out.println("Since this is your first time having a fight. A brief introduction will be provided.");
            System.out.println("During a fight you have 4 options:");
            System.out.println("1, Fight: This is used to deal damage against the enemy. The damage is dependent on your character's attack status. There's also a chance of dealing critical damage.");
            //fight, defend, flee, use item.
            System.out.println("2, Defend: a tricky move. It uses the same concept as \"parrying\", if you believe that the enemy will be launching a heavy hit on his turn, use this to reduce damage taken.");
            System.out.println("3, Use Item: this option will allow you to use items that are in your inventory. However, this also ends your current turn. Use it wisely.");
            System.out.println("4, Flee: If things go south and you believe it is certain death. Flee. However, there's a chance that fleeing may be unsuccessful and puts you vulnerable on the enemy's turn.");
            System.out.println("4, Flee: IMPORTANT: successful flee will only bring you to Menu, once you explore, you'll be brought back to the previous fight until you finish it.");
            System.out.println("That covers the basics of the game mechanics. Survive. See what lies within the darkness.");
            Enter();
            skipped = true;

        }

        fight(player);
    }

    public static void exploreStart2(Hero player) {

        System.out.println("You continue to venture within the cavern.");
        Enter();
        System.out.println("As you walked through the cavern, you noticed something glowing within a room-like section of the cavern.");
        System.out.println("You think hard to yourself, and decides: ");
        Enter();
        boolean chosen = false;
        while (!chosen) {
            System.out.println("1: Check it out, 2: Avoid it as it could be a trap.");
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("1")) {
                System.out.println("You decided to see the origin of the light.");
                treasureRoom(player);
                chosen = true;

            } else if (choice.equalsIgnoreCase("2")) {
                System.out.println("You decided to refrain from approaching it.");
                chosen = true;

            } else {
                System.out.println("Invalid Input. Please input the correct number.");

            }

        }
        System.out.println();
        System.out.println("You continue to traverse through the cavern.");
        System.out.println("When suddenly, the wall collapses and an Armored Skeleton appeared!");
        ArmoredSkeleton heavyBones = new ArmoredSkeleton();
        fight2(player);
        return;
    }

    public static void treasureRoom(Hero player) {
        System.out.println("You carefully followed through the room-like section and found... an odd glowing chest.");
        System.out.println("You looked at the chest expectantly, what will you do?");
        boolean chosen = false;
        while (!chosen) {
            System.out.println("Open it? \n 1: Yes, 2: No");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("1")) {
                System.out.println();
                System.out.println("You decided to approach the chest and open it. As you opened the chest, you feel a hot burning sensation on your right waist.\nYou've been hit by a spike trap! Lost: 80 Hp!");
                player.hp -= 80;
                if (player.hp < 0) {
                    gameOver(player);
                    System.exit(0);
                }
                String item = "goldenapple";
                inventory.put(item, 1);
                System.out.println("Injured, but you successfully grabbed the item and obtained: Golden Apple x1");
                System.out.println("Consuming it will fully heal and permanently increase your max hp by 50!");
                return;
            } else if (choice.equalsIgnoreCase("2")) {
                System.out.println("You decided to stop at the last minute as it was too risky.");
                chosen = false;
                return;
            } else {
                System.out.println("Invalid Input, please input a valid number.");
            }
        }
    }

    
    public static void exploreStart3(Hero player) {

        System.out.println();
        //TO AVOID HAVING THE ENEMY GUARANTEED CRITICAL AT THE START OF BATTLE.
        isPrepared = false;

        System.out.println("You wonder to yourself, \"How long has it been since I first fell on this cavern?...\"");
        System.out.println("Your vision begins to blur... You are hungry and thirsty.");
        Enter();
        System.out.println("walking...");
        System.out.println("You hear loud footsteps...");
        Enter();
        System.out.println("You looked up and was horrified in what you saw.");
        System.out.println("Right before you, is the throne room of what seems to be an undead king.");
        Enter();
        System.out.println("The Undead Lich appears before you!");
        System.out.println("You shiver in fear, but is left with no choice.");
        Enter();
        System.out.println("Once more, you held your battle-worn axe, as the fight begins!");
        fight3(player);
        return;
    }

    //FIGHT SEQUENCE FOR THE FIRST ENCOUNTER
    public static int fight(Hero player) {
        String choiceText = "1: Fight, 2: Defend, 3: Use Item, 4: Flee.";
        System.out.println();
        System.out.println("||Fight Begins||");
        System.out.println();
        //INITIALIZED AN OBJECT NAMED AS BONES FROM SKELETON CLASS. THIS IS IN ORDER TO MANAGE THE ENEMY'S ATTRIBUTES SUCH AS: HP, ATK, ETC.,
        Skeleton bones = new Skeleton();
        //THIS IS INITIALLY SET TO TRUE IN ORDER TO BEGIN THE LOOP OF THE FIGHT SEQUENCE.
        boolean isFight = true;
        while (isFight) {
            status(player);
            if (!isPrepared) {
                System.out.println("ENEMY: Bones || HP: " + bones.hp + "/" + bones.maxHp + "|| ATTACK: " + bones.atk);
            } else {
                System.out.println("ENEMY: Bones || HP: " + bones.hp + "/" + bones.maxHp + "|| ATTACK: " + bones.atk * 2);
            }
            System.out.println("The skeleton stands menacingly, what will you do? " + choiceText);
            String choice = input.nextLine();
            System.out.println();
            switch (choice) {
                case "1" :
                    int criticalRoll = (int) (Math.random() * 101);
                    if (criticalRoll > 80) {
                        System.out.println("You slashed your axe and dealt a critical damage of: " + (player.atk * 1.5 + " damage!"));
                        bones.hp -= (player.atk * 1.5);
                        System.out.println("The enemy was hit and lost: " + (player.atk * 1.5) + " hp and now has: " + bones.hp + " hp left.");

                    } else {
                        bones.hp -= player.atk;
                        System.out.println("You slashed your axe and dealt: " + player.atk + " damage!");
                        System.out.println("The enemy was hit and lost: " + player.atk + " hp and now has: " + bones.hp + " hp left.");
                    }
                    skeletonCounterMove(player, bones);
                break;
                case "2": 
                    System.out.println("You prepared for an attack.");
                    isDefend = true;
                    skeletonCounterMove(player, bones);
                break;
                case "3" :
                    itemSelect(player);
                    skeletonCounterMove(player, bones);
                break;
                case "4" : 
                    System.out.println("You Tried To Flee The Battle..");

                    int fleeRate = (int) (Math.random() * 100);
                    if (fleeRate >= 85) {
                        System.out.println("Fled Successfully!");
                        System.out.println();
                        System.out.println("NOTE: THERE IS NO FLEE OPTION DURING THE FINAL BATTLE");
                        System.out.println("GUIDE: THIS IS ONLY A TEMPORARY SAFE ZONE. EXPLORING WILL RESUME THE PREVIOUS FIGHT.");

                        Menu(player);
                        bones.hp = bones.maxHp;
                    } else {
                        System.out.println("Failed to escape!");
                        skeletonCounterMove(player, bones);
                    }
                break;

            }
            //CASE 1 IS FOR THE FIGHT OPTION: YOU CAN DEAL CRITICAL DAMAGE, DEPENDING ON LUCK.
            //CASE 2 IS FOR THE DEFEND OPTION, OR PARRY OPTION. IT IS USED TO REDUCE THE DAMAGE TAKEN FROM ENEMY ATTACKS.
            //CASE 3 IS FOR USING ITEMS, IT ALLOWS THE USER TO ACCESS LIMITED MENU FUNCTIONALITIES, SPECIFICALLY, USING CONSUMABLES.
            //CASE 4 IS FOR FLEEING, IT GIVES THE USER THE CHANCE TO FLEE, HOWEVER, IT ONLY BRINGS THE PLAYER BACK TO MENU AND WILL RESUME THE FIGHT AT EXPLORATION OPTION.

            //CHECKS IF EITHER THE PLAYER OR ENEMY HAS BEEN DEFEATED, IF YES, END THE LOOP AND GO BACK TO THE MAIN METHOD.
            //ELSE, LOOP BACK.
            System.out.println();
            winloseCheck(player, bones);
            if (player.hp <= 0 || bones.hp <= 0) {
                isFight = false;
            }
        }
        return player.hp;
    }

    //FIGHT SEQUENCE FOR THE SECOND FIGHT SEQUENCE
    public static int fight2(Hero player) {
        String choiceText = "1: Fight, 2: Defend, 3: Use Item, 4: Flee.";
        System.out.println();
        System.out.println("||Fight Begins||");
        System.out.println();
        //INITIALIZED AN OBJECT NAMED AS BONES FROM SKELETON CLASS. THIS IS IN ORDER TO MANAGE THE ENEMY'S ATTRIBUTES SUCH AS: HP, ATK, ETC., 
        ArmoredSkeleton heavyBones = new ArmoredSkeleton();

        //THIS IS INITIALLY SET TO TRUE IN ORDER TO BEGIN THE LOOP OF THE FIGHT SEQUENCE.
        boolean isFight = true;
        while (isFight) {
            status(player);
            if (!isPrepared) {
                System.out.println("ENEMY: Heavy Bones || HP: " + heavyBones.hp + "/" + heavyBones.maxHp + "|| ATTACK: " + heavyBones.atk);
            } else {
                System.out.println("ENEMY: Heavy Bones || HP: " + heavyBones.hp + "/" + heavyBones.maxHp + "|| ATTACK: " + heavyBones.atk * 2);
            }
            System.out.println("The skeleton stands menacingly, what will you do? " + choiceText);
            String choice = input.nextLine();
            System.out.println();
            switch (choice) {
                //CASE 1 IS FOR THE FIGHT OPTION: YOU CAN DEAL CRITICAL DAMAGE, DEPENDING ON LUCK.
                case "1":

                    int criticalRoll = (int) (Math.random() * 101);
                    if (criticalRoll > 80) {
                        System.out.println("You slashed your axe and dealt a critical damage of: " + (player.atk * 1.5 + " damage!"));
                        heavyBones.hp -= (player.atk * 1.5);
                        System.out.println("The enemy was hit and lost: " + (player.atk * 1.5) + " hp and now has: " + heavyBones.hp + " hp left.");

                    } else {
                        heavyBones.hp -= player.atk;
                        System.out.println("You slashed your axe and dealt: " + player.atk + " damage!");
                        System.out.println("The enemy was hit and lost: " + player.atk + " hp and now has: " + heavyBones.hp + " hp left.");

                    }

                    skeletonCounterMove2(player, heavyBones);
                    break;
                //CASE 2 IS FOR THE DEFEND OPTION, OR PARRY OPTION. IT IS USED TO REDUCE THE DAMAGE TAKEN FROM ENEMY ATTACKS.
                case "2":
                    System.out.println("You prepared for an attack.");
                    isDefend = true;
                    skeletonCounterMove2(player, heavyBones);
                    break;
                //CASE 3 IS FOR USING ITEMS, IT ALLOWS THE USER TO ACCESS LIMITED MENU FUNCTIONALITIES, SPECIFICALLY, USING CONSUMABLES.
                case "3":
                    itemSelect(player);
                    skeletonCounterMove2(player, heavyBones);
                    break;
                //CASE 4 IS FOR FLEEING, IT GIVES THE USER THE CHANCE TO FLEE, HOWEVER, IT ONLY BRINGS THE PLAYER BACK TO MENU AND WILL RESUME THE FIGHT AT EXPLORATION OPTION.
                case "4":
                    System.out.println("You Tried To Flee The Battle..");

                    int fleeRate = (int) (Math.random() * 100);
                    if (fleeRate >= 85) {
                        System.out.println("Fled Successfully!");
                        System.out.println();
                        System.out.println("NOTE: THERE IS NO FLEE OPTION DURING THE FINAL BATTLE");
                        System.out.println("GUIDE: THIS IS ONLY A TEMPORARY SAFE ZONE. EXPLORING WILL RESUME THE PREVIOUS FIGHT.");

                        Menu(player);
                        heavyBones.hp = heavyBones.maxHp;
                    } else {
                        System.out.println("Failed to escape!");
                        skeletonCounterMove2(player, heavyBones);
                    }
                    break;

            }

            //CHECKS IF EITHER THE PLAYER OR ENEMY HAS BEEN DEFEATED, IF YES, END THE LOOP AND GO BACK TO THE MAIN METHOD.
            //ELSE, LOOP BACK.
            System.out.println();
            winloseCheck2(player, heavyBones);
            if (player.hp <= 0 || heavyBones.hp <= 0) {
                isFight = false;
            }
        }
        return player.hp;
    }

    public static int fight3(Hero player) {
        String choiceText = "1: Fight, 2: Defend, 3: Use Item, 4: Flee.";
        System.out.println();
        System.out.println("||Fight Begins||");
        System.out.println();

        Boss undeadLich = new Boss();

        //THIS IS INITIALLY SET TO TRUE IN ORDER TO BEGIN THE LOOP OF THE FIGHT SEQUENCE.
        boolean isFight = true;
        while (isFight) {
            status(player);
            if (!isPrepared) {
                System.out.println("ENEMY: The Undead Lich || HP: " + undeadLich.hp + "/" + undeadLich.maxHp + "|| ATTACK: " + undeadLich.atk + "||  Magic Attack: " + undeadLich.mAtk);
            } else {
                System.out.println("ENEMY: The Undead Lich || HP: " + undeadLich.hp + "/" + undeadLich.maxHp + "|| ATTACK: " + undeadLich.atk * 2 + "||  Magic Attack: " + undeadLich.mAtk);
            }
            System.out.println("The Lich looks down on you. \n" + choiceText);
            String choice = input.nextLine();
            System.out.println();
            switch (choice) {
                //CASE 1 IS FOR THE FIGHT OPTION: YOU CAN DEAL CRITICAL DAMAGE, DEPENDING ON LUCK.
                case "1":

                    int criticalRoll = (int) (Math.random() * 101);
                    if (criticalRoll > 80) {
                        System.out.println("You slashed your axe and dealt a critical damage of: " + (player.atk * 1.5 + " damage!"));
                        undeadLich.hp -= (player.atk * 1.5);
                        System.out.println("The Lich was hit and lost: " + (player.atk * 1.5) + " hp and now has: " + undeadLich.hp + " hp left.");

                    } else {
                        undeadLich.hp -= player.atk;
                        System.out.println("You slashed your axe and dealt: " + player.atk + " damage!");
                        System.out.println("The Lich was hit and lost: " + player.atk + " hp and now has: " + undeadLich.hp + " hp left.");

                    }

                    lichCounterMove(player, undeadLich);
                    break;
                //CASE 2 IS FOR THE DEFEND OPTION, OR PARRY OPTION. IT IS USED TO REDUCE THE DAMAGE TAKEN FROM ENEMY ATTACKS.
                case "2":
                    System.out.println("You prepared for an attack.");
                    isDefend = true;
                    lichCounterMove(player, undeadLich);
                    break;
                //CASE 3 IS FOR USING ITEMS, IT ALLOWS THE USER TO ACCESS LIMITED MENU FUNCTIONALITIES, SPECIFICALLY, USING CONSUMABLES.
                case "3":
                    itemSelect(player);
                    lichCounterMove(player, undeadLich);
                    break;
                //CASE 4 IS FOR FLEEING, IT GIVES THE USER THE CHANCE TO FLEE, HOWEVER, IT ONLY BRINGS THE PLAYER BACK TO MENU AND WILL RESUME THE FIGHT AT EXPLORATION OPTION.
                case "4":
                    System.out.println("Fleeing is impossible.");
                    Enter();
                    System.out.println("Your impending doom approaches.");

                    break;
            }
            //CHECKS IF EITHER THE PLAYER OR ENEMY HAS BEEN DEFEATED, IF YES, END THE LOOP AND GO BACK TO THE MAIN METHOD.
            //ELSE, LOOP BACK.
            System.out.println();
            winloseCheck3(player, undeadLich);
            if (player.hp <= 0 || undeadLich.hp <= 0) {
                isFight = false;
            }
        }
        return player.hp;
    }

    
    
    
    
    
    public static void skeletonCounterMove(Hero player, Skeleton enemy) {
        boolean isEnemyTurn = true;
        //THIS IS USED TO DETERMINE WHAT ACTIONS THE ENEMY WOULD TAKE.
        int fateRoll = (int) (Math.random() * 100);

        //THIS CHECKS IF THE HP OF ENEMY AFTER THE METHOD/LOOP, AND THE METHOD IS CALLED AGAIN, IS <= 0, IN ORDER TO PREVENT IT FROM LOOPING AGAIN BEFORE FINALLY STOPPING.
        if (enemy.hp <= 0) {
            isEnemyTurn = false;
        }

        while (isEnemyTurn) {

            if (fateRoll >= 50 && !isPrepared && !isDefend) {
                player.hp -= enemy.atk;
                System.out.println("The enemy slashes its sword and deals: " + enemy.atk + " damage! You now have: " + player.hp + " hp left.");

            } else if (fateRoll >= 50 && isPrepared && !isDefend) {
                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 8) {
                    System.out.println("The enemy missed its attack!");
                    isPrepared = false;
                } else {
                    player.hp -= enemy.atk * 2;
                    System.out.println("The enemy rushed towards you and launched a heavy sword slash! It dealt: " + enemy.atk * 2 + " damage! You now have: " + player.hp + " hp left.");
                    isPrepared = false;
                }
            }

            //DEFENDED VARIANT
            if (fateRoll >= 50 && !isPrepared && isDefend) {
                player.hp -= enemy.atk - 5;
                System.out.println("The enemy slashes its sword but gets parried! dealing: " + (enemy.atk - 5) + " damage! You now have: " + player.hp + " hp left.");

            } else if (fateRoll >= 50 && isPrepared && isDefend) {
                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 6) {
                    System.out.println("The enemy missed its attack!");
                    isPrepared = false;
                } else {
                    player.hp -= ((enemy.atk * 2) - 10);
                    System.out.println("The enemy rushed towards you and launched a heavy sword slash but was parried! It dealt: " + ((enemy.atk * 2) - 10) + " damage! You now have: " + player.hp + " hp left.");
                    isPrepared = false;
                }
            } else if (fateRoll >= 30 && fateRoll < 50) {
                System.out.println("The enemy is preparing to launch a heavy attack.");
                isPrepared = true;

            } else if (fateRoll >= 20 && !isDefend && fateRoll < 30 && !isDefend) {
                System.out.println("The enemy picked a bone from its body and threw it at you!");

                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 6) {
                    System.out.println("The enemy missed its attack!");
                    isPrepared = false;

                } else {
                    player.hp -= enemy.atk - 5;
                    System.out.println("You were struck by the bone! and lost: " + (enemy.atk - 5) + " hp! You now have: " + player.hp + " hp left.");

                }
            } //DEFENDED VARIANT
            else if (fateRoll >= 20 && isDefend && fateRoll < 30 && isDefend) {
                System.out.println("The enemy picked a bone from its body and threw it at you!");

                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 6) {
                    System.out.println("The enemy missed its attack!");
                    isPrepared = false;
                } else {
                    player.hp -= enemy.atk - 8;
                    System.out.println("You were struck by the bone but was able to delect it and lost: " + (enemy.atk - 8) + " hp! You now have: " + player.hp + " hp left.");
                }
            } else if (fateRoll >= 10 && fateRoll < 20) {
                System.out.println("The enemy remains still and growls at you.");

            } else if (fateRoll < 10) {
                int heal = (int) (Math.random() * 30);

                System.out.println("The enemy consumes: ?? and healed: " + heal + " hp!");
                enemy.hp += heal;
                if (enemy.hp >= enemy.maxHp) {
                    enemy.hp = enemy.maxHp;
                }
            }

            isDefend = false;
            isEnemyTurn = false;
            System.out.println();
        }
    }

    
    
    
    
    
    
    public static void skeletonCounterMove2(Hero player, ArmoredSkeleton enemy) {
        boolean isEnemyTurn = true;
        //THIS IS USED TO DETERMINE WHAT ACTIONS THE ENEMY WOULD TAKE.
        int fateRoll = (int) (Math.random() * 80);

        //THIS CHECKS IF THE HP OF ENEMY AFTER THE METHOD/LOOP, AND THE METHOD IS CALLED AGAIN, IS <= 0, IN ORDER TO PREVENT IT FROM LOOPING AGAIN BEFORE FINALLY STOPPING.
        if (enemy.hp <= 0) {
            isEnemyTurn = false;
        }

        while (isEnemyTurn) {

            if (fateRoll >= 50 && !isPrepared && !isDefend) {
                player.hp -= enemy.atk;
                System.out.println("The enemy slashes its sword and deals: " + enemy.atk + " damage! You now have: " + player.hp + " hp left.");

            } else if (fateRoll >= 50 && isPrepared && !isDefend) {
                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 6) {
                    System.out.println("The enemy missed its attack!");
                    isPrepared = false;
                } else {
                    player.hp -= enemy.atk * 2;
                    System.out.println("The enemy rushed towards you and launched a heavy sword slash! It dealt: " + enemy.atk * 2 + " damage! You now have: " + player.hp + " hp left.");
                    isPrepared = false;
                }
            }

            //DEFENDED VARIANT
            if (fateRoll >= 50 && !isPrepared && isDefend) {
                player.hp -= enemy.atk - 5;
                System.out.println("The enemy slashes its sword but gets parried! dealing: " + (enemy.atk - 5) + " damage! You now have: " + player.hp + " hp left.");

            } else if (fateRoll >= 50 && isPrepared && isDefend) {
                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 6) {
                    System.out.println("The enemy missed its attack!");
                    isPrepared = false;
                } else {
                    player.hp -= ((enemy.atk * 2) - 10);
                    System.out.println("The enemy rushed towards you and launched a heavy sword slash but was parried! It dealt: " + ((enemy.atk * 2) - 10) + " damage! You now have: " + player.hp + " hp left.");
                    isPrepared = false;
                }
            } else if (fateRoll >= 30 && fateRoll < 50) {
                System.out.println("The enemy is preparing to launch a heavy attack.");
                isPrepared = true;

            } else if (fateRoll >= 20 && !isDefend && fateRoll < 30 && !isDefend) {
                System.out.println("The enemy picked a bone from its body and threw it at you!");

                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 6) {
                    System.out.println("The enemy missed its attack!");
                    isPrepared = false;

                } else {
                    player.hp -= enemy.atk - 5;
                    System.out.println("You were struck by the bone! and lost: " + (enemy.atk - 5) + " hp! You now have: " + player.hp + " hp left.");

                }
            } //DEFENDED VARIANT
            else if (fateRoll >= 20 && isDefend && fateRoll < 30 && isDefend) {
                System.out.println("The enemy picked a bone from its body and threw it at you!");

                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 6) {
                    System.out.println("The enemy missed its attack!");
                    isPrepared = false;
                } else {
                    player.hp -= enemy.atk - 8;
                    System.out.println("You were struck by the bone but was able to delect it and lost: " + (enemy.atk - 8) + " hp! You now have: " + player.hp + " hp left.");
                }
            } else if (fateRoll >= 10 && fateRoll < 20) {
                System.out.println("The enemy remains still and growls at you.");

            } else if (fateRoll < 10) {
                int heal = (int) (Math.random() * 30);

                System.out.println("The enemy consumes: ?? and healed: " + heal + " hp!");
                enemy.hp += heal;
                if (enemy.hp >= enemy.maxHp) {
                    enemy.hp = enemy.maxHp;
                }
            }

            isDefend = false;
            isEnemyTurn = false;
            System.out.println();
        }
    }

    
    
    
    
    
    
    
    //LICH COUNTERATTACK SEQUENCES
    public static void lichCounterMove(Hero player, Boss undeadLich) {
        boolean isEnemyTurn = true;

        //THIS IS USED TO DETERMINE WHAT ACTIONS THE ENEMY WOULD TAKE.
        int fateRoll = (int) (Math.random() * 90);

        //THIS CHECKS IF THE HP OF ENEMY AFTER THE METHOD/LOOP, AND THE METHOD IS CALLED AGAIN, IS <= 0, IN ORDER TO PREVENT IT FROM LOOPING AGAIN BEFORE FINALLY STOPPING.
        if (undeadLich.hp <= 0) {
            isEnemyTurn = false;
        }

        while (isEnemyTurn) {

            if (fateRoll >= 84 && !isPrepared && !isDefend) {
                player.hp -= undeadLich.mAtk;
                System.out.println("The Lich casts a  spell upon you causing: " + undeadLich.mAtk + " damage! You now have: " + player.hp + " hp left.");

            } else if (fateRoll >= 70 && isPrepared && !isDefend) {
                int willMiss = (int) (Math.random() * 10);
                if (willMiss > 8) {
                    System.out.println("The Lich missed its spell!");
                    isPrepared = false;
                } else {
                    player.hp -= undeadLich.atk * 1.5;
                    System.out.println("The Lich lunges towards you with its claws catching you off guard! dealing: " + (undeadLich.atk * 1.5) + " damage! You now have: " + player.hp + " hp left.");
                    isPrepared = false;
                }
            }

            //DEFENDED VARIANT
            if (fateRoll >= 50 && !isPrepared && isDefend) {
                player.hp -= undeadLich.atk - 10;
                System.out.println("The Lich lunges towards you with its claws but gets parried! dealing: " + (undeadLich.atk - 20) + " damage! You now have: " + player.hp + " hp left.");

            } else if (fateRoll >= 50 && isPrepared && isDefend) {
                int willMiss = (int) (Math.random() * 11);
                if (willMiss > 9) {
                    System.out.println("The Lich missed its attack!");
                    isPrepared = false;
                } else if (fateRoll >= 50 && isPrepared && isDefend && willMiss < 9) {
                    player.hp -= ((undeadLich.atk * 2) - 10);
                    System.out.println("The Lich rushed towards you and launched a heavy claw slash but was parried! It dealt: " + ((undeadLich.atk * 2) - 20) + " damage! You now have: " + player.hp + " hp left.");
                    isPrepared = false;
                }
            } else if (fateRoll >= 30 && fateRoll < 50) {
                System.out.println("The Lich is preparing a devastating spell.");
                isPrepared = true;

            } else if (fateRoll >= 20 && !isDefend && fateRoll < 30 && !isDefend) {
                System.out.println("The Lich sends bone spears towards you.");
                int miss = (int) (Math.random() * 10);
                if (miss > 7) {
                    System.out.println("The bone spears missed!");
                } else {
                    player.hp -= undeadLich.atk;
                    System.out.println("The bone spears pierces your body! Dealing: " + undeadLich.atk + " damage! You now have: " + player.hp + " hp left.");

                }
            } //DEFENDED VARIANT
            else if (fateRoll >= 20 && isDefend && fateRoll < 30 && isDefend) {
                System.out.println("The Lich sends bone spears towards you.");
                int miss = (int) (Math.random() * 10);
                if (miss > 7) {
                    System.out.println("The bone spears missed!");
                } else {
                    player.hp -= undeadLich.atk - 20;
                    System.out.println("You were able to block some of the bone spears but gets hit nonetheless! Dealing: " + undeadLich.atk + " damage! You now have: " + player.hp + " hp left.");
                }
            } else if (fateRoll >= 10 && fateRoll < 20) {
                System.out.println("The Lich recuperates for a moment.");
            } else if (fateRoll < 10) {
                int heal = (int) (Math.random() * 30);
                player.hp -= heal;
                System.out.println("The Lich consumes some of your health: " + heal + "+ and healed: " + heal + " hp!");
                undeadLich.hp += heal;
                if (undeadLich.hp >= undeadLich.maxHp) {
                    undeadLich.hp = undeadLich.maxHp;
                }
            }
            isDefend = false;
            isEnemyTurn = false;
            System.out.println();
        }
    }

    
    
    
    
    
    
    
    
    //METHOD USED TO CHECK AND DISPLAY VICTORY/DEFEAT MESSAGE.
    public static void winloseCheck(Hero player, Skeleton enemy) {
        if (player.hp <= 0 || enemy.hp <= 0) {
            if (player.hp <= 0) {
                return;

            } else if (enemy.hp <= 0) {
                System.out.println("The enemy's hp dropped to " + enemy.hp + "! You Won!");
                skeletonDrops(player);
                return;
            } else {
                return;
            }
        }
    }

    public static void winloseCheck2(Hero player, ArmoredSkeleton enemy) {
        if (player.hp <= 0 || enemy.hp <= 0) {
            if (player.hp <= 0) {
                return;

            } else if (enemy.hp <= 0) {
                System.out.println("The enemy's hp dropped to " + enemy.hp + "! You Won!");
                skeletonDrops(player);
                return;
            } else {
                return;
            }
        }
    }

    public static void winloseCheck3(Hero player, Boss undeadLich) {
        if (player.hp <= 0 || undeadLich.hp <= 0) {
            if (player.hp <= 0) {
                return;

            } else if (undeadLich.hp <= 0) {
                System.out.println("The enemy's hp dropped to " + undeadLich.hp + "! You Won!");
                skeletonDrops(player);
                return;
            } else {
                return;
            }
        }
    }

    
    
    
    
    
    
    
    
    // THIS METHOD IS RESPONSIBLE FOR DETERMINING WHAT WILL BE THE DROPPED ITEMS UPON VICTORY.
    public static void skeletonDrops(Hero player) {
        //bone meal, bone heart
        int drop = (int) (Math.random() * 11);
        int dropAmount = (int) (Math.random() * 3 + 1);
        if (drop < 8) {
            
            	System.out.println("testing.11");
            String item = "BoneHeart";
            System.out.println("You've got " + item + " x" + dropAmount);
            System.out.println("a consumable item that heals 50 HP when used!");            
            getItem(item, dropAmount);
    
      }  
         else {
            String item = "BoneMeal";
            dropAmount = 1;
            System.out.println("You've got " + item + " x" + dropAmount);
            System.out.println("a RARE consumable item that permanently increases your atk by 10!");
            getItem(item, dropAmount);
        }
    }
    
    //THIS METHOD IS USED TO DISPLAY THE CURRENT STATUS OF THE PLAYER.
    public static void status(Hero player) {
        System.out.println("NAME: " + player.name + " || HP: " + player.hp + "/" + player.maxHp + "|| ATTACK: " + player.atk);
    }
    
    //ENTER METHOD TO HANDLE PROMPTING THE USER
    public static void Enter() {
        System.out.println();
        System.out.println("Press Enter To Continue: \nType \"Exit\" if you wish to quit program.");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("exit")) {
            System.out.println("Shutting Down...");
            System.exit(0);
        }
    }
    
    
    
    
    
    
    
    
    
    public static void getItem(String item, int dropAmount) {

        //IF ITEM ALREADY EXISTS, INCREMENT THE KEY VALUE. TO INCREASE QUANTITY OF THAT ITEM.
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + dropAmount);
        } else {
            inventory.put(item, dropAmount);
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    

    //THIS METHOD IS USED TO HANDLE THE REST OPTION IN THE MENU.
    public static void rest(Hero player) {
        int roll = (int) (Math.random() * 301);
        if (roll <= 150) {
            int heal = (int) (Math.random() * 50) + 10;
            System.out.println("You Rested Successfully and healed: " + heal + " HP.");
            player.hp += heal;

            if (player.hp >= player.maxHp) {
                player.hp = player.maxHp;
            }
        } else {
            int hurt = (int) (Math.random() * 20) + 5;
            System.out.println("Failed to rest, you were attacked by bugs in your sleep and lost: " + hurt + " HP");
            player.hp -= hurt;
            if (player.hp <= 0) {
                gameOver(player);
                System.exit(0);
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    

    //MENU METHOD, CONTAINS THE MAIN MENU / MAIN INTERFACE OF THE GAME
    public static void Menu(Hero player) {
        boolean isMenu = true;

        while (isMenu) {

            if (player.hp <= 0) {
                return;
            }
            status(player);
            System.out.println("|---------MENU--------|");
            System.out.println("1: EXPLORE");
            System.out.println("2: INVENTORY");
            System.out.println("3: REST");
            System.out.println("4: USE ITEM");
            System.out.println("5: QUIT PROGRAM");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Exploring...");
                    isMenu = false;
                    break;

                case "2":
                    System.out.println("The Items currently in your inventory: ");
                    System.out.println(inventory);
                    break;

                case "3":
                    System.out.println("You Decided To Rest");
                    rest(player);
                    break;

                case "4":
                    itemSelect(player);
                    break;
                case "5":
                    System.out.println("Are you sure you want to quit game? \n1: Yes ||2: No ");
                    String confirmation = input.nextLine();
                    if (confirmation.equalsIgnoreCase("1")) {
                        System.out.println("Shutting Down.");
                        System.exit(0);
                    } else if (confirmation.equalsIgnoreCase("2")) {
                        System.out.println("Cancelled.");
                        break;
                    } else {
                        System.out.println("Please Input A Valid Number.");
                        break;
                    }

            }
            
        }
        
    }
    
    
    
    

    //INVENTORY SEGMENT
    //THIS METHOD  IS USED TO "SEARCH" AND CONSUME ITEMS FROM THE HASHMAP INVENTORY.
    public static void itemSelect(Hero player) {
        System.out.println(inventory);
        System.out.println("Please Enter The Name Of Item You Want To Use. Type \"Back\" to return.");
        System.out.println("|||  During Combat, This will end the player's turn regardless of whether an item was used or not.  |||");

        String item = input.nextLine().toLowerCase();
        if (item.equalsIgnoreCase("back")) {
            return;
        }

        //THIS PROMPTS THE USER TO SEARCH FOR THE ITEM HE WANTS TO USE.
        //EACH ITEM HAS THEIR OWN PREDEFINED DEFINITIONS AND EFFECTS, AS LISTED IN THE SWITCH CASE.
        if (inventory.containsKey(item)) {
            switch (item.toLowerCase()) {
                case "apple":
                    int heal = 15;
                    player.hp += heal;
                    System.out.println("Oddly enough, the apple still tastes fresh..? Recovered: " + heal + " hp.");

                    break;
                case "bean":
                    heal = 25;
                    player.hp += heal;
                    System.out.println("It's bland, but surprisingly hearty! Recovered: " + heal + " hp.");

                    break;
                case "bonemeal":
                    int buff = 10;
                    player.atk += buff;
                    System.out.println("Tastes like protein...? player attack permanently raised by: " + buff + "!");

                    break;
                case "boneheart":
                    heal = 50;
                    player.hp += heal;
                    System.out.println("Disgusting! Recovered: " + heal + " hp.");
                    break;

                case "goldenapple":
                    heal = 50;
                    player.maxHp += heal;
                    player.hp += player.maxHp;
                    System.out.println("Yum!!! So this is what it means to be rich? Healed: " + player.maxHp + " hp!");
                    break;

            }
            //THIS IS DONE TO ENSURE THAT ONCE A PLAYER USES A CONSUMABLE, HIS/HER HP WON'T EXCEED THE MAXIMUM VALUE.
            if (player.hp > player.maxHp) {
                player.hp = player.maxHp;
            }
        } 
        //ONCE THE ITEMSELECT METHOD IS DONE AND OUTPUTS THE ITEM, IT IS THEN TAKEN AS THE INPUT FOR THE USEITEM METHOD. THIS IS TO DISPLAY DECREASE OF ITEM.
        useItem(item.toLowerCase());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    //THIS METHOD USE ITEM IS USED AFTER THE ITEMSELECT METHOD. IT IS RESPONSIBLE FOR "DELETING" OR DECREASING THE ITEM FROM INVENTORY, ONCE USED.
    // THE ITEM INPUTTED BY THE USER WILL BE CHECKED HERE IF IT EXISTS, AND IF YES, IT WILL EITHER REMOVE OR DECREMENT IT DEPENDING ON THE AMOUNT.
    public static void useItem(String item) {
        //CHECKS IF ITEM EXISTS IN INVENTORY.
        if (!inventory.containsKey(item)) {
            System.out.println("You don't have that item.");
            return;
        }
        //IF COUNT OF ITEM TO BE USED IS 1, IT WILL BE 0, SO MUST BE REMOVED.
        if (inventory.get(item) == 1) {
            inventory.remove(item);
            System.out.println("You ate a/an : " + item);

            //ALMOST, IF NEVER USED DUE TO THE FIRST STATEMENT. MAKING IT IMPOSSIBLE TO HAVE LESS THAN 1.
        } else if (inventory.get(item) < 1) {
            System.out.println("Failed to perform action. Insufficient amount of " + item);

            //IF ITEM EXISTS, AND IS MORE THAN 1, DECREMENT THE ITEM.
        } else {
            inventory.put(item, inventory.get(item) - 1);
            System.out.println("You ate a/an : " + item);
        }
        return;
    }
}






//CREATED A CLASS CALLED HERO TO DEFINE THE ATTRIBUTES OF THE OBJECT CALLED PLAYER.
class Hero {

    String name;
    int maxHp = 150;
    int hp = 150;
    int atk = 25;

    public Hero(String name) {
        this.name = name;

    }

}
//SIMILAR TO HERO, THIS IS USED TO DEFINE THE ATTRIBUTES OF THE ENEMY "BONES".

class Skeleton {

    int maxHp = 250;
    int hp = 250;
    int atk = 13;

}

class ArmoredSkeleton {

    int maxHp = 350;
    int hp = 350;
    int atk = 11;

}

class Boss {

    int maxHp = 900;
    int hp = 900;
    int atk = 40;
    int mAtk = 60;

}
