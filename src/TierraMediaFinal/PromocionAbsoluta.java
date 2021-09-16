package TierraMediaFinal;

import java.util.LinkedList;

public class PromocionAbsoluta extends Promocion {  
 
	public PromocionAbsoluta(double costo,LinkedList<Atraccion> listaAtracciones, Tipo tipo) { 
		super(tipo, listaAtracciones);
		super.setCosto(costo); 
	}  

	@Override
	public String toString() {
		return "Promocion Absoluta: Pack " + super.getTipo() + ", Costo: " + this.getCosto() + 
				", Duración: " + super.getDuracion() + ", Atracciones que incluye: " + this.getNombresAtracciones();
	}
	 
	
}
