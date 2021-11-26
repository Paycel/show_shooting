package com.company.models.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.company.models.Scenario;
import com.company.models.paging.Direction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ScenarioComparator {

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<Scenario>> map = new HashMap<>();

    static {
        map.put(new Key("id", Direction.asc), Comparator.comparing(Scenario::getId));
        map.put(new Key("id", Direction.desc), Comparator.comparing(Scenario::getId)
                .reversed());

        map.put(new Key("version", Direction.asc), Comparator.comparing(Scenario::getVersion));
        map.put(new Key("version", Direction.desc), Comparator.comparing(Scenario::getVersion)
                .reversed());

        map.put(new Key("link", Direction.asc), Comparator.comparing(Scenario::getLink));
        map.put(new Key("link", Direction.desc), Comparator.comparing(Scenario::getLink)
                .reversed());

        map.put(new Key("approval", Direction.asc), Comparator.comparing(Scenario::getApproval));
        map.put(new Key("approval", Direction.desc), Comparator.comparing(Scenario::getApproval)
                .reversed());
    }

    public static Comparator<Scenario> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }
}
