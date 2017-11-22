
package ohtu.verkkokauppa;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Kirjanpito implements KirjanpitoIF {
//    private static KirjanpitoIF instance;
//    
//    public static KirjanpitoIF getInstance() {
//        if ( instance==null) {
//            instance = new Kirjanpito();
//        }
//        
//        return instance;
//    }
    
    private ArrayList<String> tapahtumat;

    public Kirjanpito() {
        tapahtumat = new ArrayList<String>();
    }
    
    /* (non-Javadoc)
	 * @see ohtu.verkkokauppa.KirjanpitoIF#lisaaTapahtuma(java.lang.String)
	 */
    @Override
	public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    /* (non-Javadoc)
	 * @see ohtu.verkkokauppa.KirjanpitoIF#getTapahtumat()
	 */
    @Override
	public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }       
}
