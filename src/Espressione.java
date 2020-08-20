import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe {@code Espressione} rappresenta qualsiasi tipo di
 * <i>espressione</i> che è ammessa dalla <i>grammatica di input</i>.
 * 
 * @author Marco Scanu
 */
public class Espressione {
	
	/**
	 * Questo {@code ArrayList<Token>} indica un'espressione.
	 */
	private ArrayList<Token> anExpression;
	
	/**
	 * Questa istanza della classe {@code Long} indica il risultato
	 * dell'<i>espressione</i>
	 */
	private Long risultato;
	
	/**
	 * Quest'istanza della classe {@code AlberoSintattico} rappresenta un <i>albero
	 * sintattico</i> per l'{@code Espressione}.
	 */
	private AlberoSintattico aTree;
	
	/**
	 * Inizializza una nuova {@code Espressione} vuota.
	 */
	public Espressione() {
		this.anExpression = new ArrayList<Token>(0);
		this.risultato = null;
		this.aTree = new AlberoSintattico();
	}
	
	/**
	 * Inizializza una nuova {@code Espressione} con i {@code Token} uguali a quelli
	 * dell'<i>espressione</i> passata come parametro e un <i>albero sintattico</i>
	 * vuoto.
	 * 
	 * @param exp
	 *            <i>espressione</i> da cui copiare i <i>token</i>.
	 */
	public Espressione(Espressione exp) {
		this.anExpression = new ArrayList<Token>(0);
		for (int i = 0; i < exp.dimensione(); i++)
			this.anExpression.add(exp.getToken(i));
		this.aTree = new AlberoSintattico();
	}
	
	/**
	 * Questo metodo restituisce un numero {@code Long} contenente il risultato
	 * dell'espressione se è gia stato calcolato, altrimenti <i>null</i>.
	 * 
	 * @return risultato dell'espressione.
	 */
	public Long getRisultato() {
		return this.risultato;
	}
	
	/**
	 * Questo metodo aggiunge il {@code Token}, passato come parametro,
	 * all'espressione.
	 * 
	 * @param aToken
	 *            {@code Token} da aggiungere all'espressione.
	 */
	public void aggiungiToken(Token aToken) {
		this.anExpression.add(aToken);
	}
	
	/**
	 * Questo metodo restituisce il {@code Token} contenuto 
	 * nell'espressione in una data posizione passata come parametro.
	 * 
	 * @param index
	 *            posizione del {@code Token}
	 * 
	 * @return il {@code Token}
	 */
	public Token getToken(int index) {
		Token aToken = new Token(this.anExpression.get(index));
		return aToken;
	}
	
	/**
	 * Questo metodo calcola il numero di {@code Token} 
	 * contenuti nell'<i>espressione</i>.
	 * 
	 * @return numero {@code int} di token.
	 */
	public int dimensione() {
		return this.anExpression.size();
	}
	
	/**
	 * Questo metodo controlla se l'espressione in questione ha una <i>sintassi
	 * valida</i>. <br>
	 * Se è corretta restituisce l'indice del {@code Token} posizionato dopo l'espressione,
	 * alrimenti genera un <i>errore</i>.
	 * 
	 * @param variabili
	 *            {@code HashMap<String, Long>} che tiene traccia delle <i>variabili
	 *            inizializzate</i> e dei rispettivi valori.
	 *            
	 * @return l'indice del {@code Token} successiovo all'espressione.
	 */
	public int isValida(HashMap<String, Long> variabili) {
		Integer parentesi = 0;
		for (int i = 0; i < this.dimensione(); i++) {
			if (this.anExpression.get(i).equals(new Token("(")))
				parentesi++;
			
			if (this.anExpression.get(i).equals(new Token(")")))
				parentesi--;
		}
		if (parentesi != 0)
			throw new Error("le parentesi non sono bilanciate correttamente.");
		int i = this.controllaEspressione(variabili, 0, this.aTree.getRadice());
		return (i + 1);
	}
	
	/**
	 * Questo metodo svolge i calcoli contenuti nell'<i>espressione</i> in
	 * questione.
	 * 
	 * @param variabili
	 *            {@code HashMap<String, Long>} che tiene traccia delle <i>variabili
	 *            inizializzate</i> e dei rispettivi valori.
	 */
	public void risolvi(HashMap<String, Long> variabili) {
		this.risultato = this.valutaEspressione(this.aTree.getRadice(), 
				variabili);
	}
	
