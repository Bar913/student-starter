Prerequisites: JDK 25, Maven, Git, terminal/IDE.

java -version
mvn test
mvn exec:java -Dexec.mainClass=edu.starter.Main

Actions: attack, defend, special, recover. Reward: recovery or power.

Traces

The player starts at HP 100, resources 5, attack 10. Every fight-1 line below is the same in all traces.

Trace A - POWER, with invalid reward entries

Input: special, attack, attack, abc, Power, power, attack, attack, attack, attack

Encounter 1 start:            HP 100, resources 5, attack 10, Enemy HP 30
after Special:                HP 90,  resources 2, attack 10, Enemy HP 15
after Attack:                 HP 80,  resources 2, attack 10, Enemy HP 5
Attack -> "encounter won"     (no counterattack)
choose one reward: recovery, power
> abc
invalid reward                (nothing changed, asked again)
> Power
invalid reward                (input is case-sensitive, nothing changed)
> power
fighter gained +2 to basic attack
Encounter 2 start:            HP 80,  resources 2, attack 12, Enemy HP 45
after 3 attacks:              HP 50,  resources 2, attack 12, Enemy HP 9
4th attack -> "encounter won", then "game won"
Trace B - RECOVERY, same first fight

Input: special, attack, attack, recovery, attack x5

After fight 1:                HP 80,  resources 2, attack 10
> recovery
restored 25 hp (max) to fighter
Encounter 2 start:            HP 100, resources 2, attack 10, Enemy HP 45
after 4 attacks:              HP 60,  resources 2, attack 10, Enemy HP 5
5th attack -> "encounter won", then "game won"
Trace C - loss in encounter 1

Input: defend repeated

...
player hp = 5.0
enemy hp = 30.0
encounter lost
game lost

No reward prompt appears and no second battle starts.

Trace D - loss in encounter 2

Input: special, attack, attack, recovery, defend repeated

...
enemy hp = 45.0
encounter lost
game lost
Acceptance tests

ID	Check	Result
T1 / W03-RWD-01	HP 90, Recovery gives HP 100; resources and attack unchanged	pass
T2	HP 100, Recovery stays 100	pass
T3 / W03-RWD-02	Power: attack 12, Special still deals 15	pass
T4 / W03-RWD-04	Unknown entry changes nothing, then Power works once (Trace A)	pass
T5	Reward before first victory	cannot happen: the reward loop in main sits after the return on a lost encounter 1
T6 / W03-RWD-05	Second reward	cannot happen: the reward loop appears once in main and nothing returns to it
T7 / W03-END-01	Lose encounter 1: no reward, no encounter 2 (Trace C)	pass
T8 / W02-ENC-01	HP and resources persist into encounter 2	pass
T9 / W01-END-01	Finishing attack on Enemy 2: adventure won, no counterattack	pass
Regression	Special with fewer than 3 resources fails without a counterattack; Recover caps at 10; HP never negative; independent enemy HP; invalid action consumes no turn	pass
Regression	Pending defence cleared before each encounter cleardefense() is the first call in encounter

I used an ai assistant to check my project against the list of objactives, to review my contracts use, to understand some problems I got, in implementing the enum Reward { RECOVERY, POWER } declaration and also to run the checks to check my code.

Useful suggestion I accepted, the ai pointed out that my first reward used sethp and setresources, which is not allowed so i changed it.

Suggestion I rejected: the ai recommended removing the line resources = MAX_RESOURCES in line 123 Fighter but I think the code looks clearer with it, so i kept it.

Test independently verified: I ran a test to see if the Reward works as it suppused to.