package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////

// Test 123
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


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
        throw new UnsupportedOperationException();
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
    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
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

        //Indekskontroll?

        Node<T> node = new Node<>(verdi);

        if (tom()) {
            hode = hale = node;
            antall++;
            endringer++;
            return true;
        } else {
            node = hale.forrige;
            hale = node.neste;
            node = hale;
            antall++;
            endringer++;
            return true;
        }
        //throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        int elementer = liste.antall();

        if(!liste.tom()) {
            //boblesortering
            int mi = 0;
            T mv = liste.hent(0);

            for (int n = elementer; n > 1; n--) {           //Intervallgrense
                for (int i = 1; i < n; i++) {
                    if (c.compare(liste.hent(i - 1), liste.hent(i)) > 0) {
                        mv = liste.hent(i);
                        mi = i;
                    }
                    T tempP = liste.hent(mi);
                    T tempQ = liste.hent(i-1);
                    liste.oppdater(mi, tempQ);
                    liste.oppdater(i-1,mv);
                }
            }
        }
        // throw new UnsupportedOperationException();
    }


} // class DobbeltLenketListe


