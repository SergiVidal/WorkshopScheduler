package com.algorithmic.service;

import java.util.Scanner;

import static java.util.Objects.isNull;

public class CLI {

    public static void showWelcome() {
        System.out.println("_-_-_- WorkshopScheduler -_-_-_\n");
    }

    public static String askForPathWithMessage() {
        System.out.print("Introduce la ubicación del fichero: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int askForMenuOption() {
        System.out.println();
        int option;

        System.out.println("1. Todas las configuraciones posibles.");
        System.out.println("2. Maximizar horas.");
        System.out.println("3. Maximizar presupuesto disponible.");
        System.out.println("4. Sortir");
        option = askForInteger();
        return option;
    }

    public static boolean askForMarkOption() {
        return askForBoolean("¿Quieres aplicar mejoras en la eficiencia?");
    }

    private static int askForInteger() {
        try {
            System.out.print("Selecciona un objectivo: ");
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Error! Debes de introducir un entero");
            askForInteger();
        }
        return -1;
    }

    public static float askForBudget() {
        System.out.print("¿Cuál es el presupuesto disponible? (€) ");
        Scanner scanner = new Scanner(System.in);
        return Float.parseFloat(scanner.next());
    }

    public static boolean askForBoolean(String message) {
        Boolean result = null;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(message + " (s/n)");
            final String userText = scanner.next();
            if (userText.equals("s")) {
                result = true;
            } else if (userText.equals("n")) {
                result = false;
            } else {
                System.out.println("Error! S'ha d'introduir 's' o 'n'.");
            }

        } while (isNull(result));
        return result;
    }
}
