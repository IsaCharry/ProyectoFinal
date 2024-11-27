package vehiculos;

/**
 *
 * Isabella Charry Moreno - 20241220186
 */
public class Vehiculo {

    private String matricula;
    private String marca;
    private double precio;
    private int cilindraje;
    private double impuestoCirculacion;
    private double cuotaMesGaraje;

    private static double cuota = 100.0;

    public Vehiculo(String marca, double precio, int cilindraje) {
        this.matricula = null;
        this.marca = marca;
        this.precio = precio;
        this.cilindraje = cilindraje;
        this.cuotaMesGaraje = cuota;
        this.impuestoCirculacion = 0.0;
        this.calcularImpuestoCirculacion();
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public double getImpuestoCirculacion() {
        return impuestoCirculacion;
    }

    public double getCuotaMesGaraje() {
        return cuotaMesGaraje;
    }

    public void setCuotaMesGaraje(double cuotaMesGaraje) {
        if (cuotaMesGaraje >= 0) {
            this.cuotaMesGaraje = cuotaMesGaraje;
        }
    }

    protected void setImpuestoCirculacion(double impuesto) {
        this.impuestoCirculacion = impuesto;
    }

    public void calcularImpuestoCirculacion() {
        this.impuestoCirculacion = this.precio * 0.02;
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean matricular(String matricula) {
        if (matricula != null && matricula.length() == 6) {
            this.matricula = matricula;
            return true;
        }
        return false;
    }

}
