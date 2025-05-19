package appMain;

//Importamos las clases y paquetes que necesitamos
import model.BibliotecaManager;
import model.RecursoBiblioteca;
import model.DVD;
import model.Libro;
import model.Revista;
import model.Usuario;
import enums.EstadoRecurso;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

//Clase principal
public class Main {
	/**
	 * Método principal.Inicializa el sistema de gestión de la biblioteca, muestra
	 * un menú que permite al usuario realizar operaciones como listar, prestar o devolver recursos.
	 */
	public static void main(String[] args) {

		BibliotecaManager manager = new BibliotecaManager();// instancia un objeto para trabajar con la
															// BibliotecaManager
		Scanner teclado = new Scanner(System.in);// Crea un objeto teclado para leer la entrada del usuario desde la
													// consola

		agregarDatos(manager); // lamar al metodo agregarDatos.

		// Bucle infinito hasta que el ususuario le de a salir.
		while (true) {
			System.out.println("\n=== Biblioteca Menu ===");
			System.out.println("1. Listar recurso");
			System.out.println("2. Prestar recurso");
			System.out.println("3. Devolver recurso");
			System.out.println("4. Salir");
			System.out.print("\nSeleccione una opción: ");

			int opcion;
			// excepcion try catch por si el usuario inserta una letra en vez de numero
			try {
				opcion = Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException nfe) {
				System.out.println("Por favor, ingrese un número válido del 1 al 4.");
				continue;
			}

			switch (opcion) {// evalua la opcion selecionada por el usuario.
			case 1:
				manager.listarRecursos();// llama al metodo listarRecursos
				break;

			case 2:
				mostrarRecursosDisponibles(manager, "prestar");// muestra los recursos disponibles para prestar.
				System.out.print("Ingrese ID del recurso: ");
				String idRecurso = teclado.nextLine();
				System.out.print("Ingrese ID del usuario: ");
				String idUsuario = teclado.nextLine();
				manager.prestarRecurso(idRecurso, idUsuario);// llama al metodo prestarRecurso.
				break;

			case 3:
				mostrarRecursosDisponibles(manager, "devolver");// muestra los recursos disponibles para devolver.
				System.out.print("Ingrese ID del recurso: ");
				idRecurso = teclado.nextLine();
				manager.devolverRecurso(idRecurso);// llama al metodo idRecurso.
				break;
			// cierra el teclado mostrando el mesaje del println
			case 4:
				System.out.println("Gracias por usar la biblioteca.");
				teclado.close();
				return;

			default:// si la opcion no vale muestra un mensaje de error.
				System.out.println("Por favor, ingrese un número válido del 1 al 4.");
			}
		}
	}

	/**
	 * Método para agregar datos de usuarios y recursos a la biblioteca. Agrega
	 * usuarios, libros, revistas y DVDs.
	 * @param manager. Objeto BibliotecaManager es la clase donde se almacenan los datos.
	 */
	private static void agregarDatos(BibliotecaManager manager) {

		// Agrega usuarios al sistema
		manager.agregarUsuario(new Usuario("U01", "Marcos Sandin"));
		manager.agregarUsuario(new Usuario("U02", "Laura Sandin"));
		manager.agregarUsuario(new Usuario("U03", "Irina Sandin"));
		manager.agregarUsuario(new Usuario("U04", "Eray Sandin"));
		manager.agregarUsuario(new Usuario("U05", "Azucena Sandin"));

		// Agrega libro sistema
		manager.agregarRecurso(new Libro("L01", "Cien Años de Soledad", "Gabriel García Márquez"));
		manager.agregarRecurso(new Libro("L02", "El señor de los anillos ", "J. R. R. Tolkien"));
		manager.agregarRecurso(new Libro("L03", "En busca del tiempo perdido", "Marcel Proust"));
		manager.agregarRecurso(new Libro("L04", "Don Quijote de la Mancha", "Miguel de Cervantes"));
		manager.agregarRecurso(new Libro("L05", "El retrato de Dorian Gray", "Oscar Wilde"));

		// agrega revista al sistema
		manager.agregarRecurso(new Revista("R01", "Moto gp", 123));
		manager.agregarRecurso(new Revista("R02", "Todo mecanica", 456));

		// agrega dvd al sistema
		manager.agregarRecurso(new DVD("D01", "Cónclave", 175));
		manager.agregarRecurso(new DVD("D02", "Frozen II", 136));
		manager.agregarRecurso(new DVD("D03", "La isla misteriosa", 148));
	}

