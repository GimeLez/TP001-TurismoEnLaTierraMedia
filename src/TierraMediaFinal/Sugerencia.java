package TierraMediaFinal;

import java.util.Collections;
import java.util.LinkedList;

public class Sugerencia {

	private LinkedList<Atraccion> listaAtraccionesPreferencia;
	private LinkedList<Promocion> listaPromocionesPreferencia;
	private LinkedList<Atraccion> listaAtracciones;
	private LinkedList<Promocion> listaPromociones;
	private LinkedList<Atraccion> listaAtraccionesEntrada;
	private LinkedList<Promocion> listaPromocionesEntrada;
	private Visitante visitante;

	public Sugerencia(Visitante unVisitante, LinkedList<Atraccion> listaAtracciones,
			LinkedList<Promocion> listaPromociones) {
		this.listaAtraccionesEntrada = listaAtracciones;
		this.listaPromocionesEntrada = listaPromociones;
		this.visitante = unVisitante;
		this.listaAtraccionesPreferencia = new LinkedList<Atraccion>();
		this.listaAtracciones = new LinkedList<Atraccion>();
		this.listaPromociones = new LinkedList<Promocion>();
		this.listaPromocionesPreferencia = new LinkedList<Promocion>();
	}

	public LinkedList<Atraccion> getListaAtraccionesPreferencia() {
		return listaAtraccionesPreferencia;
	}

	public LinkedList<Promocion> getListaPromocionesPreferencia() {
		return listaPromocionesPreferencia;
	}

	public LinkedList<Atraccion> getListaAtracciones() {
		return listaAtracciones;
	}

	public LinkedList<Promocion> getListaPromociones() {
		return listaPromociones;
	}

	public LinkedList<Atraccion> getListaAtraccionesEntrada() {
		return listaAtraccionesEntrada;
	}

	public LinkedList<Promocion> getListaPromocionesEntrada() {
		return listaPromocionesEntrada;
	}

	public Visitante getVisitante() {
		return visitante;
	}
 
	
	public void ordenarListaPromociones() {

		for (Promocion p : this.listaPromocionesEntrada) {

		
 
				
				if (p.getTipo() == this.visitante.getPreferencia()) {
					this.listaPromocionesPreferencia.add(p);
				} else if (p.getTipo() != this.visitante.getPreferencia()) {
					listaPromociones.add(p);
				}
		

		}
 
		
		Collections.sort(listaPromocionesPreferencia, new ComparadorPromociones());
		Collections.sort(listaPromociones, new ComparadorPromociones());

	}
 
	
	public void ordenarListaAtracciones() {

		for (Atraccion a : this.listaAtraccionesEntrada) {

		

				if (a.getTipo() == this.visitante.getPreferencia()) {
					listaAtraccionesPreferencia.add(a);
				} else if (a.getTipo() != this.visitante.getPreferencia()) {
					listaAtracciones.add(a);
				}
	
		}

		Collections.sort(listaAtraccionesPreferencia, new ComparadorPorCostoYTiempo());
		Collections.sort(listaAtracciones, new ComparadorPorCostoYTiempo());

	}

	public void imprimirItinerarioDetallado(Visitante unVisitante) {
		LinkedList<Atraccion> listaAux = unVisitante.getItinerario();
		double costoTotal = 0;
		double tiempoTotal = 0;

		System.out.println("\n****Resumen de compra del visitante " + unVisitante.getNombre() + "****");
		for (Atraccion a : listaAux) {
			System.out.println(a);
			costoTotal += a.getCosto();
			tiempoTotal += a.getDuracion();
		}

		System.out.println("Costo final de la compra : " + costoTotal);
		System.out.println("Tiempo requerido : " + tiempoTotal); 
		System.out.println("**********************************");

	}

}
