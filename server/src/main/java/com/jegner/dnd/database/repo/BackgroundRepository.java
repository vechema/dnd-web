package com.jegner.dnd.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jegner.dnd.model.predefined.Background;

@Repository
public interface BackgroundRepository extends JpaRepository<Background, Long> {
}
