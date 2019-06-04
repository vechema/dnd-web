package com.jegner.dnd.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jegner.dnd.utility.GameEntity;

@Repository
public interface GameEntityRepository extends JpaRepository<GameEntity, Long> {

}
