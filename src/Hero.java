/**
 * Created by BodunovRA on 19.09.2016.
 */
public class Hero extends GameCharacter implements Cloneable {

    private int currentExp;
    private int expToNextLevel;
    private int killedMonster;
    private int currentZone;

    private int posX;
    public int getX(){ return posX;}
    private int posY;
    public int getY(){ return posY;}

    public void setXY(int _x, int _y)
    {
        posX = _x;
        posY = _y;
    }

    public void moveHero(int _vx, int _vy)
    {
        posX += _vx;
        posY += _vy;
    }

    public int getZoneDangerous()
    {
        return currentZone;
    }



    public Hero(String _charClass, String _name, int _strength, int _dexterity, int _endurance)
    {

        super(_charClass, _name, _strength, _dexterity, _endurance);
        InitHero();
    }

    public void InitHero()
    {
        currentZone = 1;
        currentExp = 0;
        expToNextLevel = 1000;
        killedMonster = 0;
        myInv = new Inventory();
        myInv.add(new Item("Слабый камень здоровья", Item.ItemType.infConsumables));
        myInv.add(new Item("Слабое зелье лечения", Item.ItemType.Consumables));
        myInv.addSomeCoins(1000);
    }

    public void expGain(int _exp)
    {
        currentExp += _exp;
        System.out.println(name + " получил " + _exp);
        if(currentExp >= expToNextLevel)
        {
            currentExp -= expToNextLevel;
            expToNextLevel *= 2;
            level++;
            attack += 5;
            hpMax += 50;
            strength += 2;
            dexterity += 2;
            endurance += 1;
            CalculateSecondaryParamiters();
            hp = hpMax;
            System.out.println(name + " повысил уровень до " + level);
        }

    }

    public void goToDangerousZone()
    {
        currentZone++;
        System.out.println("Герой перешел в зону опасности " + currentZone);
    }

    public void addKillCounter()
    {
        killedMonster++;
    }

    public void ShowInfo()
    {
        System.out.println("Имя: " + name + " Здоровье: " + hp + "/" + hpMax + " уровень: " + level + " [" + currentExp + "/" + expToNextLevel + "]");
    }

    public Object clone()
    {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException a)
        {
            System.out.println("Клонирование невозможно");
            return this;
        }

    }
}