	/**
	 * Questo metodo si occupa di controllare la <i>correttezza sintattica</i> dei
	 * <i>token</i> che compongono l'espressione. Se l'espressione è corretta
	 * restituisce l'<i>indice del token</i> finale dell'espressione, altrimenti
	 * viene generato un <i>errore</i>.
	 * 
	 * @param index
	 *            indice del token da cui iniziare a controllare l'espressione.
	 * @param variabili
	 *            {@code HashMap<String, Long>} che tiene traccia delle <i>variabili
	 *            inizializzate</i> e dei rispettivi valori.
	 * @param aNode
	 *            nodo da cui iniziare a costruire l'<i>albero sintattico</i>
	 * 
	 * @return l'indice del <i>token</i> se è valido.
	 */
	private int controllaEspressione(HashMap<String, Long> variabili, 
			int index, NodoSintattico aNode) {
		
		if (this.anExpression.get(index).isValidNumber()) {
			aNode.setContenuto(anExpression.get(index));
			aNode.setTipo(TipoNodoSintattico.NUMERO);
			return index;
		}
		
		if (this.anExpression.get(index).isValidVariable()) {
			if (!variabili.containsKey(anExpression.get(index).getValore()))
				throw new Error("è stata utilizza una variabile "
						+ "non definita in precedenza");

			aNode.setContenuto(anExpression.get(index));
			aNode.setTipo(TipoNodoSintattico.VARIABILE);
			return index;
		}
		
		if (this.anExpression.get(index).isParentesiAperta()) {
			if (this.anExpression.get(++index).isValidOperator()) {
				aNode.setContenuto(anExpression.get(index));
				aNode.setTipo(TipoNodoSintattico.OPERATORE);
				
				aNode.setNodoSinistro(new NodoSintattico());
				index = this.controllaEspressione(variabili, (index + 1), 
						aNode.getNodoSinistro());
				
				aNode.setNodoDestro(new NodoSintattico());
				index = this.controllaEspressione(variabili, (index + 1), 
						aNode.getNodoDestro());
			} else if (this.anExpression.get(index).isParentesiAperta() 
					|| this.anExpression.get(index).isParentesiChiusa()) {
				throw new Error("sono presenti"
						+ " delle parentesi in eccesso");
			} else
				throw new Error("è presente un operatore non valido.");
			
			if ((index + 1) < this.anExpression.size() 
					&& this.anExpression.get(++index).isParentesiChiusa()) {
					return index;
			} else
				throw new Error("token non valido.");
		} else 
			throw new Error("token non valido.");
	}
	
	/**
	 * Questo metodo valuta l'<i>espressione</i>, svolgendo le <i>operazioni</i> al
	 * suo interno, restituendo il risultato {@code long}.
	 * 
	 * @param aNode
	 *            {@code NodoSintattico} da cui iniziare la valutazione
	 *            dell'espressione.
	 * @param variabili
	 *            {@code HashMap} che tiene traccia delle <i>variabili
	 *            inizializzate</i> e dei rispettivi valori.
	 * 
	 * @return risultato {@code long} dell'espressione.
	 */
	private long valutaEspressione(NodoSintattico aNode, 
			HashMap<String, Long> variabili) {
		if (aNode.getTipo() == TipoNodoSintattico.OPERATORE) {
			long num1 = valutaEspressione(aNode.getNodoSinistro(), variabili);
			long num2 = valutaEspressione(aNode.getNodoDestro(), variabili);
			
			switch (aNode.getContenuto().getValore()) {
				case "ADD":
					return (num1 + num2);
		
				case "SUB":
					return (num1 - num2);
		
				case "MUL":
					return (num1 * num2);
		
				case "DIV": {
					if (num2 == 0)
						throw new Error("non si può dividere per zero.");
					else
						return (num1 / num2);
				}
				
				default :
					return 0;
			}
		} else {
			long num = 0;
			
			if (aNode.getTipo() == TipoNodoSintattico.NUMERO)
				num = Long.parseLong(aNode.getContenuto().getValore());
			
			if (aNode.getTipo() == TipoNodoSintattico.VARIABILE)
				num = variabili.get(aNode.getContenuto().getValore());
			
			return num;
		}
	}
}
