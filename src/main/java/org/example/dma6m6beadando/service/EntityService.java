package org.example.dma6m6beadando.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.dma6m6beadando.Dto.*;
import org.example.dma6m6beadando.database.ProjectRepository;
import org.example.dma6m6beadando.database.SprintRepository;
import org.example.dma6m6beadando.database.TaskRepository;
import org.example.dma6m6beadando.database.UserRepository;
import org.example.dma6m6beadando.entity.Project;
import org.example.dma6m6beadando.entity.Sprint;
import org.example.dma6m6beadando.entity.Task;
import org.example.dma6m6beadando.service.mapping.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EntityService {


    private final UserRepository userRepository;
    private final UserListDTOMapper userListDTOMapper;
    private final AuthService authService;
    private final NewSprintMapper newSprintMapper;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final SprintRepository sprintRepository;
    private final NewTaskMapper newTaskMapper;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskMapperToEnt taskMapperToEnt;
    private final SprintMapper sprintMapper;

    public EntityService(UserRepository userRepository,
                         UserListDTOMapper userListDTOMapper,
                         AuthService authService, NewSprintMapper newSprintMapper,
                         ProjectRepository projectRepository, ProjectMapper projectMapper,
                         SprintRepository sprintRepository,
                         NewTaskMapper newTaskMapper, TaskRepository taskRepository, TaskMapper taskMapper, TaskMapperToEnt taskMapperToEnt, SprintMapper sprintMapper) {
        this.userRepository = userRepository;
        this.userListDTOMapper = userListDTOMapper;
        this.authService = authService;
        this.newSprintMapper = newSprintMapper;


        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.sprintRepository = sprintRepository;
        this.newTaskMapper = newTaskMapper;

        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.taskMapperToEnt = taskMapperToEnt;
        this.sprintMapper = sprintMapper;
    }

    @PreAuthorize("hasRole('MANAGER')")
    public void deleteTask(UUID taskId,UUID sprintId) {
        var task = taskRepository.findById(taskId).orElseThrow(EntityNotFoundException::new);
        var sprint = sprintRepository.findById(sprintId).orElseThrow(()->new EntityNotFoundException("Sprint not found"));
        sprint.getTasks().remove(task);
        taskRepository.delete(task);
        sprintRepository.save(sprint);
    }

    @PreAuthorize("hasRole('MANAGER')")
    public TaskDTO createTask(NewTaskDTO newTaskDTO) {
        var task = newTaskMapper.ToEnt(newTaskDTO);
        task.setSprint(sprintRepository.findById(newTaskDTO.getSprintId()).orElse(null));
        task.setStatus("Open");
        taskRepository.save(task);
        var dto = taskMapper.toDto(taskRepository.findById(task.getId()).orElseThrow(()->new EntityNotFoundException("Task not found")));
        return dto;
    }


    public TaskDTO moveTask(TaskDTO taskDTO) {

        try {
            var task = taskMapperToEnt.ToEnt(taskDTO);
            if(task.getStatus().equals("Finished")) {
                task.setFinishedOn(new Date());
            }
            task.setSprint(sprintRepository.findById(taskDTO.getSprintId()).orElse(null));
            taskRepository.save(task);
            return taskMapper.toDto(taskRepository.findById(task.getId())
                    .orElseThrow(()->new EntityNotFoundException("Task not found")));
        }catch (Exception e){
            throw new RuntimeException("Task was already moved to another stage");
        }


    }


    public TaskDTO unassignTask(TaskDTO taskDTO) {

        try {
            var task = taskMapperToEnt.ToEnt(taskDTO);
            task.setStatus("Open");
            task.setAssignedTo(null);
            task.setSprint(sprintRepository.findById(taskDTO.getSprintId()).orElseThrow(()->new EntityNotFoundException("Sprint not found")));
            taskRepository.save(task);
            var entity = taskMapper.toDto(taskRepository.findById(task.getId()).orElse(null));
            return entity;
        }catch (Exception e){
            throw new RuntimeException("Task was already unassigned");
        }


    }


    public TaskDTO assignToUser(TaskDTO taskDTO){
        try {

            var task = taskMapperToEnt.ToEnt(taskDTO);
            task.setSprint(sprintRepository.findById(taskDTO.getSprintId())
                    .orElseThrow(()->new EntityNotFoundException("Sprint not found")));

            task.setStatus("Assigned");

            taskRepository.save(task);
            var entity = "";
            return taskMapper.toDto(taskRepository.findById(task.getId()).orElse(null));
        }catch (Exception e){
            throw new RuntimeException("Task was already assigned to somebody");
        }
    }

    public List<UserListDTO> getProjParticipants(UUID projId){
        List<UserListDTO> currU = userRepository.findUsernamesByProjectId(projId);
        return currU;
    }

    @PreAuthorize("hasRole('MANAGER')")
    public SprintDTO createNewSprint(NewSprintDTO newSprintDTO){
        var sprint = newSprintMapper.ToEnt(newSprintDTO);
        sprint.setProject(projectRepository.findById(newSprintDTO.getProjId()).orElse(null));
        var tasks = getUnfinishedTasks(newSprintDTO.getProjId());

        sprintRepository.save(sprint);

        if(!tasks.isEmpty()){
            for(var task : tasks){
                task.setSprint(sprint);
            }
            taskRepository.saveAll(tasks);
            sprint.setTasks(tasks);
            sprintRepository.save(sprint);
        }
        var retSprint = sprintRepository.findById(sprint.getId()).orElse(null);
        return sprintMapper.toDto(retSprint);
    }

    private List<Task> getUnfinishedTasks(UUID projId) {
        try {
            var proj = projectRepository.findById(projId).orElseThrow(()->new EntityNotFoundException("Project not found"));

            var previousSprint = proj.getSprints().stream()
                    .max(Comparator.comparing(Sprint::getStartDate)).orElseThrow(()->new EntityNotFoundException("Sprint not found"));

            var unfinishedTasks = previousSprint.getTasks().stream()
                    .filter(task -> !task.getStatus().equals("Finished")).collect(Collectors.toList());

            return unfinishedTasks;

        }catch (Exception e){
            return List.of();
        }


    }

    public List<ProjectDTO> getProjectsForUser(UUID userId){
        return projectMapper.toDto(projectRepository.findProjectsByUserIdNative(userId));
    }

    public void FinishProject(UUID id) {
        var proj = projectRepository.findById(id).orElseThrow(()-> new RuntimeException("Project not found"));
        proj.setStatus("Finished");
        proj.setFinishedOn(new Date());
        projectRepository.save(proj);
    }

    public void CreateProj(NewProjDTO newProjDTO) {
        var users = userRepository.findAllById(newProjDTO.getUserIds());
        var currU = userRepository.findByUsername(authService.getAuthenticatedUser()).orElseThrow();

        var proj = new Project();
        proj.setName(newProjDTO.getName());
        proj.setCreatedBy(currU.getUsername());
        proj.setStatus("Open");
        projectRepository.save(proj);

        for (var user : users) {
            if(!user.getProjects().contains(proj)) {
                user.getProjects().add(proj);
            }
        }

        if(!currU.getProjects().contains(proj)) {
            currU.getProjects().add(proj);
            users.add(currU);
        }


        userRepository.saveAll(users);

    }

    public List<UserListDTO> GetUserList(){

        var users = userListDTOMapper.toDto(userRepository.findAll());
        return users;
    }



}
