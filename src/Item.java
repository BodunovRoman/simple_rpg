/**
 * Created by BodunovRA on 24.09.2016.
 */


public class Item {

    public static enum ItemType {Consumables, infConsumables, Qest, Armor, Weapon};

    private String name;
    private ItemType type;

    public String getName()
    {
        return name;
    }

    public ItemType getType()
    {
        return type;
    }

    public Item(String _name, ItemType _type)
    {
        name = _name;
        type = _type;
    }


}
