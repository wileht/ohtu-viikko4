package ohtu.verkkokauppa;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class KauppaTest {

	private Kauppa k;
	private Pankki pankki;
	private Varasto varasto;
	private Viitegeneraattori viite;
	
	@Before
	public void setUp() {
	    // luodaan ensin mock-oliot
	    pankki = mock(Pankki.class);
	    
	    viite = mock(Viitegeneraattori.class);
	    // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
	    when(viite.uusi()).thenReturn(42);

	    varasto = mock(Varasto.class);
	    // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
	    when(varasto.saldo(1)).thenReturn(10); 
	    when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

	    // sitten testattava kauppa 
	    k = new Kauppa(varasto, pankki, viite); 
	}
	
	@Test
	public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
	    // tehd‰‰n ostokset
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
	    k.tilimaksu("pekka", "12345");

	    // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
	    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));   
	    // toistaiseksi ei v‰litetty kutsussa k‰ytetyist‰ parametreista
	}

	@Test
	public void kaksiEriTuotetta() {
	    when(varasto.saldo(2)).thenReturn(12); 
	    when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "pahvi", 3));
	    
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);
	    k.lisaaKoriin(2);
	    k.tilimaksu("pekka", "12345");
	    
	    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(8));
	}
	
	@Test
	public void kaksiSamaaTuotetta() {
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);
	    k.lisaaKoriin(1);
	    k.tilimaksu("pekka", "12345");
	    
	    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));
	}
	
	@Test
	public void loppunutTuote() {
	    when(varasto.saldo(2)).thenReturn(0); 
	    when(varasto.haeTuote(2)).thenReturn(new Tuote(1, "pahvi", 3));
	    
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);
	    k.lisaaKoriin(2);
	    k.tilimaksu("pekka", "12345");
	    
	    verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
	}
	
	@Test
	public void aloitaAsiointiNollaa() {
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);
	    k.tilimaksu("pekka", "12345");
	    
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);
	    k.tilimaksu("pekka", "12345");
	    
	    verify(pankki, times(2)).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
	}
	
	@Test
	public void uusiViiteJokaiselleMaksulle() {
	    k.aloitaAsiointi();
	    k.lisaaKoriin(1);
	    k.tilimaksu("pekka", "12345");
	    
	    k.lisaaKoriin(1);
	    k.tilimaksu("pekka", "12345");
	    
	    verify(viite, times(2)).uusi();
	}
	
	@Test
	public void koristaPoisto() {
		k.aloitaAsiointi();
	    k.lisaaKoriin(1);
	    k.poistaKorista(1);
	    
	    verify(varasto, times(2)).palautaVarastoon(varasto.haeTuote(1));
	}
}
