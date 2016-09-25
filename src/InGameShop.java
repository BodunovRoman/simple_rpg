/**
 * Created by BodunovRA on 26.09.2016.
 */
public class InGameShop {

    public final int ITEMS_CONST = 3;
    private Item[] itm = new Item[ITEMS_CONST];
    private int[] itmCost = new int[ITEMS_CONST];
    private int goldAmount;

    public InGameShop()
    {
        goldAmount = 2000;
        itm[0] = new Item("Слабое зелье лечения", Item.ItemType.Consumables);
        itmCost[0] = 100;
        itm[1] = new Item("Полуразбитый щит", Item.ItemType.Armor);
        itmCost[1] = 200;
        itm[2] = new Item("Камень удачи", Item.ItemType.infConsumables);
        itmCost[2] = 600;
    }

    public void showItems()
    {
        System.out.println("Ассортимент:");
        for(int i=0; i<ITEMS_CONST; i++)
        {
            System.out.println((i+1) + "." + itm[i].getName() + " - " + itmCost[i]);
        }

    }

    public void buyByHero (int index, Hero h)
    {
        if(h.myInv.isCoinsEnough(itmCost[index]))
        {
            goldAmount += itmCost[index];
            h.myInv.spendCoins(itmCost[index]);
            h.myInv.add(itm[index]);
            System.out.println("Герой купил " + itm[index].getName());
        }
        else
        {
            System.out.println("Недостаточно золота");
        }
    }
 }
