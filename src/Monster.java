/**
 * Created by BodunovRA on 19.09.2016.
 */
public class Monster extends GameCharacter  implements Cloneable {

    public Monster(String _charClass, String _name, int _strength, int _dexterity, int _endurance)
    {
        super(_charClass, _name, _strength, _dexterity, _endurance);

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
