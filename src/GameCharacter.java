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
    protected int attack;
    protected int defence;
    protected int critChance;
    protected int level;
    public boolean isAllive;

    public GameCharacter(String _charClass, String _name, int _hp, int _attack, int _defense)
    {
        name = _name;
        charClass = _charClass;
        hpMax = _hp;
        hp = hpMax;
        attack = _attack;
        defence = _defense;
        critChance = 10;
        level = 1;
        isAllive = true;
    }

    public int getHpMax()
    {
        return hpMax;
    }

    public void ShowInfo()
    {
    System.out.println("Имя: " + name + " Здоровье: " + hp + "/" + hpMax);

    }

    public int makeAttack()
    {
        int minAttack = (int)(attack * 0.8f);
        int deltaAttack = (int)(attack * 0.4f);
        int currentAttack = minAttack + GameClass.rand.nextInt(deltaAttack);
        if (GameClass.rand.nextInt(100) < critChance)
        {
            currentAttack *= 2;
            System.out.println(name + " нанес критический урон в размере " + currentAttack + " единиц урона");
        }
        else
        {
            System.out.println(name + " нанес " + currentAttack + " единиц урона");
        }
        return currentAttack;
    }

    public void getDamage(int _inputDamage)
    {
        System.out.println(name + " получил " + _inputDamage);
        hp -= _inputDamage;
        if(hp < 1)
        {
            isAllive = false;
        }
    }
}
