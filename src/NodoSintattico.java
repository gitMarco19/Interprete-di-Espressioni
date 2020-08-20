/**
 * La classe {@code NodoSintattico} rappresenta un singolo nodo dell'<i>albero
 * sintattico</i>.
 * 
 * @author Marco Scanu
 */
public class NodoSintattico {
	
	/**
	 * Questa istanza della classe {@code NodoSintattico} rappresenta il figlio
	 * sinistro del <i>nodo sintattico</i>.
	 */
	private NodoSintattico nodoSinistro;
	
	/**
	 * Questa istanza della classe {@code NodoSintattico} rappresenta il figlio
	 * destro del <i>nodo sintattico</i>.
	 */
	private NodoSintattico nodoDestro;
	
	/**
	 * Questa istanza della classe {@code Token} indica il contenuto del
	 * <i>nodo sintattico</i>.
	 */
	private Token contenuto;
	
	/**
	 * Questo valore {@code TipoNodoSintattico} indica il tipo di <i>token</i>
	 * contenuto nel <i>nodo sintattico</i>.
	 */
	private TipoNodoSintattico tipo;
	
	/**
	 * Inizializza un nuovo <i>nodo sintattico</i> con il figlio destro, il figlio
	 * sinistro, il contenuto e il tipo uguali a <i>null</i>.
	 */
	public NodoSintattico() {
		this.setNodoSinistro(null);
		this.setNodoDestro(null);
		this.contenuto = null;
		this.tipo = null;
	}
	
	/**
	 * Questo metodo imposta il <i>figlio sinistro</i> del <i>nodo sintattico</i>
	 * uguale a quello passato come parametro.
	 * 
	 * @param nodoSinistro
	 *            <i>figlio sinistro</i> del <i>nodo sintattico</i>.
	 */
	public void setNodoSinistro(NodoSintattico nodoSinistro) {
		this.nodoSinistro = nodoSinistro;
	}
	
	/**
	 * Questo metodo restituisce il <i>figlio sinistro</i> del <i>nodo
	 * sintattico</i>.
	 * 
	 * @param nodoSinistro
	 *            <i>figlio sinistro</i> del <i>nodo sintattico</i>.
	 */
	public NodoSintattico getNodoSinistro() {
		return this.nodoSinistro;
	}
	
	/**
	 * Questo metodo imposta il <i>figlio destro</i> del <i>nodo sintattico</i>
	 * uguale a quello passato come parametro.
	 * 
	 * @param nodoDestro
	 *            <i>figlio destro</i> del <i>nodo sintattico</i>.
	 */
	public void setNodoDestro(NodoSintattico nodoDestro) {
		this.nodoDestro = nodoDestro;
	}
	
	/**
	 * Questo metodo restituisce il <i>figlio destro</i> del <i>nodo 
	 * sintattico</i>.
	 * 
	 * @return il <i>figlio destro</i> del <i>nodo sintattico</i>.
	 */
	public NodoSintattico getNodoDestro() {
		return this.nodoDestro;
	}
	
	/**
	 * Questo metodo imposta il <i>contenuto</i> del <i>nodo sintattico</i> uguale a
	 * quello passato come parametro.
	 * 
	 * @param aToken
	 *            <i>token</i> da assegnare al <i>nodo sintattico</i> in questione.
	 */
	public void setContenuto(Token aToken) {
		this.contenuto = new Token(aToken);
	}
	
	/**
	 * Questo metodo restituisce il <i>token</i> contenuto nel <i>nodo
	 * sintattico</i> in questione.
	 * 
	 * @return <i>token</i> contenuto nel <i>nodo sintattico</i>.
	 */
	public Token getContenuto() {
		Token aToken = new Token(this.contenuto);
		return aToken;
	}

	/**
	 * Questo metodo imposta il <i>tipo</i> a cui appartiene il <i>token</i>
	 * contenuto nel <i>nodo sintattico</i> uguale a quello passato come parametro.
	 * 
	 * @param tipo
	 *            il <i>tipo</i> del nodo sintattico da impostare.
	 */
	public void setTipo(TipoNodoSintattico tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Questo metodo restituisce il <i>tipo</i> a cui appartiene il
	 * <i>token</i> contenuto nel <i>nodo sintattico</i>.
	 * 
	 * @return tipo del <i>token</i> contenuto nel nodo sintattico.
	 */
	public TipoNodoSintattico getTipo() {
		return this.tipo;
	}
}
