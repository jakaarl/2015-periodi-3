Raportti 2
==========

Toinen viikko osoittautui haasteelliseksi: poskiontelontulehdus ja lasten sairastelu eivät tarkalleen ottaen lisänneet intoa, aikaa tai energiaa projektin edistämiseksi.

Viikon (vähäiset) saavutukset ovat:
- JSON-import tiedostosta (kaipaa enemmän testaamista)
- reitinhaku breadth-first searchilla
- Coverallsin käyttöönotto
- doc commenttien lisäilyä/parantelua

Pohtiessani määrittelyä ja algoritmeja, tulin tajunneeksi, ettei A* taida olla kovinkaan hyvä ratkaisu, koska verkon yhteyksien "pituus" tai "paino" on vakio etäisyydestä riippumatta. Myös heuristiikkafunktio olisi ongelmallinen, koska varsinaisella etäisyydellä ei ole väliä, vaan perusteltu minimiarvo yhteyden painolle olisi sama, kuin vakiopaino. Siispä harkitsin Dijkstran algoritmia, mutta:
"Breadth-first search can be viewed as a special-case of Dijkstra's algorithm on unweighted graphs, where the priority queue degenerates into a FIFO queue." Tämän Wikipedia-lainauksen valossa onkin perusteltua käyttää aluksi reitinhakuun BFS:ää. Jatkossa voidaan vertailun vuoksi kenties toteuttaa muita.

Seuraavan viikon alustavat tavoitteet:
- priorisointialgoritmin alustava/karkea toteutus
- komentoriviltä ajaminen JSON-syötetiedoston ja annettujen parametrien perusteella
 - esim. luodaan materiaalista verkko, annetaan alku- ja loppupiste, ohjelma tulostaa reitin
- jonkin sovelluksessa käytetyn tietorakenteen toteuttaminen itse
- jos jokin ihmeellinen inspiraatio- ja energiapuuska iskee, kenties alkeellinen visualisaatio?
