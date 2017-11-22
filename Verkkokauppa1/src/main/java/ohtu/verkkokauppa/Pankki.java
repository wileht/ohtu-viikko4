package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pankki implements PankkiIF {

//    private static PankkiIF instanssi;
//
//    public static PankkiIF getInstance() {
//        if (instanssi == null) {
//            instanssi = new Pankki();
//        }
//
//        return instanssi;
//    }
    private KirjanpitoIF kirjanpito;

    @Autowired
    public Pankki(KirjanpitoIF kirjanpito) {
        this.kirjanpito = kirjanpito;
    }

    /* (non-Javadoc)
	 * @see ohtu.verkkokauppa.PankkiIF#tilisiirto(java.lang.String, int, java.lang.String, java.lang.String, int)
	 */
    @Override
	public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
