import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe {@code DefinizioneVariabile} che <i>eredita</i> dalla classe
 * {@code Istruzione} rappresenta tutti i tipi di istruzione che definiscono
 * una varaibile, ovvero quelle istruzioni identificata dalla keyword
 * <i>SET</i>.
 * 
 * @author Marco Scanu
 */
public class DefinizioneVariabile extends Istruzione{
	
	/**
	 * Quest'istanza della classe {@code Token} indica la variabile che viene
	 * definita dallo <i>statement</i> in questione.
	 */
	private Token variabile;
	
	/**
	 * Istanza della classe {@code Espressione} che indica l'espressione che fornirà
	 * il valore {@code long} da assegnare alla <i>variabile</i>.
	 */
	private Espressione anExpression;
	
	/**
	 * Inizializza una nuova <i>dichiarazione di variabile</i>. La variabile che
	 * identifica è impostata a <i>null</i> e l'espressione associata è vuota.
	 */
	public DefinizioneVariabile() {
		this.variabile = null;
		this.anExpression = new Espressione();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void isValida(HashMap<String, Long> variabili) {
		ArrayList<Token> anInstruction = this.getAnInstruction();

		int index = 0;
		Token aToken = anInstruction.get(index);

		if (aToken.isParentesiAperta()) {
			aToken = anInstruction.get(++index);

			if (aToken.equals(new Token(Token.paroleChiave[1]))) {
				aToken = anInstruction.get(++index);
				if (aToken.isValidVariable()) {
					for (String elemento : Token.paroleChiave)
						if (aToken.getValore().equals(elemento))
							throw new Error("è stata utilizza una parola " 
									+ "chiave come identificativo");

					for (String elemento : Token.operatori)
						if (aToken.getValore().equals(elemento))
							throw new Error("è stata utilizza " 
									+ "una parola chiave come identificativo");

					variabili.put(aToken.getValore(), Long.valueOf(0));
					this.variabile = new Token(aToken);

					for (int i = ++index; i < anInstruction.size() - 1; i++)
						this.anExpression.aggiungiToken(anInstruction.get(i));

					index += this.anExpression.isValida(variabili);

					if (index + 1 != anInstruction.size())
						throw new Error("sono presenti" 
								+ " delle parentesi in eccesso");

					aToken = anInstruction.get(index);
					if (!aToken.isParentesiChiusa())
						throw new Error("token non valdo, " 
								+ "manca la parentesi finale.");
				} else
					throw new Error("l'identificativo della variabile " 
							+ "non è valido");
			} else if (aToken.isParentesiAperta() 
					|| aToken.isParentesiChiusa()) {
				throw new Error("sono presenti delle " 
						+ "parentesi in eccesso");
			} else
				throw new Error("la parola chiave è errata");
		} else
			throw new Error("token non valido, " 
					+ "manca la parentesi iniziale.");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valuta(HashMap<String, Long> variabili) {
		this.anExpression.risolvi(variabili);
		variabili.put(this.variabile.getValore(), 
				this.anExpression.getRisultato());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRisultatoToString() {
		String aString = this.variabile.getValore() + " = " 
				+ this.anExpression.getRisultato();
		
		return aString;
	}
}
