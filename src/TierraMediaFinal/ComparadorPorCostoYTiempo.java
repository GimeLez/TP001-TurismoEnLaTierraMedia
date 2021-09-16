package TierraMediaFinal;

import java.util.Comparator;

public class ComparadorPorCostoYTiempo implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion a1, Atraccion a2) {

		int comparacion = 0;

		if (a1.getCosto() > a2.getCosto()) {
			comparacion = -1;
		} else if (a1.getCosto() < a2.getCosto()) {
			comparacion = 1;
		} else { 
			if (a1.getDuracion() > a2.getDuracion()) {
				comparacion = -1;
			} else {
				comparacion = 1;
			}
		}
		return comparacion;
	}

}
