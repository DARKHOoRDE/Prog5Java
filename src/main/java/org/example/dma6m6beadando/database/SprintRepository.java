package org.example.dma6m6beadando.database;

import org.example.dma6m6beadando.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, UUID> {
}
