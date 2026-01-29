/*CREATE DATABASE control_prueba
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;*/
  
USE control_acceso;

/*CREATE TABLE datos (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    waiting_time INT NOT NULL,
    unit_time VARCHAR(30) NOT NULL,
    attempts INT NOT NULL,
    open_door_time INT NOT NULL,
    unit_door_time VARCHAR (30) NOT NULL
    );*/
    
/*INSERT INTO datos (waiting_time,unit_time,attempts,open_door_time,unit_door_time)
VALUES 
  (5,'seg',3,5,'seg');*/

/*CREATE TABLE usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  key_pass VARCHAR(100) NOT NULL
);*/

/*INSERT INTO usuarios (`user`, password, key_pass)
VALUES 
    ('admin', '4dm1n', '1234'),
    ('user', 'us3r', '1235'),
    ('anonymous', 'null', 'null');*/

/*CREATE TABLE logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    acceso VARCHAR(30) NOT NULL,

    CONSTRAINT fk_logs_usuario
      FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);*/

/*INSERT INTO logs (usuario_id, fecha_hora, acceso)
VALUES
(1, '2025-01-02 01:12:05', 'OK'),
(1, '2025-01-05 08:15:12', 'OK'),
(2, '2025-01-09 10:10:10', 'OK'),
(3, '2025-01-14 04:22:33', 'FAIL'),
(1, '2025-01-17 12:24:35', 'OK'),
(3, '2025-01-20 00:30:30', 'FAIL'),
(2, '2025-01-29 19:45:00', 'OK'),
(1, '2025-02-03 09:02:01', 'OK'),
(3, '2025-02-11 02:22:22', 'FAIL'),
(2, '2025-02-14 18:45:00', 'OK'),
(2, '2025-02-20 13:05:05', 'OK'),
(2, '2025-02-28 23:15:44', 'OK'),
(1, '2025-03-01 07:05:59', 'OK'),
(3, '2025-03-09 13:13:13', 'FAIL'),
(2, '2025-03-15 06:06:06', 'OK'),
(1, '2025-03-22 21:30:10', 'OK'),
(3, '2025-03-30 11:11:11', 'FAIL'),
(2, '2025-04-04 17:17:17', 'OK'),
(1, '2025-04-10 14:12:33', 'OK'),
(3, '2025-04-18 19:19:59', 'FAIL'),
(1, '2025-04-25 23:59:59', 'OK'),
(2, '2025-05-06 11:11:11', 'OK'),
(3, '2025-05-12 09:09:09', 'FAIL'),
(2, '2025-05-20 08:08:08', 'OK'),
(3, '2025-05-29 21:21:21', 'FAIL'),
(2, '2025-06-02 12:34:56', 'OK'),
(3, '2025-06-14 07:07:07', 'FAIL'),
(1, '2025-06-18 16:00:00', 'OK'),
(2, '2025-06-25 03:21:00', 'OK'),
(3, '2025-07-01 11:11:12', 'FAIL'),
(1, '2025-07-04 00:00:01', 'OK'),
(1, '2025-07-19 13:37:42', 'OK'),
(3, '2025-07-21 16:40:40', 'FAIL'),
(2, '2025-07-30 14:00:00', 'OK'),
(3, '2025-08-05 06:06:30', 'FAIL'),
(2, '2025-08-11 09:45:12', 'OK'),
(3, '2025-08-25 22:22:02', 'FAIL'),
(1, '2025-08-30 19:19:19', 'OK'),
(3, '2025-09-03 09:09:09', 'FAIL'),
(1, '2025-09-12 05:05:05', 'OK'),
(3, '2025-09-18 12:12:12', 'FAIL'),
(2, '2025-09-27 20:20:20', 'OK'),
(2, '2025-10-05 01:02:03', 'OK'),
(3, '2025-10-10 14:14:14', 'FAIL'),
(3, '2025-10-22 03:03:03', 'FAIL'),
(1, '2025-10-31 22:22:22', 'OK'),
(3, '2025-11-02 05:05:55', 'FAIL'),
(1, '2025-11-13 15:15:15', 'OK'),
(3, '2025-11-17 18:18:18', 'FAIL'),
(3, '2025-12-01 20:20:21', 'FAIL');*/

-- DESCRIBE usuarios;
-- DESCRIBE logs;
-- DESCRIBE datos;
-- SELECT * FROM usuarios;
-- SELECT * FROM logs;
-- SELECT * FROM datos;

SELECT
  id,
  usuario_id,
  DATE_FORMAT(fecha_hora, '%d-%m-%Y %H:%i:%s') AS fecha_formato,
  acceso
FROM logs;

/*UPDATE datos 
SET waiting_time = '5',
    unit_time = 'seg',
    attempts = '3',
    open_door_time = '5',
    unit_door_time = 'seg'
WHERE id = '1';*/