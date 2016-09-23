/**
 * Created by BodunovRA on 19.09.2016.
 */
public class GameCharacter {

    protected String name;
    public String getName()
    {
        return name;
    }

    protected String charClass;

    protected int hp;
    protected int hpMax;
    public int getHpMax()
    {
        return hpMax;
    }

    protected int strength;
    protected int dexterity;
    protected int endurance;

    protected int attack;
    protected int defence;
    protected int critChance;
    protected int evoidChance;
    protected float critMultiplier;
    protected int level;
    protected boolean blockStance;
    public boolean isAllive;

    public GameCharacter(String _charClass, String _name, int _strength, int _dexterity, int _endurance)
    {
        name = _name;
        charClass = _charClass;
        strength = _strength;
        dexterity = _dexterity;
        endurance = _endurance;

        CalculateSecondaryParamiters();

        level = 1;
        hp = hpMax;
        isAllive = true;
        blockStance = false;
    }

    public void CalculateSecondaryParamiters()
    {
        attack = strength * 2;
        hpMax = endurance * 50;
        defence = (int)((strength + dexterity) / 4.0f);
        critChance = dexterity * 1;
        critMultiplier = 1.2f + (float)(dexterity / 20.0f);
        evoidChance = 8 + (int)(dexterity / 5.0f);
    }


    public void ShowInfo()
    {
    System.out.println("Имя: " + name + " Здоровье: " + hp + "/" + hpMax);

    }

    public void setBlockStance()
    {
        blockStance = true;
        System.out.println(name + " встал в защитную стойку");
    }

    public void Cure(int _val)
    {
        hp += _val;
        if (hp>hpMax) hp = hpMax;
    }

    public void makeNewRound()
    {
        blockStance = false;

    }

    public int makeAttack()
    {
        int minAttack = (int)(attack * 0.8f);
        int deltaAttack = (int)(attack * 0.4f);
        int currentAttack = minAttack + GameClass.rand.nextInt(deltaAttack);
        if (GameClass.rand.nextInt(100) < critChance)
        {
            currentAttack = (int)(currentAttack * critMultiplier);
            System.out.println(name + " провел критическую атаку на " + currentAttack + " единиц урона");
        }
        else
        {
            System.out.println(name + " провел атаку на " + currentAttack + " единиц урона");
        }
        return currentAttack;
    }

    public void getDamage(int _inputDamage)
    {
        if(GameClass.rand.nextInt(100) < evoidChance)
        {
            System.out.println(name + " увернулся от атаки");
        }
        else {
            _inputDamage -= GameClass.rand.nextInt(defence);
            if (blockStance) {
                System.out.println(name + " дополнительно заблокировал часть урона");
                _inputDamage -= GameClass.rand.nextInt(defence);
            }
            if (_inputDamage < 0) _inputDamage = 0;
            System.out.println(name + " получил " + _inputDamage);
            hp -= _inputDamage;
            if (hp < 1) {
                isAllive = false;
            }
            }
    }

    public void useItem(String _item)
    {
        switch(_item)
        {
            case "Слабое зелье лечения":
                Cure(50);
                System.out.println(name + " пополнил здоровье на 50 ед.");
                break;
        }
    }
}
