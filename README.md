Hacksprint JUGTAA 2016
======================

L'esercizio sarà giudicato in base ai seguenti requisiti:

* Implementazione dei requisiti richiesti

* Funzionamento del programma

* Implementazione di unit test

* Eleganza della soluzione

* Stile di programmazione

Tutte le librerie necessarie allo svolgimento corretto dell'esercizio sono già incluse, non è quindi necessario includere ulteriori librerie.

L'implementazione dei requisiti va fatta senza l'ausilio di alcun framework o libreria esterna, dovrete scrivere voi tutto il codice necessario.

### 1. Eseguite i test e troverete un errore: correggetelo e fate in modo che i test siano eseguiti con successo.

### 2. Eseguite un refactoring del codice esistenete

### 3. Modificate il codice per fare in modo di inviare gli auguri alle persone nate il 29 febbraio.
  Se l'anno è bisestile gli auguri vanno mandati il 29 febbraio, mentre negli altri anni deve essere
  inviato il primo di marzo.

### 4. Modificate il codice in modo che per alcuni utenti sia inviato anche un fax.
  Gli utenti per i quali va utilizzato il fax sono coloro che hanno il campo relativo
  al numero di telefono fax valido.

  Per inviare un fax basta utilizzare una particolare formattazione del testo della e-mail,
  come descritto di seguito:

* destinatario    : inviafax@jugtaa2016.org
* oggetto         : __titolo email e deve terminare con INVIOFAXDAEMAIL__
* corpo del testo :


    [emailaccount]=<email per login>
    [password]=<password di login>
    [mittente]=<nome o identificativo del mittente>
    [emailrisposta]=<indirizzo di e-mail a cui inviare la risposta>
    [numerofax]=<numero di fax da chiamare>
    [contenutofax]=<testo email con i saluti>


* emailaccount      : greetingsKata@jugtaa.org
* password          : hacksprint2016

### 5. Modificate il codice in modo da poter inviare anche gli auguri di Pasqua
  Il messaggio deve contenere il seguente testo:
  Oggetto: Auguri di Buona Pasqua!
  Corpo: <Nome> <Cognome> ti facciamo un augurio di una Pasqua serena.

  <Nome> e <Cognome> vanno sostituiti con il rispettivo Nome e Cognome della
  persona alla quale si inviano gli auguri.

### 5. Modificate il codice per caricare i dati anche da un file JSON.


Metodo per il calcolo della Pasqua
----------------------------------

Di seguito il metodo di calcolo della Pasqua di Gauss:

p = giorno di Pasqua da calcolare

    Y = anno in cui si calcola la Pasqua
    M = 24
    N = 5

    a = Y mod 19
    b = Y mod 4
    c = Y mod 7

    d = (19a + M) mod 30
    e = (2b + 4c + 6d + N) mod 7

Se (d + e) < 10 il giorno cade in marzo:

    p = d + e + 22

Altrimenti cade in aprile:

    p = d + e − 9
