package Test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;

import TierraMediaFinal.*;

public class TestTierraMedia {

	Visitante unVisitante = new Visitante("Eowyn", 150, 80, Tipo.AVENTURA);
	Atraccion unaAtraccion = new Atraccion("Moria", 10, 2, Tipo.AVENTURA, 6);

	@Test
	public void queContruyeObjetosNoNulosTest() {

		assertNotNull(unVisitante);
		assertNotNull(unaAtraccion);
	}

	@Test
	public void queLevantaCorrectamenteArchivosTest() throws Throwable {

		ArchivoLyE unArchivoL = new ArchivoLyE("visitantes.txt", "atracciones.txt", "promociones.txt");

		System.out.println(unArchivoL.getListaVisitantes().toString());
		System.out.println(unArchivoL.getListaAtracciones().toString());
		System.out.println("COSTO DE PROMOCION PORCENTUAL: " + unArchivoL.getListaPromociones().get(0).getCosto());
		System.out.println("COSTO DE PROMOCION ABSOLUTA: " + unArchivoL.getListaPromociones().get(1).getCosto());
		System.out.println("COSTO DE PROMOCION AXB: " + unArchivoL.getListaPromociones().get(2).getCosto());

	}

	@Test
	public void queGeneraSugerenciasOrdenadas() throws Throwable {

		ArchivoLyE unArchivo = new ArchivoLyE("visitantes.txt", "atracciones.txt", "promociones.txt");
		Sugerencia unaSugerencia = new Sugerencia(unVisitante, unArchivo.getListaAtracciones(),
				unArchivo.getListaPromociones());

		System.out.println(unaSugerencia.getListaAtraccionesEntrada());
		unaSugerencia.ordenarListaAtracciones();
		System.out.println(unaSugerencia.getListaAtraccionesPreferencia());
		System.out.println(unaSugerencia.getListaAtracciones());

		System.out.println(unaSugerencia.getListaPromocionesEntrada());
		unaSugerencia.ordenarListaPromociones();
		System.out.println(unaSugerencia.getListaPromocionesPreferencia());
		System.out.println(unaSugerencia.getListaPromociones());

	}

	@Test
	public void queFuncionaComparador() throws Throwable {

		ArchivoLyE unArchivo = new ArchivoLyE("visitantes.txt", "atracciones.txt", "promociones.txt");

		LinkedList<Atraccion> listaAux = new LinkedList<Atraccion>();
		listaAux = unArchivo.getListaAtracciones();
		Collections.sort(listaAux, new ComparadorPorCostoYTiempo());

		System.out.println(listaAux.toString());
	}

	@Test // Probar con archivos erroneos o inexistentes
	public void queTrataExcepcionesArchivoEnLecturaDeArchivo() throws Throwable {
		ArchivoLyE unArchivo = new ArchivoLyE("visitantes.txt", "atracciones.txt", "promociones.txt");

		System.out.println(unArchivo.getListaVisitantes().toString());
	}

	@Test
	public void queGeneraResumenDeCompraCorrectamente() {

		Atraccion a1 = new Atraccion("a1", 2, 1, Tipo.AVENTURA, 6);
		Atraccion a2 = new Atraccion("a2", 3, 2, Tipo.AVENTURA, 5);
		Atraccion a3 = new Atraccion("a3", 5, 5, Tipo.AVENTURA, 10);

		unVisitante.setItinerario(a1);
		unVisitante.setItinerario(a2);
		unVisitante.setItinerario(a3);

		Compra c = new Compra(unVisitante);
		assertEquals(10, c.getCostoTotal(), 0.001);
		assertEquals(8, c.getTiempoTotal(), 0.001);
	}

	@Test
	public void promocionPorcentual() {

		Atraccion a1 = new Atraccion("a1", 15, 1, Tipo.AVENTURA, 6);
		Atraccion a2 = new Atraccion("a2", 15, 2, Tipo.AVENTURA, 5);
		Atraccion a3 = new Atraccion("a3", 10, 5, Tipo.AVENTURA, 10);

		LinkedList<Atraccion> lista = new LinkedList<>();

		lista.add(a1);
		lista.add(a2);
		lista.add(a3);

		PromocionPorcentual p1 = new PromocionPorcentual(20, lista, Tipo.AVENTURA);

		assertNotNull(p1);

		assertEquals(32, p1.getCosto(), 0);

		Atraccion a4 = new Atraccion("a4", 20, 1, Tipo.AVENTURA, 6);

		p1.cambiarAtraccion(2, a4);

		// System.out.println(p1.getListaAtracciones());

		assertEquals(40, p1.getCosto(), 0);

		p1.setPorcentajeDescuento(35);

		assertEquals(35, p1.getDescuento(), 0);

		assertEquals(32.5, p1.getCosto(), 0);

		a4.setCupo(0);

		assertFalse(p1.getCupoHabilitado());

		p1.setPorcentajeDescuento(25);

		assertEquals(25, p1.getDescuento(), 0);

	}

	@Test
	public void promocionAbsoluta() {

		Atraccion a1 = new Atraccion("a1", 15, 1, Tipo.AVENTURA, 6);
		Atraccion a2 = new Atraccion("a2", 15, 2, Tipo.AVENTURA, 5);
		Atraccion a3 = new Atraccion("a3", 10, 5, Tipo.AVENTURA, 10);

		LinkedList<Atraccion> lista = new LinkedList<>();

		lista.add(a1);
		lista.add(a2);
		lista.add(a3);

		PromocionAbsoluta p1 = new PromocionAbsoluta(50, lista, Tipo.DEGUSTACION);

		assertEquals(50, p1.getCosto(), 0);

		Atraccion a4 = new Atraccion("a4", 20, 1, Tipo.AVENTURA, 6);

		p1.cambiarAtraccion(2, a4);

		assertEquals(50, p1.getCosto(), 0);

		a4.setCupo(0);

		assertFalse(p1.getCupoHabilitado()); 
		
		p1.setCosto(42); 
		
		assertEquals(42, p1.getCosto(), 0);

	}

	@Test
	public void promocionAxB() {

		Atraccion a1 = new Atraccion("a1", 15, 1, Tipo.AVENTURA, 6);
		Atraccion a2 = new Atraccion("a2", 15, 2, Tipo.AVENTURA, 5);
		Atraccion a3 = new Atraccion("a3", 10, 5, Tipo.AVENTURA, 10);

		LinkedList<Atraccion> lista = new LinkedList<>();

		lista.add(a1);
		lista.add(a2);
		lista.add(a3);

		PromocionAxB p1 = new PromocionAxB(lista, Tipo.DEGUSTACION);

		assertEquals(30, p1.getCosto(), 0);

		Atraccion a4 = new Atraccion("a4", 20, 1, Tipo.AVENTURA, 6);

		p1.cambiarAtraccion(1, a4);

		assertEquals(35, p1.getCosto(), 0); 
		
		a4.setCupo(0);

		assertFalse(p1.getCupoHabilitado()); 

	}

}
