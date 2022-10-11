package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////

// Test 123
import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        //Får feilmelding dersom a == null
        Objects.requireNonNull(a,"Tabellen a kan ikke være nulL!");

        //Finner første element som ikke er null
        int i = 0;
        for (; i < a.length && a[i] == null; i++);

        if (i < a.length) {
            Node<T> p = hode = new Node<>(a[i], null, null);       //Oppretter den første noden ved hode uten pekere
            antall++;
            endringer++;

            //Fortsetter gjennom a så langt det lar seg gjøre (a[i] != null) og oppretter node og pekere for hvert element i a
            for (i++; i < a.length; i++) {
                if (a[i] != null) {
                    p = p.neste = new Node<>(a[i], p, null);
                    antall++;
                    endringer++;
                }
                hale = p;
                hale.neste = null;
                hode.forrige = null;
            }
        }
        //throw new UnsupportedOperationException();
    }

    // Oppgave 3a)
    private Node<T> finnNode(int indeks){ // Denne metoden skal finne noden til en gitt indeks
        Node<T> p = hode; // p hjelpepeker
        Node<T> q = hale; // q hjelpepeker
        if(indeks < (antall/2)){  // Dersom indeks < antall/2 skal vi lete fra venstre side
            for (int i = 0; i < indeks; i++){
                p = p.neste; // går gjennom hver node frem til vi treffer den som er indeksen
            }
            return p;
        }
        else{
            for (int i = antall-1; i > indeks; i--){ // Leter fra høyre side (hale siden)
                q = q.forrige; // returnerer indeksen
            }
            return q;
        }
    }
    // Oppgave 3b)
    private static void fratilKontroll(int antall, int fra, int til) { // Se kompendiet 1.2.3 a) bare at tablengde har blitt byttet til antall
        if (fra < 0)                             // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                     // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")"); // tablengde byttet til antall

        if (fra > til)                           // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    //Oppgave 3b)
    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall,fra,til);  // Sjekker om grensen er definert ellers kastes et unntak
        Liste<T> liste = new DobbeltLenketListe<>();  // Danner en liste av grensesnittet Liste<T>
        Node<T> p = hode;  // Hjelpepeker p
        endringer = 0;  // endringer skal alltid resettes til 0 hver gang
        while(p != null){  // Så lenge neste node p ikke er null, skal vi gå gjennom while løkken
            if((endringer >= fra) && (endringer < til)){  // // Så lenge endringer attributten er innafor grensen, skal vi legge til node verdien i listen.
                liste.leggInn(p.verdi);
            }
            p = p.neste; // pekeren peker på neste node
            endringer++; // inkrementerer endringer
        }
        return liste;
    }

    @Override
    public int antall() {
        //Returnerer antall
        return antall;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {
        //Returerner true dersom antall = 0
        return antall == 0;
        //throw new UnsupportedOperationException();
    }

    @Override
    public boolean leggInn(T verdi) {
        //Gir feilmelding dersom verdi er null
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        if(tom()){
            hode = hale = new Node<>(verdi, null, null);
        }
        else  // ny verdi bakerst
        {
            hale = hale.neste = new Node<>(verdi, hale, null);
        }

        antall++;      // ny verdi i listen
        endringer++;   // en endring i listen

        return true;
    }
    //Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        //throw new UnsupportedOperationException();
        Objects.requireNonNull(verdi, "Kan ikke være null-verdier");
        indeksKontroll(indeks, true);

        if (indeks == 0)  // ny verdi skal ligge først
        {
            if (antall == 0) {  //Listen er en tom liste
                hode = hale = new Node<>(verdi);
            }
            else {
                hode = hode.forrige = new Node<>(verdi, null, hode);  // Verdien legges først i listen
            }
    }else if (indeks == antall)  // ny verdi skal ligge bakerst
        {
            hale = hale.neste = new Node<>(verdi, hale, null);  // legges bakerst
        }
        else
        {
            Node<T> p = finnNode(indeks-1);  // p flyttes med (indeks - 1) ganger
            p.neste.forrige = p.neste = new Node<>(verdi, p, p.neste);  // verdien blir satt inn i listen
        }
        endringer++;             // en endring ble gjort i listen
        antall++;                // listen har fått en ny verdi
    }

    //Oppgave 4
    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) >= 0; // returnerer true om indexen til verdi er over -1.
    }
    // Oppgave 3a)
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig
        return finnNode(indeks).verdi;    // returnerer verdien til noden til en gitt indeks
    }

    //Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        int i = 0; // index teller
        Node<T> p = hode;
        Node<T> q = hale;

        if (i < antall/2) { // avgjør om vi skal lete fra hode eller hale.
            for (i = 0; i < antall; i++){
                if (p.verdi.equals(verdi)){ // metode for å finne "verdi" i p.
                    // returnerer index til verdi om den blir funnet
                    return i;
                }
                p = p.neste; // hvis verdien ikke blir funnet sjekker vi neste node siden vi har startet helt til venstre.
            }
        }
        else {
            // leter etter verdi fra hodet og innover
            for (i = antall - 1; i >= 0; i--){
                if (q.verdi.equals(verdi)){ // metode for å finne "verdi" i p.
                    // returnerer verdi til index om den blir funnet
                    return i;
                }
                q = q.forrige; // hvis verdien ikke blir funnet sjekker vi forrige node siden vi har startet helt til høyre.
            }
        }
        // returnerer -1 om verdi er null
        return -1;
    }
    // Oppgave 3a)
    @Override
    public T oppdater(int indeks, T nyverdi) {

        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");
        indeksKontroll(indeks,false);   // Den skal være lik false fordi indeks skal ikke være lov til å være antall

        Node<T> p = finnNode(indeks); // hjelpepekeren p skal begynne på indeksen
        T gammelVerdi = p.verdi;  // Tar vare på den gamle verdien av p.verdi

        p.verdi = nyverdi;   // p.verdien til noden skal få verdien som ble satt inn i parameteren
        endringer++;         // endringer inkrementeres
        return gammelVerdi;
    }
    // Oppgave 6
    @Override
    public boolean fjern(T verdi) {

        if (verdi == null){
            return false;          // ingen nullverdier i listen
        }

        Node<T> q = hode, p = null;               // hjelpepeker q og p

        while (q != null)                         // While løkke for å gp gjennom nodene
        {
            if (q.verdi.equals(verdi)){  // Dersom verdien til q er lik input verdi, skal vi ut av løkken
                break;       // verdien funnet
            }
            p = q; q = q.neste;                     // p er prev til q
        }
        if(q == null){          // Dersom q == null finnes ikke verdien
            return false;
        }
        else if(antall == 1){    // Dersom antall == 1, skal hode og hale peke på null
            hode = null;
            hale = null;
        }
        else if(q == hode){       // Dersom q er lik hode, men det er flere noder enn 1, skal hoden forskyves fremover
            hode = hode.neste; // hodepekeren flyttes til neste node
            hode.forrige = null; // hodepekeren sin forrige peker på null
        }
        else if(q == hale){   // Dersom q er lik halen skal halen flyttes til noden til venstre
            hale = hale.forrige; // halen flytter til venstre
            hale.neste = null; // nestsiste node skal nå peke på null
        }
        else{   // Nå skal vi isolere noden q peker på
            Node<T> r = q.neste;        // hjelpepeker r
            p.neste = q.neste;          // p neste skal være q neste
            r.forrige = q.forrige;      // r skal peke på q
        }
        q.verdi = null;  // nullstiller noden q med alle verdiene sine og pekerene sine
        q.neste = null;
        q.forrige = null;

        antall--; // deinkrementerer antall fordi det finnes en mindre node i listen
        endringer++;  // Vi har gjort en endring i listen nå

        return true;
    }
    // Oppgave 6
    @Override
    public T fjern(int indeks) {

        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        T temp;                           // hjelpevariabel

        if(antall == 1){         // Dersom det bare er en node i listen skal hode og hale være null. hode.neste og hode.forrige peker allerede på null.
            temp = hode.verdi;
            hode = null;
            hale = null;
        }
        else if (indeks == 0)                     // Dersom det finnes flere enn 1 node i listen og vi skal fjerne første node
        {
            temp = hode.verdi;                 // tar vare på verdien som skal fjernes
            hode = hode.neste;                 // hode flyttes til neste node
            hode.forrige = null;               // Vi vil at det nye hode ikke skal peke tilbake på den gamle hode noden.
        }
        else if(indeks == antall-1){
            //
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;               // q er noden som skal fjernes
            temp = q.verdi;         // Tar vare på den gamle verdien av q
            hale = p;       // halen skal nå peke på den nest siste noden p
            p.neste = null;       // Den nye siste noden skal ha nestpeker på null
            q.forrige = null;      // q forrige skal nå peke på null
        }
        else
        {
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;               // q skal fjernes
            Node<T> r = q.neste;               // r trenger vi for å ordne bakoverpekere

            temp = q.verdi;                    // tar vare på verdien som skal fjernes

            p.neste = q.neste;                 // Ordner pekerene til de nye nodene
            r.forrige = q.forrige;
        }

        antall--;                            // reduserer antallet
        endringer++;                         // øker endringer
        return temp;                         // returner fjernet verdi

    }

    //Oppgave 7
    @Override
    public void nullstill() {
        Node<T> p = hode;

        //1. metode:
        for (; p!= null; p = p.neste){ // sjekker at p ikke er null før vi går videre.
            p.forrige = null; // nullstiller nåværende node
            p.neste = null; // nullstiller peker
            p.verdi = null; // nullstiller peker
        }

        //2. metode:
        /*
        for (int i = 0; i < antall; i++){ // forløkke for å gå gjennom hver node i p.
            fjern(i); // kaller på "fjern()" metoden.
            i++; // endrer node
        }
        */

        hode = null; // nullstiller første node til slutt
        hale = null; // nullstiller også siste node
        antall = 0; // setter antall til 0 siden det ikke er flere noder.
        endringer ++; // øker endringer
        // løsning 1 er mer effektiv ettersom løsning 2 krever flere operasjoner i gjennomsnitt.
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();          //Benytter StringBuilder for å opprette tegnstrengen.
        s.append('[');                              //Begynner med "[" og slutter med "]" for å få på formatet : "[1,2,3]"

        if(!tom()) {                                //Hvis den lenkede listen ikke er tom:
            Node<T> p = hode;                       //Begynner vi på den første noden og legger verdien inn i strengen,
            s.append(p.verdi);

            p = p.neste;                            //peker på neste node

            while (p != null) {             //Fortsetter om det gjenstår flere elementer
                s.append(',').append(' ').append(p.verdi);
                p = p.neste;
            }
        }

        s.append(']');

        return s.toString();
        //throw new UnsupportedOperationException();
    }

    //Gjør akkuratt det samme som toString(), men skal gå baklengs
    public String omvendtString() {
        StringBuilder s = new StringBuilder();
        s.append('[');

        if(!tom()) {
            Node<T> p = hale;                          //Begynner på bakerste node
            s.append(p.verdi);

            p = p.forrige;                            //peker på forrige node

            while (p != null) {
                s.append(',').append(' ').append(p.verdi);
                p = p.forrige;
            }
        }

        s.append(']');

        return s.toString();
        //throw new UnsupportedOperationException();
    }
    //Oppgave 8 b)
    @Override
    public Iterator<T> iterator() {
        //throw new UnsupportedOperationException();
        return new DobbeltLenketListeIterator(); //Returnerer instanse av iteratorklassen
    }
    //Oppgave 8 d)
    public Iterator<T> iterator(int indeks) {
        //throw new UnsupportedOperationException();
        return new DobbeltLenketListeIterator(indeks); //Returnerer instanse av iteratorklassen med indeks parameter

    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }
        //Oppgave 8 c)
        private DobbeltLenketListeIterator(int indeks) {
            //throw new UnsupportedOperationException();
            indeksKontroll(indeks, false); //Luker ut feilsituasjoner

            hode = finnNode(indeks);   //Finner noden til indeksen
            denne = hode;      //setter denne pekeren til indeksen
            fjernOK = false;   //Setter fjernOK til false
            iteratorendringer = endringer;  //Sjekker om iteratorendringer = endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }
        //Oppgave 8 a)
        @Override
        public T next() {
            //throw new UnsupportedOperationException();
            if(iteratorendringer != endringer){   //Sjekker om endringene er like
                throw new ConcurrentModificationException(); //Hvis de er ulike kastes Exception
            }
            if(!hasNext()){  //Sjekker om listen har en neste, hvis ikke er listen tom
                throw new NoSuchElementException("Listen er tom");
            }
            fjernOK = true;   //
            T current = denne.verdi;  //Lager en ny variabel som skal returnere verdien til denne før den blir endret.
            denne = denne.neste; //Setter denne til denne.neste

            return current; //Returnerer verdien til denne
        }

        // Oppgave 9

        @Override
        public void remove() {

            if (!fjernOK){    // Det skal ikke være lov å kalle remove rett etter hverandre
                throw new IllegalStateException("Ulovlig tilstand!");
            }

            if (iteratorendringer != endringer){        // iteratorendringer skal være i takt med endringer
                throw new ConcurrentModificationException("Listen har blitt endret!");
            }

            fjernOK = false;            // Det skal ikke være mulig å kalle remove rett etter hverandre
            Node<T> q = hode;       //  Hjelpe peker q

            if(antall == 1){         // Dersom det bare er en node, skal hode og hale peke på null
                hode = null;
                hale = null;
            }
            else if(denne == null){             // Dersom den siste noden skal fjernes skal q peke på hale
                // og den nye halen skal peke på sin forrige og den nest siste noden skal peke fremover på null.
                q = hale;
                hale = q.forrige;
                hale.neste = null;
            }
            else if(denne.forrige == null){ // Den første noden skal fjernes og hode flyttes til neste node samtidig som det nye hodet peker bakover på null
                hode = hode.neste;
                hode.forrige = null;
            }
            else if(hode.neste == denne){       // Dersom denne peker på den nest første verdien må vi fange den opp ellers vil while() løkken fortsette mot uendelig
                hode = hode.neste;
                hode.forrige = null;
            }
            else{
                Node<T> r = hode;

                while(r.neste.neste != denne){          // Vi må passe på at r pekeren alltid er to noder bak denne pekeren
                    r = r.neste;    // dersom den er mer enn 2 noder bak, skal r til neste node.
                }

                q = r.neste;            // q noden skal være noden etter r
                r.neste = denne;        // r noden skal peke på denne
                denne.forrige = r;      // denne noden skal ha bakover peker på r
            }
            q.verdi = null;     // Deretter skal q slettes og nullstilles
            q.neste = null;
            q.forrige = null;

            antall--;   // reduserer antall med 1
            endringer++;    // endringer økes med 1
            iteratorendringer++;    // iteratorendringer økes med 1
        }
    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        int lengde = liste.antall() - 1;                                        //Øvre intervallgrense som skal brukes til boblesortering
        boolean sortert = false;                                                //Boolsk variabel som er usann dersom listen ikke er sortert

        if(!liste.tom()) {                                                      //Kode som kjøres dersom listen ikke er tom
            //boblesortering

            while (!sortert) {                                                  //Går inn while-løkken dersom listen ikke er sortert
                sortert = true;
                for (int i = 0; i < lengde; i++) {
                    //Bytt-metoden
                    T val1 = liste.hent(i);
                    T val2 = liste.hent(i+1);
                    if (c.compare(val1,val2) > 0) {                         //Sammenligner variablene og bytter plass dersom de ikke er i riktig rekkefølge
                        sortert = false;
                        liste.oppdater(i, val2);
                        liste.oppdater(i+1, val1);
                    }
                }
                lengde--;
            }
        }
    }

    public static void main(String[] args) {
        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};

        Liste<String> liste = new DobbeltLenketListe<>(navn);

        DobbeltLenketListe.sorter(liste, Comparator.naturalOrder());

        System.out.println(liste);
    }

} // class DobbeltLenketListe


