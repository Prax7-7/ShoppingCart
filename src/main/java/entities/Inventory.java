package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Inventory class is singleton calls that encapculates inventorylist for shopping as a HashMap
 * InventoryMap has Item Name as key and Unit price as Value
 * It binds the inventoryItems map with basic CRUD operations
 * @author  Prashanth B S
 * @version 1.0
 * @since   09-11-2018
 */
public class Inventory {

    private static Inventory inventoryInstance;
    private HashMap<String, Double> inventoryMap;

    /**
     * Constructor
     */
    private Inventory() {
        inventoryMap = new HashMap<>();
    }

    /**
     * This method adds a new Item to the inventory
     * @param item String type key
     * @param unitPrice non-zero, non-negative double value
     * @return boolean success status
     */
    public boolean addItem(String item, double unitPrice) {
        if(unitPrice == 0){
            System.out.println(String.format("Unit price of item %s cannot be zero, item was not added to inventory",item));
            return false;
        }
        else if(unitPrice<0){
            System.out.println(String.format("Unit price of item %s cannot be negative, item was not added to inventory",item));
            return false;
        }
        else if (inventoryMap.containsKey(item)) {
            System.out.println(String.format("Item %s already present in the inventory ",item));
            return false;
        } else {
            inventoryMap.put(item, unitPrice);
            System.out.println(String.format("Successfully added item %s to the inventory ",item));
            return true;
        }

    }

    /**
     * This method updates an existing item with the new unit price
     * @param item String type key
     * @param newPrice non-zero, non-negative double value
     * @return boolean success status
     */
    public boolean updateUnitPrice(String item, double newPrice) {
        if(newPrice == 0){
            System.out.println(String.format("New Unit price of item %s cannot be zero, item was not updated",item));
            return false;
        }
        else if(newPrice<0){
            System.out.println(String.format("New Unit price of item %s cannot be negative, item was not updated",item));
            return false;
        }
        else if (!inventoryMap.containsKey(item)) {
            System.out.println(String.format("Item %s not present in the inventory ",item));
            return false;
        }else {
            inventoryMap.put(item,newPrice);
            System.out.println(String.format("Successfully updated unit price for the item %s in  the inventory",item));
            return true;
        }
    }

    /**
     * This method removes an existing item from the inventory
     * @param item String type key
     * @return boolean success status
     */
    public boolean deleteItem(String item) {
        if (inventoryMap.containsKey(item)) {
            inventoryMap.remove(item);
            return true;
        } else {
            System.out.println(String.format("Item %s is not present in the Inventory List",item));
            return false;
        }
    }

    /**
     * This method is used to remove all the existing items from the inventory
     * @return nothing
     */
    public void deleteAllItems(){
        if(inventoryMap.size()>0)
            inventoryMap.clear();
    }

    /**
     * This method returns a list of inventory items
     * @return List of inventory items
     */
    public List<String> getItemsList() {
        return new ArrayList<String>(inventoryMap.keySet());
    }

    /**
     * This method searches for an item if present in inventory
     * @param item String type key
     * @return boolean found status
     */
    public boolean isItemPresent(String item){
        return inventoryMap.containsKey(item);
    }

    /**
     * This static method returns Inventory instance
     * @return Inventory instance
     */
    public synchronized static Inventory getInstance(){
        if(inventoryInstance ==null)
            inventoryInstance = new Inventory();

        return inventoryInstance;
    }

    /**
     * This methods returns the unit price for an existing item
     * @param item String type key
     * @return unit price
     * @throws NullPointerException
     */
    public double getUnitPriceForItem(String item) throws NullPointerException{
        if(inventoryMap.containsKey(item))
            return inventoryMap.get(item);
        else throw new NullPointerException(String.format("Item %s is not present in the Inventory List",item));
    }
}
