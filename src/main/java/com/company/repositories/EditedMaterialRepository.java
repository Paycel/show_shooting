package com.company.repositories;

import com.company.models.EditedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditedMaterialRepository extends JpaRepository<EditedMaterial, Integer> {

    @Override
    Optional<EditedMaterial> findById(Integer integer);
}
