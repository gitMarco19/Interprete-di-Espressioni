/**
 * La classe {@code Token} serve per identificare 
 * gli <i>elementi lessicali</i> delle espressioni.
 * 
 * @author Marco Scanu
 */
public class Token {
	
	/**
	 * Questo <i>array</i> di {@code String} indica le <i>parole chiave</i> che identificano le istruzioni.
	 */
	public static final String[] paroleChiave = {"GET", "SET"};
	
	/**
	 * Questo <i>array</i> di {@code String} indica gli <i>operatori</i> delle possibili operazioni.
	 */
	public static final String[] operatori = {"ADD", "SUB", "MUL", "DIV"};
	
	/**
	 * Questa istanza della classe {@code String} indica il contenuto del {@code Token}.
	 */
	private String valore;
	
	/**
	 * Inizializza un nuovo {@code Token} con il valore uguale 
	 * a quello passato come parametro.
	 * 
	 * @param valore {@code String} che indica il valore del <i>token</i>.
	 */
	public Token(String valore) {
		this.valore = valore;
	}
	
	/**
	 * Inizializza un nuovo {@code Token} con il valore uguale 
	 * a quello del Token passato come parametro.
	 * 
	 * @param aToken {@code Token} da cui impostare i valori.
	 */
	public Token(Token aToken) {
		this.valore = aToken.getValore();
	}
	
	/**
	 * Questo metodo restituisce il contenuto del {@code Token}.
	 * 
	 * @return contenuto del {@code Token}.
	 */
	public String getValore() {
		return this.valore;
	}
	
	/**
	 * Questo metodo controlla se il {@code Token} in questione 
	 * corrisponde ad una <i>parentesi tonda aperta</i>.
	 * 
	 * @return <i>true</i> se è una parentesi aperta, <i>false</i> altrimenti.
	 */
	public boolean isParentesiAperta() {
		return (this.getValore().equals("("));
	}
	
	/**
	 * Questo metodo controlla se il {@code Token} in questione 
	 * corrisponde ad una <i>parentesi tonda chiusa</i>.
	 * 
	 * @return <i>true</i> se è una parentesi chiusa, <i>false</i> altrimenti.
	 */
	public boolean isParentesiChiusa() {
		return (this.getValore().equals(")"));
	}
	
	/**
	 * Questo metodo, controlla se il {@code Token} in questione è una <i>parola chiave</i>.
	 * 
	 * @return <i>true</i> se è una parola chiave valida, <i>false</i> altrimenti.
	 */
	public boolean isValidKeyWord() {
		for (String elemento : Token.paroleChiave)
			if (this.getValore().equals(elemento))
				return true;
		return false;
	}
	
	/**
	 * Questo metodo, controlla se il {@code Token} in questione è un <i>operatore</i>.
	 * 
	 * @return <i>true</i> se è un operatore valido, <i>false</i> altrimenti.
	 */
	public boolean isValidOperator() {
		for (String elemento : Token.operatori)
			if (this.getValore().equals(elemento))
				return true;
		return false;
	}
	
	/**
	 * Questo metodo, controlla se il {@code Token} in questione è un <i>numero</i>.
	 * 
	 * @return <i>true</i> se è un numero valido, <i>false</i> altrimenti.
	 */
	public boolean isValidNumber() {
		if (this.getValore().length() == 1)
			if ((this.getValore().charAt(0) >= '0') 
					&& (this.getValore().charAt(0) <= '9'))
				return true;	
		
		if (this.getValore().length() > 1) {
			if (this.getValore().charAt(0) == '-') {
				if (this.getValore().length() == 2) {
					if ((this.getValore().charAt(1) >= '0') 
							&& (this.getValore().charAt(1) <= '9'))
						return true;
				} else {
					if (this.getValore().charAt(1) == '0')
						return false;
					else {
						for (int i = 1; i < this.getValore().length(); i++)
							if (!((this.getValore().charAt(i) >= '0') 
									&& (this.getValore().charAt(i) <= '9')))
								return false;
						
						return true;
					}
				}
			}
			
			if (this.getValore().charAt(0) == '0')
				return false;
			else {
				for (int i = 0; i < this.getValore().length(); i++)
					if (!((this.getValore().charAt(i) >= '0') 
							&& (this.getValore().charAt(i) <= '9')))
						return false;
				
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Questo metodo, controlla se il {@code Token} in questione è una <i>variabile</i>.
	 * 
	 * @return <i>true</i> se è una variabile valida, <i>false</i> altrimenti.
	 */
	public boolean isValidVariable() {		
		for (int i = 0; i < this.getValore().length(); i++) {
			if (!((this.getValore().charAt(i) >= 'a') 
					&& (this.getValore().charAt(i) <= 'z')) 
						&& !((this.getValore().charAt(i) >= 'A') 
							&& (this.getValore().charAt(i) <= 'Z'))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Questo metodo serve per confrontare se due {@code Token} 
	 * hanno lo stesso valore.
	 * 
	 * @param anObject
	 *            L'oggetto con cui confrontare il {@code Token}.
	 *
	 * @return <i>true</i> se i {@code Token} corrispondono, <i>false</i> altrimenti.
	 */
	@Override
	public boolean equals(Object anObject) {
		Token aToken = (Token) anObject;
		return this.getValore().equals(aToken.getValore());
	}
}
