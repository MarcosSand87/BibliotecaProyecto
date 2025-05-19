package model;

import enums.EstadoRecurso;
import interfaces.Prestamista;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaManager implements Prestamista {
	private Map<String, RecursoBiblioteca> recursos;
	private Map<String, Usuario> usuarios;
	private Map<String, String> prestamos;
	
	/**
     * Constructor que inicializa las colecciones para almacenar recursos,
     *  usuarios, préstamos y registro de devoluciones.
     */
	public BibliotecaManager() {
		this.recursos = new HashMap<>();
		this.usuarios = new HashMap<>();
		this.prestamos = new HashMap<>();
		//this.registroDevoluciones = new ArrayList<>();
	}

	/**
     * Agrega un recurso con su ID.
     * @param recurso. Recurso de biblioteca (Libro, Revista, DVD) a agregar.
     */
	public void agregarRecurso(RecursoBiblioteca recurso) {
		recursos.put(recurso.getId(), recurso);
	}

	/**
     * Agrega un usuario con su ID.
     * @param usuario. El usuario a agregar.
     */
	public void agregarUsuario(Usuario usuario) {
		usuarios.put(usuario.getId(), usuario);
	}

	/**
     * Realiza el préstamo de un recurso a un usuario, actualizando su estado y registrando el préstamo.
     * @param recurso. El recurso (libro, revista, dvd).
     * @param usuario. El usuario que solicita el préstamo.
     * @return true. Si el préstamo se realizó, false en caso contrario.
     */
	@Override
	public boolean prestar(RecursoBiblioteca recurso, Usuario usuario) {
		if (recurso == null || usuario == null) {
			System.out.println("Recurso o usuario no encontrado.");
			return false;
		}

		if (recurso.getEstado() == EstadoRecurso.DISPONIBLE) {
			recurso.setEstado(EstadoRecurso.PRESTADO);
			prestamos.put(recurso.getId(), usuario.getId());
			System.out.println("Recurso prestado exitosamente a " + usuario.getNombre() + ".");
			System.out.println(recurso.descripcion());
			return true;
		} else {
			System.out.println("El recurso no está disponible para préstamo.");
			return false;
		}
	}

	/**
     * Realiza la devolución de un recurso, actualizando su estado a disponible.
     * @param recurso El recurso a devolver.
     * @return true si la devolución se realizó con éxito, false en caso contrario.
     */
    @Override
	public boolean devolver(RecursoBiblioteca recurso) {
		if (recurso == null) {
			System.out.println("Recurso no encontrado.");
			return false;
		}

		if (recurso.getEstado() == EstadoRecurso.PRESTADO) {
			recurso.setEstado(EstadoRecurso.DISPONIBLE);
			System.out.println("\nRecurso devuelto exitosamente.");
			System.out.println("\n" + recurso.descripcion());
			return true;
		} else {
			System.out.println("El recurso no está en estado prestado.");
			return false;
		}
	}

    /**
     * Presta un recurso identificado por su ID a un usuario identificado por su ID.
     * @param idRecurso. El ID del recurso a prestar.
     * @param idUsuario. El ID del usuario que solicita el préstamo.
     */
	public void prestarRecurso(String idRecurso, String idUsuario) {
		RecursoBiblioteca recurso = recursos.get(idRecurso);
		Usuario usuario = usuarios.get(idUsuario);
		prestar(recurso, usuario);
	}

	/**
     * Devuelve un recurso identificado por su ID.
     * @param idRecurso. El ID del recurso a devolver.
     */
	public void devolverRecurso(String idRecurso) {
		RecursoBiblioteca recurso = recursos.get(idRecurso);
		devolver(recurso);
	}

	/**
     * Lista todos los recursos del sistema si estan prestados, el nombre del usuario,
     */
	public void listarRecursos() {
		System.out.println("\n=== Lista de Recursos ===");
		for (RecursoBiblioteca recurso : recursos.values()) {
			System.out.println(recurso.descripcion());
			if (recurso.getEstado() == EstadoRecurso.PRESTADO) {
				String usuarioId = prestamos.get(recurso.getId());
				System.out.println("  Prestado a: " + usuarios.get(usuarioId).getNombre());
			}
		}
	}

	/**
     * Obtiene el mapa de recursos registrados en el sistema.
     * @return Mapa con los recursos, donde la clave es el ID del recurso.
     */
	public Map<String, RecursoBiblioteca> getRecursos() {
		return recursos;
	}
	/**
     * Obtiene el mapa de préstamos que se ven, asociando ID de recursos con ID de usuarios.
     * @return Mapa con los préstamos activos.
     */
	public Map<String, String> getPrestamos() {
		return prestamos;
	}

	/**
     * Obtiene el mapa de usuarios registrados en el sistema.
     * @return Mapa con los usuarios, donde la clave es el ID del usuario.
     */
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
}
