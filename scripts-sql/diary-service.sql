CREATE DATABASE IF NOT EXISTS mydiary_db;
USE mydiary_db;

CREATE TABLE IF NOT EXISTS diary_entries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    mood VARCHAR(50) NULL,
    location VARCHAR(255) NULL,
    weather VARCHAR(100) NULL,
    tags VARCHAR(500) NULL,
    is_favorite BOOLEAN DEFAULT FALSE,
    is_private BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_user_id (user_id),
    INDEX idx_created_at (created_at),
    INDEX idx_mood (mood),
    INDEX idx_is_favorite (is_favorite)
);

INSERT INTO diary_entries (title, content, user_id, mood, location, weather, tags, is_favorite, is_private) 
VALUES 
('Mi primer día', 'Hoy fue un día increíble. Comencé mi nuevo proyecto y todo salió mejor de lo esperado.', 1, 'HAPPY', 'Lima, Perú', 'Soleado', 'trabajo,logros', true, false),
('Reflexiones nocturnas', 'A veces necesito tiempo para pensar sobre mi futuro y mis metas.', 1, 'NEUTRAL', 'Lima, Perú', 'Nublado', 'reflexión,metas', false, true),
('Día en familia', 'Pasé todo el día con mi familia. Fue muy reconfortante estar con ellos.', 1, 'GRATEFUL', 'Cusco, Perú', 'Lluvioso', 'familia,felicidad', true, false);