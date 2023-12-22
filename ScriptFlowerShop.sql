-- Schema FlowerShop
CREATE SCHEMA IF NOT EXISTS FlowerShop;

-- Table FlowerShop.FlowerShop
CREATE TABLE IF NOT EXISTS FlowerShop.FlowerShop(
                                               idFlowerShop INT AUTO_INCREMENT PRIMARY KEY,
                                               name VARCHAR(255) NOT NULL,
    total_Stock INT
);

-- Table FlowerShop.Flowers
CREATE TABLE IF NOT EXISTS FlowerShop.Flowers(
                                            idFlowers INT AUTO_INCREMENT PRIMARY KEY,
                                            color VARCHAR(45) NOT NULL,
    price FLOAT NOT NULL,
    FlowerShop_idFlowerShop INT NOT NULL,
    Ticket_idTicket INT NOT NULL,
    Ticket_FlowerShop_idFlowerShop INT NOT NULL,
    CONSTRAINT fk_Flowers_FlowerShop1
    FOREIGN KEY (FlowerShop_idFlowerShop)
    REFERENCES FlowerShop.FlowerShop (idFlowerShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Table FlowerShop.Tree
CREATE TABLE IF NOT EXISTS FlowerShop.Tree(
                                         idTree INT AUTO_INCREMENT PRIMARY KEY,
                                         height SMALLINT NOT NULL,
                                         price FLOAT NOT NULL,
                                         FlowerShop_idFlowerShop INT NOT NULL,
                                         Ticket_idTicket INT NOT NULL,
                                         Ticket_FlowerShop_idFlowerShop INT NOT NULL,
                                         CONSTRAINT fk_Tree_FlowerShop1
                                         FOREIGN KEY (FlowerShop_idFlowerShop)
    REFERENCES FlowerShop.FlowerShop (idFlowerShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Table FlowerShop.Decoration
CREATE TABLE IF NOT EXISTS FlowerShop.Decoration(
                                               idDecoration INT PRIMARY KEY,
                                               type VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    FlowerShop_idFlowerShop INT NOT NULL,
    Ticket_idTicket INT NOT NULL,
    Ticket_FlowerShop_idFlowerShop INT NOT NULL,
    CONSTRAINT fk_Decoration_FlowerShop1
    FOREIGN KEY (FlowerShop_idFlowerShop)
    REFERENCES FlowerShop.FlowerShop (idFlowerShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Table FlowerShop.Ticket
CREATE TABLE IF NOT EXISTS FlowerShop.Ticket(
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
    REFERENCES FlowerShop.FlowerShop (idFlowerShop)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_id_flower
    FOREIGN KEY (id_product)
    REFERENCES FlowerShop.Flowers (idFlowers)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT fk_id_tree
    FOREIGN KEY (id_product)
    REFERENCES FlowerShop.Tree (idTree)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT fk_id_decoration
    FOREIGN KEY (id_product)
    REFERENCES FlowerShop.Decoration (idDecoration)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);