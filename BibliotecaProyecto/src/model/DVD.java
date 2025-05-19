package model;

public class DVD extends RecursoBiblioteca {
	private int duracionMinutos;
	
	/**
     * Constructor que inicializa un DVD con un ID, título y duración en minutos.
     * @param id Identificador único del DVD.
     * @param titulo Título del DVD.
     * @param duracionMinutos Duración del DVD en minutos.
     */
	public DVD(String id, String titulo, int duracionMinutos) {
		super(id, titulo);
		this.duracionMinutos = duracionMinutos;
	}

	/**
     * Descripción detallada del DVD, incluyendo ID, título, duración y estado (PRESTADO, DISPONIBLE).
     * @return la descripción del DVD.
     */
    @Override
	public String descripcion() {
		return "\nEl DVD con el ID: " + getId() + "\nTítulo: " + getTitulo() + "\nduración de minutos: " + duracionMinutos + "\nEstado: " + getEstado();
	}
}
