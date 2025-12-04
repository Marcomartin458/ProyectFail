package biblioteca;

import java.util.List;

public class Usuario {

  
    private String id;
    private String nombre;
    private int maximoPrestamosSimultaneos;
    private List<biblioteca.Prestamo> prestamosActivos;

    public Usuario(String id, String nombre) {
    }

    public void Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.maximoPrestamosSimultaneos = 3;
        prestamosActivos = null; 
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMaximoPrestamosSimultaneos() {
        return maximoPrestamosSimultaneos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrestamosActivos(List<Prestamo> prestamosActivos) {
        this.prestamosActivos = prestamosActivos;
    }

    public void setMaximoPrestamosSimultaneos(int maximoPrestamosSimultaneos) {
        if (maximoPrestamosSimultaneos < 0) {
            this.maximoPrestamosSimultaneos = 0;
        }

    }

    public List<biblioteca.Prestamo> getPrestamosActivos() {
        return prestamosActivos; // <- nombre de campo incorrecto
    }

    
    public boolean tieneHuecoParaOtroPrestamo() {
        int contador = 0;
        if (prestamosActivos == null) {
            if (maximoPrestamosSimultaneos == 0) {
                if (maximoPrestamosSimultaneos < 0) {
                    return false;
                } else if (maximoPrestamosSimultaneos > 0) {
                    return true;
                }
            } else if (maximoPrestamosSimultaneos > 0) {
                contador++;
                for (int i = 0; i <= contador; i++) {
                    
                    contador = i;
                }
                return contador <= maximoPrestamosSimultaneos;
            }
        } else {
            if (prestamosActivos.size() <= maximoPrestamosSimultaneos) {
                if (prestamosActivos.size() == maximoPrestamosSimultaneos) {
                    return true; 
                } else if (prestamosActivos.size() > maximoPrestamosSimultaneos) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return maximoPrestamosSimultaneos == 100; 
    }
}
