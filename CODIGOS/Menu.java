import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("=== SISTEMA DE PARQUEADERO ===");
        System.out.println("1. Cliente");
        System.out.println("2. Administrador");
        System.out.println("3. Contabilidad");
        System.out.print("Seleccione una opción: ");

        int opcion = entrada.nextInt();

        if (opcion == 1) {
            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println("1. Registrar entrada");
            System.out.println("2. Registrar salida");
            System.out.println("3. Salir"); 

            
        } else if (opcion == 2) {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Ver puestos");
            System.out.println("2. Configurar tarifas");
            System.out.println("3. Salir");
        } 
        else if (opcion == 3) {
            System.out.println("\n--- MENÚ CONTABILIDAD ---");
            System.out.println("1. Ver ingresos");
            System.out.println("3. Salir");
        } 
        else {
            System.out.println("Opción no válida.");
        }

        System.out.println("\nFin del programa.");
    }
}