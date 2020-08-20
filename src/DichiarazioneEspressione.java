import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe {@code DichiarazioneEspressione} che <i>eredita</i> dalla classe
 * {@code Istruzione} rappresenta tutti i tipi di istruzione che definiscono
 * un'espressione, ovvero quelle istruzioni identificata dalla keyword
 * <i>GET</i>.
 * 
 * @author Marco Scanu
 */
public class DichiarazioneEspressione extends Istruzione {
	
	/**
	 * Istanza della classe {@code Espressione} che indica l'espressione dichiarata
	 * dallo <i>statement</i> in questione.
	 */
	private Espressione anExpression;
	
	/**
	 * Inizializza una nuova <i>dichiarazione di espressione</i>.
	 * <br>Inizialmente l'espressione che deve essere dichiarata è vuota.
	 */
	public DichiarazioneEspressione() {
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
			
			if (aToken.equals(new Token(Token.paroleChiave[0]))) {
				for (int i = ++index; i < anInstruction.size() - 1; i++)
						this.anExpression.aggiungiToken(anInstruction.get(i));
				
				index += this.anExpression.isValida(variabili);
				
				if (index + 1 != anInstruction.size())
					throw new Error("sono presenti"
							+ " delle parentesi in eccesso");
					
				aToken = anInstruction.get(index);
				if (!aToken.isParentesiChiusa())
					throw new Error("token non valio, "
							+ "manca la parentesi finale.");
			} else if (aToken.isParentesiAperta() 
					|| aToken.isParentesiChiusa()) {
				throw new Error("sono presenti"
						+ "delle parentesi in eccesso");
			} else
				throw new Error("la parola chiave è errata");
		} else {
			throw new Error("token non valido, "
					+ "manca la parentesi iniziale.");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void valuta(HashMap<String, Long> variabili) {
		this.anExpression.risolvi(variabili);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRisultatoToString() {
		return this.anExpression.getRisultato().toString();
	}
}
