package com.company.models.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.company.models.Actor;
import com.company.models.paging.Direction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ActorComparator {

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<Actor>> map = new HashMap<>();

    static {
        map.put(new Key("id", Direction.asc), Comparator.comparing(Actor::getId));
        map.put(new Key("id", Direction.desc), Comparator.comparing(Actor::getId)
                .reversed());

        map.put(new Key("name", Direction.asc), Comparator.comparing(Actor::getFullName));
        map.put(new Key("name", Direction.desc), Comparator.comparing(Actor::getFullName)
                .reversed());

        map.put(new Key("age", Direction.asc), Comparator.comparing(Actor::getAge));
        map.put(new Key("age", Direction.desc), Comparator.comparing(Actor::getAge)
                .reversed());

        map.put(new Key("number", Direction.asc), Comparator.comparing(Actor::getTelNumber));
        map.put(new Key("number", Direction.desc), Comparator.comparing(Actor::getTelNumber)
                .reversed());

        map.put(new Key("role", Direction.asc), Comparator.comparing(Actor::getRole));
        map.put(new Key("role", Direction.desc), Comparator.comparing(Actor::getRole)
                .reversed());


        map.put(new Key("denyMovie", Direction.asc), Comparator.comparing(Actor::getDenyMovieShooting));
        map.put(new Key("denyMovie", Direction.desc), Comparator.comparing(Actor::getDenyMovieShooting)
                .reversed());

        map.put(new Key("denyScenario", Direction.asc), Comparator.comparing(Actor::getDenyScenario));
        map.put(new Key("denyScenario", Direction.desc), Comparator.comparing(Actor::getDenyScenario)
                .reversed());

        map.put(new Key("scenario_id", Direction.asc), Comparator.comparing(Actor::getScenarioId));
        map.put(new Key("scenario_id", Direction.desc), Comparator.comparing(Actor::getScenarioId)
                .reversed());
    }

    public static Comparator<Actor> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }
}
