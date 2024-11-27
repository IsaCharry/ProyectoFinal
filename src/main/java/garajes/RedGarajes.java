package garajes;

//Isabella Charry Moreno - 20241220186

import java.util.ArrayList;
import vehiculos.*;
import excepciones.*;

public class RedGarajes {

    private ArrayList<Garaje> garajes;
    private static RedGarajes instancia;

    private RedGarajes() {
        this.garajes = new ArrayList<>();
    }

    public static RedGarajes getInstancia() {
        if (instancia == null) {
            instancia = new RedGarajes();
        }
        return instancia;
    }

    public void agregarGaraje(Garaje garaje) throws ExcepcionGaraje {
        if (buscarGarajePorId(garaje.getId()) != null) {
            throw new ExcepcionGaraje("Ya existe un garaje con el ID: " + garaje.getId());
        }
        garajes.add(garaje);
    }

    public void eliminarGaraje(String id) throws ExcepcionGaraje {
        Garaje garaje = buscarGarajePorId(id);
        if (garaje == null) {
            throw new ExcepcionGaraje("No se encontro el garaje con ID: " + id);
        }
        if (!garaje.getEspacios().isEmpty()) {
            throw new ExcepcionGaraje("No se puede eliminar un garaje con vehículos");
        }
        garajes.remove(garaje);
    }

    public void actualizarGaraje(String id, Garaje garajeActualizado) throws ExcepcionGaraje {
        Garaje garaje = buscarGarajePorId(id);
        if (garaje == null) {
            throw new ExcepcionGaraje("No se encontro el garaje con ID: " + id);
        }
        int index = garajes.indexOf(garaje);
        garajes.set(index, garajeActualizado);
    }

    // Métodos de búsqueda
    public Garaje buscarGarajePorId(String id) {
        return garajes.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Garaje> buscarGarajePorCiudad(String ciudad) {
        ArrayList<Garaje> garajesCiudad = new ArrayList<>();
        for (Garaje garaje : garajes) {
            if (garaje.getCiudad().equalsIgnoreCase(ciudad)) {
                garajesCiudad.add(garaje);
            }
        }
        return garajesCiudad;
    }

    // Métodos para gestión de vehículos
    public void ingresarVehiculo(String idGaraje, Vehiculo vehiculo) throws ExcepcionGaraje {
        if (verificarVehiculoEnRed(vehiculo.getMatricula())) {
            throw new VehiculoYaRegistrado("El vehiculo ya esta registrado en otro garaje");
        }

        Garaje garaje = buscarGarajePorId(idGaraje);
        if (garaje == null) {
            throw new ExcepcionGaraje("Garaje no encontrado");
        }

        garaje.agregarVehiculo(vehiculo);
    }

    public void retirarVehiculo(String idGaraje, String matricula) throws ExcepcionGaraje {
        Garaje garaje = buscarGarajePorId(idGaraje);
        if (garaje == null) {
            throw new ExcepcionGaraje("Garaje no encontrado");
        }

        if (!garaje.retirarVehiculo(matricula)) {
            throw new ExcepcionGaraje("Vehiculo no encontrado en el garaje");
        }
    }

    public boolean verificarVehiculoEnRed(String matricula) {
        return garajes.stream()
                .anyMatch(garaje -> garaje.buscarVehiculo(matricula) != -1);
    }

    // Métodos para generación de informes
    public String generarReporteOcupacionTotal() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE OCUPACION TOTAL DE GARAJES\n");
        reporte.append("\n\n");

        for (Garaje garaje : garajes) {
            reporte.append(String.format("Garaje %s (%s, %s)\n",
                    garaje.getId(), garaje.getCiudad(), garaje.getDepartamento()));
            reporte.append(String.format("Ocupacion: %d/%d (%.1f%%)\n",
                    garaje.getEspaciosOcupados(),
                    garaje.getNumeroEspacios(),
                    (garaje.getEspaciosOcupados() * 100.0 / garaje.getNumeroEspacios())));
            reporte.append("-------------------------------------\n");
        }

        return reporte.toString();
    }

    public String generarReporteOcupacionPorTipo() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE OCUPACION POR TIPO DE VEHICULO\n");
        reporte.append("\n\n");

        for (Garaje garaje : garajes) {
            reporte.append(String.format("Garaje %s (%s)\n", garaje.getId(), garaje.getCiudad()));
            reporte.append(String.format("Autos: %d\n",
                    garaje.calcularOcupacionPorTipoVehiculo(new Auto("", 0, 0, false, false))));
            reporte.append(String.format("Motos: %d\n",
                    garaje.calcularOcupacionPorTipoVehiculo(new Moto("", 0, 0, false))));
            reporte.append(String.format("Camiones: %d\n",
                    garaje.calcularOcupacionPorTipoVehiculo(new Camion("", 0, 0, 2, "Sencillo", 0))));
            reporte.append("-------------------------------------\n");
        }

        return reporte.toString();
    }

    public String generarReporteRecaudoMensual() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTE DE RECAUDO MENSUAL\n");
        reporte.append("\n\n");

        double recaudoTotal = 0;

        for (Garaje garaje : garajes) {
            double recaudoGaraje = garaje.calcularIngresos();
            recaudoTotal += recaudoGaraje;

            reporte.append(String.format("Garaje %s (%s)\n", garaje.getId(), garaje.getCiudad()));
            reporte.append(String.format("Recaudo: $%.2f\n", recaudoGaraje));
            reporte.append("-------------------------------------\n");
        }

        reporte.append(String.format("\nRECAUDO TOTAL DE LA RED: $%.2f\n", recaudoTotal));

        return reporte.toString();
    }

    public ArrayList<Garaje> getGarajes() {
        return new ArrayList<>(garajes);
    }

    public int getCantidadGarajes() {
        return garajes.size();
    }
}
