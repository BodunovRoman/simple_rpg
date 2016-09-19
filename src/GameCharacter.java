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
    protected int attack;
    protected int defence;

    public GameCharacter(String _name, String _charClass, int _hp, int _attack, int _defense)
    {
        name = _name;
        charClass = _charClass;
        hp = _hp;
        attack = _attack;
        defence = _defense;
    }

    public void ShowInfo()
    {

    }
}
