package TierraMediaFinal;

import java.util.Scanner;

public class AppTierraMedia {

	public static void main(String[] args) throws Throwable {

		ArchivoLyE archivos = new ArchivoLyE("visitantes.txt", "atracciones.txt", "promociones.txt");
		
		Sugerencia sugerencia = null;

		Scanner sc = new Scanner(System.in);

		System.out.println("***********TIERRA MEDIA TURISMO***************\n\n");

		for (Visitante v : archivos.getListaVisitantes()) {

			sugerencia = new Sugerencia(v, archivos.getListaAtracciones(), archivos.getListaPromociones());
			boolean condicionVisitante = v.getMonedas() > 0 && v.getTiempo() > 0;

			char opcion = '1';

			sugerencia.ordenarListaAtracciones();
			sugerencia.ordenarListaPromociones();

			for (Promocion p : sugerencia.getListaPromocionesPreferencia()) {
				
				boolean condicionPromocion = p.getCosto() <= v.getMonedas() && p.getDuracion() <= v.getTiempo()
						&& p.getCupoHabilitado();

				
				if (!v.getItinerario().contains(p) && condicionVisitante && condicionPromocion) {

					System.out.println("\nSugerencia para " + v.getNombre() + "-->" + " Monedas: " + v.getMonedas()
							+ " - Tiempo: " + v.getTiempo() + "\n");
					
					System.out.println(p.toString() + "\n");
					System.out.println("Acepta sugerencia ??? (1-S / 0-N)");

					opcion = sc.next().charAt(0);

					while(opcion != '1' && opcion != '0') {
						System.out.println("Opcion invalida, intente de nuevo :");
						opcion = sc.next().charAt(0);
					}
					
					if (opcion == '1') {
						v.aceptarSugerencia(p);
						for (Atraccion a : p.getListaAtracciones())
							a.actualizarCupo();
					}
					
				}
				
				
			}

			for (Atraccion a : sugerencia.getListaAtraccionesPreferencia()) {

				boolean atraccionNoComprada = true;

				for (Promocion p : sugerencia.getListaPromocionesPreferencia()) {
					for (Atraccion a2 : v.getItinerario()) {
						if (a2.equals(p) && atraccionNoComprada) {
							for (Atraccion a3 : p.getListaAtracciones()) {
								if (a3.equals(a)) {
									atraccionNoComprada = false;
								}
							}
						}

					}

				}

				if (!atraccionNoComprada) {
					continue;
				}
				boolean condicionAtraccion = a.getCosto() <= v.getMonedas() && a.getDuracion() <= v.getTiempo()
						&& a.getCupo() > 0;

				if (!v.getItinerario().contains(a) && condicionVisitante && condicionAtraccion) {

					System.out.println("\nSugerencia para " + v.getNombre() + "-->" + " Monedas: " + v.getMonedas()
							+ " - Tiempo: " + v.getTiempo() + "\n"); 
					System.out.println(a.toString() + "\n");
					System.out.println("Acepta sugerencia ??? (1-S / 0-N)");

					opcion = sc.next().charAt(0);
					
					while(opcion != '1' && opcion != '0') {
						System.out.println("Opcion invalida, intente de nuevo :");
						opcion = sc.next().charAt(0);
					}
					

					if (opcion == '1') {
						v.aceptarSugerencia(a);
						a.actualizarCupo();
					}
				}
			}

			for (Promocion p : sugerencia.getListaPromociones()) {

				boolean condicionPromocion = p.getCosto() <= v.getMonedas() && p.getDuracion() <= v.getTiempo()
						&& p.getCupoHabilitado();

				if (!v.getItinerario().contains(p) && condicionVisitante && condicionPromocion) {

					System.out.println("\nSugerencia para " + v.getNombre() + "-->" + " Monedas: " + v.getMonedas()
							+ " - Tiempo: " + v.getTiempo() + "\n");
					System.out.println(p.toString() + "\n");
					System.out.println("Acepta sugerencia ??? (1-S / 0-N)");

					opcion = sc.next().charAt(0);

					
					while(opcion != '1' && opcion != '0') {
						System.out.println("Opcion invalida, intente de nuevo :");
						opcion = sc.next().charAt(0);
					}
					
					if (opcion == '1') {
						v.aceptarSugerencia(p);
						for (Atraccion a : p.getListaAtracciones())
							a.actualizarCupo();
					}
				}

			}

			for (Atraccion a : sugerencia.getListaAtracciones()) {

				boolean atraccionNoComprada = true;

				for (Promocion p : sugerencia.getListaPromociones()) {
					for (Atraccion a2 : v.getItinerario()) {
						if (a2.equals(p) && atraccionNoComprada) {
							for (Atraccion a3 : p.getListaAtracciones()) {
								if (a3.equals(a)) {
									atraccionNoComprada = false;
								}
							}
						}

					}

				}

				if (!atraccionNoComprada) {
					continue;
				}

				boolean condicionAtraccion = a.getCosto() <= v.getMonedas() && a.getDuracion() <= v.getTiempo()
						&& a.getCupo() > 0;

				if (!v.getItinerario().contains(a) && condicionVisitante && condicionAtraccion) {

					System.out.println("\nSugerencia para " + v.getNombre() + "-->" + " Monedas: " + v.getMonedas()
							+ " - Tiempo: " + v.getTiempo() + "\n"); 
					System.out.println(a.toString() + "\n");
					System.out.println("Acepta sugerencia ??? (1-S / 0-N)");

					opcion = sc.next().charAt(0);

					while(opcion != '1' && opcion != '0') {
						System.out.println("Opcion invalida, intente de nuevo :");
						opcion = sc.next().charAt(0);
					}
					
					if (opcion == '1') {
						v.aceptarSugerencia(a);
						a.actualizarCupo();
					}
				}
			}

			sugerencia.imprimirItinerarioDetallado(v);
			Compra unaCompra = new Compra(v);
			archivos.escribirArchivoDeSalida(unaCompra);

		}

		sc.close();

		System.out.println("*********El sistema Tierra Media Turismo ha finalizado**********");
	}

}
