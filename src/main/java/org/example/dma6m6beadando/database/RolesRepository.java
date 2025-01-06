package org.example.dma6m6beadando.database;


import org.example.dma6m6beadando.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;


@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID> {

    Roles findByName(String role);

}
