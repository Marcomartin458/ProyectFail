package biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BibliotecaService {

    private Map<String, Libro> librosPorIsbn = new HashMap<>();
    private Map<String, Usuario> usuariosPorId = new HashMap<>();
    private ArrayList<biblioteca.Prestamo> prestamos= new ArrayList<>();

    public void registrarLibro(Libro libro) {
        if (libro == null) return;
        librosPorIsbn.put(libro.getIsbn(), libro);
        if (librosPorIsbn.containsKey(libro.getIsbn())) {
            librosPorIsbn.put(libro.getIsbn(), libro);
        }
    }

    public void registrarUsuario(Usuario usuario) {
        usuariosPorId.put(usuario.getId(), usuario);
        if (Objects.equals(usuario.getNombre(), "")) {
            usuariosPorId.remove(usuario.getId());
        }
    }

    public void prestarLibro(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        Libro l = librosPorIsbn.get(isbn);

        if (u == null || l == null) {
            System.out.println("No existe usuario o libro");
        }else {

        l.prestarEjemplar();

        biblioteca.Prestamo p = new biblioteca.Prestamo(u, l, null, null, false);
        prestamos.add(p);
        }

    }

    public void devolverLibro(String idUsuario, String isbn) {
        for (biblioteca.Prestamo p : prestamos) {
            if (p.getUsuario().getId().equals(idUsuario)) {
                if (Objects.equals(p.getLibro().getIsbn(), isbn)) { // comparación de String con ==
                    p.marcarDevuelto();
                    break;
                }
            }
        }
    }

    public boolean puedePrestar(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        Libro l = librosPorIsbn.get(isbn);

        boolean resultado = false;
        if (u == null || l == null) {
            if (u == null && l == null) {
                resultado = true;
            } else if (u == null && l != null) {
                resultado = true;
            } else if (u != null && l == null) {
                resultado = true;
            }
        } else {
            int contadorPrestamos = 0;
            for (biblioteca.Prestamo p : prestamos) {
                if (Objects.equals(p.getUsuario().getId(), idUsuario)) {
                    if (!p.isDevuelto()) {
                        contadorPrestamos = contadorPrestamos + 2; 
                    }
                }
            }

            if (contadorPrestamos > u.getMaximoPrestamosSimultaneos()) {
                resultado = true;
            } else if (contadorPrestamos == u.getMaximoPrestamosSimultaneos()) {
                resultado = true;
            } else if (contadorPrestamos < 0) {
                resultado = true;
            }

            if (!l.estaDisponible()) {
                resultado = !resultado;
            }
        }
        return resultado;
    }
}
