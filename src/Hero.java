/**
 * Created by BodunovRA on 19.09.2016.
 */
public class Hero extends GameCharacter implements Cloneable {

    private int currentExp;
    private int expToNextLevel;
    private int killedMonster;

    public Hero(String _charClass, String _name, int _hp, int _attack, int _defense)
    {

        super(_charClass, _name, _hp, _attack, _defense);
        InitHero();
    }

    public void InitHero()
    {
        currentExp = 0;
        expToNextLevel = 1000;
        killedMonster = 0;
    }

    public void expGain(int _exp)
    {
        currentExp += _exp;
        System.out.println(name + " получил " + _exp);
        if(currentExp > expToNextLevel)
        {
            currentExp -= expToNextLevel;
            expToNextLevel *= 2;
            level++;
            attack += 5;
            System.out.println("Атака героя повысилась до " + attack + " ед. урона");
            hpMax += 50;
            System.out.println("Запас здоровья героя повысился до " + hpMax);
            hp = hpMax;
            System.out.println(name + " повысил уровень до " + level);
        }

    }

    public void addKillCounter()
    {
        killedMonster++;
    }

    public void ShowFullInfo()
    {

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
