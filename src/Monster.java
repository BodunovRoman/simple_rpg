/**
 * Created by BodunovRA on 19.09.2016.
 */
public class Monster extends GameCharacter  implements Cloneable {

    public Monster(String _charClass, String _name, int _hp, int _attack, int _defense)
    {
        super(_charClass, _name, _hp, _attack, _defense);

    }

    public Object clone()
    {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException a)
        {
            System.out.println("Клонирование монстра невозможно");
            return this;
        }

    }
}
