package interfaces;

import model.RecursoBiblioteca;
import model.Usuario;

/**
 * Interfaz que define las operaciones de préstamo y devolución de recursos en la biblioteca.
 */
public interface Prestamista {
	
	/**
     * Realiza el préstamo de un recurso a un usuario.
     * @param recurso. El recurso a prestar.
     * @param usuario. El usuario que solicita el préstamo.
     * @return true: Si el préstamo se realizó con éxito. false: En caso contrario.
     */
	boolean prestar(RecursoBiblioteca recurso, Usuario usuario);

	/**
     * Realiza la devolución de un recurso.
     * @param recurso. El recurso a devolver.
     * @return true: Si la devolución se realizó con éxito. false: En caso contrario.
     */
	boolean devolver(RecursoBiblioteca recurso);
}
