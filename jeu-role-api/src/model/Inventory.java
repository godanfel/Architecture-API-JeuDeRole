package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory {
    private List<Item> itemList;
    private static final int MAX_ITEMS = 10;


    public Inventory() {
        this.itemList = new ArrayList<>();
    }

    public void addItem(Item item){
        if (itemList.size() == MAX_ITEMS){
            throw new InventoryInvalidException("Error, inventory is already full");
        }
        itemList.add(item);
    }

    public void useItem(Item item, Hero hero){
        if(!itemList.contains(item)){
            throw new InventoryInvalidException("Error, hero does not own this item");
        }

        item.use(hero);

        if(item.isConsumedOnUse()){
            itemList.remove(item);
        }
    }

    public List<Item> getItemList() {
        return Collections.unmodifiableList(itemList);
    }

    public boolean contains(Item item) {
        return itemList.contains(item);
    }
}
