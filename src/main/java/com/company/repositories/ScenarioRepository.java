package com.company.repositories;

import com.company.models.Scenario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {
    @Override
    Optional<Scenario> findById(Integer integer);

    void deleteById(Integer id);
}
