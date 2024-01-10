package org.example;


import java.sql.Connection;

public class Menu {

    public static byte showMenu() {

        byte option;
        final byte MIN = 0;
        final byte MAX = 13;

        do {
            System.out.println("Application menu: \r\n"
                    + "1. Add Tree. \r\n"
                    + "2. Add Flower.\r\n"
                    + "3. Add Decoration. \r\n"
                    + "4. Stock: Print all Trees, flowers and Decoration. \r\n"
                    + "5. Remove a Tree item \r\n"
                    + "6. Remove a Flower item. \r\n"
                    + "7. Remove a Decoration item. \r\n"
                    + "8. Print stock with quantities. \r\n"
                    + "9. Print stock total value. \r\n"
                    + "10. Create a ticket with the total items. \r\n"
                    + "11. Show old purchases. \r\n"
                    + "12. View the total money earned from all sales. \r\n"
                    + "13. Restart DataBase. \r\n"
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
                    System.out.println("Menu 1 - Add Tree.");
                    Methods.createTree(con);
                    break;
                case 2:
                    System.out.println("Menu 2 - Add Flower.");
                    Methods.createFlower(con);
                    break;
                case 3:
                    System.out.println("Menu 3 - Add Decoration.");
                    Methods.createDecoration(con,Methods.searchFlowerShop(con));
                    break;
                case 4:
                    System.out.println("Menu 4 - Stock: Print all Trees, Flowers and Decoration.");
                    Methods.printStock(con);
                    break;
                case 5:
                    System.out.println("Menu 5 - Remove a Tree item.");
                    Methods.deleteProduct(con);
                    break;
                case 6:
                    System.out.println("Menu 6 - Remove a Flower item.");
                    Methods.deleteProduct(con);
                    break;
                case 7:
                    System.out.println("Menu 7 - Remove a Decoration item.");
                    Methods.deleteProduct(con);
                    break;
                case 8:
                    System.out.println("Menu 8 - Print stock with quantities.");
                    Methods.printProductCount(con);
                    break;
                case 9:
                    System.out.println("Menu 9 - Print stock total value.");
                    Methods.stockTotalValue(con);
                    break;
                case 10:
                    System.out.println("Menu 10 - Create a ticket with the total items.");
                    Methods.createTicketMethod(con);
                    break;
                case 11:
                    System.out.println("Menu 11 - Show old purchases.");
                    Methods.showOldPurchases(con);
                    break;
                case 12:
                    System.out.println("Menu 12 - View the total money earned from all sales.");
                    Methods.showTotalEarnings(con);
                    break;
                case 13:
                    System.out.println("Menu 13 - Restart DataBase.");
                    break;
                case 0:
                    System.out.println("Thank you for use the app.");
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public static int menuTicket(){
        int menuOption = Input.getInt("1. Add product. \r\n"
                + "0. Exit. \r\n"
                + "Choose option:");
        return menuOption;
    }

    public static void chooseMenuTicket(Connection con){
        boolean exit = false;
        do {
            int menu = menuTicket();
            switch (menu){
                case 1:
                    Methods.addProductTicket(con);
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }while (!exit);
        System.out.println("Ticket:");

    }


    }



