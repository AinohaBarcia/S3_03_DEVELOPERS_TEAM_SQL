package org.example;


import java.sql.Connection;

import static org.example.Methods.addProductTicket;

public class Menu {

    public static byte showMenu() {

        byte option;
        final byte MIN = 0;
        final byte MAX = 14;

        do {
            System.out.println("Application menu: \r\n"
                    + "1. Add FlowerShop. \r\n"
                    + "2. Add Tree. \r\n"
                    + "3. Add Flower.\r\n"
                    + "4. Add Decoration. \r\n"
                    + "5. Stock: Print all Trees, flowers and Decoration. \r\n"
                    + "6. Remove a Tree item \r\n"
                    + "7. Remove a Flower item. \r\n"
                    + "8. Remove a Decoration item. \r\n"
                    + "9. Print stock with quantities. \r\n"
                    + "10. Print stock total value. \r\n"
                    + "11. Create a ticket with the total items. \r\n"
                    + "12. Show old purchases. \r\n"
                    + "13. View the total money earned from all sales. \r\n"
                    + "14. Restart DataBase. \r\n"
                    + "0. Exit app.");
            option = Input.getByte("Choose the section you want to access:");
            if (option < MIN || option > MAX) {
                System.out.println("Choose a valid option");
            }
        } while (option < MIN || option > MAX);
        return option;
    }

    public static void choseMenu(Connection con){
        boolean exit = false;
        do {
            switch (showMenu()) {
                case 1:
                    System.out.println("Menu 1 - Add FlowerShop.");
                    Methods.createNewFlowerShop(con);
                    break;
                case 2:
                    System.out.println("Menu 1 - Add Tree.");
                    Methods.createTree(con,Methods.searchFlowerShop(con));
                    break;
                case 3:
                    System.out.println("Menu 2 - Add Flower.");
                    Methods.createFlower(con,Methods.searchFlowerShop(con));
                    break;
                case 4:
                    System.out.println("Menu 3 - Add Decoration.");
                    Methods.createDecoration(con,Methods.searchFlowerShop(con));
                    break;
                case 5:
                    System.out.println("Menu 4 - Stock: Print all Trees, Flowers and Decoration.");
                    Methods.printStock(con);
                    break;
                case 6:
                    System.out.println("Menu 5 - Remove a Tree item.");
                    Methods.deleteTree(con);
                    break;
                case 7:
                    System.out.println("Menu 6 - Remove a Flower item.");
                    Methods.deleteFlower(con);
                    break;
                case 8:
                    System.out.println("Menu 7 - Remove a Decoration item.");
                    Methods.deleteDecoration(con);
                    break;
                case 9:
                    System.out.println("Menu 8 - Print stock with quantities.");
                    Methods.printProductCount(con);
                    break;
                case 10:
                    System.out.println("Menu 9 - Print stock total value.");
                    Methods.stockTotalValue(con);
                    break;
                case 11:
                    System.out.println("Menu 10 - Create a ticket with the total items.");
                    Methods.createTicketAndAddProducts(con,Methods.searchFlowerShop(con));
                    break;
                case 12:
                    System.out.println("Menu 11 - Show old purchases.");
                    Methods.showOldPurchases(con);
                    break;
                case 13:
                    System.out.println("Menu 12 - View the total money earned from all sales.");
                    Methods.showTotalEarnings(con);
                    break;
                case 14:
                    System.out.println("Menu 13 - Restart DataBase. (work in progress)");//TODO create method for restart database.
                    break;
                case 0:
                    System.out.println("Thank you for use the app.");
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public static int menuTicket(){
        return Input.getInt("1. Add product. \r\n"
                + "0. Exit. \r\n"
                + "Choose option:");
    }

    public static void chooseMenuTicket(Connection con, int idTicket,int idFlowershop){
        boolean exit = false;
        do {
            int menu = menuTicket();
            switch (menu){
                case 1:
                    Methods.addProductTicket(con,idTicket,idFlowershop);
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }while (!exit);
        System.out.println("Ticket:");

    }

    public static void chooseMenuProduct(Connection con, int idTicket,int idFlowershop){
        boolean exit = false;
        do {
            int menu = showMenuProduct();
            switch (menu){
                case 1:
                    //Methods.addTreeToTicket(con,idTicket,idFlowershop);
                case 2:
                   // Methods.addFlowerToTicket(con,idTicket,idFlowershop);
                case 3:
                   // Methods.addDecorationToTicket(con,idTicket,idFlowershop);
                case 0:
                    exit = true;
                    break;
            }
        }while (!exit);

    }

    public static int showMenuProduct(){
        return Input.getInt("Select a type of product. \r\n"
                + "1. Add tree on ticket. \r\n"
                + "2. Add flower on ticket. \r\n"
                + "3. Add decoration on ticket. \r\n"
                + "0. Exit. \r\n"
                + "Choose option:");
    }


    }



