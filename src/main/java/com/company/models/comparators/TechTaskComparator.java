package com.company.models.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.company.models.TechTask;
import com.company.models.paging.Direction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class TechTaskComparator {

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<TechTask>> map = new HashMap<>();

    static {
        map.put(new Key("id", Direction.asc), Comparator.comparing(TechTask::getId));
        map.put(new Key("id", Direction.desc), Comparator.comparing(TechTask::getId)
                .reversed());

        map.put(new Key("link", Direction.asc), Comparator.comparing(TechTask::getTaskLink));
        map.put(new Key("link", Direction.desc), Comparator.comparing(TechTask::getTaskLink)
                .reversed());
    }

    public static Comparator<TechTask> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }
}
