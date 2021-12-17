package io.github.mrmarvel.schemabuild.tests.test26;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class DealT {
}

interface Deal { // Стратегия - разные варианты сделок
    public boolean close();
    public boolean open();
}

class Assignment {
    @NonNull
    Deal deal;
    boolean isOpened = false;
    @NonNull
    @Getter
    Subject s1, s2 = new Subject(new Organization("Государство"));

    public Assignment(@NotNull Deal deal, @NotNull Subject s1, @NotNull Subject s2) {
        this(deal, s1);
        this.s2 = s2;
    }

    public Assignment(@NotNull Deal deal, @NotNull Subject s1) {
        this.deal = deal;
        this.s1 = s1;
    }
}

class Subject {
    @Getter
    private Player p = null;
    @Getter
    private boolean isPlayer = false;
    @Getter
    private Organization o = null;
    public Subject(Player p) {
        this.p = p;
        isPlayer = true;
    }
    public Subject(Organization o) {
        this.o = o;
    }
}

@AllArgsConstructor
class Organization {
    @Getter
    @NonNull
    private String name;
}

@AllArgsConstructor
class DealWithoutTime implements Deal { // Сделка, не учитывающие сроки
    @Getter
    private Date dateStart;

    @Override
    public boolean close() {
        return true;
    }

    @Override
    public boolean open() {
        return true;
    }
}

@AllArgsConstructor
class DealWithTime implements Deal { // Сделка, учитывающие сроки
    @Getter
    private Date dateStart;
    @Getter
    private Date dateEnd;

    @Override
    public boolean close() {
        return true;
    }

    @Override
    public boolean open() {
        return true;
    }
}