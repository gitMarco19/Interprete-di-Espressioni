import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe {@code Istruzione} è una classe astratta.<br>
 * Rappresenta qualsiasi <i>istruzione</i> di tipo 
 * <i>GET</i> o <i>SET</i>.
 * 
 * @author Marco Scanu
 */
public abstract class Istruzione {
	
	/**
	 * Questo {@code ArrayList<Token>} indica un'istruzione.
	 */
	private ArrayList<Token> anInstruction;
	
	/**
	 * Inizializza una nuova {@code Istruzione} priva di <i>token</i>.
	 */
	public Istruzione() {
		this.anInstruction = new ArrayList<Token>(0);
	}
	
	/**
	 * Questo metodo restituisce il flusso di <i>token</i> 
	 * che rappresenta l'istruzione.
	 * 
	 * @return l'{@code ArrayList<Token>}.
	 */
	public ArrayList<Token> getAnInstruction() {
		ArrayList<Token> istr = new ArrayList<Token>(0);
		istr.addAll(this.anInstruction);
		return istr;
	}

	/**
	 * Questo metodo aggiunge il {@code Token}, passato come parametro,
	 * all'istruzione.
	 * 
	 * @param aToken
	 *            {@code Token} da aggiungere all'istruzione.
	 */
	public void aggiungiToken(Token aToken) {
		this.anInstruction.add(aToken);
	}
	
	/**
	 * Questo metodo controlla se l'istruzione in questione è valida, ovvero se i
	 * token che la identificano sono corretti sintatticamente.
	 * 
	 * @param variabili
	 *            {@code HashMap<String, Long>} che tiene traccia delle
	 *            <i>variabili inizializzate</i> e dei rispettivi valori.
	 */
	public abstract void isValida(HashMap<String, Long> variabili);
	
	/**
	 * Questo metodo si occupa di valutare l'espressione contenuta all'interno
	 * dell'istruzione in questione.
	 * 
	 * @param variabili
	 *            {@code HashMap<String, Long>} che tiene traccia delle <i>variabili
	 *            inizializzate</i> e dei rispettivi valori.
	 */
	public abstract void valuta(HashMap<String, Long> variabili);
	
	/**
	 * Questo metodo restituisce una {@code String} contenente il risultato
	 * dell'espressione contenuta nell'istruzione in questione.
	 * 
	 * @return risultato.
	 */
	public abstract String getRisultatoToString();
}
