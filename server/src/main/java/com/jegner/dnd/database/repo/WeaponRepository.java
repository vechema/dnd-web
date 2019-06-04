package com.jegner.dnd.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jegner.dnd.model.item.Weapon;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {

}
