package TierraMediaFinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class ArchivoLyE {

	private LinkedList<Visitante> listaVisitantes;
	private LinkedList<Atraccion> listaAtracciones;
	private LinkedList<Promocion> listaPromociones;

	public ArchivoLyE(String visitantes, String atracciones, String promociones) throws Throwable {

		this.listaVisitantes = generarListaVisitantes(visitantes);
		this.listaAtracciones = generarListaAtracciones(atracciones);
		this.listaPromociones = generarListaPromociones(promociones);
	}

	private LinkedList<Visitante> generarListaVisitantes(String visitantes) {

		LinkedList<Visitante> listaAux = new LinkedList<Visitante>();
		File visitanteFile = null;
		Scanner sc = null;

		try {

			visitanteFile = new File(visitantes);
			sc = new Scanner(visitanteFile);

		} catch (FileNotFoundException fnfe) {
			System.err.println("El archivo no existe o la ruta es incorrecta!!!");
		}

		while (sc.hasNext()) {
			try {
				String linea = sc.nextLine();
				String datos[] = linea.split(",");

				String nombre = datos[0];
				double monedas = Double.parseDouble(datos[1]);
				double tiempo = Double.parseDouble(datos[2]);
				Tipo tipo = Tipo.valueOf(datos[3]);

				Visitante unVisitante = new Visitante(nombre, monedas, tiempo, tipo);

				listaAux.add(unVisitante);
			} catch (NumberFormatException nfe) {
				System.err.println("Dato erroneo en el archivo " + visitantes);
			} catch (ArrayIndexOutOfBoundsException aiobe) {
				System.err.println("Dato faltante en el archivo " + visitantes);
			}
		}

		sc.close();

		return listaAux;
	}

	private LinkedList<Atraccion> generarListaAtracciones(String atracciones) {

		LinkedList<Atraccion> listaAux = new LinkedList<Atraccion>();
		File atraccionFile = null;
		Scanner sc = null;

		try {

			atraccionFile = new File(atracciones);
			sc = new Scanner(atraccionFile);

		} catch (FileNotFoundException fnfe) {
			System.err.println("El archivo no existe o la ruta es incorrecta!!!");
		}

		while (sc.hasNext()) {
			try {
				String linea = sc.nextLine();
				String datos[] = linea.split(",");

				String nombre = datos[0];
				double costo = Double.parseDouble(datos[1]);
				double duracion = Double.parseDouble(datos[2]);
				int cupo = Integer.parseInt(datos[3]);
				Tipo tipo = Tipo.valueOf(datos[4]);

				Atraccion unAtraccion = new Atraccion(nombre, costo, duracion, tipo, cupo);

				listaAux.add(unAtraccion);

			} catch (NumberFormatException nfe) {
				System.err.println("Dato erroneo en el archivo " + atracciones);
			} catch (ArrayIndexOutOfBoundsException aiobe) {
				System.err.println("Dato faltante en el archivo " + atracciones);
			}
		}

		sc.close();

		return listaAux;
	}

	private LinkedList<Promocion> generarListaPromociones(String promociones) {

		LinkedList<Promocion> listaAux = new LinkedList<Promocion>();
		File promocionFile = null;
		Scanner sc = null;

		try {

			promocionFile = new File(promociones);
			sc = new Scanner(promocionFile);

		} catch (FileNotFoundException fnfe) {
			System.err.println("El archivo no existe o la ruta es incorrecta!!!");
		}

		
		
		while (sc.hasNext()) {

			try {

				String linea = sc.nextLine();
				String datos[] = linea.split(",");
				LinkedList<Atraccion> lista = new LinkedList<Atraccion>();

				if (datos[0].equalsIgnoreCase("A")) {
					double descuento = Double.parseDouble(datos[1]);
					int i = 2;
					for (Atraccion atraccion : this.listaAtracciones) {
						if (atraccion.getNombre().equalsIgnoreCase(datos[i])) {
							lista.add(atraccion);
							i++;
							break;
						}

					}
					for (Atraccion atraccion : this.listaAtracciones) {
						if (atraccion.getNombre().equalsIgnoreCase(datos[i])) {
							lista.add(atraccion);
							break;
						}
					}
					Tipo tipo = Tipo.valueOf(datos[4]);

					PromocionPorcentual promocion = new PromocionPorcentual(descuento, lista, tipo);

					listaAux.add(promocion);

				}
				if (datos[0].equalsIgnoreCase("B")) {

					double costo = Double.parseDouble(datos[1]);
					int i = 2;
					for (Atraccion atraccion : this.listaAtracciones) {
						if (atraccion.getNombre().equalsIgnoreCase(datos[i])) {
							lista.add(atraccion);
							i++;
							break;
						}

					}
					for (Atraccion atraccion : this.listaAtracciones) {
						if (atraccion.getNombre().equalsIgnoreCase(datos[i])) {
							lista.add(atraccion);
							break;
						}
					}
					Tipo tipo = Tipo.valueOf(datos[4]);

					PromocionAbsoluta promocion = new PromocionAbsoluta(costo, lista, tipo);

					listaAux.add(promocion);

				} if (datos[0].equalsIgnoreCase("C")) {

					int i = 1;
					for (Atraccion atraccion : this.listaAtracciones) {
						if (atraccion.getNombre().equalsIgnoreCase(datos[i])) {
							lista.add(atraccion);
							i++;
							break;
						}

					}
					for (Atraccion atraccion : this.listaAtracciones) {
						if (atraccion.getNombre().equalsIgnoreCase(datos[i])) {
							lista.add(atraccion);
							i++;
							break;
						}
					}
					for (Atraccion atraccion : this.listaAtracciones) {
						if (atraccion.getNombre().equalsIgnoreCase(datos[i])) {
							lista.add(atraccion);
							break;
						}
					}

					Tipo tipo = Tipo.valueOf(datos[4]);

					PromocionAxB promocion = new PromocionAxB(lista, tipo);

					listaAux.add(promocion); 

				}

			} catch (NumberFormatException nfe) {
				System.err.println("Dato erroneo en el archivo " + promociones);
			} catch (ArrayIndexOutOfBoundsException aiobe) {
				System.err.println("Dato faltante en el archivo " + promociones);
			}
				
			
		}

		sc.close();

		return listaAux;
	}

	public void escribirArchivoDeSalida(Compra unaCompra) throws IOException {

		PrintWriter salida = new PrintWriter(new FileWriter(unaCompra.getUnVisitante().getNombre() + ".out"));
		salida.println(unaCompra.toString());
		salida.close();
	}

	public LinkedList<Atraccion> getListaAtracciones() {
		return listaAtracciones;
	}

	public LinkedList<Visitante> getListaVisitantes() {
		return listaVisitantes;
	}

	public LinkedList<Promocion> getListaPromociones() {
		return listaPromociones;
	}
}
