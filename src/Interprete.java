import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * La classe {@code Interprete} rappresenta un <i>interprete di statement</i>
 * che dato un <i>file sorgente</i>, contenente delle istruzioni, è capace di
 * interpretarle eseguendo l'<i>analisi lessicale, sintattica e semantica</i>.
 * 
 * @author Marco Scanu
 */
public class Interprete {
	
	/**
	 * Questa istanza della classe {@code File} indica il <i>file</i> contenente le
	 * istruzioni da analizzare.
	 */
	private File fileIstruzioni;
	
	/**
	 * Quest'istanza della classe {@code ListaEspressioni} indica le <i>istruzioni</i>
	 * sottoforma di <i>token</i> da analizzare.
	 */
	private ListaIstruzioni aList;
	
	/**
	 * Inizializza un nuovo <i>interprete di statement</i>.
	 * 
	 * @param percorso
	 *            {@code String} che indica il percorso del file contenente le
	 *            istruzioni da analizzare.
	 */
	public Interprete(String percorso) {
		this.fileIstruzioni = new File(percorso);
		this.aList = new ListaIstruzioni();
	}
	
	/**
	 * Questo metodo esegue l'<i>interpretazione delle istruzioni</i> fornite in
	 * input all'interprete.
	 */
	public void interpretazioneIstruzioni() {
		this.analisiLessicale();
		this.analisiSintattica();
		this.analisiSemantica();
	}
	
	/**
	 * Questo metodo esegue l'<i>analisi lessicale</i> fornendo per ogni istruzioni
	 * la sequenza dei <i>token</i> che le compongono.
	 */
	private void analisiLessicale() {
		Scanner aScanner = null;
		try {
			aScanner = new Scanner(this.fileIstruzioni);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int index = 0; aScanner.hasNextLine(); index++) {
			String aString = aScanner.nextLine();
			if (aString.length() != 0) {
				if (aString.contains(Token.paroleChiave[0]))
					this.aList.creaIstruzione(new Token(Token.paroleChiave[0]));
				else if (aString.contains(Token.paroleChiave[1]))
					this.aList.creaIstruzione(new Token(Token.paroleChiave[1]));
				else
					this.aList.creaIstruzione(new Token(""));
			} else {
				index--;
				continue;
			}
			String tmp = "";
			for (int i = 0; i < aString.length(); i++) {
				if (aString.charAt(i) == ' ')
					continue;
				if (aString.charAt(i) == '(')
					this.aList.componiIstruzione(index, new Token("("));
				else if (aString.charAt(i) == ')')
					this.aList.componiIstruzione(index, new Token(")"));
				else {
					tmp += aString.charAt(i);
					if ((i + 1) >= aString.length() 
							|| aString.charAt(i + 1) == ' ' 
							|| aString.charAt(i + 1) == '(' 
							|| aString.charAt(i + 1) == ')') {
						this.aList.componiIstruzione(index, new Token(tmp));
						tmp = "";
					}
				}
			}
		}
	}
	
	/**
	 * Questo metodo esegue l'<i>analisi sintattica</i> controllando la correttezza
	 * sintattica delle sequenze dei token delle istruzioni, tenendo anche traccia
	 * delle variabili e dei rispettivi valori. Se tutto è corretto costruisce un
	 * <i>albero sintattico</i> per ogni espressione contenuta nelle istruzioni.
	 */
	private void analisiSintattica() {
		for (int i = 0; i < this.aList.numeroIstruzioni(); i++)
			try {
				this.aList.getIstruzione(i).isValida(this.aList.getVariabili());
			} catch (Error e) {
				System.err.println("ERROR nell'istruzione n°" + (i + 1) 
						+ ": " + e.getLocalizedMessage());
				System.exit(0);
			}
	}
	
	/**
	 * Questo metodo esegue l'<i>analisi semantica</i> valutando le espressioni
	 * nelle istruzioni tenendo conto degli assegnamenti delle variabili,
	 * identificando gli errori semantici. Produce in output il risultato dei vari
	 * passi di valutazione.
	 */
	private void analisiSemantica() {
		for (int i = 0; i < this.aList.numeroIstruzioni(); i++) {
			try {
				this.aList.getIstruzione(i).valuta(this.aList.getVariabili());
				System.out.println(this.aList.getIstruzione(i).
						getRisultatoToString());
			} catch (Error e) {
				System.err.println("ERROR nell'istruzione n°" + (i + 1) 
						+ ": " + e.getLocalizedMessage());
				System.exit(0);
			}
		}
	}
}
