# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:

* Gaute Kjelstadli, s362066, s362066@oslomet.no
* Andre Gregussen, s364562, s364562@oslomet.no
* Niklas Havnaas, s356237, s356237@oslomet.com
* Gisle Garen, s351893, s351893@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Gaute har hatt hovedansvar for oppgave 1, 2 og 10
* Gisle har hatt hovedansvar for oppgave 3, 6 og 9. 
* Andre har hatt hovedansvar for oppgave 4 og 7. 
* Niklas har hatt hovedansvar for oppgave 5 og 8.

# Oppgavebeskrivelse

I oppgave 1 gikk jeg frem ved å følge kompendiet. funksjonen antall() skal returnere antall elementer i listen mens tom() skal sjekke om listen er tom.
Konstruktøren er forholdsvis lik som den i kompendiet, men vi må passe på å også ha peker til forrige. Videre fant jeg det første elementet som ikke
er null: her oppretter vi noden hode. Vi går gjennom resten av verdiene i t[] så lenge a[i] ikke er null. Til slutt blir halen og pekerene tilordnet.


I oppgave 2 dannet jeg en toString() og en omvendtString() metode ved hjelp av StringBuilder. Formatet jeg skulle oppnå var:"[1, 2, 3]".
Først "appendet" jeg '[' til stringbuilderen, så sjekker jeg om listen ikke er tom og lukker klammeparentesen dersom den er det. Altså: "[]".
Videre går vi inn i en while-løkke så lenge det er flere elementer å legge til. Legger til komma, mellomrom og neste verdi til vi er ferdig.
omvendtString() følger samme logikk, men starter på halen og bruker pekere til forrige node i stedet for å få omvendt rekkefølge.


I oppgave 3 begynte jeg på a) Hvor jeg skulle lage finnNode() metoden. Denne returnerer indeksen avhengig av om indeksen
er mindre eller større enn antall/2. Dette var for å effektivisere søket. Hent() returnerer verdien til finnNode(indeks), 
men pass på at indekskontrollen er false fordi vi vil ikke at indeks == antall skal være en gyldig parameter. Oppdater()
metoden passer på at vi ikke kan sette inn en null verdi og at vi finner noden til indeksen og setter pekeren p på den. 
Deretter skal vi ha en tilsvarende bytt metode bare for lenketlister og ta vare på den gamle verdien til noden samtidig
som vi bytter den gamle med den nye verdien. Til slutt returneres den gamle verdien. I subliste() 3b) skal vi lage en liste
av grensesnittet Liste. Vi har en while løkke som fortsetter så lenge p ikke er lik null. Endringer skal økes med 1 hver
iterasjon i løkken. Dersom endringer er i grensen fra til, skal vi legge til node verdien i listen. Til slutt returnerer vi
listen.


I oppgave 4 startert jeg først med å lete gjennom hele arrayet gjennom hode-noden, men skjønte fort at dette var en innefektiv algoritme.
Jeg endret dermed koden til at det var en peker på hode-noden og en på hale-noden.
ved å kontrollere om indeksen vi lette etter var større eller mindre enn antall/2.
gikk deretter frem med å legge inn en if-setning for å kontrollere verdien med indeksen.
skjønte deretter at jeg trengte en for-løkke for å loope gjennom nodene slik at hvis verdien vi lette etter ikke ble funnet gikk vi videre.
I inneholder() sjekket jeg kun om indeksen var større om null, ettersom at hvis indeksen ikke finnes vil den bli returnert som -1.


I oppgave 5 kontrollerte jeg først inputvalidering med indeksKontroll() og Objects.requireNonNull()
for å håndtere spesialtilfellene som kan oppstå. Hvis indeksen er null skal verdien legges inn først.
Deretter hvis antall er 0 vil hale være lik hode fordi listen er tom, hvis listen ikke er tom, vil den heller
lage en ny node som blir lagt først i listen. Hvis indeksen = antall skal den legges inn bakerst, hvis ingen
av ifene trer i kraft vil det si at verdien skal et sted midt i listen. Da blir finnNode(indeks) metoden kaldt
for å finne indeksen til verdien som skal legges inn. Verdien vil da bli lagt inn i listen og pekerne vil bli
opprettet i linje 173. Til slutt vil metoden inkrementere endringer++ og antall++.



