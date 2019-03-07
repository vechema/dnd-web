package com.jegner.dnd.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jegner.dnd.model.item.EquipmentPack;

@Repository
public interface EquipmentPackRepository extends JpaRepository<EquipmentPack, Long> {

}
