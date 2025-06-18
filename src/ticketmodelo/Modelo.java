package ticketmodelo;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.ibm.icu.text.RuleBasedNumberFormat;

public abstract class Modelo {

    // Los valores iniciales se hacen en base a valores predeterminados.
    private int modulo = 0;
    private int fichas = 0;
    private int denominacion = 0;
    private String premio = "";
    private int ticket = 0;
    private String moneda = "Pesos";
    private String nom_casino = "Jisnel";

    // Define el formato de la fecha y la hora para la factura.
    private SimpleDateFormat fecha;
    // Formateador de números para convertir el premio a palabras.
    private RuleBasedNumberFormat formateador;

    // Métodos abstractos que deben ser implementados por las subclases.
    public abstract void fecha_formato();
    public abstract void lenguaje_moneda();
    public abstract String formato_factura();

    public Modelo(String moneda, String nom_casino) {
        this.moneda = moneda;
        this.nom_casino = nom_casino;
    }

    public void var_model(int modulo, int fichas, int denominacion, int ticket) {

        this.modulo = modulo;
        this.fichas = fichas;
        this.denominacion = denominacion;
        this.ticket = ticket;

        // Formatea el valor del premio a una cadena para visualizar las cifras de
        // miles.
        DecimalFormat formato = new DecimalFormat("#,###");
        this.premio = formato.format(denominacion * fichas).replace(",", ".");

    }

    // Retorna el premio en palabras.
    public String getPremio_letters() {

        return (formateador.format(this.getDenominacion() * this.getFichas())).toUpperCase();
    }

    // Set's y Get's para cada variable.
    public RuleBasedNumberFormat getFormateador() {
        return formateador;
    }
    public void setFormateador(RuleBasedNumberFormat formateador) {
        this.formateador = formateador;
    }

    public String getNom_casino() {
        return nom_casino;
    }

    public void setNom_casino(String nom_casino) {
        this.nom_casino = nom_casino;
    }

    public int getTicket() {
        return ticket;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getPremio() {

        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public String getFecha() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return fecha.format(timestamp);
    }

    public void setFecha(SimpleDateFormat sdf3) {
        this.fecha = sdf3;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public int getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(int denominacion) {
        this.denominacion = denominacion;
    }

}
