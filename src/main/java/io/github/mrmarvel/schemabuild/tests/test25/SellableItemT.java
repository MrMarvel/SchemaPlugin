package io.github.mrmarvel.schemabuild.tests.test25;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SellableItemT implements Sellable {
    @NonNull
    String label;
    int price;

    @Override
    public @NotNull String getLabel() {
        return label;
    }

    @Override
    public boolean sell() {
        return true;
    }
}

interface Sellable {
    public @NotNull String getLabel();
    public boolean sell();
}

@AllArgsConstructor
class SellableDecorator implements Sellable {//Собственно декоратор
    @NonNull
    private Sellable item;
    @NonNull
    private String label;
    private int price;

    @Override
    public @NotNull String getLabel() {
        return label;
    }

    @Override
    public boolean sell() {
        return true;
    }
}

class Building extends SellableDecorator {
    @NonNull
    ArrayList<Item> required_resources;

    public Building(@NonNull Sellable item, @NonNull String label, int price, List<Item> required_resources) {
        super(item, label, price);
        this.required_resources = new ArrayList<>(required_resources);
    }
}

class Service extends SellableDecorator {
    @NonNull
    String desc;

    public Service(@NonNull Sellable item, @NonNull String label, int price, @NotNull String desc) {
        super(item, label, price);
        this.desc = desc;
    }
}