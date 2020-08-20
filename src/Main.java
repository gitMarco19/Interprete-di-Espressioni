/**
 * La <i>classe</i> {@code Main} contiene il metodo <i>main</i>.
 * 
 * @author Marco Scanu
 */
public class Main {
	
	/**
	 * Questo metodo esegue l'interpretazione delle espressioni.
	 * 
	 * @param args
     *            vettore {@code String} che indica i parametri passati 
     *            tramite la linea di comando.
	 */
	public static void main(String[] args) {
		if (args.length == 1) {
			Interprete aInterpreter = new Interprete(args[0]);
			aInterpreter.interpretazioneIstruzioni();
		} else {
			throw new Error ("Parametri da linea di comando errati");
		}
	}
}
