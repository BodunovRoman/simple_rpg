import java.util.ArrayList;

/**
 * Created by BodunovRA on 23.09.2016.
 */
public class Inventory {

    private int gold;

    ArrayList<Item> inv;

    public Inventory()
    {
        gold = 0;
        inv = new ArrayList<>();
    }

    public void addSomeCoins(int amount){gold += amount;}
    public void spendCoins (int amount){gold -= amount;}

    public boolean isCoinsEnough(int amount)
    {
        if(gold>=amount) return true;
        return false;
    }


    public void add(Item _newItem)
    {
        inv.add(_newItem);
    }

    public void showAllItems()
    {
        System.out.println("Инвентарь:");
        if (inv.size() > 0) {
            for (int i = 0; i < inv.size(); i++) {
                System.out.println((i+1) + ". " + inv.get(i).getName());
            }
        }
        else
        {
            System.out.println("Инвентарь пуст");
        }
        System.out.println("Золото: " + gold);
        System.out.println("0. Закончить осмотр");
    }

    public String useItem(int _itemID)
    {
        if (_itemID == 0)
            return "";
        String a = inv.get(_itemID - 1).getName();
        if(inv.get(_itemID - 1).getType() == Item.ItemType.Consumables)
        inv.remove(_itemID - 1);
        return a;
    }

    public int getSize()
    {
        return inv.size();
    }

    public void transferAllItemsToAnotherInventory(Inventory _inv)
    {
        for (int i=0; i<inv.size(); i++)
        {
            _inv.add(inv.get(i));
            _inv.addSomeCoins(gold);
        }
    }

}
