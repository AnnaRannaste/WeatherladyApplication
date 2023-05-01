package MainEntry;

import Locations.AddRequest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {


            Scanner scanner = new Scanner(System.in);


            System.out.println("1- Add location");
            System.out.println("2- List previous searches");
            System.out.println("3- Current forecast ");
            System.out.println("4- Exit");

            System.out.println("Choose an action: ");

            int action;

            if (scanner.hasNextInt()) {
                action = scanner.nextInt();
            } else {
                System.out.println("Invalid action");
                return;
            }

            switch (action) {

                case 1:
                    // Prompt user for city
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Enter city: ");
                    String inputCity = scanner1.nextLine();

                    //Date
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Enter date: ");
                    String dateStr = scanner2.nextLine();

                    AddRequest add = new AddRequest();
                    add.addLocation(inputCity);

                //    add.addLocation("inputCity", "dateStr");
                    break;

                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    System.out.println("Performing exit");
                    scanner.close();
                    break;
            }
        }

    }

