package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class Viitegeneraattori implements ViitegeneraattoriIF {

//    private static ViitegeneraattoriIF instanssi;
//
//    public static ViitegeneraattoriIF getInstance() {
//        if (instanssi == null) {
//            instanssi = new Viitegeneraattori();
//        }
//
//        return instanssi;
//    }
    
    private int seuraava;
    
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    /* (non-Javadoc)
	 * @see ohtu.verkkokauppa.ViitegeneraattoriIF#uusi()
	 */
    @Override
	public int uusi(){
        return seuraava++;
    }
}
