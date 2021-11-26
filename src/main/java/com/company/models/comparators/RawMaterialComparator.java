package com.company.models.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.company.models.RawMaterial;
import com.company.models.paging.Direction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class RawMaterialComparator {

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<RawMaterial>> map = new HashMap<>();

    static {
        map.put(new Key("id", Direction.asc), Comparator.comparing(RawMaterial::getId));
        map.put(new Key("id", Direction.desc), Comparator.comparing(RawMaterial::getId)
                .reversed());

        map.put(new Key("name", Direction.asc), Comparator.comparing(RawMaterial::getName));
        map.put(new Key("name", Direction.desc), Comparator.comparing(RawMaterial::getName)
                .reversed());

        map.put(new Key("duration", Direction.asc), Comparator.comparing(RawMaterial::getDuration));
        map.put(new Key("duration", Direction.desc), Comparator.comparing(RawMaterial::getDuration)
                .reversed());

        map.put(new Key("status", Direction.asc), Comparator.comparing(RawMaterial::getStatus));
        map.put(new Key("status", Direction.desc), Comparator.comparing(RawMaterial::getStatus)
                .reversed());

        map.put(new Key("scenario_id", Direction.asc), Comparator.comparing(RawMaterial::getScenarioId));
        map.put(new Key("scenario_id", Direction.desc), Comparator.comparing(RawMaterial::getScenarioId)
                .reversed());

        map.put(new Key("techtask_id", Direction.asc), Comparator.comparing(RawMaterial::getTechTaskId));
        map.put(new Key("techtask_id", Direction.desc), Comparator.comparing(RawMaterial::getTechTaskId)
                .reversed());
    }

    public static Comparator<RawMaterial> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }
}
