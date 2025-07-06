package hello;

import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        System.out.print("Enter the temperature value: ");
        double temperature = scanner.nextDouble();

        System.out.print("Enter the unit (C for Celsius, F for Fahrenheit, K for Kelvin): ");
        char unit = scanner.next().toUpperCase().charAt(0);

        // Conversion and Output
        switch (unit) {
            case 'C':
                double fFromC = (temperature * 9/5) + 32;
                double kFromC = temperature + 273.15;
                System.out.printf("%.2f°C is %.2f°F and %.2fK\n", temperature, fFromC, kFromC);
                break;

            case 'F':
                double cFromF = (temperature - 32) * 5/9;
                double kFromF = cFromF + 273.15;
                System.out.printf("%.2f°F is %.2f°C and %.2fK\n", temperature, cFromF, kFromF);
                break;

            case 'K':
                double cFromK = temperature - 273.15;
                double fFromK = (cFromK * 9/5) + 32;
                System.out.printf("%.2fK is %.2f°C and %.2f°F\n", temperature, cFromK, fFromK);
                break;

            default:
                System.out.println("Invalid unit entered. Please use C, F, or K.");
        }

        scanner.close();
    }
}
