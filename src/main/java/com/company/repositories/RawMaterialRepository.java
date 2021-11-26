package com.company.repositories;

import com.company.models.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Integer> {

    @Override
    Optional<RawMaterial> findById(Integer integer);
}
