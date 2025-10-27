/*
 * - Matriz 5x4 donde la fila 0 sera para discapacitados, 1 para bicicletas, 2 para las motos, 3-4 para los carros)
 * - Placas en mayúscula utilizando el comando .toUpperCase guarda lo escrito en mayusculas
 * - Bicicletas apareceran como "SIN-PLACA" ya que no la usan
 * - Pago y salida contolado, el pago no libera la plaza asi que seguira apareciendo hasta quue el vehiculo salga del parqueadero
 * - Tickets alineados usando String.format para que el fromato ascii no se corra 
 */


import java.util.Scanner;

public class Menu1_1 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // esta matriz epresenta las plazas del parqueadero
        String[][] parqueadero = new String[5][4]; //en este caso se utilizaran 5 filas x 4 columnas

        // Matriz paralela que indica si ya pago (true) o no (false)
        boolean[][] pagado = new boolean[5][4];

        int OpMenuPrincipal = 0;

        // Bucle principal del programa se repetira hasta presionar la opcionn salir.
        while (OpMenuPrincipal != 4) {
            
            System.out.println("\n=====SISTEMA DE PARQUEADERO=====");
            System.out.println("");
            System.out.println("1. Cliente");
            System.out.println("2. Administrador");
            System.out.println("3. Contabilidad");
            System.out.println("4. Salir");

            System.out.print("\nSeleccione una de las opciones: ");
            OpMenuPrincipal = entrada.nextInt();
            entrada.nextLine(); // limpia los datos de entrada para que no haya errores entre los nextInt y nextLine (es como una buena practica)

            // Apartado de Cliente

            if (OpMenuPrincipal == 1) {

                int OpMenuCliente = 0;

                // Submenu de cliente

                while (OpMenuCliente != 4) {

                    System.out.println("\n=====Menú Cliente====");
                    System.out.println("");
                    System.out.println("1. Ingreso");
                    System.out.println("2. Pago");
                    System.out.println("3. Salida");
                    System.out.println("4. Atrás");

                    System.out.print("\nElija una opción: ");
                    OpMenuCliente = entrada.nextInt();
                    entrada.nextLine();

                    // Ingreso al parqueadero

                    if (OpMenuCliente == 1) {

                        System.out.println("\nEscoja un tipo de vehículo\n");
                        System.out.println("");
                        System.out.println("1. Carro");
                        System.out.println("2. Motocicleta");
                        System.out.println("3. Bicicleta");

                        System.out.print("\nSeleccione una opción: ");
                        int OpTipoVehiculo = entrada.nextInt();
                        entrada.nextLine();

                        String TipoVehiculo = "";
                        int fila = -1; // fila donde intentaremos asignar la plaza

                        // Determinar tipo y fila 

                        switch (OpTipoVehiculo) {
                            case 1 -> { TipoVehiculo = "Carro"; fila = 3; }      // carros empiezan en fila 3 

                            case 2 -> { TipoVehiculo = "Motocicleta"; fila = 2; } // motos van en la fila 2

                            case 3 -> { TipoVehiculo = "Bicicleta"; fila = 1; }   // bicicletas van en la fila 1

                            default -> System.out.println("Opción inválida.");
                        }

                        // Leer placa solo si no es bicicleta
                        String Placa;

                        if (!TipoVehiculo.equals("Bicicleta")) { //utilizandoo el operador logico ! (NOT) le damos la condicion de que si no es bicileta pedira la placa
                            System.out.print("\nIngrese su Placa: ");
                            Placa = entrada.nextLine().toUpperCase(); // el toUpperCase para que aparezca la placa en mayusculas

                        } else {
                            Placa = "SIN-PLACA"; //pero cuando es bicicleta se registrara sin la placa ya que no la necesita
                        }

                        // Preguntar si necesita plaza para discapacitados
                        System.out.println("\n¿Necesita lugar para discapacitado?");
                        System.out.println("");
                        System.out.println("1. Sí");
                        System.out.println("2. No");

                        System.out.print("Escoja una opción: ");
                        int OpDiscapacitado = entrada.nextInt();
                        entrada.nextLine();

                        String Discapacidad = "";

                        if (OpDiscapacitado == 1) {   
                            Discapacidad = "SI";
                            fila = 0; // si es discapacitado se le dara la fila 0 

                        } else {
                            Discapacidad = "NO";
                        }

                        //Buscar espacios libres

                        boolean asignado = false; //variable de control para saber si se encontro o no un puesto


                        for (int j = 0; j < 4; j++) { // recorremos las columnas 
                             
                            if (parqueadero[fila][j] == null) { //verifica si la plaza esta vacia, los cupos vacios aparecen como null, si esta vacio se le asigna pero si esta ocupado brinca al siguiente espacio

                                parqueadero[fila][j] = Placa; // ocupamos la placa con el vehiculo que entro
                                pagado[fila][j] = false; // no ha pagado

                                // imprimir el tikets
                                // string.format alinea el texto con el %- alinea a la izquierda el numero (21) es la cantidad de espacios, y la (s) tipo de dato osea string

                                System.out.println("\nRegistro exitoso. Ticket de ingreso:\n");
                                System.out.println("╔═════════════════════════════╗");
                                System.out.println("║  Parqueadero CC PLAZA 90    ║");
                                System.out.println("╠═════════════════════════════╣");
                                System.out.println(String.format("║ TIPO: %-21s ║", TipoVehiculo)); //utilizamos el string.format %-s para que el formato quede bien alineado a la izquierda 
                                System.out.println(String.format("║ PLACA: %-20s ║", Placa));
                                System.out.println(String.format("║ PLAZA ESP: %-16s ║", Discapacidad));
                                String puesto = "(" + (fila + 1) + "," + (j + 1) + ")"; //aqui se crea una cadena con el numero de las filas y columnas se suma un 1 ya que empiezan desde el 0 y para el usuario se ve mal ver (0,0)
                                System.out.println(String.format("║ PUESTO: %-19s ║", puesto));
                                System.out.println("╚═════════════════════════════╝");
                                asignado = true;
                                break; // salimos del for
                            }
                        }

                        if (!asignado) {
                            System.out.println("No hay espacio disponible para ese tipo de vehículo en la fila seleccionada.");
                        }

                    // OPCIÓN DE PAGO

                    } else if (OpMenuCliente == 2) {

                        //se pide el tipo de vehiculos
                        System.out.println("\nSeleccione su tipo de vehículo:");
                        System.out.println("");
                        System.out.println("1. Carro");
                        System.out.println("2. Motocicleta");
                        System.out.println("3. Bicicleta");

                        System.out.print("\nOpción: ");
                        int tipoPago = entrada.nextInt();
                        entrada.nextLine();

                        String Placa = "";
                        boolean encontrado = false;

                        // Si es bicicleta, no pedimos placa
                        if (tipoPago == 3) {
                            Placa = "SIN-PLACA";
                            System.out.println("Las bicicletas no requieren pago. Gracias por su visita.");

                            // Buscar bicicleta bicicleta y aparezca oagada y deje salir
                            for (int i = 0; i < 5; i++) {
                                for (int j = 0; j < 4; j++) {
                                    if ("SIN-PLACA".equals(parqueadero[i][j])) {
                                        pagado[i][j] = true; // las bicicletas son gratis
                                        encontrado = true;
                                    }
                                }
                            }

                        } else {
                            // Si no es una bicicleta seguimos con el proceso normal
                            System.out.print("\nIngrese su placa: ");
                            Placa = entrada.nextLine().toUpperCase();

                            for (int i = 0; i < 5; i++) {
                                for (int j = 0; j < 4; j++) {

                                    if (Placa.equals(parqueadero[i][j])) {
                                        encontrado = true;

                                        if (pagado[i][j]) {
                                            System.out.println("Ya ha realizado el pago.");

                                        } else {
                                            // descuento si es empleado
                                            System.out.print("¿Es empleado del centro comercial? (s/n): ");
                                            char empleado = entrada.next().toLowerCase().charAt(0);
                                            entrada.nextLine();

                                            double valor = 3000; // tarifa base

                                            if (empleado == 's') {
                                                System.out.print("Ingrese el código de empleado: ");
                                                int codigoEmpleado = entrada.nextInt();
                                                entrada.nextLine();

                                                if (codigoEmpleado == 123) {
                                                    valor = 0;
                                                    System.out.println("Empleado verificado. No paga.");

                                                } else {
                                                    System.out.println("Código incorrecto. No se aplica beneficio.");
                                                }

                                            } else {
                                                System.out.print("Ingrese el valor de sus compras: ");
                                                double compras = entrada.nextDouble();
                                                entrada.nextLine();

                                                if (compras > 100000) valor = 0;

                                                else if (compras > 50000) valor *= 0.5;

                                                else if (compras > 25000) valor *= 0.75;

                                                if (valor == 0)
                                                    System.out.println("Su estacionamiento es gratuito.");
                                                else
                                                    System.out.println("Debe pagar $" + valor);
                                            }

                                            pagado[i][j] = true;
                                        }
                                    }
                                }
                            }
                        }

                        if (!encontrado) {
                            System.out.println("No se encontró ese vehículo registrado.");
                        }



                    //salida
                    } else if (OpMenuCliente == 3) {
                        System.out.print("\nIngrese su placa: ");
                        String Placa = entrada.nextLine().toUpperCase();
                        boolean encontrado = false;

                        // Buscamos la placa en la matriz
                        for (int i = 0; i < 5; i++) {

                            for (int j = 0; j < 4; j++) {

                                if (Placa.equals(parqueadero[i][j])) {
                                    encontrado = true;

                                    // Solo dejamos salir si pago
                                    if (pagado[i][j]) {
                                        parqueadero[i][j] = null;   // liberar plaza
                                        pagado[i][j] = false;       // resetar pago
                                        System.out.println("Vehículo retirado. ¡Gracias por su visita!");
                                    
                                    } else {
                                        System.out.println("No puede salir. No ha realizado el pago.");
                                    }
                                }
                            }
                        }

                        if (!encontrado) {
                            System.out.println("No se encontró esa placa registrada.");
                        }

                    // atras
                    } else if (OpMenuCliente == 4) {
                        System.out.println("Regresando al menú principal...");

                    } else {
                        System.out.println("Opción inválida.");
                    }
                } 
            } 

            // Aquí puedes añadir opciones 2 (Administrador) y 3 (Contabilidad)
            

        } 

        entrada.close();
    } 
} 
