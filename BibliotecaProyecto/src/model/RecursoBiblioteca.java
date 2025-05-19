package model;

import enums.EstadoRecurso;

public abstract class RecursoBiblioteca {
	private String id;
	private String titulo;
	private EstadoRecurso estado;

	/**
     * Constructor que inicializa un recurso de biblioteca con un ID, título y estado del recurso disponible.
     * @param id Identificador único del recurso.
     * @param titulo Título del recurso.
     */
	public RecursoBiblioteca(String id, String titulo) {
		this.id = id;
		this.titulo = titulo;
		this.estado = EstadoRecurso.DISPONIBLE;
	}

	/**
     * Obtiene el ID del recurso.
     * @return El ID del recurso.
     */
	public String getId() {
		return id;
	}

	/**
     * Obtiene el título del recurso.
     * @return El título del recurso.
     */
	public String getTitulo() {
		return titulo;
	}

	/**
     * Obtiene el estado actual del recurso DISPONIBLE, PRESTADO, RESERVADO (no se a usado en el trabajo).
     * @return El estado del recurso.
     */
	public EstadoRecurso getEstado() {
		return estado;
	}

	/**
     * Establece el estado del recurso.
     * @param estado El nuevo estado del recurso.
     */
	public void setEstado(EstadoRecurso estado) {
		this.estado = estado;
	}

	/**
     * Método abstracto que proporciona una descripción detallada del recurso.
     * Las clases derivadas (Libro, Revista, DVD) implementan este método.
     * @return Una cadena con la descripción del recurso.
     */
	public abstract String descripcion();
}
