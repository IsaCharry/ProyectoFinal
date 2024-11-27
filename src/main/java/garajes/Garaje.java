package garajes;

// Isabella Charry Moreno - 20241220186

import java.util.ArrayList;
import vehiculos.*;
import excepciones.*;

public class Garaje implements iGaraje {

    private String id;
    private String departamento;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private String administrador;
    private int numeroEspacios; // Gestion de espacios
    private ArrayList<Vehiculo> espacios;

    public Garaje(String id, String departamento, String ciudad, String direccion,
            String telefono, String email, String administrador, int numeroEspacios) throws ExcepcionGaraje {
        if (numeroEspacios <= 0) {
            throw new ExcepcionGaraje("El numero de espacios debe ser mayor que cero");
        }

        this.id = id;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.administrador = administrador;
        this.numeroEspacios = numeroEspacios;
        this.espacios = new ArrayList<>();
    }

    // Métodos para gestionar vehículos
    public boolean agregarVehiculo(Vehiculo vehiculo) throws ExcepcionGaraje {
        // Verificar si hay espacio disponible
        if (espacios.size() >= numeroEspacios) {
            throw new EspacioInsuficiente("No hay espacios disponibles en el garaje");
        }

        // Verificar matrícula
        if (vehiculo.getMatricula() == null || vehiculo.getMatricula().isEmpty()) {
            throw new ExcepcionGaraje("El vehiculo debe tener una matricula valida");
        }

        // Verificar límites por tipo de vehículo según las políticas
        if (vehiculo instanceof Camion) {
            int maxCamiones = numeroEspacios >= 100 ? 20 : 10;
            int camionesActuales = contarVehiculosPorTipo(Camion.class);
            if (camionesActuales >= maxCamiones) {
                throw new LimiteTipoVehiculo("Se ha alcanzado el limite máximo de camiones");
            }
        } else if (vehiculo instanceof Moto) {
            int maxMotos = (int) (numeroEspacios * 0.2);
            int motosActuales = contarVehiculosPorTipo(Moto.class);
            if (motosActuales >= maxMotos) {
                throw new LimiteTipoVehiculo("Se ha alcanzado el limite maximo de motos");
            }
        }

        espacios.add(vehiculo);
        return true;
    }

    public boolean retirarVehiculo(String matricula) {
        return espacios.removeIf(v -> v.getMatricula().equals(matricula));
    }

    public int buscarVehiculo(String matricula) {
        for (int i = 0; i < espacios.size(); i++) {
            if (espacios.get(i).getMatricula().equals(matricula)) {
                return i;
            }
        }
        return -1;
    }

    private int contarVehiculosPorTipo(Class<?> tipo) {
        return (int) espacios.stream()
                .filter(v -> tipo.isInstance(v))
                .count();
    }

    // Métodos de la interfaz iGarage
    @Override
    public double calcularIngresos() {
        return espacios.stream()
                .mapToDouble(Vehiculo::getCuotaMesGaraje)
                .sum();
    }

    @Override
    public int calcularOcupacionPorTipoVehiculo(Vehiculo v) {
        return contarVehiculosPorTipo(v.getClass());
    }

    // Métodos para estadísticas y reportes
    public void mostrarEstadisticas() {
        System.out.println("\nEstadisticas del Garaje " + id);
        System.out.println("Ubicacion: " + ciudad + ", " + departamento);
        System.out.println("Espacios totales: " + numeroEspacios);
        System.out.println("Espacios ocupados: " + espacios.size());
        System.out.println("Espacios disponibles: " + getEspaciosDisponibles());
        System.out.println("Ocupacion por tipo:");
        System.out.println("- Autos: " + contarVehiculosPorTipo(Auto.class));
        System.out.println("- Motos: " + contarVehiculosPorTipo(Moto.class));
        System.out.println("- Camiones: " + contarVehiculosPorTipo(Camion.class));
        System.out.println("Ingresos mensuales: $" + calcularIngresos());
    }

    public void contarCamionesPorTipo() {
        int camionesSencillos = 0;
        int camionesDobles = 0;

        for (Vehiculo v : espacios) {
            if (v instanceof Camion) {
                Camion camion = (Camion) v;
                if (camion.getTipo().equals("Sencillo")) {
                    camionesSencillos++;
                } else {
                    camionesDobles++;
                }
            }
        }

        System.out.println("\nCamiones por tipo en Garaje " + id + ":");
        System.out.println("Sencillos: " + camionesSencillos);
        System.out.println("Dobles: " + camionesDobles);
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public int getNumeroEspacios() {
        return numeroEspacios;
    }

    public ArrayList<Vehiculo> getEspacios() {
        return new ArrayList<>(espacios); // Retorna una copia para encapsulación
    }

    public int getEspaciosDisponibles() {
        return numeroEspacios - espacios.size();
    }

    public int getEspaciosOcupados() {
        return espacios.size();
    }

    // Para validación de políticas
    public boolean validarEspacioDisponible(Vehiculo vehiculo) {
        if (getEspaciosDisponibles() == 0) {
            return false;
        }

        if (vehiculo instanceof Camion) {
            int maxCamiones = numeroEspacios >= 100 ? 20 : 10;
            return contarVehiculosPorTipo(Camion.class) < maxCamiones;
        }

        if (vehiculo instanceof Moto) {
            int maxMotos = (int) (numeroEspacios * 0.2);
            return contarVehiculosPorTipo(Moto.class) < maxMotos;
        }

        return true;
    }

    @Override
    public String toString() {
        return String.format("Garaje %s - %s, %s (%d/%d espacios ocupados)",
                id, ciudad, departamento, getEspaciosOcupados(), numeroEspacios);
    }
}
