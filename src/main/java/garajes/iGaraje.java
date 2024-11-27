package garajes;

/**
 *
 Isabella Charry Moreno - 20241220186
 */
import vehiculos.Vehiculo;

public interface iGaraje {

    double calcularIngresos();

    int calcularOcupacionPorTipoVehiculo(Vehiculo v);
}
