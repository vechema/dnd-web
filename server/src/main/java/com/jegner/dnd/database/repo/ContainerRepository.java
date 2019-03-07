package com.jegner.dnd.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jegner.dnd.model.item.Container;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {

}
