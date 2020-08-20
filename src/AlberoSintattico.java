/**
 * La classe {@code AlberoSintattico} è un modo alternativo per rappresentare
 * un'<i>espressione</i> che però permette di svolgere i calcoli al suo interno.
 * 
 * @author Marco Scanu
 */
public class AlberoSintattico {
	
	/**
	 * Quest'istanza della classe {@code NodoSintattico} rappresenta la <i>radice</i>
	 * dell'<i>albero sintattico</i>.
	 */
	private NodoSintattico radice;
	
	/**
	 * Inizializza un nuovo {@code AlberoSintattico} contenente solamente la
	 * <i>radice</i> vuota.
	 */
	public AlberoSintattico() {
		this.setRadice(new NodoSintattico());
	}

	/**
	 * Questo metodo imposta la <i>radice</i> dell'<i>albero sintattico</i> uguale a
	 * quella passata come argomento.
	 * 
	 * @param aRoot
	 *            nuova radice dell'<i>albero sintattico</i>.
	 */
	public void setRadice(NodoSintattico aRoot) {
		this.radice = aRoot;
	}
	
	/**
	 * Questo metodo restituisce la <i>radice</i> dell'<i>albero sintattico</i>.
	 * 
	 * @return la radice dell'<i>albero sintattico</i>.
	 */
	public NodoSintattico getRadice() {
		return this.radice;
	}
}
