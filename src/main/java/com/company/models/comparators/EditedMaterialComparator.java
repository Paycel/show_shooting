package com.company.models.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.company.models.EditedMaterial;
import com.company.models.paging.Direction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class EditedMaterialComparator {

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<EditedMaterial>> map = new HashMap<>();

    static {
        map.put(new Key("id", Direction.asc), Comparator.comparing(EditedMaterial::getId));
        map.put(new Key("id", Direction.desc), Comparator.comparing(EditedMaterial::getId)
                .reversed());

        map.put(new Key("name", Direction.asc), Comparator.comparing(EditedMaterial::getName));
        map.put(new Key("name", Direction.desc), Comparator.comparing(EditedMaterial::getName)
                .reversed());

        map.put(new Key("duration", Direction.asc), Comparator.comparing(EditedMaterial::getDuration));
        map.put(new Key("duration", Direction.desc), Comparator.comparing(EditedMaterial::getDuration)
                .reversed());

        map.put(new Key("status", Direction.asc), Comparator.comparing(EditedMaterial::getStatus));
        map.put(new Key("status", Direction.desc), Comparator.comparing(EditedMaterial::getStatus)
                .reversed());

        map.put(new Key("raw_id", Direction.asc), Comparator.comparing(EditedMaterial::getRawMaterialId));
        map.put(new Key("raw_id", Direction.desc), Comparator.comparing(EditedMaterial::getRawMaterialId)
                .reversed());
    }

    public static Comparator<EditedMaterial> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }
}
