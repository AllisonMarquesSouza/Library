CREATE TABLE reservation(
    id_reservation INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_book INT NOT NULL,
    reservation_date DATE NOT NULL,
    return_date DATE ,
    CONSTRAINT fk_id_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id) ,
    CONSTRAINT fk_id_book FOREIGN KEY(id_book) REFERENCES book(id)
);