package io.github.mrmarvel.schemabuild.tests.test22;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.entity.Player;

public abstract class SchemaT {
    public String filename;
    public Player owner;
    public abstract int build();
    public SchemaT(Player owner, String filename) {
        this.owner = owner;
        this.filename = filename;
    }
}

enum SchemaType {
    Tree,
    House
}

class Tree extends SchemaT {
    public String treeSpeciesName;

    public Tree(Player owner, String filename) {
        super(owner, filename);
    }

    @Override
    public int build() {
        if (treeSpeciesName.isEmpty()) return -1;
        return 0;
    }
}

class House extends SchemaT {
    public Player author;

    public House(Player owner, String filename) {
        super(owner, filename);
        this.author = owner;
    }

    @Override
    public int build() {
        if (author == null) return -1;
        if (!owner.isOnline()) return -1;
        return 0;
    }
}

class SchemaFactory {
    public SchemaT makeSchema(SchemaType st, Player owner, String filename) {
        switch(st) {
            case Tree:
                return new Tree(owner, filename);
            case House:
                return new House(owner, filename);
        }
        return null;
    }
}