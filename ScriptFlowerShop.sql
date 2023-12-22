-- Schema mydb
CREATE SCHEMA IF NOT EXISTS mydb;

-- Table mydb.FlowerShop
CREATE TABLE IF NOT EXISTS mydb.FlowerShop (
                                               idFlowerShop INT AUTO_INCREMENT PRIMARY KEY,
                                               name VARCHAR(255) NOT NULL,
    total_Stock INT
    );

-- Table mydb.Flowers
CREATE TABLE IF NOT EXISTS mydb.Flowers (
                                            idFlowers INT AUTO_INCREMENT PRIMARY KEY,
                                            color VARCHAR(45) NOT NULL,
    price FLOAT NOT NULL,
    FlowerShop_idFlowerShop INT NOT NULL,
    Ticket_idTicket INT NOT NULL,
    Ticket_FlowerShop_idFlowerShop INT NOT NULL,
    CONSTRAINT fk_Flowers_FlowerShop1
    FOREIGN KEY (FlowerShop_idFlowerShop)
    REFERENCES mydb.FlowerShop (idFlowerShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- Table mydb.Tree
CREATE TABLE IF NOT EXISTS mydb.Tree (
                                         idTree INT AUTO_INCREMENT PRIMARY KEY,
                                         height SMALLINT NOT NULL,
                                         price FLOAT NOT NULL,
                                         FlowerShop_idFlowerShop INT NOT NULL,
                                         Ticket_idTicket INT NOT NULL,
                                         Ticket_FlowerShop_idFlowerShop INT NOT NULL,
                                         CONSTRAINT fk_Tree_FlowerShop1
                                         FOREIGN KEY (FlowerShop_idFlowerShop)
    REFERENCES mydb.FlowerShop (idFlowerShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- Table mydb.Decoration
CREATE TABLE IF NOT EXISTS mydb.Decoration (
                                               idDecoration INT PRIMARY KEY,
                                               type VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    FlowerShop_idFlowerShop INT NOT NULL,
    Ticket_idTicket INT NOT NULL,
    Ticket_FlowerShop_idFlowerShop INT NOT NULL,
    CONSTRAINT fk_Decoration_FlowerShop1
    FOREIGN KEY (FlowerShop_idFlowerShop)
    REFERENCES mydb.FlowerShop (idFlowerShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- Table mydb.Ticket
CREATE TABLE IF NOT EXISTS mydb.Ticket (
                                           idTicket INT AUTO_INCREMENT PRIMARY KEY,
                                           id_product INT NOT NULL,
                                           product_type VARCHAR(45) NOT NULL,
    quantity SMALLINT NOT NULL,
    unit_price FLOAT NOT NULL,
    total_purchase INT NOT NULL,
    FlowerShop_idFlowerShop INT NOT NULL,
    date VARCHAR(45) NOT NULL,
    CONSTRAINT fk_Ticket_FlowerShop
    FOREIGN KEY (FlowerShop_idFlowerShop)
    REFERENCES mydb.FlowerShop (idFlowerShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_id_flower
    FOREIGN KEY (id_product)
    REFERENCES mydb.Flowers (idFlowers)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT fk_id_tree
    FOREIGN KEY (id_product)
    REFERENCES mydb.Tree (idTree)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT fk_id_decoration
    FOREIGN KEY (id_product)
    REFERENCES mydb.Decoration (idDecoration)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    );