package model;

public class Usuario {
	private String id;
	private String nombre;

	/**
     * Constructor que inicializa un usuario con un ID y un nombre.
     * @param id Identificador único del usuario.
     * @param nombre Nombre del usuario.
     */
	public Usuario(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	/**
     * Obtiene el ID del usuario.
     * @return El ID del usuario.
     */
	public String getId() {
		return id;
	}

	/**
     * Obtiene el nombre del usuario.
     * @return El nombre del usuario.
     */
	public String getNombre() {
		return nombre;
	}
}