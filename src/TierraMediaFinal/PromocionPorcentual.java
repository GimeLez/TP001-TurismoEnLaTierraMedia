package TierraMediaFinal;

import java.util.LinkedList;

public class PromocionPorcentual extends Promocion{ 
	
	private double porcentajeDescuento;
	
	public PromocionPorcentual(double descuento, LinkedList<Atraccion> listaAtracciones, Tipo tipo) { 
		super(tipo, listaAtracciones);
		this.porcentajeDescuento = descuento;  
	} 
	   
	@Override
	public double getCosto() {   
		double costo = 0;
		for (Atraccion cadaAtraccion : this.getListaAtracciones()) {
			costo +=cadaAtraccion.getCosto();
		} 
	    costo = costo - (costo*(porcentajeDescuento/100));   
	    return costo; 
	}  
	
	public double getDescuento() { 
		return porcentajeDescuento;
	}
	
	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	public String toString() {
		return "Promocion Porcentual: Pack " + super.getTipo() + ", Costo: " + this.getCosto() + 
				", Descuento: "+ porcentajeDescuento + "%" + ", Duración: "  
				+ super.getDuracion() + ", Atracciones que incluye: " + this.getNombresAtracciones();
	}

}
