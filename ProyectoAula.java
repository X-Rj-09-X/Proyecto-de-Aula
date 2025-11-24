import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProyectoAula {

    static Scanner entrada = new Scanner(System.in);

    // Matriz 6 filas x 5 columnas 
    static String[][] parqueadero = new String[6][5]; //Guardar las placas o ID de cada vehiculo
    static String[][] tipoEnPuesto = new String[6][5]; // tipos que se guardaran en la matriz "CARRO", "MOTO", "BICI", "DISCAP"
    static LocalDateTime[][] horaEntrada = new LocalDateTime[6][5]; // hora de ingreso
    static boolean[][] pagado = new boolean[6][5]; // comprobar si realizo los pagos
 
    // Totales para contabilidad
    static double totalDia = 0; // recaudado en el dia
    static double totalSemana = 0; //recaudado en la semana
    static double totalMes = 0; // recaudado en el mes

    // Contador para numerar las bicicletas
    static int contadorBicicletas = 0;

    // Credenciales de los roles administrador y contabilidad
    static final String UsuarioAdmin = "admin";
    static final String ContraseñaAdmin = "123";
    static final String UsuarioConta = "conta";
    static final String ContraseñaConta = "321";

    // para dar el formato de la fecha en el tickets de manera mas ordenada y corta
    static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        empezarParqueadero(); //empieza la matriz del parqueadero libre
        menuPrincipal(); // muestra el menu principal
    }

    // inicio de la matriz con todos los cupos libres
    public static void empezarParqueadero() {
        for (int i = 0; i < parqueadero.length; i++) {
            for (int j = 0; j < parqueadero[0].length; j++) {
                parqueadero[i][j] = "LIBRE";
                horaEntrada[i][j] = null;
                tipoEnPuesto[i][j] = "";
                pagado[i][j] = false;
            }
        }
    }

    // menu principal del parqueadero 
    public static void menuPrincipal() {
        while (true) {
            System.out.println("\n\nBienvenido al Parqueadero del Centro Comercial Plaza 90.\n\n");

            System.out.println("╔════════════════════════════════╗");
            System.out.println("║        MENU PRINCIPAL          ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Cliente                     ║");
            System.out.println("║ 2. Administrador               ║");
            System.out.println("║ 3. Contabilidad                ║");
            System.out.println("║ 4. Salir                       ║");
            System.out.println("╚════════════════════════════════╝");

            System.out.print("\nSeleccione una opción: ");

            int op = leerEntero();

            switch (op) {
                case 1 -> menuCliente();
                case 2 -> {
                    if (loginAdmin()) menuAdmin();
                }
                case 3 -> {
                    if (loginContabilidad()) menuContabilidad();
                }
                case 4 -> {
                    System.out.println("\nGracias. Saliendo...");
                    return;
                }
                default -> System.out.println("\nOpción inválida.");
            }
        }
    }

    // Login de administrador y contabilidad 
    //valida si las credenciales son las correctas (Admin)
    public static boolean loginAdmin() {
        System.out.print("\nUsuario: ");
        String u = entrada.nextLine();
        System.out.print("Contraseña: ");
        String c = entrada.nextLine();
        if (UsuarioAdmin.equals(u) && ContraseñaAdmin.equals(c)) return true;
        System.out.println("\nCredenciales inválidas.");
        return false;
    }
   
    //valida las credenciales son las correctctas (contabilidad)
    public static boolean loginContabilidad() {
        System.out.print("\nUsuario: ");
        String u = entrada.nextLine();
        System.out.print("Contraseña: ");
        String c = entrada.nextLine();
        if (UsuarioConta.equals(u) && ContraseñaConta.equals(c)) return true;
        System.out.println("\nCredenciales inválidas.");
        return false;
    }

    // Menu cliente 
    public static void menuCliente() {
        while (true) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║         MENU CLIENTE           ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Ingreso                     ║");
            System.out.println("║ 2. Pago                        ║");
            System.out.println("║ 3. Salida                      ║");
            System.out.println("║ 4. Salir                       ║");
            System.out.println("╚════════════════════════════════╝");

            System.out.print("\nSeleccione una opcion: ");

            int op = leerEntero();
            switch (op) {
                case 1 -> ingresoCliente();
                case 2 -> pagoCliente();
                case 3 -> salidaCliente();
                case 4 -> { return; }
                default -> System.out.println("\nOpción inválida.");
            }
        }
    }

    // Menú administrador
    public static void menuAdmin() {
        while (true) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║      MENU ADMINISTRAVITO       ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Ver ocupacion               ║");
            System.out.println("║ 2. Ingreso manual              ║");
            System.out.println("║ 3. Salida manual por placa/Id  ║");
            System.out.println("║ 4. Salir                       ║");
            System.out.println("╚════════════════════════════════╝");

            System.out.print("\nSeleccione una opcion: ");

            int op = leerEntero();
            switch (op) {
                case 1 -> mostrarParqueadero();
                case 2 -> ingresoManualAdmin();
                case 3 -> salidaManualPorPlaca();
                case 4 -> { return; }
                default -> System.out.println("\nOpción inválida.");
            }
        }
    }

    // Menú contabilidad
    public static void menuContabilidad() {

        int op;
        do {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║        MENU CONTABILIDAD       ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║ 1. Ver Totales                 ║");
        System.out.println("║ 0. Volver al menú principal    ║");
        System.out.println("╚════════════════════════════════╝");

        System.out.print("\nSeleccione una opción: ");
        op = leerEntero();

        switch (op) {
            case 1 -> mostrarTotales();
            case 0 -> System.out.println("\nRegresando al menú principal...");
            default -> System.out.println("\nOpción inválida.");
            }
        } while (op != 0);

    }

    //muestra los totales recaudados
    public static void mostrarTotales() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║         CONTABILIDAD         ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ Total del dia   : " + String.format("%10.2f", totalDia) + " ║");
        System.out.println("║ Total semana    : " + String.format("%10.2f", totalSemana) + " ║");
        System.out.println("║ Total mes       : " + String.format("%10.2f", totalMes) + " ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("\nPresione ENTER para regresar al menu...");
        entrada.nextLine();
}


    // Muestra la matriz con información clara
    public static void mostrarParqueadero() {
        
        System.out.println("");
        for (int i = 0; i < parqueadero.length; i++) {
            System.out.print("Fila " + (i + 1) + "| ");
            for (int j = 0; j < parqueadero[0].length; j++) {

                String cell = parqueadero[i][j];
                if (!"LIBRE".equals(cell)) {

                    String tipo = tipoEnPuesto[i][j];

                    // Si es la fila de discapacitados (fila 1 = índice 0)
                    if (i == 0) {
                        System.out.print("[" + cell + ", D] ");
                    } else {
                        System.out.print("[" + cell + ", " + tipo + "] ");
                    }

                } else {
                    System.out.print("[LIBRE] ");
                }
            }
            System.out.println();
        }
    }

    // INGRESO CLIENTE: tipo, discapacitado, placa (si aplica), asignación automática dependiendo de los parametros
    public static void ingresoCliente() {

        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║         TIPO DE VEHICULO       ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║ 1. Carro                       ║");
        System.out.println("║ 2. Moto                        ║");
        System.out.println("║ 3. Bicicleta                   ║");
        System.out.println("╚════════════════════════════════╝");

        System.out.println("\nSeleccione una opcion: ");
        int tipo = leerEntero();
        if (tipo < 1 || tipo > 3) {
            System.out.println("\nTipo inválido.");
            return;
        }
        boolean esDiscapacitado = false;
        if (tipo != 3) { // bicicletas no entran como discapacitado
            System.out.print("\n¿Es discapacitado? (s/n): ");
            String r = entrada.nextLine().trim().toLowerCase();
            esDiscapacitado = r.startsWith("s");
        }

        String placa = "";
        if (tipo != 3) { // los unicos que ingresan placa son las motos y carros
            System.out.print("\nIngrese placa: ");
            placa = entrada.nextLine().trim().toUpperCase();
            if (placa.isEmpty()) {
                System.out.println("\nPlaca inválida.");
                return;
            }
            if (estaPlacaEnParqueadero(placa)) {
                System.out.println("\nERROR: El vehiculo con placa " + placa + " ya está ingresado.");
                return;
            }
        }

        // Determinar ubicacion segun reglas:
        // Fila 1 -> discapacitados
        // Fila 2 -> bicicletas
        // Filas 3-4 -> motos
        // Filas 5-6 -> carros

        int[] posicion = null;
        if (esDiscapacitado) {
            // colocar en fila 1 (indice 0) primer lugar libre
            posicion = colocarEnFilaConPosicion(0, placa, tipo == 1 ? "CARRO" : "MOTO");
        } else if (tipo == 3) { // bicicleta -> fila 2 (indice 1)
            // Asignar ID numérica única a la bicicleta para identificarla (1,2,3...)
            contadorBicicletas++;
            String idBici = String.valueOf(contadorBicicletas); // e.g. "1"
            posicion = colocarEnFilaConPosicion(1, idBici, "BICI");
        } else if (tipo == 2) { // moto -> filas 3-4 (indices 2 y 3)
            posicion = colocarEnFilasMultiplesConPosicion(new int[]{2,3}, placa, "MOTO");
        } else if (tipo == 1) { // carro -> filas 5-6 (indices 4 y 5)
            posicion = colocarEnFilasMultiplesConPosicion(new int[]{4,5}, placa, "CARRO");
        }

        if (posicion != null) {
            System.out.println("\nIngreso registrado. Emitiendo ticket...");
            imprimirTicketPorPosicion(posicion[0], posicion[1]);
        } else {
            System.out.println("\nNo hay espacio disponible para su tipo de vehiculo.");
        }
    }

    // Comprueba si una placa ya está en la matriz
    public static boolean estaPlacaEnParqueadero(String placa) {
        for (int i = 0; i < parqueadero.length; i++) {
            for (int j = 0; j < parqueadero[0].length; j++) {
                if (!"LIBRE".equals(parqueadero[i][j]) && parqueadero[i][j].equalsIgnoreCase(placa)) return true;
            }
        }
        return false;
    }

    // Colocar en una fila especifica (busca primer libre en esa fila) y devuelve la posición [i,j] o null
    public static int[] colocarEnFilaConPosicion(int filaIndex, String placa, String tipo) {
        for (int j = 0; j < parqueadero[0].length; j++) {
            if ("LIBRE".equals(parqueadero[filaIndex][j])) {
                parqueadero[filaIndex][j] = placa;
                horaEntrada[filaIndex][j] = LocalDateTime.now();
                tipoEnPuesto[filaIndex][j] = tipo;
                pagado[filaIndex][j] = false;
                return new int[]{filaIndex, j};
            }
        }
        return null;
    }

    // Colocar en varias filas dada una lista de indices y devuelve la posición [i,j] o null
    public static int[] colocarEnFilasMultiplesConPosicion(int[] filas, String placa, String tipo) {
        for (int f : filas) {
            for (int j = 0; j < parqueadero[0].length; j++) {
                if ("LIBRE".equals(parqueadero[f][j])) {
                    parqueadero[f][j] = placa;
                    horaEntrada[f][j] = LocalDateTime.now();
                    tipoEnPuesto[f][j] = tipo;
                    pagado[f][j] = false;
                    return new int[]{f, j};
                }
            }
        }
        return null;
    }

    // Imprime ticket por posición (fila/columna)
    public static void imprimirTicketPorPosicion(int i, int j) {

        String titulo = "TICKET DE INGRESO";
        String puesto = "Puesto: F" + (i+1) + "-C" + (j+1);
        String tipo   = tipoEnPuesto[i][j];
        String placa  = parqueadero[i][j];
        String hora   = horaEntrada[i][j].format(dtf);

        System.out.println("\n╔════════════════════════════╗");
        System.out.printf ("║ %-26s ║\n", titulo);
        System.out.println("╠════════════════════════════╣");
        System.out.printf ("║ %-26s ║\n", puesto);

        if ("BICI".equals(tipo)) {
            System.out.printf("║ Tipo : %-19s ║\n", "BICICLETA");
            System.out.printf("║ ID   : %-19s ║\n", placa);
        } else {
            System.out.printf("║ Placa: %-19s ║\n", placa);
            System.out.printf("║ Tipo : %-19s ║\n", tipo);
        }

        System.out.printf("║ Hora : %-18s ║\n", hora);
        System.out.println("╚════════════════════════════╝");
} 


    // PAGO: consulta placa/tipo y calcula tarifa según reglas
    public static void pagoCliente() {

        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║         TIPO DE VEHICULO       ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║ 1. Carro                       ║");
        System.out.println("║ 2. Moto                        ║");
        System.out.println("║ 3. Bicicleta                   ║");
        System.out.println("╚════════════════════════════════╝");

        System.out.println("\nSeleccione una opcion: ");

        int tipo = leerEntero();
        if (tipo < 1 || tipo > 3) {
            System.out.println("\nTipo inválido.");
            return;
        }
        if (tipo == 3) {
            // Bicicletas salen inmediatamente SIN PAGO ni solicitar placa
            int[] posBici = buscarPrimeraBicicleta();
            if (posBici == null) {
                System.out.println("\nNo hay bicicletas registradas actualmente.");
            } else {
                // liberar esa bicicleta
                int i = posBici[0], j = posBici[1];
                parqueadero[i][j] = "LIBRE";
                horaEntrada[i][j] = null;
                tipoEnPuesto[i][j] = "";
                pagado[i][j] = false;
                System.out.println("\nSalida bicicleta procesada (gratuita).");
            }
            return;
        }

        System.out.print("\nIngrese placa: ");
        String placa = entrada.nextLine().trim().toUpperCase();
        int[] pos = buscarPlaca(placa);
        if (pos == null) {
            System.out.println("\nPlaca no encontrada.");
            return;
        }
        int i = pos[0], j = pos[1];
        String tipoEn = tipoEnPuesto[i][j];
        if ("BICI".equals(tipoEn)) {
            System.out.println("\nID corresponde a una bicicleta. Salida gratuita.");
            parqueadero[i][j] = "LIBRE"; horaEntrada[i][j] = null; tipoEnPuesto[i][j] = ""; pagado[i][j] = false;
            return;
        }

        // Verificar empleado
        System.out.print("\n¿Es empleado? (s/n): ");
        String resp = entrada.nextLine().trim().toLowerCase();
        boolean esEmpleado = resp.startsWith("s");
        boolean pagoGratisPorEmpleado = false;
        if (esEmpleado) {
            System.out.print("Ingrese código empleado: ");
            String codigo = entrada.nextLine().trim();
            if ("123".equals(codigo)) {
                pagoGratisPorEmpleado = true;
            } else {
                System.out.println("\nCódigo inválido. No se aplicará gratuidad por empleado.");
            }
        }

        // Valor de compra
        System.out.print("\n¿Realizo alguna compra? Ingrese valor en pesos (coloque 0 si no): ");
        double valorCompra = leerDouble();

        // Calcular tarifa
        double valorBasePorMinuto = "CARRO".equals(tipoEn) ? 50.0 : 35.0; // pesos por minuto
        LocalDateTime inicio = horaEntrada[i][j];
        LocalDateTime ahora = LocalDateTime.now();
        Duration dur = Duration.between(inicio, ahora);
        long minutosTotales = dur.toMinutes();

        // Aplicar cortesias y promociones
        if (pagoGratisPorEmpleado) {
            // gratis
            pagado[i][j] = true;
            parqueadero[i][j] = "LIBRE"; horaEntrada[i][j] = null; tipoEnPuesto[i][j] = "";
            System.out.println("\nPago validado como empleado. Salida permitida sin costo.");
            return;
        }

        // Promociones por compra
        long minutosGratisPorCompra = 0;
        double descuento = 0.0;
        if (valorCompra >= 100000) minutosGratisPorCompra = 180; // 3 horas gratis
        else if (valorCompra > 50000) descuento = 0.25; // 25% descuento

        long minutosACobrar = minutosTotales - 15; // quitar 15 minutos de cortesia
        if (minutosACobrar <= 0) minutosACobrar = 0;
        // restar minutos gratis por compra
        if (minutosGratisPorCompra > 0) {
            if (minutosACobrar <= minutosGratisPorCompra) minutosACobrar = 0;
            else minutosACobrar -= minutosGratisPorCompra;
        }

        double total = minutosACobrar * valorBasePorMinuto;
        if (descuento > 0) total = total * (1 - descuento);

        // Si el tiempo total fue <=15 minutos, es gratis
        if (minutosTotales <= 15) total = 0;

        System.out.println("Tiempo total (min): " + minutosTotales + ", minutos a cobrar: " + minutosACobrar);
        System.out.println("Valor a pagar: " + total + " pesos");
        System.out.print("\nConfirmar pago? (s/n): ");
        String rp = entrada.nextLine().trim().toLowerCase();
        if (rp.startsWith("s")) {
            pagado[i][j] = true;
            totalDia += total; totalSemana += total; totalMes += total;
            System.out.println("\nPago registrado. Puede salir una vez se valide en módulo de salida.");
        } else {
            System.out.println("\nPago cancelado.");
        }
    }

    // SALIDA: por placa, verifica si pagó o condiciones de bicicleta
    public static void salidaCliente() {

        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║            SALIDA              ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║ 1. Carro                       ║");
        System.out.println("║ 2. Moto                        ║");
        System.out.println("║ 3. Bicicleta                   ║");
        System.out.println("╚════════════════════════════════╝");

        System.out.println("\nSeleccione una opcion: ");

        int tipo = leerEntero();
        if (tipo < 1 || tipo > 3) { System.out.println("\nTipo inválido."); return; }
        if (tipo == 3) {
            // Bicicleta: no pide placa, sale gratis; libera primer puesto bici
            int[] posBici = buscarPrimeraBicicleta();
            if (posBici == null) {
                System.out.println("\nNo hay bicicletas registradas actualmente.");
                return;
            }
            int ib = posBici[0], jb = posBici[1];
            parqueadero[ib][jb] = "LIBRE"; horaEntrada[ib][jb] = null; tipoEnPuesto[ib][jb] = ""; pagado[ib][jb] = false;
            System.out.println("\nBicicleta salida libre.");
            return;
        }

        System.out.print("\nIngrese placa/ID: ");
        String placa = entrada.nextLine().trim().toUpperCase();
        int[] pos = buscarPlaca(placa);
        if (pos == null) { System.out.println("\nPlaca no encontrada."); return; }
        int i = pos[0], j = pos[1];
        String tipoEn = tipoEnPuesto[i][j];
        if ("BICI".equals(tipoEn)) {
            // Bicicletas salen gratis (caso raro si placa apunta a bici)
            parqueadero[i][j] = "LIBRE"; horaEntrada[i][j] = null; tipoEnPuesto[i][j] = ""; pagado[i][j] = false;
            System.out.println("\nBicicleta salida libre.");
            return;
        }
        if (pagado[i][j]) {
            parqueadero[i][j] = "LIBRE"; horaEntrada[i][j] = null; tipoEnPuesto[i][j] = ""; pagado[i][j] = false;
            System.out.println("\nSalida permitida. Gracias.");
        } else {
            System.out.println("\nNo ha realizado el pago correspondiente. No puede salir.");
        }
    }

    // Buscar placa y devolver posición [i,j]
    public static int[] buscarPlaca(String placa) {
        for (int i = 0; i < parqueadero.length; i++) {
            for (int j = 0; j < parqueadero[0].length; j++) {
                if (!"LIBRE".equals(parqueadero[i][j]) && parqueadero[i][j].equalsIgnoreCase(placa)) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    // Buscar primera bicicleta (fila 2 -> indice 1) y devolver su posición [i,j] o null
    public static int[] buscarPrimeraBicicleta() {
        int fila = 1; // indice 1 = fila 2 para bicicletas
        for (int j = 0; j < parqueadero[0].length; j++) {
            if (!"LIBRE".equals(parqueadero[fila][j]) && "BICI".equals(tipoEnPuesto[fila][j])) {
                return new int[]{fila, j};
            }
        }
        return null;
    }

    // Liberar bicicleta por ID (ya no usado normalmente)
    public static boolean liberarPorPlacaParaBicicleta(String id) {
        int[] pos = buscarPlaca(id);
        if (pos == null) return false;
        int i = pos[0], j = pos[1];
        if ("BICI".equals(tipoEnPuesto[i][j])) {
            parqueadero[i][j] = "LIBRE"; horaEntrada[i][j] = null; tipoEnPuesto[i][j] = ""; pagado[i][j] = false;
            return true;
        }
        return false;
    }

    // Ingreso manual administrador: ahora el usuario verá filas 1..6 y columnas 1..5
    public static void ingresoManualAdmin() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║      INGRESO MANUAL (ADMIN)    ║");
        System.out.println("╚════════════════════════════════╝");

        System.out.print("\nIngrese fila (1-6): ");
        int fila = leerEntero();
        System.out.print("Ingrese columna (1-5): ");
        int col = leerEntero();

        if (fila < 1 || fila > 6 || col < 1 || col > 5) {
            System.out.println("\nValor fuera del rango.");
            return;
        }

        int fi = fila - 1;
        int co = col - 1;

        if (!"LIBRE".equals(parqueadero[fi][co])) {
            System.out.println("\nEste puesto ya se encuentra ocupado.");
            return;
        }

        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║     TIPO DE VEHiCULO (ADMIN)   ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║ 1. Carro                       ║");
        System.out.println("║ 2. Moto                        ║");
        System.out.println("║ 3. Bicicleta                   ║");
        System.out.println("║ 4. Discapacitado               ║");
        System.out.println("╚════════════════════════════════╝");

        System.out.print("\nSeleccione una opción: ");
        int t = leerEntero();

        String tipoStr;
        String placa = "";

        switch (t) {

            case 1 -> {
                tipoStr = "CARRO";
                System.out.print("Ingrese placa: ");
                placa = entrada.nextLine().trim().toUpperCase();

                if (estaPlacaEnParqueadero(placa)) {
                    System.out.println("\nEsa placa ya se encuentra registrada.");
                    return;
                }
            }

            case 2 -> {
                tipoStr = "MOTO";
                System.out.print("Ingrese placa: ");
                placa = entrada.nextLine().trim().toUpperCase();

                if (estaPlacaEnParqueadero(placa)) {
                    System.out.println("\nEsa placa ya se encuentra registrada.");
                    return;
                }
            }

            case 3 -> {
                tipoStr = "BICI";
                contadorBicicletas++;
                placa = String.valueOf(contadorBicicletas); // ID única
            }

            case 4 -> {
                tipoStr = "DISCAP";
                System.out.print("Ingrese placa (ENTER si no tiene): ");
                placa = entrada.nextLine().trim().toUpperCase();

                if (!placa.isEmpty() && estaPlacaEnParqueadero(placa)) {
                    System.out.println("\nEsa placa ya se encuentra registrada.");
                    return;
                }
            }

            default -> {
                System.out.println("\nOpción inválida.");
                return;
            }
        }
    
        // Si es discapacitado sin placa, registrar marcador
        if (placa.isEmpty()) placa = tipoStr.equals("BICI") ? placa : "SIN_PLACA";

        // Registrar en la matriz
        parqueadero[fi][co] = placa;
        horaEntrada[fi][co] = LocalDateTime.now();
        tipoEnPuesto[fi][co] = tipoStr;
        pagado[fi][co] = false;

        // Mostrar ticket como el ingreso normal
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║       REGISTRO COMPLETADO      ║");
        System.out.println("╚════════════════════════════════╝");

        imprimirTicketPorPosicion(fi, co);
    }

    // Salida manual por placa (admin)
    public static void salidaManualPorPlaca() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║   SALIDA MANUAL (ADMIN)        ║");
        System.out.println("╚════════════════════════════════╝");

        System.out.print("\nIngrese placa del vehiculo: ");
        String placa = entrada.nextLine().trim().toUpperCase();

        boolean encontrado = false;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {

                if (placa.equals(parqueadero[i][j])) {

                    encontrado = true;

                    System.out.println("\nVehiculo encontrado en:");
                    System.out.println("Fila: " + (i + 1) + "  -  Columna: " + (j + 1));

                    LocalDateTime salida = LocalDateTime.now();
                    Duration tiempo = Duration.between(horaEntrada[i][j], salida);

                    long minutos = tiempo.toMinutes();
                    double horas = Math.ceil(minutos / 60.0);

                    double precioPagar = calcularPrecio(tipoEnPuesto[i][j], horas);

                    System.out.println("\n╔════════════════════════════════╗");
                    System.out.println("║            TICKET              ║");
                    System.out.println("╠════════════════════════════════╣");
                    System.out.println("║ Placa:        " + placa);
                    System.out.println("║ Tipo:         " + tipoEnPuesto[i][j]);
                    System.out.println("║ Entrada:      " + horaEntrada[i][j].format(DateTimeFormatter.ofPattern("HH:mm")));
                    System.out.println("║ Salida:       " + salida.format(DateTimeFormatter.ofPattern("HH:mm")));
                    System.out.println("║ Tiempo:       " + minutos + " min");
                    System.out.println("║ Total pagar:  $" + precioPagar);
                    System.out.println("╚════════════════════════════════╝");

                    // Confirmación
                    System.out.println("\n¿Confirmar salida?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    System.out.print("\nOpción: ");
                    int opc = leerEntero();

                    if (opc == 1) {

                        parqueadero[i][j] = "LIBRE";
                        horaEntrada[i][j] = null;
                        tipoEnPuesto[i][j] = null;
                        pagado[i][j] = false;

                        System.out.println("\nVehiculo retirado con exito.");

                    } else {
                        System.out.println("\nOperacion cancelada.");
                    }

                    return;
                }
            }
        }

        if (!encontrado) {
            System.out.println("\nNo se encontro un vehiculo con esa placa.");
        }
    }

    // para validar que son numeros enteros y no decimales
    public static int leerEntero() {
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (Exception e) {
            System.out.println("\nDebe ingresar un número entero.");
            return 0;
        }
    }

    public static double leerDouble() {
        try { return Double.parseDouble(entrada.nextLine().trim()); }
        catch (Exception e) { System.out.println("\nError: valor inválido, se toma 0."); return 0; }
    }

    public static String getUsuarioAdmin() {
        return UsuarioAdmin;
    }

    public static String getContraseñaAdmin() {
        return ContraseñaAdmin;
    }

    public static String getUsuarioConta() {
        return UsuarioConta;
    }

    public static String getContraseñaConta() {
        return ContraseñaConta;
    }

    private static double calcularPrecio(String string, double horas) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
