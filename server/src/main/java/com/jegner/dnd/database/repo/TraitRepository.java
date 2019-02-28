package com.jegner.dnd.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jegner.dnd.model.predefined.Trait;

@Repository
public interface TraitRepository extends JpaRepository<Trait, Long> {

}
