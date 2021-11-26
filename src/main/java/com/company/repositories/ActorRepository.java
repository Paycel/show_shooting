package com.company.repositories;

import com.company.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Override
    Optional<Actor> findById(Integer id);

    void deleteById(Integer id);
}
