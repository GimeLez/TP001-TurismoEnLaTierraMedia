package TierraMediaFinal;

import java.util.Objects;

public class Atraccion {

	private String nombre;
	private double costo;
	private double duracion;
	private Tipo tipo;
	private int cupo;

	public Atraccion(String nombre, double costo, double duracion, Tipo tipo, int cupo) {
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.tipo = tipo;
		this.cupo = cupo;

	} 
	
	public Atraccion(Tipo tipo) { 
		this.tipo = tipo;
	}
 
	public String getNombre() {
		return nombre;
	} 
	
	public double getCosto() {
		return costo;
	}

	public double getDuracion() {
		return duracion;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public int getCupo() {
		return cupo;
	} 

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public void setCupo(int cupo) {
		this.cupo = cupo;
	}


	public void actualizarCupo() {
		if (this.cupo > 0) {
			cupo--;
		}
	}


	@Override
	public int hashCode() {
		return Objects.hash(costo, cupo, duracion, nombre, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo) && cupo == other.cupo
				&& Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& Objects.equals(nombre, other.nombre) && tipo == other.tipo;
	} 
	
	@Override
	public String toString() {
		return "Atraccion: " + nombre + ", Costo: " + costo + ", Duracion: " + duracion + ", Tipo: " + tipo
				+ ", Cupo: " + cupo;
	}
 
}
