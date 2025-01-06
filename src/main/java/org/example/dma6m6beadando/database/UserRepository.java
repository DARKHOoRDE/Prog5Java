package org.example.dma6m6beadando.database;


import org.example.dma6m6beadando.Dto.UserListDTO;
import org.example.dma6m6beadando.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@EnableJpaAuditing
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT new org.example.dma6m6beadando.Dto.UserListDTO(u.id, u.username) " +
            "FROM UserEntity u JOIN u.projects p WHERE p.id = :projId")
    List<UserListDTO> findUsernamesByProjectId(@Param("projId") UUID projId);

    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

}
