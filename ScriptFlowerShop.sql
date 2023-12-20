-- MySQL Script generated by MySQL Workbench
-- Wed Dec 20 09:31:17 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`FlowerShop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`FlowerShop` (
  `idFlowerShop` INT NOT NULL AUTO_INCREMENT,
  `name` INT NOT NULL,
  `total_Stock` INT NULL,
  PRIMARY KEY (`idFlowerShop`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Flowers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Flowers` (
  `idFlowers` INT NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `stock` INT NOT NULL AUTO_INCREMENT,
  `FlowerShop_idFlowerShop` INT NOT NULL,
  `Ticket_idTicket` INT NOT NULL,
  `Ticket_FlowerShop_idFlowerShop` INT NOT NULL,
  PRIMARY KEY (`idFlowers`, `FlowerShop_idFlowerShop`, `Ticket_idTicket`, `Ticket_FlowerShop_idFlowerShop`),
  INDEX `fk_Flowers_FlowerShop1_idx` (`FlowerShop_idFlowerShop` ASC) VISIBLE,
  INDEX `fk_Flowers_Ticket1_idx` (`Ticket_idTicket` ASC, `Ticket_FlowerShop_idFlowerShop` ASC) VISIBLE,
  CONSTRAINT `fk_Flowers_FlowerShop1`
    FOREIGN KEY (`FlowerShop_idFlowerShop`)
    REFERENCES `mydb`.`FlowerShop` (`idFlowerShop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Flowers_Ticket1`
    FOREIGN KEY (`Ticket_idTicket` , `Ticket_FlowerShop_idFlowerShop`)
    REFERENCES `mydb`.`Ticket` (`idTicket` , `FlowerShop_idFlowerShop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tree` (
  `idTree` INT NOT NULL,
  `height` SMALLINT(100) NOT NULL,
  `price` FLOAT NOT NULL,
  `stock` INT NOT NULL AUTO_INCREMENT,
  `FlowerShop_idFlowerShop` INT NOT NULL,
  `Ticket_idTicket` INT NOT NULL,
  `Ticket_FlowerShop_idFlowerShop` INT NOT NULL,
  PRIMARY KEY (`idTree`, `FlowerShop_idFlowerShop`, `Ticket_idTicket`, `Ticket_FlowerShop_idFlowerShop`),
  INDEX `fk_Tree_FlowerShop1_idx` (`FlowerShop_idFlowerShop` ASC) VISIBLE,
  INDEX `fk_Tree_Ticket1_idx` (`Ticket_idTicket` ASC, `Ticket_FlowerShop_idFlowerShop` ASC) VISIBLE,
  CONSTRAINT `fk_Tree_FlowerShop1`
    FOREIGN KEY (`FlowerShop_idFlowerShop`)
    REFERENCES `mydb`.`FlowerShop` (`idFlowerShop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tree_Ticket1`
    FOREIGN KEY (`Ticket_idTicket` , `Ticket_FlowerShop_idFlowerShop`)
    REFERENCES `mydb`.`Ticket` (`idTicket` , `FlowerShop_idFlowerShop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Decoration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Decoration` (
  `idDecoration` INT NOT NULL,
  `type` ENUM('Wood', 'Plastic') NOT NULL,
  `price` FLOAT NOT NULL,
  `stock` INT NOT NULL AUTO_INCREMENT,
  `FlowerShop_idFlowerShop` INT NOT NULL,
  `Ticket_idTicket` INT NOT NULL,
  `Ticket_FlowerShop_idFlowerShop` INT NOT NULL,
  PRIMARY KEY (`idDecoration`, `FlowerShop_idFlowerShop`, `Ticket_idTicket`, `Ticket_FlowerShop_idFlowerShop`),
  INDEX `fk_Decoration_FlowerShop1_idx` (`FlowerShop_idFlowerShop` ASC) VISIBLE,
  INDEX `fk_Decoration_Ticket1_idx` (`Ticket_idTicket` ASC, `Ticket_FlowerShop_idFlowerShop` ASC) VISIBLE,
  CONSTRAINT `fk_Decoration_FlowerShop1`
    FOREIGN KEY (`FlowerShop_idFlowerShop`)
    REFERENCES `mydb`.`FlowerShop` (`idFlowerShop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Decoration_Ticket1`
    FOREIGN KEY (`Ticket_idTicket` , `Ticket_FlowerShop_idFlowerShop`)
    REFERENCES `mydb`.`Ticket` (`idTicket` , `FlowerShop_idFlowerShop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ticket` (
  `idTicket` INT NOT NULL AUTO_INCREMENT,
  `id_product` INT NOT NULL,
  `product_type` VARCHAR(45) NOT NULL,
  `quantity` SMALLINT(100) NOT NULL,
  `unit_price` FLOAT NOT NULL,
  `total_purchase` INT NOT NULL,
  `FlowerShop_idFlowerShop` INT NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTicket`, `FlowerShop_idFlowerShop`),
  INDEX `fk_Ticket_FlowerShop_idx` (`FlowerShop_idFlowerShop` ASC) VISIBLE,
  INDEX `fk_id_tree_idx` (`id_product` ASC) VISIBLE,
  CONSTRAINT `fk_Ticket_FlowerShop`
    FOREIGN KEY (`FlowerShop_idFlowerShop`)
    REFERENCES `mydb`.`FlowerShop` (`idFlowerShop`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_flower`
    FOREIGN KEY (`id_product`)
    REFERENCES `mydb`.`Flowers` (`idFlowers`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_tree`
    FOREIGN KEY (`id_product`)
    REFERENCES `mydb`.`Tree` (`idTree`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_decoration`
    FOREIGN KEY (`id_product`)
    REFERENCES `mydb`.`Decoration` (`idDecoration`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
