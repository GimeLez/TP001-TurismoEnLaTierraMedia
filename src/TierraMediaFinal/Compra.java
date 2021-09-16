package TierraMediaFinal;

import java.util.LinkedList;

public class Compra {

	
	private Visitante unVisitante;
	private double costoTotal;
	private double tiempoTotal;

	public Compra(Visitante unVisitante) {
		this.unVisitante = unVisitante;
		this.costoTotal = this.calcularCostoTotal(unVisitante.getItinerario());
		this.tiempoTotal = this.calcularTiempoTotal(unVisitante.getItinerario());
	}

	
	private double calcularCostoTotal(LinkedList<Atraccion> unaAtraccion) {
		double costo = 0;
		for( Atraccion a : this.unVisitante.getItinerario()) {
			costo += a.getCosto();
		}
		return costo;
	}
	

	private double calcularTiempoTotal(LinkedList<Atraccion> unaAtraccion) {
		double tiempo = 0;
		for( Atraccion a : this.unVisitante.getItinerario()) {
			tiempo += a.getDuracion();
		}
		return tiempo;
	}
	
	@Override
	public String toString() {
		return unVisitante.toString()+"\n"+unVisitante.getItinerario().toString()+
				"\nCosto Final: "+this.costoTotal+"\nTiempo requerido: "+this.tiempoTotal;
	}
	
	public Visitante getUnVisitante() {
		return unVisitante;
	}


	public double getCostoTotal() {
		return costoTotal;
	}


	public double getTiempoTotal() {
		return tiempoTotal;
	}

	
}
