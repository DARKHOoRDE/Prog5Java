package org.example.dma6m6beadando.database;

import org.example.dma6m6beadando.entity.Project;
import org.example.dma6m6beadando.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query(value = "SELECT p.* FROM projects p " +
            "JOIN user_projects up ON p.id = up.project_id " +
            "WHERE up.user_id = :userId", nativeQuery = true)
    List<Project> findProjectsByUserIdNative(@Param("userId") UUID userId);

}
