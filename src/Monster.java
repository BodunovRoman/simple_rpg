/**
 * Created by BodunovRA on 19.09.2016.
 */
public class Monster extends GameCharacter  implements Cloneable {

    public Monster(String _charClass, String _name, int _strength, int _dexterity, int _endurance)
    {
        super(_charClass, _name, _strength, _dexterity, _endurance);
        myInv = new Inventory();
        myInv.add(new Item("Слабое зелье лечения", Item.ItemType.Consumables));
        myInv.addSomeCoins(100);
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

    public void lvlUp(int _1)
    {
        for (int i=0; i<_1;i++) {
            ShowInfo();
            strength += base_strength * 0.3f;
            dexterity += base_dexterity * 0.3f;
            endurance += base_endurance * 0.3f;
            CalculateSecondaryParamiters();
            hp = hpMax;
            ShowInfo();
        }
    }
}
