package garajes;

// Isabella Charry Moreno - 20241220186
import java.util.ArrayList;
import java.util.Scanner;
import vehiculos.*;
import excepciones.*;

public class MenuGaraje {

    private Scanner scanner;
    private RedGarajes red;

    public MenuGaraje() {
        this.scanner = new Scanner(System.in);
        this.red = RedGarajes.getInstancia();
    }

    public void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n SISTEMA DE GESTION DE PARQUEADEROS");
            System.out.println("1. Gestionar Garajes");
            System.out.println("2. Gestionar Vehiculos");
            System.out.println("3. Generar Informes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        menuGarajes();
                        break;
                    case 2:
                        menuVehiculos();
                        break;
                    case 3:
                        menuInformes();
                        break;
                    case 4:
                        System.out.println("Gracias!");
                        return;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero valido");
            }
        }
    }

    private void menuGarajes() {
        while (true) {
            System.out.println("\n GESTION DE GARAJES");
            System.out.println("1. Crear nuevo garaje");
            System.out.println("2. Eliminar garaje");
            System.out.println("3. Actualizar garaje");
            System.out.println("4. Listar garajes");
            System.out.println("5. Ver detalles de un garaje");
            System.out.println("6. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        crearGaraje();
                        break;
                    case 2:
                        eliminarGaraje();
                        break;
                    case 3:
                        actualizarGaraje();
                        break;
                    case 4:
                        listarGarajes();
                        break;
                    case 5:
                        verDetallesGaraje();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero valido");
            }
        }
    }

    private void menuVehiculos() {
        while (true) {
            System.out.println("\n GESTION DE VEHICULOS");
            System.out.println("1. Ingresar vehiculo");
            System.out.println("2. Retirar vehiculo");
            System.out.println("3. Buscar vehiculo");
            System.out.println("4. Listar vehiculos por garaje");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        ingresarVehiculo();
                        break;
                    case 2:
                        retirarVehiculo();
                        break;
                    case 3:
                        buscarVehiculo();
                        break;
                    case 4:
                        listarVehiculosPorGaraje();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero valido");
            }
        }
    }

    private void menuInformes() {
        while (true) {
            System.out.println("\n GENERACION DE INFORMES");
            System.out.println("1. Reporte de ocupacion total");
            System.out.println("2. Reporte de ocupacion por tipo de vehiculo");
            System.out.println("3. Reporte de recaudo mensual");
            System.out.println("4. Reporte de garajes por ciudad");
            System.out.println("5. Estadisticas detalladas por garaje");
            System.out.println("6. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.println(red.generarReporteOcupacionTotal());
                        break;
                    case 2:
                        System.out.println(red.generarReporteOcupacionPorTipo());
                        break;
                    case 3:
                        System.out.println(red.generarReporteRecaudoMensual());
                        break;
                    case 4:
                        generarReportePorCiudad();
                        break;
                    case 5:
                        generarEstadisticasGaraje();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero valido");
            }
        }
    }

    // Métodos para gestión de garajes
    private void crearGaraje() {
        try {
            System.out.println("\n CREAR NUEVO GARAJE");
            System.out.print("ID del garaje: ");
            String id = scanner.nextLine();
            System.out.print("Departamento: ");
            String departamento = scanner.nextLine();
            System.out.print("Ciudad: ");
            String ciudad = scanner.nextLine();
            System.out.print("Direccion: ");
            String direccion = scanner.nextLine();
            System.out.print("Telefono: ");
            String telefono = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Nombre del administrador: ");
            String administrador = scanner.nextLine();
            System.out.print("Numero de espacios: ");
            int numeroEspacios = Integer.parseInt(scanner.nextLine());

            Garaje garaje = new Garaje(id, departamento, ciudad, direccion,
                    telefono, email, administrador, numeroEspacios);
            red.agregarGaraje(garaje);
            System.out.println("Garaje creado exitosamente");
        } catch (NumberFormatException e) {
            System.out.println("Error: El numero de espacios debe ser un numero valido");
        } catch (ExcepcionGaraje e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarGaraje() {
        try {
            System.out.println("\n ELIMINAR GARAJE");
            System.out.print("ID del garaje a eliminar: ");
            String id = scanner.nextLine();
            red.eliminarGaraje(id);
            System.out.println("Garaje eliminado exitosamente");
        } catch (ExcepcionGaraje e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void actualizarGaraje() {
        try {
            System.out.println("\n ACTUALIZAR GARAJE");
            System.out.print("ID del garaje a actualizar: ");
            String id = scanner.nextLine();

            Garaje garajeExistente = red.buscarGarajePorId(id);
            if (garajeExistente == null) {
                System.out.println("Error: Garaje no encontrado");
                return;
            }

            System.out.println("Ingrese los nuevos datos (Enter para mantener el valor actual):");

            System.out.print("Departamento [" + garajeExistente.getDepartamento() + "]: ");
            String departamento = scanner.nextLine();
            if (departamento.isEmpty()) {
                departamento = garajeExistente.getDepartamento();
            }

            System.out.print("Ciudad [" + garajeExistente.getCiudad() + "]: ");
            String ciudad = scanner.nextLine();
            if (ciudad.isEmpty()) {
                ciudad = garajeExistente.getCiudad();
            }

            System.out.print("Direccion [" + garajeExistente.getDireccion() + "]: ");
            String direccion = scanner.nextLine();
            if (direccion.isEmpty()) {
                direccion = garajeExistente.getDireccion();
            }

            System.out.print("Telefono [" + garajeExistente.getTelefono() + "]: ");
            String telefono = scanner.nextLine();
            if (telefono.isEmpty()) {
                telefono = garajeExistente.getTelefono();
            }

            System.out.print("Email [" + garajeExistente.getEmail() + "]: ");
            String email = scanner.nextLine();
            if (email.isEmpty()) {
                email = garajeExistente.getEmail();
            }

            System.out.print("Administrador [" + garajeExistente.getAdministrador() + "]: ");
            String administrador = scanner.nextLine();
            if (administrador.isEmpty()) {
                administrador = garajeExistente.getAdministrador();
            }

            Garaje garajeActualizado = new Garaje(id, departamento, ciudad, direccion,
                    telefono, email, administrador,
                    garajeExistente.getNumeroEspacios());

            red.actualizarGaraje(id, garajeActualizado);
            System.out.println("Garaje actualizado exitosamente");
        } catch (ExcepcionGaraje e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarGarajes() {
        System.out.println("\n LISTA DE GARAJES");
        if (red.getGarajes().isEmpty()) {
            System.out.println("No hay garajes registrados");
            return;
        }

        for (Garaje garaje : red.getGarajes()) {
            System.out.println(garaje.toString());
        }
    }

    private void verDetallesGaraje() {
        System.out.println("\n DETALLES DE GARAJE");
        System.out.print("ID del garaje: ");
        String id = scanner.nextLine();

        Garaje garaje = red.buscarGarajePorId(id);
        if (garaje == null) {
            System.out.println("Error: Garaje no encontrado");
            return;
        }

        garaje.mostrarEstadisticas();
    }

    // Métodos para gestión de vehículos
    private void ingresarVehiculo() {
        try {
            System.out.println("\n INGRESAR VEHICULO");
            System.out.print("ID del garaje: ");
            String idGaraje = scanner.nextLine();

            // Validación si existe el garaje
            Garaje garaje = red.buscarGarajePorId(idGaraje);
            if (garaje == null) {
                throw new ExcepcionGaraje("El garaje con ID " + idGaraje + " no existe");
            }

            // Verificar si hay espacio disponible
            if (garaje.getEspaciosDisponibles() == 0) {
                throw new ExcepcionGaraje("El garaje no tiene espacios disponibles");
            }

            System.out.println("Tipo de vehiculo:");
            System.out.println("1. Auto");
            System.out.println("2. Moto");
            System.out.println("3. Camion");
            System.out.print("Seleccione una opcion: ");
            int tipo = Integer.parseInt(scanner.nextLine());

            System.out.print("Matricula: ");
            String matricula = scanner.nextLine();
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = Double.parseDouble(scanner.nextLine());
            System.out.print("Cilindraje: ");
            int cilindraje = Integer.parseInt(scanner.nextLine());

            Vehiculo vehiculo = null;

            switch (tipo) {
                case 1:
                    System.out.print("¿Tiene radio? (s/n): ");
                    boolean tieneRadio = scanner.nextLine().equalsIgnoreCase("s");
                    System.out.print("¿Tiene navegador? (s/n): ");
                    boolean tieneNavegador = scanner.nextLine().equalsIgnoreCase("s");
                    vehiculo = new Auto(marca, precio, cilindraje, tieneRadio, tieneNavegador);
                    break;
                case 2:
                    System.out.print("¿Tiene sidecar? (s/n): ");
                    boolean tieneSidecar = scanner.nextLine().equalsIgnoreCase("s");
                    vehiculo = new Moto(marca, precio, cilindraje, tieneSidecar);
                    break;
                case 3:
                    System.out.print("Numero de ejes: ");
                    int numeroEjes = Integer.parseInt(scanner.nextLine());
                    System.out.print("Tipo (Sencillo/Doble): ");
                    String tipoCamion = scanner.nextLine();
                    System.out.print("Capacidad de carga: ");
                    double capacidadCarga = Double.parseDouble(scanner.nextLine());
                    vehiculo = new Camion(marca, precio, cilindraje, numeroEjes, tipoCamion, capacidadCarga);
                    break;
                default:
                    throw new ExcepcionGaraje("Tipo de vehículo invalido");
            }

            vehiculo.matricular(matricula);
            red.ingresarVehiculo(idGaraje, vehiculo);
            System.out.println("Vehiculo ingresado exitosamente");

        } catch (NumberFormatException e) {
            System.out.println("Error: Valor numerico invalido");
        } catch (ExcepcionGaraje e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void retirarVehiculo() {
        try {
            System.out.println("\n RETIRAR VEHICULO");
            System.out.print("ID del garaje: ");
            String idGaraje = scanner.nextLine();
            System.out.print("Matricula del vehiculo: ");
            String matricula = scanner.nextLine();

            red.retirarVehiculo(idGaraje, matricula);
            System.out.println("Vehiculo retirado exitosamente");
        } catch (ExcepcionGaraje e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarVehiculo() {
        System.out.println("\n BUSCAR VEHICULO");
        System.out.print("Matricula del vehiculo: ");
        String matricula = scanner.nextLine();

        boolean encontrado = false;
        for (Garaje garaje : red.getGarajes()) {
            int posicion = garaje.buscarVehiculo(matricula);

            if (posicion != -1) {
                System.out.println("\n Vehiculo encontrado en:");
                System.out.println("Garaje: " + garaje.getId());
                System.out.println("Ciudad: " + garaje.getCiudad());
                System.out.println("Direccion: " + garaje.getDireccion());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Vehiculo no encontrado en ningun garaje");
        }
    }

    private void listarVehiculosPorGaraje() {
        System.out.println("\n LISTAR VEHICULOS POR GARAJE");
        System.out.print("ID del garaje: ");
        String id = scanner.nextLine();

        Garaje garaje = red.buscarGarajePorId(id);
        if (garaje == null) {
            System.out.println("Error: Garaje no encontrado");
            return;
        }

        ArrayList<Vehiculo> vehiculos = garaje.getEspacios();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos en este garaje");
            return;
        }

        System.out.println("\n Vehiculos en garaje " + id + ":");
        System.out.println("--------------------------------");
        for (Vehiculo v : vehiculos) {
            String tipo = "";
            if (v instanceof Auto) {
                tipo = "Auto";
            } else if (v instanceof Moto) {
                tipo = "Moto";
            } else if (v instanceof Camion) {
                tipo = "Camion";
            }

            System.out.printf("Matricula: %s | Tipo: %s | Marca: %s\n",
                    v.getMatricula(), tipo, v.getMarca());
        }
    }

    // Métodos para informes específicos
    private void generarReportePorCiudad() {
        System.out.println("\n REPORTE POR CIUDAD");
        System.out.print("Ingrese la ciudad: ");
        String ciudad = scanner.nextLine();

        ArrayList<Garaje> garajesCiudad = red.buscarGarajePorCiudad(ciudad);
        if (garajesCiudad.isEmpty()) {
            System.out.println("No hay garajes registrados en " + ciudad);
            return;
        }

        System.out.println("\nGarajes en " + ciudad + ":");
        System.out.println("--------------------------------");
        for (Garaje garaje : garajesCiudad) {
            System.out.println("\nGaraje: " + garaje.getId());
            System.out.println("Direccion: " + garaje.getDireccion());
            System.out.println("Espacios ocupados: " + garaje.getEspaciosOcupados()
                    + "/" + garaje.getNumeroEspacios());
            System.out.println("Recaudo mensual: $" + garaje.calcularIngresos());
        }

        // Calcular totales
        int totalEspacios = garajesCiudad.stream()
                .mapToInt(Garaje::getNumeroEspacios)
                .sum();
        int totalOcupados = garajesCiudad.stream()
                .mapToInt(Garaje::getEspaciosOcupados)
                .sum();
        double totalRecaudo = garajesCiudad.stream()
                .mapToDouble(Garaje::calcularIngresos)
                .sum();

        System.out.println("\nResumen para " + ciudad + ":");
        System.out.println("Numero de garajes: " + garajesCiudad.size());
        System.out.println("Total espacios: " + totalEspacios);
        System.out.println("Total ocupados: " + totalOcupados);
        System.out.println("Ocupacion total: "
                + String.format("%.1f%%", (totalOcupados * 100.0 / totalEspacios)));
        System.out.println("Recaudo total: $" + String.format("%.2f", totalRecaudo));
    }

    private void generarEstadisticasGaraje() {
        System.out.println("\n  ESTADISTICAS DETALLADAS DE GARAJE");
        System.out.print("ID del garaje: ");
        String id = scanner.nextLine();

        Garaje garaje = red.buscarGarajePorId(id);
        if (garaje == null) {
            System.out.println("Error: Garaje no encontrado");
            return;
        }

        // Mostrar estadísticas generales
        System.out.println("\nInformación general:");
        System.out.println("--------------------------------");
        System.out.println("ID: " + garaje.getId());
        System.out.println("Ubicacion: " + garaje.getCiudad() + ", " + garaje.getDepartamento());
        System.out.println("Direccion: " + garaje.getDireccion());
        System.out.println("Administrador: " + garaje.getAdministrador());
        System.out.println("Contacto: " + garaje.getTelefono() + " | " + garaje.getEmail());

        // Mostrar estadísticas de ocupación
        System.out.println("\nEstadisticas de ocupacion:");
        System.out.println("--------------------------------");
        int espaciosTotal = garaje.getNumeroEspacios();
        int espaciosOcupados = garaje.getEspaciosOcupados();
        double porcentajeOcupacion = (espaciosOcupados * 100.0) / espaciosTotal;

        System.out.println("Espacios totales: " + espaciosTotal);
        System.out.println("Espacios ocupados: " + espaciosOcupados);
        System.out.println("Espacios disponibles: " + garaje.getEspaciosDisponibles());
        System.out.println("Porcentaje de ocupacion: " + String.format("%.1f%%", porcentajeOcupacion));

        // Mostrar distribución por tipo de vehículo
        System.out.println("\nDistribucion por tipo de vehiculo:");
        System.out.println("--------------------------------");
        int autos = garaje.calcularOcupacionPorTipoVehiculo(new Auto("", 0, 0, false, false));
        int motos = garaje.calcularOcupacionPorTipoVehiculo(new Moto("", 0, 0, false));
        int camiones = garaje.calcularOcupacionPorTipoVehiculo(new Camion("", 0, 0, 2, "Sencillo", 0));

        System.out.println("Autos: " + autos);
        System.out.println("Motos: " + motos);
        System.out.println("Camiones: " + camiones);

        // Mostrar estadísticas de camiones
        garaje.contarCamionesPorTipo();

        // Mostrar información financiera
        System.out.println("\nInformacion Financiera:");
        System.out.println("--------------------------------");
        System.out.println("Recaudo mensual: $" + String.format("%.2f", garaje.calcularIngresos()));
        System.out.println("Recaudo promedio por vehículo: $"
                + String.format("%.2f", espaciosOcupados > 0
                        ? garaje.calcularIngresos() / espaciosOcupados : 0));
    }
}
