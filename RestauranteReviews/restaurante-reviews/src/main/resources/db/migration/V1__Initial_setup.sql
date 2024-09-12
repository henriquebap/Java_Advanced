-- Cria a tabela de Usuarios
CREATE TABLE usuarios_review (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  senha VARCHAR(100) NOT NULL
);

-- Cria a tabela de Restaurantes
CREATE TABLE restaurantes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  endereco VARCHAR(255) NOT NULL
);

-- Cria a tabela de Pedidos
CREATE TABLE pedidos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  data DATE NOT NULL,
  valor DECIMAL(10, 2) NOT NULL,
  restaurante_id BIGINT NOT NULL,
  usuario_id BIGINT NOT NULL,
  CONSTRAINT fk_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id),
  CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Cria a tabela de Avaliações
CREATE TABLE avaliacoes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nota INT NOT NULL CHECK (nota BETWEEN 1 AND 5),
  comentario TEXT,
  pedido_id BIGINT NOT NULL,
  CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);