package io.github.mrmarvel.schemabuild.tests.test23;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.*;

public class Building {
    private String name;
    private Player owner;
    private List<Item> requiredResources;
    public Building(String name, Player owner, List<Item> requiredResources) {
        this.name = name;
        this.owner = owner;
        this.requiredResources = new ArrayList<>(requiredResources);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return name.equals(building.name) &&
                owner.equals(building.owner) &&
                requiredResources.equals(building.requiredResources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner, requiredResources);
    }
}
