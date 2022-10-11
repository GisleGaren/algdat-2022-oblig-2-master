# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:

* Gaute Kjelstadli, 
* Andre Gregussen, 
* Niklas Havnaas, s356237, s356237@oslomet.com
* Gisle Garen, 

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Per har hatt hovedansvar for oppgave 1, 3, og 5. 
* Else har hatt hovedansvar for oppgave 2, 4, og 6. 
* Fatima har hatt hovedansvar for oppgave 7 og 8. 
* Vi har i fellesskap løst oppgave 10. 

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å 


I oppgave 2 så brukte vi en til å


I oppgave 3 så gikk vi frem ved å 


I oppgave 4 så gikk vi frem ved å 


I oppgave 5 kontrollerte jeg først inputvalidering med indeksKontroll() og Objects.requireNonNull()
for å håndtere spesialtilfellene som kan oppstå. Hvis indeksen er null skal verdien legges inn først.
Deretter hvis antall er 0 vil hale være lik hode fordi listen er tom, hvis listen ikke er tom, vil den heller
lage en ny node som blir lagt først i listen. Hvis indeksen = antall skal den legges inn bakerst, hvis ingen
av ifene trer i kraft vil det si at verdien skal et sted midt i listen. Da blir finnNode(indeks) metoden kaldt
for å finne indeksen til verdien som skal legges inn. Verdien vil da bli lagt inn i listen og pekerne vil bli
opprettet i linje 173. Til slutt vil metoden inkrementere endringer++ og antall++.



I oppgave 6 så gikk vi frem ved å 


I oppgave 7 så gikk vi frem ved å 


I oppgave 8 startet jeg med deloppgave a og lagde metoden T next().
Hvis iteratorendringer er ulik endringer ville metoden kaste en
ConcurrentModificationException. Dermed skal koden sjekke om listen er tom
med å kalle metoden hasNext(), vis hasNext() er false vil koden kaste en 
NoSuchElementException fordi den er tom og ikke har noen next. Etter det
settes fjernK til sann, vedrien current returneres og denne blir flyttet
til denne sin neste i listen. Deloopgave b ble løst bare ved å returnere et
kall fra DobbeltLenketListeIterator(). Samme som i oppgave 8 d) bare at den
tok inn indeksen som parameter. I oppgave 8 c) startet jeg med indeksKontroll()
for å luke ut feiltilfeller. Deretter brukte jeg metoden finnNode(indeks) for
å sette 'denne' pekeren til den anngående indeksen på den tilhørende noden.
resten var likt som den andre konstruktøren.


I oppgave 9 så gikk vi frem ved å 


I oppgave 10 så gikk vi frem ved å 


