import java.util.Random;
import java.util.Scanner;

/**
 * Created by BodunovRA on 19.09.2016.
 */
public class GameClass {
    public static Random rand = new Random();

    private Hero[] heroPattern = new Hero[3];
    private  Monster[] monsterPattern = new Monster[3];

    private Hero mainHero;
    private  Monster currentMonster;
    private int currentRound;
    private int monsterID = 0;
    private Scanner sc = new Scanner(System.in);

    public GameClass()
    {
        initGame();
    }

    public void mainGameLoop()
    {

        int inpInt = 0;
        System.out.println("Игра началась!");

        System.out.println("Выберите героя:");
        for (int i = 0; i < 3; i++)
            System.out.println((i+1)+". " + heroPattern[i].getName());
        inpInt = getAction(1,3, "");
        mainHero = (Hero)heroPattern[inpInt - 1].clone();
        System.out.println("Вы выбрали " + mainHero.getName());
        currentMonster = (Monster)monsterPattern[monsterID].clone();

    do
    {
        System.out.println("Текущий раунд: " + currentRound);
        mainHero.ShowInfo();
        currentMonster.ShowInfo();
        mainHero.makeNewRound();

        inpInt = getAction(0, 3, "Ход игрока: 1. Атака 2. Защита 3. Пропустить ход 0. Завершить игру");

        if (inpInt == 1)
        {
            currentMonster.getDamage(mainHero.makeAttack());
        }
        if (inpInt == 2)
        {
            mainHero.setBlockStance();
        }


        if (inpInt == 0) break;
        currentMonster.makeNewRound();

        if (rand.nextInt(100) < 80)
        {
            mainHero.getDamage(currentMonster.makeAttack());
        }
        else
        {
            currentMonster.setBlockStance();
        }

        currentRound++;
        if (!mainHero.isAllive)
        {
            break;
        }

        if (!currentMonster.isAllive)
        {
            System.out.println(currentMonster.name + " погиб");
            mainHero.expGain(currentMonster.getHpMax() * 2);
            mainHero.addKillCounter();
            monsterID++;
            if(monsterID < monsterPattern.length)
            {
                currentMonster = (Monster) monsterPattern[monsterID].clone();
                System.out.println("На поле боя выходит " + currentMonster.name);
            }
            else
            {
                break;
            }
        }
    }
    while(true);

        if (!mainHero.isAllive)
        {
            System.out.println("Победил " + currentMonster.name);
        }
        if (!currentMonster.isAllive)
        {
            System.out.println("Победил " + mainHero.name);
        }

        System.out.println("Игра завершена");

    }
    public void initGame()
    {
        heroPattern[0] = new Hero("Knight", "Lancelot", 500, 10, 10);
        heroPattern[1] = new Hero("Barbarian", "Konan", 1000, 20, 0);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 500, 20, 20);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 250, 30, 2);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 380, 60, 2);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 490, 50, 1);

        currentRound = 1;

    }

    public int getAction(int _min, int _max, String _str)
    {

        int x = 0;

        do
        {   if (_str != "") System.out.println(_str);
            x = sc.nextInt();
        }
        while (x < _min || x > _max);

        return x;

    }
}
