package TierraMediaFinal;

import java.util.LinkedList;

public class PromocionAxB extends Promocion{ 
	
	public PromocionAxB(LinkedList <Atraccion> listaAtracciones, Tipo tipo) { 
		super(tipo, listaAtracciones); 
	}  
	 
	@Override
	public double getCosto() { 
		double costoTotal = 0;  
		for (int i = 0; i < this.getListaAtracciones().size()-1; i++) {
			 
			costoTotal+= this.getListaAtracciones().get(i).getCosto();
		}
	 
		return costoTotal;
	}

	@Override
	public String toString() {
		return "Promocion AxB: Pack " + super.getTipo() +  
				", Costo: " + this.getCosto() + ", Duración: " + super.getDuracion() + ", Atracciones que incluye: " + this.getNombresAtracciones();
	} 
	
}
