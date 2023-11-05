-- Create the employees table
CREATE TABLE todos
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    status     VARCHAR(100) NOT NULL,
    created_at VARCHAR(255),
    updated_at VARCHAR(255)
);

INSERT INTO todos (name, status, created_at, updated_at)
VALUES ('밥먹기', 'CREATED', '2023-11-02 10:00:00', '2023-11-02 10:00:00'),
       ('공부하기', 'CREATED', '2023-11-02 10:30:00', '2023-11-02 10:30:00'),
       ('잠자기', 'CREATED', '2023-11-02 10:30:00', '2023-11-02 10:30:00'),
       ('주짓수', 'CREATED', '2023-11-02 10:30:00', '2023-11-02 10:30:00'),
       ('유튜브 촬영', 'CREATED', '2023-11-02 10:30:00', '2023-11-02 10:30:00'),
       ('출근하기', 'CREATED', '2023-11-02 10:30:00', '2023-11-02 10:30:00'),
       ('클라이밍 가기', 'CREATED', '2023-11-02 10:30:00', '2023-11-02 10:30:00'),
       ('공부하기', 'DONE', '2023-11-02 10:30:00', '2023-11-02 10:30:00'),
       ('운동하기', 'CREATED', '2023-11-02 11:00:00', '2023-11-02 11:00:00');
