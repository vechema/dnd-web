package com.jegner.dnd.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jegner.dnd.model.magic.Spell;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {

}
