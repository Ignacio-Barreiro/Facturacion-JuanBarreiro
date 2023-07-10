CREATE TABLE IF NOT EXISTS Client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    numero_documento VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS Product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    precio DOUBLE NOT NULL,
    codigo_interno VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS Invoice (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);
CREATE TABLE IF NOT EXISTS InvoiceDetail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_factura BIGINT NOT NULL,
    id_producto BIGINT NOT NULL,
    precio DOUBLE NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (id_factura) REFERENCES Invoice(id),
    FOREIGN KEY (id_producto) REFERENCES Product(id)
);
