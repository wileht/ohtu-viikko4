package ohtu.verkkokauppa;

public interface PankkiIF {

	boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);

}