import java.util.Scanner;

public class Menu1_1 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("=====SISTEMA DE PARQUEADERO=====");
        System.out.println("");
        System.out.println("1. Cliente");
        System.out.println("2. Administrador");
        System.out.println("3. Contabilidad");

        System.out.println("\nSeleccione una de las opciones: ");
        int OpMenuPrincipal = entrada.nextInt();
        
        if (OpMenuPrincipal == 1) {

            int OpMenuCliente = 0;

            while (OpMenuCliente != 4) {
                System.out.println("\n=====Menu Cliente====\n");

                System.out.println("1. Ingreso");
                System.out.println("2. Pago");
                System.out.println("3. Salida");
                System.out.println("4. Atras");

                System.out.println("\nElija una de las opciones: ");
                OpMenuCliente = entrada.nextInt();

                if (OpMenuCliente == 1) {

                    System.out.println("\nEscoja un tipo de vehiculo\n");
                    System.out.println("1. Carro"); 
                    System.out.println("2. Motocicleta");
                    System.out.println("3. Bicicleta");

                    System.out.println("\nSeleccione una de las opciones: ");
                    int OpTipoVehiculo = entrada.nextInt();

                    String TipoVehiculo;
                    String Placa;

                    switch (OpTipoVehiculo) {
                        case 1:
                            TipoVehiculo = "Carro";
                            System.out.print("\nIngrese su Placa: ");
                            Placa = entrada.next().toUpperCase();
                            break;

                        case 2:
                            TipoVehiculo = "Motocicleta";
                            System.out.print("\nIngrese su Placa: ");
                            Placa = entrada.next().toUpperCase();
                            break;

                        case 3:
                            TipoVehiculo = "Bicicleta";
                            Placa = "N/A";
                            break;

                        default:
                            System.out.println("Opción inválida.");
                            continue;
                    }
       

                    System.out.println("\n¿Necesita lugar para discapacitado?\n");
                    System.out.println("1. Sí");
                    System.out.println("2. No");

                    System.out.println("Escoja una Opción\n");
                    int OpDiscapacitado = entrada.nextInt();

                    String Discapacidad = "";

                    switch (OpDiscapacitado) {
                        case 1:
                            Discapacidad = "SI";
                            System.out.println("\n"+Discapacidad+ " Necesita Plaza Especial.");
                            break;

                        case 2:
                            Discapacidad = "NO";
                            System.out.println("\n"+Discapacidad+ " Necesita Plaza Especial.");
                            break;

                        default:
                            System.out.println("Opcion errada.");
                            break;
                    }

                    System.out.println("\nSu registro exitoso, a continuacion su ticket de ingreso\n\n");

                    System.out.println("╔═════════════════════════════╗");
                    System.out.println("║  Parqueadero CC PLAZA 90    ║");
                    System.out.println("╠═════════════════════════════╣");
                    System.out.println("║ FECHA: DD/MM/AAAA           ║");
                    System.out.println("║ HORA:  00:00:00             ║");
                    System.out.println("║                             ║");
                    System.out.println(String.format("║ TIPO: %-21s ║", TipoVehiculo));
                    System.out.println(String.format("║ PLACA: %-20s ║", Placa));
                    System.out.println(String.format("║ PLAZA ESP: %-16s ║", Discapacidad));
                    System.out.println("║                             ║");
                    System.out.println("║ SU PLAZA ES:                ║");
                    System.out.println("║             XXXX            ║");
                    System.out.println("║                             ║");
                    System.out.println("╚═════════════════════════════╝");

                }else if (OpMenuCliente == 2) {
                    
                }else if (OpMenuCliente == 3) {

                }else if (OpMenuCliente == 4) {

                }else {

                }

            }




        }


    }
}