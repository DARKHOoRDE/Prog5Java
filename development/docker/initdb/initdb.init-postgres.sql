-- User Table
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL
);

-- Role Table
CREATE TABLE roles (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(50) UNIQUE NOT NULL
);

-- User_Role Join Table
CREATE TABLE user_roles (
                            user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                            role_id UUID REFERENCES roles(id) ON DELETE CASCADE,
                            PRIMARY KEY (user_id, role_id)
);

-- Project Table
CREATE TABLE projects (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          name VARCHAR(100) UNIQUE NOT NULL,
                          created_on TIMESTAMP DEFAULT now(),
                          finished_on TIMESTAMP,
                          status VARCHAR(100),
                          created_by VARCHAR(100) NOT NULL
);

-- User_Project Join Table
CREATE TABLE user_projects (
                               user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                               project_id UUID REFERENCES projects(id) ON DELETE CASCADE,
                               PRIMARY KEY (user_id, project_id)
);

-- Sprint Table
CREATE TABLE sprints (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         name VARCHAR(100) NOT NULL,
                         start_date TIMESTAMP DEFAULT now(),
                         end_date TIMESTAMP,
                         project_id UUID REFERENCES projects(id) ON DELETE CASCADE
);


-- Task Table
CREATE TABLE tasks (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       finished_on TIMESTAMP,
                       version INT NOT NULL DEFAULT 0,
                       assigned_to VARCHAR(255),
                       status VARCHAR(255),
                       sprint_id UUID REFERENCES sprints(id) ON DELETE CASCADE
);

INSERT INTO roles (id, name) VALUES

                                 ('b4c3d2e5-f678-90ac-bdef-123456789abc', 'ROLE_USER'),
                                 ('c5d4e3f6-a789-01bc-cdef-23456789abcd', 'ROLE_MANAGER');


INSERT INTO users (id, username, password) VALUES
    ('ac2b0bdd-dc06-4f48-a58a-e210558a489e','Dev1','$2a$10$nkSm/lycG.3jEkIrnoU9q.GiYMbEB20AMvd2PxE0a0BENYyiH74k2'),
    ('444afe8f-c425-42d3-8c2a-79ad56f18daa','Dev2','$2a$10$nkSm/lycG.3jEkIrnoU9q.GiYMbEB20AMvd2PxE0a0BENYyiH74k2'),
    ('d6e5f4c3-b890-12cd-def0-3456789abcde', 'TestManager', '$2a$10$c6rHNgy/yJUZiYqQLiQ7BO1ANOxT8Vnum6OuU0mzBO.Ngd5Hk0WIy');


INSERT INTO user_roles (user_id, role_id) VALUES
    ('444afe8f-c425-42d3-8c2a-79ad56f18daa','b4c3d2e5-f678-90ac-bdef-123456789abc'),
    ('ac2b0bdd-dc06-4f48-a58a-e210558a489e','b4c3d2e5-f678-90ac-bdef-123456789abc'),
    ('d6e5f4c3-b890-12cd-def0-3456789abcde','b4c3d2e5-f678-90ac-bdef-123456789abc'),
    ('d6e5f4c3-b890-12cd-def0-3456789abcde', 'c5d4e3f6-a789-01bc-cdef-23456789abcd');
