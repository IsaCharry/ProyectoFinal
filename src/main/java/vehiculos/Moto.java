package vehiculos;

/**
 *
 * Isabella Charry Moreno - 20241220186
 */
public class Moto extends Vehiculo {

    private boolean tieneSidecar;

    public Moto(String marca, double precio, int cilindraje, boolean tieneSidecar) {
        super(marca, precio, cilindraje);
        this.tieneSidecar = tieneSidecar;

        calcularImpuestoCirculacion();
        actualizarCuotaGaraje();
    }

    public boolean getTieneSidecar() {
        return tieneSidecar;
    }

    public void setTieneSidecar(boolean tieneSidecar) {
        this.tieneSidecar = tieneSidecar;
        calcularImpuestoCirculacion();
        actualizarCuotaGaraje();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        super.calcularImpuestoCirculacion();

        if (tieneSidecar) {
            double impuestoActual = getImpuestoCirculacion();
            double aumento = impuestoActual * 0.10;
            setImpuestoCirculacion(impuestoActual + aumento);
        }
    }

    private void actualizarCuotaGaraje() {
        double cuotaMesGaraje = super.getCuotaMesGaraje();
        if (tieneSidecar) {
            setCuotaMesGaraje(cuotaMesGaraje * 1.5);
        }
    }
}
