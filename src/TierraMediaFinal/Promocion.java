package TierraMediaFinal;

import java.util.LinkedList;
import java.util.Objects;

public abstract class Promocion extends Atraccion {

	private LinkedList<Atraccion> listaAtracciones = new LinkedList<Atraccion>();
	private boolean cupoHabilitado = true;

	public Promocion(Tipo tipo, LinkedList<Atraccion> lista) {
		super(tipo);
		listaAtracciones = lista;
	} 

	@Override
	public double getDuracion() {

		double duracion = 0;

		for (Atraccion atraccion : this.getListaAtracciones()) {

			duracion += atraccion.getDuracion();
		}

		return duracion;
	}

	public LinkedList<Atraccion> getListaAtracciones() {
		return listaAtracciones;
	} 
	 
	public LinkedList<String> getNombresAtracciones(){ 
		LinkedList<String> atraccionesNombres = new LinkedList<String>();
		 
		for (Atraccion a : this.listaAtracciones) {
			atraccionesNombres.add(a.getNombre());
		}
		
		return atraccionesNombres;
		
	}
	public boolean getCupoHabilitado() {
		for (Atraccion atraccion : listaAtracciones) {
			if (atraccion.getCupo() == 0) {
				this.cupoHabilitado = false;
			}

		}

		return cupoHabilitado;
	}    
	
	public void cambiarAtraccion(int indice, Atraccion nueva) {
		listaAtracciones.remove(indice);
		listaAtracciones.add(indice, nueva); 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cupoHabilitado, listaAtracciones);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return cupoHabilitado == other.cupoHabilitado && Objects.equals(listaAtracciones, other.listaAtracciones);
	} 
 
	
	
}
