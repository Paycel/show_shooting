package com.company.repositories;

import com.company.models.TechTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechTaskRepository extends JpaRepository<TechTask, Integer> {

    @Override
    Optional<TechTask> findById(Integer id);
}
