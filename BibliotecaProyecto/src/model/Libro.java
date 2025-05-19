package model;

public class Libro extends RecursoBiblioteca {
	private String autor;

	/**
     * Constructor que inicializa un libro con un ID, título y autor.
     * @param id Identificador único del libro.
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     */
	public Libro(String id, String titulo, String autor) {
		super(id, titulo);
		this.autor = autor;
	}

	/**
     * Descripción detallada del libro, incluyendo ID, título, autor y estado (PRESTADO, DISPONIBLE).
     * @return La descripción del libro.
     */
    @Override
	public String descripcion() {
		return "\nEl libro con el ID: " + getId() + "\nTítulo: " + getTitulo() + "\nAutor: " + autor + "\nEstado: " + getEstado();
	}
}
