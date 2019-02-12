package com.jegner.dnd.database.repo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jegner.dnd.utility.GameEntity;

@Repository
public interface GameEntityRepository extends JpaRepository<GameEntity, Long> {

}
