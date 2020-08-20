import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe {@code ListaIstruzioni} rappresenta un insieme di istruzioni che
 * devono essere valutate dall'<i>interprete</i>.
 * 
 * @author Marco Scanu
 */
public class ListaIstruzioni {
	
	/**
	 * {@code ArrayList<Espressione>} che indica un insieme di istruzioni.
	 */
	private ArrayList<Istruzione> istruzioni;
	
	/**
	 * {@code HashMap<String, Long>} che tiene traccia delle <i>variabili
	 * inizializzate</i> e dei rispettivi valori.
	 */
	private HashMap<String, Long> variabili;
	
	/**
	 * Inizializza una nuova {@code ListaIstruzioni} non contenente istruzioni, cioè
	 * vuota.
	 */
	public ListaIstruzioni() {
		this.istruzioni = new ArrayList<Istruzione>(0);
		this.variabili = new HashMap<String, Long>(0);
	}
	
	/**
	 * Questo metodo aggiunge un'<i>istruzione</i> vuota alla
	 * {@code ListaIstruzioni} se il token passato come parametro corrisponde a
	 * <i>GET</i> oppure a <i>SET</i>.
	 * 
	 * @param aToken
	 *            <i>token</i> per capire il tipo di istruzione da creare.
	 */
	public void creaIstruzione(Token aToken) {
		if (aToken.equals(new Token(Token.paroleChiave[0])) 
				|| aToken.equals(new Token("")))
			this.istruzioni.add(new DichiarazioneEspressione());
		else if (aToken.equals(new Token(Token.paroleChiave[1])))
			this.istruzioni.add(new DefinizioneVariabile());
	}
	
	/**
	 * Questo metodo restituisce {@code HashMap<String, Long>} che tiene traccia
	 * delle <i>variabili inizializzate</i> e dei rispettivi valori.
	 * 
	 * @return {@code HashMap<String, Long>} delle variabili.
	 */
	public HashMap<String, Long> getVariabili() {
		return this.variabili;
	}

	/**
	 * Questo metodo permette di aggiungere un {@code Token}, passato come
	 * parametro, ad un'istruzione indicata con l'indice, anche esso passato come
	 * parametro.
	 * 
	 * @param index
	 *            indice che specifica l'<i>espressione</i>.
	 * @param aToken
	 *            {@code Token} da aggiungere all'<i>espressione</i>.
	 */
	public void componiIstruzione(int index, Token aToken) {
		this.istruzioni.get(index).aggiungiToken(aToken);
	}
	
	/**
	 * Questo metodo restituisce l'<i>istruzione</i> contenuta nell'indice {@code int}
	 * passato come parametro.
	 * 
	 * @param index
	 *            posizione dell'<i>istruzione</i>
	 * 
	 * @return l'<i>istruzione</i>
	 */
	public Istruzione getIstruzione(int index) {
		return this.istruzioni.get(index);
	}
	
	/**
	 * Questo metodo calcola il numero delle istruzioni contenuto nella
	 * {@code ListaIstruzioni}.
	 * 
	 * @return numero {@code int} delle istruzioni.
	 */
	public int numeroIstruzioni() {
		return this.istruzioni.size();
	}
	
	
}