	/**
	 * Método para mostrar recursos disponibles o prestados. Organiza en orden y
	 * muestra los recursos del ID (libros, revistas, DVDs), indicando su estado y,
	 * si están prestados, el nombre del usuario que los tiene.
	 * @param manager. Objeto BibliotecaManager contiene los datos de recursos y préstamos.
	 * @param modo.    Modo de visualización: "prestar" para recursos disponibles,
	 * "devolver" para recursos prestados.
	 */
	private static void mostrarRecursosDisponibles(BibliotecaManager manager, String modo) {
		System.out.println("\n\t=== Recursos "
				+ (modo.equals("prestar") ? "Disponibles para Prestar" : "Prestados para Devolver") + " ===");
		System.out.println("\nID\tTítulo\t\t\t\tEstado");
		System.out.println("--------------------------------------------------");

		// Para crear listas separadas para libros, revistas y DVDs
		List<RecursoBiblioteca> libros = new ArrayList<>();
		List<RecursoBiblioteca> revistas = new ArrayList<>();
		List<RecursoBiblioteca> dvd = new ArrayList<>();

		// Clasifica los recursos según el modo y tipo
		for (RecursoBiblioteca recurso : manager.getRecursos().values()) {
			if (modo.equals("prestar")) {
				if (recurso instanceof Libro) {// Al lado izquierdo tenemos la instancia y el lado derecho el,
					// nombre de la clase y como resultado el operador instanceOf devuelve un
					// resultado booleano
					libros.add(recurso);
				} else if (recurso instanceof Revista) {
					revistas.add(recurso);
				} else if (recurso instanceof DVD) {
					dvd.add(recurso);
				}
			} else if (modo.equals("devolver") && recurso.getEstado() == EstadoRecurso.PRESTADO) {
				if (recurso instanceof Libro) {
					libros.add(recurso);
				} else if (recurso instanceof Revista) {
					revistas.add(recurso);
				} else if (recurso instanceof DVD) {
					dvd.add(recurso);
				}
			}
		}

		// Ordena las listas por ID de libros revistas y dvd.
		libros.sort(Comparator.comparing(RecursoBiblioteca::getId));
		revistas.sort(Comparator.comparing(RecursoBiblioteca::getId));
		dvd.sort(Comparator.comparing(RecursoBiblioteca::getId));

		// muestar todos los libros que hay.
		System.out.println("\nLibros:");
		for (RecursoBiblioteca recurso : libros) {
			String estado = recurso.getEstado().toString();
			if (recurso.getEstado() == EstadoRecurso.PRESTADO) {
				String usuarioId = manager.getPrestamos().get(recurso.getId());
				if (usuarioId != null) {
					estado += " a " + manager.getUsuarios().get(usuarioId).getNombre() + ".";
				}
			}
			String titulo = recurso.getTitulo();
			String tituloRellenado;
			if (titulo.length() < 30) {
				tituloRellenado = titulo + " ".repeat(30 - titulo.length());
			} else {
				tituloRellenado = titulo;
			}
			System.out.println(recurso.getId() + "\t" + tituloRellenado + "\t" + estado);
		}

		// muestra todos las revistas que hay
		System.out.println("\nrevistas:");
		for (RecursoBiblioteca recurso : revistas) {
			String estado = recurso.getEstado().toString();
			if (recurso.getEstado() == EstadoRecurso.PRESTADO) {
				String usuarioId = manager.getPrestamos().get(recurso.getId());
				if (usuarioId != null) {
					estado += " a " + manager.getUsuarios().get(usuarioId).getNombre() + ".";
				}
			}
			String titulo = recurso.getTitulo();
			String tituloRellenado;
			if (titulo.length() < 30) {
				tituloRellenado = titulo + " ".repeat(30 - titulo.length());
			} else {
				tituloRellenado = titulo;
			}
			System.out.println(recurso.getId() + "\t" + tituloRellenado + "\t" + estado);
		}

		// muestra todos los dvd que hay
		System.out.println("\nDVD:");
		for (RecursoBiblioteca recurso : dvd) {
			String estado = recurso.getEstado().toString();
			if (recurso.getEstado() == EstadoRecurso.PRESTADO) {
				String usuarioId = manager.getPrestamos().get(recurso.getId());
				if (usuarioId != null) {
					estado += " a " + manager.getUsuarios().get(usuarioId).getNombre() + ".";
				}
			}
			String titulo = recurso.getTitulo();
			String tituloRellenado;
			if (titulo.length() < 30) {
				tituloRellenado = titulo + " ".repeat(30 - titulo.length());
			} else {
				tituloRellenado = titulo;
			}
			System.out.println(recurso.getId() + "\t" + tituloRellenado + "\t" + estado);
		}

		System.out.println("--------------------------------------------------");
	}
}                                  