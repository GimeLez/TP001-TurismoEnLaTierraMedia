package TierraMediaFinal;

import java.util.Comparator;

public class ComparadorPromociones implements Comparator<Promocion> {

	@Override
	public int compare(Promocion p1, Promocion p2) {

		int comparacion = 0;

		if (p1.getCosto() > p2.getCosto()) {
			comparacion = -1;
		} else if (p1.getDuracion() < p2.getDuracion()) {
			comparacion = 1;
		} else {
			if (p1.getDuracion() > p2.getDuracion()) {
				comparacion = -1;
			} else {
				comparacion = 1;
			}

		}

		return comparacion;
	}
}