I oppgave 6 begynte jeg med T fjern() metoden hvor jeg sjekker at indeksen er gyldig ved hjelp av indeksKontroll metoden i Liste 
grensesnittet. Vi har en hjelpevariabel T temp som skal oppdateres i hver if setning. Her har jeg holdt rede på de ulike situasjonene som dukker opp.
Dersom antall er lik 1, skal hode og hale = null og vi returnerer temp verdien. Dersom antall > 1 og indeks == 0, skal vi flytte hode pekeren til neste node
og sette den nye hode.forrige til null. Else if betingelsen er når siste node skal fjernes. Da danner vi to hjelpepekere p og q hvor q er pekeren på noden som skal fjernes og p er den noden som kommer rett før. Vi returnerer q.verdi og setter halen til p som er pekeren på noden som skal være halen. Deretter tilordner vi de nye pekerene til halen. Til slutt i else{} skal vi ha 3 pekere, p som er noden foran den som skal fjernes, q som er noden til den som skal fjernes og r som er foran q. Vi returnerer q.verdi igjen og p og r peker på sine respektive nye noder som vist i kode linjene 318 og 319. Antall reduseres med 1 og endringer økes med 1. Boolean fjern er veldig lik den bare at vi ikke kan bruke finnNode() metoden fordi vi har ikke indeksen. Vi bruker en while løkke som går gjennom node verdiene frem til input verdi ==q.verdi. Ellers veldig lik T fjern(). Når det gjelder effektivitet, har den bestått prøven på PCen min.


I oppgave 7 så gikk jeg frem ved å først sette pekeren på hode-noden. 
Deretter startet jeg den første metoden og sjekket jeg at p sin verdi ikke var null ettersom at den hadde vært null om nodene allerede var tomme.
hvis for-løkken går gjennom tømmer vi verdien i noden og pekerene dens og deretter går for-løkken videre.
Den andre metoden går ut på en forløkke som endrer index for hver runde hvor det gjøres et kall på "fjern()".
ved å bruke "fjern()" vil noden nullstilles og pekerene fjernes slik at det ikke var nødvendig med mer kode i for-løkken.
Avsluttningsvis vil hode- og hale-noden nullstilles, antallet blir til 0 og endringer økes.


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


I oppgave 9 begynner remove() metoden med å passe på at fjernOK ikke er false. Dette er for å hindre at remove() metoden gjentas rett etter hverandre. Vi skal også
passe på at iteratorendringer == endringer ellers vil listen bli endret når vi skal foreta endringer i listen via remove. Vi danner en hjelpe peker q = hode. Metoden er nå veldig lik de fjern metodene bare at nå må vi passe på hvor denne pekeren peker på. Dersom antall == 1, skal både hode og hale peke på null. Dersom denne == null betyr det at den siste noden skal fjernes fordi etter next() metoden vil denne automatisk flytte til neste node. Da skal q peke på hale og pekeren justeres på lignende måte som i oppgave 6. Dersom denne.forrige == null betyr det at vi er på hode noden. Gjør som i oppgave 6 igjen. Den siste else if betingelsen var veldig viktig. Dersom den ikke ble med, hadde while løkken i else{} fortsettet til uendelig. Dette er fordi i else{} har vi en r hjelpepeker som sier at dersom r.neste.neste != denne, skal den fortsette. Hvis denne er på node indeks 1, vil while løkken hoppe over den og fortsette til uendelig. Til slutt i else{} skal q noden fjernes og alle tilsvarende pekere justeres på samme måte som i oppgave 6. Antall reduseres med 1 fordi vi har 1 færre node i listen, endringer i listen har økt med 1 og til slutt har vi en iteratorendring som har økt med 1.


I oppgave 10 gikk jeg frem ved å opprette en boolsk variabel "sortert" som skal hjelpe med å sjekke om listen er sortert. 
Først sjekker jeg om listen ikke er tom, så går vi inn i en while-løkke dersom variabelen "sortert" er usann.
Når vi har gått inn i while-løkken setter vi "sortert"= true og  sammenligner to og to elementer ved hjelp av en for-løkke.
Bruker "c.compare(val1,val2) > 0" for å sjekke om rekkefølgen er riktig. Dersom det er feil rekkefølge, setter vi sortert = false
og vi bruker metoden oppdater() for å endre verdi på nodene. Til slutt avtar intervallet. - Gjentar dette til vi har en sortert liste

