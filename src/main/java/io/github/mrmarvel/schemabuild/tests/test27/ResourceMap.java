package io.github.mrmarvel.schemabuild.tests.test27;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class ResourceMap {
    private HashMap<Material, Integer> resNeeded = new HashMap<>();
    private HashMap<Material, Integer> resGot = new HashMap<>();

    public ResourceMap(Map<Material, Integer> resNeeded, Map<Material, Integer> resGot) {
        this(resNeeded);
        this.resGot.putAll(resGot);
    }
    public ResourceMap(Map<Material, Integer> resNeeded) {
        this.resNeeded.putAll(resNeeded);
    }

    public Map<Material, Integer> getItemGot() {
        return resGot;
    }

    public void addItems(Map<Material, Integer> add) {
        add.forEach((material, integer) -> {
            Integer x = resGot.get(material);
            if (x != null) {
                resGot.put(material, x+integer);
            }
        });
    }
}
