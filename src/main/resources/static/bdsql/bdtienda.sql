-- Crear la base de datos
CREATE DATABASE bdtienda;

-- Seleccionar la base de datos
USE bdtienda;

-- Crear la tabla Usuarios
CREATE TABLE usuarios (
    idusuario INT AUTO_INCREMENT PRIMARY KEY,
    nomusuario VARCHAR(50),
    apeusuario VARCHAR(50),
    correousuario VARCHAR(100),
    tlfusuario VARCHAR(15),
    dirusuario VARCHAR(255)
);
drop TABLE productos;
-- Crear la tabla Productos
CREATE TABLE productos (
    idproducto INT AUTO_INCREMENT PRIMARY KEY,
    nomidproducto VARCHAR(100),
    descproducto TEXT,
    precioproducto DECIMAL(10, 2),
    stockproducto INT,
    marcaproducto VARCHAR(50)
);
ALTER TABLE productos ADD imagenproducto VARCHAR(255);


-- Crear la tabla Carrito
CREATE TABLE carrito (
    idcarrito INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    producto_id INT,
    cantidad INT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(idusuario),
    FOREIGN KEY (producto_id) REFERENCES Productos(idproducto)
);

-- Crear la tabla Pedidos
CREATE TABLE pedidos (
    idpedidos INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    estadopedido ENUM('pendiente', 'en preparacion', 'enviado', 'entregado', 'cancelado'),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(idusuario)
);

-- Crear la tabla Detalle del Pedido
CREATE TABLE detalle_pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT,
    producto_id INT,
    cantidadpedido INT,
    precio_unitariopedido DECIMAL(10, 2),
    subtotalpedido DECIMAL(10, 2),
    FOREIGN KEY (pedido_id) REFERENCES Pedidos(idpedidos),
    FOREIGN KEY (producto_id) REFERENCES Productos(idproducto)
);
SET SQL_SAFE_UPDATES = 1;

select * from usuarios;
INSERT INTO productos (nomidproducto, descproducto, precioproducto, stockproducto, marcaproducto, imagenproducto) VALUES 
('Arroz', 'Arroz extraído del Norte, de grano largo y fino.', 3.50, 100, 'Campo Norte', 'arroz.jpg'),
('Azúcar', 'Azúcar refinada de caña de azúcar cultivada en la costa peruana.', 2.80, 150, 'Vega', 'azucar.jpg'),
('Aceite Vegetal', 'Aceite vegetal prensado en frío, ideal para cocinar y freír.', 5.90, 80, 'Primor', 'aceite_vegetal.jpg'),
('Fideos', 'Fideos de trigo duro, perfectos para preparar deliciosos platos de pasta.', 2.20, 120, 'Don Vittorio', 'fideos.jpg'),
('Conserva de Atún', 'Atún en conserva en aceite de oliva, fuente de proteínas y omega 3.', 4.50, 90, 'Primor', 'atun.jpg');
