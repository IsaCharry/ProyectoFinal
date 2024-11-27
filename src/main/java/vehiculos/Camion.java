package vehiculos;

/**
 *
 * Isabella Charry Moreno - 20241220186
 */
// Desarrollo del punto 1
public class Camion extends Vehiculo {

    private int numeroDeEjes;
    private String tipo;
    private double capacidadCarga;

    public Camion(String marca, double precio, int cilindraje, int numeroDeEjes, String tipo, double capacidadCarga) {

        // a. asignacion de atributos en el constructor
        super(marca, precio, cilindraje);
        this.numeroDeEjes = numeroDeEjes;
        this.tipo = tipo;
        this.capacidadCarga = capacidadCarga;

        calcularImpuestoCirculacion();
        actualizarCuotaGaraje();
    }

    public int getNumeroDeEjes() {
        return numeroDeEjes;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    @Override
    // c. calculo impuesto de circulacion para camiones
    public void calcularImpuestoCirculacion() {
        double impuestoBase = getPrecio() * 0.09;

        double impuestoCarga = Math.ceil(getCapacidadCarga() / 5) * 10;

        setImpuestoCirculacion(impuestoBase + impuestoCarga);
    }
    // d. calculo de la cuota de garaje para camiones

    private void actualizarCuotaGaraje() {
        double cuotaBase = getCuotaMesGaraje();
        double aumento = 0;
        if (getTipo().equalsIgnoreCase("Sencillo")) {
            aumento = cuotaBase * 0.75;
            setCuotaMesGaraje(cuotaBase + aumento);
        } else {
            aumento = cuotaBase * 1.25;
            setCuotaMesGaraje(cuotaBase + aumento);
        }
    }
}
