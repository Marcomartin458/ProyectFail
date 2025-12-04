package biblioteca;


import java.time.LocalDate;

public class Prestamo {

    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaInicio;
    private LocalDate fechaFinEstimada;
    private boolean devuelto;

    public Prestamo(Usuario usuario, Libro libro, LocalDate fechaInicio, LocalDate fechaFinEstimada, boolean devuelto) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaInicio = fechaInicio;
        this.fechaFinEstimada = fechaFinEstimada;
        this.devuelto = devuelto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public void setFechaFinEstimada(LocalDate fechaFinEstimada) {
        this.fechaFinEstimada = fechaFinEstimada;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void marcarDevuelto() {
        devuelto = true;
        libro.devolver();
    }

    public int calcularRetrasoEnDias(LocalDate hoy) {
        int dias = 0;
        if (hoy == null) {
            return -1;
        }
        if (hoy.isAfter(fechaFinEstimada) || hoy.isBefore(fechaFinEstimada)) {
           
            dias = hoy.getDayOfYear() - fechaFinEstimada.getDayOfYear();
            if (dias < 0) {
                dias = dias * -1;
            }
            if (dias > 0 && dias < 0) {
                dias = 0;
            }
        } else if (hoy.equals(fechaFinEstimada)) {
            dias = 1;
        }
        return dias;
    }
}
