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

    private Node<T> finnNode(int indeks){ //
        Node<T> p = hode;
        Node<T> q = hale;
        if(indeks < (antall/2)){
            for (int i = 0; i < indeks; i++){
                p = p.neste;
            }
            return p;
        }
        else{
            for (int i = antall-1; i > indeks; i--){
                q = q.forrige;
            }
            return q;
        }
    }
    // Oppgave 3b)
    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                             // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                     // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                           // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    //Oppgave 3b)
    public Liste<T> subliste(int fra, int til) { // WIP
        fratilKontroll(antall,fra,til);
        Liste<T> liste = new DobbeltLenketListe<>();
        Node<T> p = hode;
        endringer = 0;
        while(p != null){
            if((endringer >= fra) && (endringer < til)){
                liste.leggInn(p.verdi);
            }
            p = p.neste;
            endringer++;
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

        if(tom() == true){
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
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true);  // Se Liste, true: indeks = antall er lovlig

        if (indeks == 0)                     // ny verdi skal ligge først
        {
            hode = new Node<>(verdi);    // legges først
            if (antall == 0) hale = hode;      // hode og hale går til samme node
        }
        else if (indeks == antall)           // ny verdi skal ligge bakerst
        {
            hale = hale.neste = new Node<>(verdi);  // legges bakerst
        }
        else
        {
            Node<T> p = hode;                  // p flyttes indeks - 1 ganger
            for (int i = 1; i < indeks; i++) p = p.neste;

            p.neste = new Node<>(verdi);  // verdi settes inn i listen
        }
        endringer++;
        antall++;                            // listen har fått en ny verdi

    }

    //Oppgave 4
    @Override
    public boolean inneholder(T verdi) {
        // returnerer true om indexen til verdi er over -1.
        return indeksTil(verdi) <= 0;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig
        return finnNode(indeks).verdi;
    }

    //Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        // index
        int i = 0;
        Node<T> p = hode;
        Node<T> q = hale;

        if (i < antall/2) {
            // leter etter verdi fra halen og innover
            for (i = 0; i < antall; i++){
                if (p.verdi == verdi){
                    // returnerer index til verdi om den blir funnet
                    return i;
                }
                p = p.neste;
            }
        }
        else {
            // leter etter verdi fra hodet og innover
            for (i = antall - 1; i >= 0; i--){
                if (q.verdi == verdi){
                    // returnerer verdi til index om den blir funnet
                    return i;
                }
                q = q.forrige;
            }
        }
        // returnerer -1 om verdi er null
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {

        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");
        indeksKontroll(indeks,false);

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = nyverdi;
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {

        if (verdi == null){
            return false;          // ingen nullverdier i listen
        }

        Node<T> q = hode, p = null;               // hjelpepekere

        while (q != null)                         // q skal finne verdien t
        {
            if (q.verdi.equals(verdi)){
                break;       // verdien funnet
            }
            p = q; q = q.neste;                     // p er forgjengeren til q
        }
        if(q == null){
            return false;
        }
        else if(antall == 1){
            hode = null;
            hale = null;
        }
        else if(q == hode){
            hode = hode.neste;
            hode.forrige = null;
        }
        else if(q == hale){
            hale = hale.forrige;
            hale.neste = null;
        }
        else{
            Node<T> r = q.neste;
            p.neste = q.neste;
            r.forrige = q.forrige;
        }
        q.verdi = null;
        q.neste = null;
        q.forrige = null;

        antall--;
        endringer++;

        return true;
    }

    @Override
    public T fjern(int indeks) {

        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        T temp;                           // hjelpevariabel

        if(antall == 1){         // Dersom det bare er en node i listen skal hode og hale være null. hode.neste og hode.forrige peker allerede på null.
            temp = hode.verdi;
            hode = null;
            hale = null;
        }
        else if (indeks == 0)                     // skal første verdi fjernes?
        {
            temp = hode.verdi;                 // tar vare på verdien som skal fjernes
            hode = hode.neste;                 // hode flyttes til neste node
            hode.forrige = null;               // Vi vil at det nye hode ikke skal peke tilbake på den gamle hode noden.
        }
        else if(indeks == antall-1){
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;               // q skal fjernes
            temp = q.verdi;
            hale = q.forrige;
            p.neste = null;
            q.forrige = null;
        }
        else
        {
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;               // q skal fjernes
            Node<T> r = q.neste;               // r trenger vi for å ordne bakoverpekere

            temp = q.verdi;                    // tar vare på verdien som skal fjernes

            p.neste = q.neste;                 // "hopper over" q
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
        for (; p!= null; p = p.neste){
            p.forrige = null;
            p.neste = null;
            p.verdi = null;
        }

        //2. metode:
        /*
        int i = 0;
        while (antall > 0){
            fjern(i);
            i++;
        }
        */

        hode = null;
        hale = null;
        antall = 0;
        endringer ++;
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

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            //throw new UnsupportedOperationException();
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException("Listen er tom");
            }
            fjernOK = true;
            T current = denne.verdi;
            denne = denne.neste;

            return current;
        }

        // Oppgave 9
        @Override
        public void remove() {
            if (!fjernOK){
                throw new IllegalStateException("Ulovlig tilstand!");
            }

            if (iteratorendringer != endringer){
                throw new ConcurrentModificationException("Listen har blitt endret!");
            }

            fjernOK = false;            // Det skal ikke være mulig å kalle remove rett etter hverandre
            Node<T> q = hode;

            if(antall == 1){
                hode = null;
                hale = null;
            }
            else if(denne == null){             // Dersom den siste noden skal fjernes
                q = hale;
                hale = q.forrige;
                hale.neste = null;
            }
            else if(denne.forrige == null){
                hode = hode.neste;
                hode.forrige = null;
            }
            else{
                Node<T> r = hode;

                while(r.neste.neste != denne){
                    r = r.neste;
                }

                q = r.neste;
                r.neste = denne;
                denne.forrige = r;
            }
            q.verdi = null;
            q.neste = null;
            q.forrige = null;

            antall--;
            endringer++;
            iteratorendringer++;
        }
    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        int lengde = liste.antall() - 1;
        boolean sortert = false;
        if(!liste.tom()) {
            //boblesortering

            while (!sortert) {
                sortert = true;
                for (int i = 0; i < lengde; i++) {
                    T val1 = liste.hent(i);
                    T val2 = liste.hent(i+1);
                    if (c.compare(val1,val2) > 0) {
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


