package vehiculos;

/**
 *
 Isabella Charry Moreno - 20241220186
 */
public class Auto extends Vehiculo {

    private boolean tieneRadio;
    private boolean tieneNavegador;

    public Auto(String marca, double precio, int cilindraje, boolean tieneRadio, boolean tieneNavegador) {
        super(marca, precio, cilindraje);
        this.tieneRadio = tieneRadio;
        this.tieneNavegador = tieneNavegador;

        calcularImpuestoCirculacion();
        actualizarCuotaGaraje();
    }

    public boolean getTieneRadio() {
        return tieneRadio;
    }

    public void setTieneRadio(boolean tieneRadio) {
        this.tieneRadio = tieneRadio;
        calcularImpuestoCirculacion();
    }

    public boolean getTieneNavegador() {
        return tieneNavegador;
    }

    public void setTieneNavegador(boolean tieneNavegador) {
        this.tieneNavegador = tieneNavegador;
        calcularImpuestoCirculacion();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        super.calcularImpuestoCirculacion();

        double impuestoBase = getImpuestoCirculacion();
        double impuestoAdicional = 0;

        if (tieneRadio) {
            impuestoAdicional += getPrecio() * 0.01;
        }

        if (tieneNavegador) {
            impuestoAdicional += getPrecio() * 0.02;
        }

        setImpuestoCirculacion(impuestoBase + impuestoAdicional);
    }

    private void actualizarCuotaGaraje() {
        double cuotaBase = getCuotaMesGaraje();
        int cilindraje = getCilindraje();

        if (cilindraje > 2499) {
            setCuotaMesGaraje(cuotaBase * 1.20);
        }
    }
}
