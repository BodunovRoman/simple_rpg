/**
 * Created by BodunovRA on 19.09.2016.
 */
public class Hero extends GameCharacter implements Cloneable {

    public Hero(String _charClass, String _name, int _hp, int _attack, int _defense)
    {
        super(_charClass, _name, _hp, _attack, _defense);
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
