package models.helpers;

import models.enums.EquipmentType;

import java.util.EnumMap;
import java.util.Map;

public class RestaurantEquipmentHelper {
    private final Map<EquipmentType, Integer> equipment;

    public RestaurantEquipmentHelper() {
        this.equipment = new EnumMap<>(EquipmentType.class);
        for (EquipmentType type : EquipmentType.values()) {
            equipment.put(type, 0);
        }
    }

    public void addEquipment(EquipmentType type, int count) {
        equipment.merge(type, count, Integer::sum);
    }

    public int getEquipmentCount(EquipmentType type) {
        return equipment.getOrDefault(type, 0);
    }

    public boolean hasBasicKitchenEquipment() {
        return getEquipmentCount(EquipmentType.KITCHEN) >= 1;
    }

    public int getMaxGuests() {
        return getEquipmentCount(EquipmentType.TABLE) * 4;
    }

    public int getMaxStorageCapacity() {
        int fridge = getEquipmentCount(EquipmentType.FRIDGE) * 50;
        int freezer = getEquipmentCount(EquipmentType.FREEZER) * 100;
        return fridge + freezer;
    }

    public boolean restaurantCanOperate() {
        return hasBasicKitchenEquipment();
    }

    public int getTableCount(){
        return getEquipmentCount(EquipmentType.TABLE);
    }

}
