# Määrittely

## Ratkaistava ongelma

Ohjelma laskee lyhyimmän reitin tähdeltä A tähdelle B, kun tähtien väliset yhteydet ovat pituudeltaan/painoltaan vakiot. Yhteydet muodostetaan etäisyyden perusteella: tähdet, joiden välimatka on korkeintaan yhtä paljon, kuin annettu kynnysetäisyys.

## Käytettävät algoritmit ja tietorakenteet

Oletusarvoisesti toteutetaan Breadth-First Search (BFS), sekä toivottavasti ajan ja energian salliessa myös A*. BFS:n toteuttamiseen tarvitaan jono (queue), lista (list), sekä assosiatiivinen lista (map). Nämä on toteutettu linkitetyllä listalla (linked list), dynaamisella taulukolla (array list), sekä hajautustaululla (hash map).

## Syötteet

Ohjelma ottaa syötteenään JSON-formaatissa annetun listan tähdistä koordinaatteineen, lähtötähden nimen, sekä kohdetähden nimen.
