import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by BodunovRA on 19.09.2016.
 */
public class GameClass {


    private Hero[] heroPattern = new Hero[3];
    private  Monster[] monsterPattern = new Monster[3];



    private int inpInt;

    private Hero mainHero;
    private  Monster currentMonster;
    private GameMap map;
    private InGameShop shop;
    private int currentRound;


    public GameClass()
    {
        initGame();
    }



    public void mainGameLoop()
    {

        map = new GameMap();
        shop = new InGameShop();
        inpInt = 0;
        System.out.println("Игра началась!");



        selectHero();
        mainHero.setXY(10, 3);
        map.buildDangeMap(10, 3);

        //currentMonster = (Monster)monsterPattern[0].clone();
        //currentMonster.myInv.showAllItems();
        //currentMonster.lvlUp(1);
        //battle (mainHero, currentMonster);
        map.updateMap(mainHero.getX(), mainHero.getY());
        map.showMap();
        selectAction();

        System.out.println("Игра завершена");


    }

    public void selectHero()
    {
        String s = "Выберите героя: ";
        for (int i = 0; i < 3; i++)
            s += (i+1)+". " + heroPattern[i].getName() + "   ";
        inpInt = getAction(1,3, s);
        mainHero = (Hero)heroPattern[inpInt - 1].clone();
        System.out.println(mainHero.getName() + "Начал свое путешествие");
    }

    public void battle(Hero h, Monster m)
    {
        currentRound = 1;
        System.out.println("Бой между игроком " + h.getName() + " и монстром " + m.getName() + " начался");
        do
        {
            System.out.println("Текущий раунд: " + currentRound);
            h.ShowInfo();
            m.ShowInfo();
            h.makeNewRound();

            inpInt = getAction(0, 3, "Ход игрока: 1. Атака 2. Защита 3. Покопаться в сумке 0. Сбежать");

            if (inpInt == 1)
            {
                m.getDamage(h.makeAttack());
            }
            if (inpInt == 2)
            {
                h.setBlockStance();
            }
            if (inpInt == 3)
            {
                h.myInv.showAllItems();
                int invInput = getAction(0, h.myInv.getSize(), "Выберите предмет для использования");
                String usedItem = h.myInv.useItem(invInput);
                if (usedItem != "")
                { System.out.println(h.getName() + " использовал " + usedItem);
                    h.useItem(usedItem);  }
                else {System.out.println(h.getName() + " просто закрыл сумку");}
            }


            if (inpInt == 0) break;
            m.makeNewRound();

            if (Utilits.rand.nextInt(100) < 80)
            {
                h.getDamage(m.makeAttack());
            }
            else
            {
                m.setBlockStance();
            }

            currentRound++;
            if (!h.isAllive)
            {
                System.out.println("Победил " + m.name);
                break;
            }

            if (!m.isAllive)
            {
                System.out.println("Победил " + h.name);
                System.out.println(m.name + " погиб");
                h.expGain(m.getHpMax() * 2);
                h.addKillCounter();
                m.myInv.transferAllItemsToAnotherInventory(h.myInv);

                //currentMonster = (Monster) monsterPattern[Utilits.rand.nextInt(3)].clone();
                //System.out.println("На поле боя выходит " + currentMonster.name);
                break;
            }
        }
        while(true);

    }

    public void selectAction() {




        while (true) {
            int x = getAction(1, 6, "Что вы хотите делать дальше: 1.Пойти влево; 2.Пойти вправо; 3.Пойти вверх; 4.Пойти вниз; 5.Поискать монстров; 6.Отдохнуть");

            switch (x) {
                case 1:
                    if(map.isCellEmpty(mainHero.getX()-1, mainHero.getY()))
                    mainHero.moveHero(-1, 0);
                    break;
                case 2:
                    if(map.isCellEmpty(mainHero.getX()+1, mainHero.getY()))
                    mainHero.moveHero(1, 0);
                    break;
                case 3:
                    if(map.isCellEmpty(mainHero.getX(), mainHero.getY()-1))
                    mainHero.moveHero(0, -1);
                    break;
                case 4:
                    if(map.isCellEmpty(mainHero.getX(), mainHero.getY()+1))
                    mainHero.moveHero(0, 1);
                    break;
                case 5:
                    currentMonster = (Monster) monsterPattern[Utilits.rand.nextInt(3)].clone();
                    currentMonster.lvlUp(map.getDangerous(mainHero.getX(),mainHero.getY()));
                    battle(mainHero, currentMonster);
                case 6:
                    mainHero.fullHeal();
                    break;
            }

            if(map.getObstValue(mainHero.getX(), mainHero.getY()) == 'S')
                shopAction();

            if(Utilits.rand.nextInt(100) < 3)
            {
                System.out.println("На вас внезапно напали!");
                currentMonster = (Monster) monsterPattern[Utilits.rand.nextInt(3)].clone();
                currentMonster.lvlUp(map.getDangerous(mainHero.getX(),mainHero.getY()));
                battle(mainHero, currentMonster);
            }

            map.updateMap(mainHero.getX(), mainHero.getY());
            map.showMap();

           /* int x = getAction(1, 5, "Что вы хотите делать дальше: 1.Следующий бой; 2.Перейти в более опасную зону; 3.Перейти в город; 4.Отдохнуть; 5.Завершить игру");

            switch (x) {
                case 1:
                    currentMonster = (Monster) monsterPattern[Utilits.rand.nextInt(3)].clone();
                    currentMonster.lvlUp(mainHero.getZoneDangerous());
                    battle(mainHero, currentMonster);
                    break;

                case 2:
                    mainHero.goToDangerousZone();
                case 4:
                    mainHero.fullHeal();
                    break;
                case 5:
                    mainHero.getDamage(mainHero.hpMax);
                    break;
            }
            if (!mainHero.isAllive) break;
            */
        }

   }

    public void shopAction()
    {
        shop.showItems();
        System.out.println("Для выхода измагазина нажмите 0");
        int x = getAction(0, shop.ITEMS_CONST, "Введите номер покупаемого товара");
        if (x ==0) return;
        shop.buyByHero(x-1, mainHero);
    }

    public void initGame()
    {
        heroPattern[0] = new Hero("Knight", "Lancelot", 16, 8, 14);
        heroPattern[1] = new Hero("Barbarian", "Konan", 16, 8, 14);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 16, 8, 14);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 12, 4, 4);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 18, 6, 6);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 32, 12, 10);

        currentRound = 1;

    }

    public int getAction(int _min, int _max, String _str)
    {

        int x = 0;

        do
        {   if (_str != "") System.out.println(_str);
            x = Utilits.sc.nextInt();
        }
        while (x < _min || x > _max);

        return x;

    }
}
