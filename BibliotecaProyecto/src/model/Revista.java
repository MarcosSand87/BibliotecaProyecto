package model;

public class Revista extends RecursoBiblioteca {
	private int numeroEdicion;

	/**
     * Constructor que inicializa una revista con un ID, título y número de edición.
     * @param id Identificador único de la revista.
     * @param titulo Título de la revista.
     * @param numeroEdicion Número de edición de la revista.
     */
	public Revista(String id, String titulo, int numeroEdicion) {
		super(id, titulo);
		this.numeroEdicion = numeroEdicion;
	}

	/**
     * Descripción detallada de la revista, incluyendo ID, título, número de edición y estado (PRESTADO, DISPONIBLE).
     * @return La descripción de la revista.
     */
    @Override
	public String descripcion() {
		return "\nLa revista con el ID: " + getId() + "\nTítulo: " + getTitulo() + "\nnumero de edición: " + numeroEdicion + "\nEstado: " + getEstado();
	}
}
