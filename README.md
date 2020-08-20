# Specifiche

## Notazione di base
Data una grammatica libera dal contesto G = (VN, VT, P, S) si ha che:

- la produzione A → α | β **abbrevia** le produzioni
		
		A → α
		A → β

- i **caratteri corsivi** definiscono i ***simboli non terminali*** VN, ad esempio program, statement, ecc.
- i **caratteri standard** sono riservati ai ***simboli terminali*** VT, ad esempio SET, MUL, (, ), ecc.
- Come consueto, il simbolo  denota la stringa vuota.

## Grammatica di input
	program → statement_list
	
	statement_list → statement statement_list | 
	
	statement → variable_def | expression_def
	
	variable_def → (SET variable expression)
	
	expression_def → (GET expression)
	
	expression → (ADD expression expression)
				| (SUB expression expression)
				| (MUL expression expression)
				| (DIV expression expression)
				| number
				| variable_id

	variable_id → alpha_list
	
	alpha_list → alpha alpha_list | alpha
	
	alpha → a | b | c | ... | z | A | B | C | ... | Z
	
	number → - posnumber | posnumber
	
	posnumber → 0 | sigdigit rest
	
	sigdigit → 1 | . . . | 9
	
	rest → digit rest | 
	
	digit → 0 | sigdigit

La grammatica proposta è **non ambigua** <br>
Al più due simboli non terminali disambiguano qualsiasi produzione:
- "(SET" e "(GET" consentono di risolvere l’ambiguità tra definizione di variabile e calcolo di espressione.
- "(ADD", "(SUB", ecc., consentono di risolvere l’ambiguità tra i vari tipi di espressione.

## Esempio file di input

	(GET (ADD (MUL 14 10) 25))
	(SET var (MUL 10 5))
	(GET (DIV 1000 var))
	(SET var (MUL 10 20))
	(GET (DIV 1000 var))

- Eventuali *spazi*, *tabulazioni* e *righe vuote* non fanno parte della sintassi del programma; devono essere considerati come “spazio vuoto” e ignorati;
- Le **parole chiave** sono GET, SET, ADD, SUB, MUL e DIV.

## Esempio di output
Ad esempio, in corrispondenza del programma elencato nell'esempio di input, l’**output** sarebbe il seguente:

	165
	var = 50
	20
	var = 200
	5

**Nota**: Si assuma che gli interi forniti in ingresso siano sempre rappresentabili in registri a 64 bit (i *long* di Java), ossia per qualsiasi numero n utilizzato si ha che |n| < 2^32.

## Interpretazione della dichiarazione delle variabili
Il risultato corrispondente ad una definizione è:

	(ERROR messaggio)

nei seguenti casi:
- la dichiarazione di variabile non è sintatticamente corretta,
- utilizza una variabile non definita in precedenza,
- utilizza una parola chiave come identificativo,
- l’espressione corrispondente contiene un errore semantico (divisione per zero).

Oppure:
	
	variable_def = number

se la dichiarazione di variabile è corretta.

Dove:
- *variable_def* è l’identificativo della variabile; <br>
- *number* è il valore ad essa assegnato ottenuto calcolando l’espressione presente nella definizione.

## Interpretazione degli statement
Il risultato corrispondente ad un'espressione è:

	(ERROR messaggio)

nei seguenti casi:
- la dichiarazione dell’espressione non è sintatticamente corretta,
- utilizza una variabile non definita in precedenza,
- contiene un errore semantico (divisione per zero)

Oppure:

	number

se la dichiarazione di espressione è corretta.
Dove:
- *number* è il valore ottenuto calcolando l’espressione presente nella definizione.

# Obiettivo
L'***interprete di espressioni*** deve eseguire:
- Un'analisi lessicale;
- Un'analisi sintattica;
- Un'analisi semantica.

## Analisi Lessicale
L'***analisi lessicale*** deve:
- leggere il file in input fornendo in uscita la sequenza degli ***elementi lessicali*** *(parole o token)* del programma.

Formalmente, per la grammatica dei file sorgente, un elemento lessicale corrisponde ad uno dei seguenti **gruppi**:
- una **parola chiave**: GET, SET, ADD, SUB, MUL, DIV;
- una **parentesi** (aperta o chiusa);
- un **numero** (definito con le regole *number*);
- un **variabile** (definita con le regole *variable_id*);

I **token** corrispondono ai **simboli terminali** di una versione "astratta" della grammatica che descrive le espressioni. <br>
L’utilizzo dei token corrisponde a utilizzare per l’analisi sintattica le seguenti regole:
	
	program → statement_list
	
	statement_list → statement statement_list | 
	
	statement → variable_def | expression_def
	
	variable_def → (SET variable expression)
	
	expression_def → (GET expression)
	
	expression → (ADD expression expression)
				| (SUB expression expression)
				| (MUL expression expression)
				| (DIV expression expression)
				| number
				| variable

## Analisi Sintattica
L'***analisi sintattica*** deve:
- Leggere la ***sequenza di token*** fornita dall’analizzatore lessicale e ne controlla la ***correttezza sintattica***.

Inizialmente:
- il valore delle variabili può essere assegnato a 0;
- in fase di valutazione, in presenza di un’istruzione *(SET x expr)* il valore di x nella tabella dei simboli viene aggiornato al valore calcolato per *expr*;
- in caso di errori sintattici, si restituisce un messaggio di errore al
primo errore incontrato.

Fornisce in uscita:
- una descrizione sotto forma di albero del programma in ingresso (c.d. ***albero sintattico***), e
- una ***tabella dei simboli***, che mette in corrispondenza le variabili con il relativo valore.

## Analisi Semantica
L'***analisi semantica*** deve:
- Valutare le espressioni negli statement (GET ... ) tenendo conto degli assegnamenti effettuati negli statement (SET ... ).
- Identificare gli errori semantici (ad esempio, divisioni per zero).

Opera in due modi possibili:
- producendo direttamente in output il risultato dei vari passi di
valutazione, oppure
- “annotando” i vari nodi SET e GET con i risultati delle espressioni e lasciare ad una visita successiva il compito di stampare i risultati.

Per eseguire la valutazione delle espressioni si può utilizzare un oggetto “Visitor” con opportuni metodi che consentano l’analisi dei vari elementi degli oggetti “Interpreter”.

# Specifiche di input
L’interprete prende in input un ***parametro da linea di comando*** che corrisponde al nome (completo di percorso) del file contenente il programma da interpretare.

# Specifiche di output
Il risultato ***deve*** essere visualizzato in console.