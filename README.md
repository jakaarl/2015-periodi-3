# Reittihaku

[![Build Status](https://travis-ci.org/jakaarl/2015-periodi-3.svg?branch=master)](https://travis-ci.org/jakaarl/2015-periodi-3) [![Coverage Status](https://coveralls.io/repos/jakaarl/2015-periodi-3/badge.svg)](https://coveralls.io/r/jakaarl/2015-periodi-3)

## Ratkaistava ongelma

Kulkeminen tähdeltä A tähdelle B lyhintä reittiä, kun tähtikartasta muodostetaan verkko yhdistämällä tietyn raja-arvon alittavan etäisyyden päässä toisistaan olevat tähdet. Yhden tähtienvälisen "hypyn" kustannus on vakio.

## Suorittaminen

Annetaan JSON-muotoinen tähtikartta (ks. `tira.input.JsonStarListReader`), aloitustähti, sekä kohdetähti. Projektin resources-hakemistoista löytyy esimerkkejä JSON-tiedostoista. Suoritettava luokka on `tira.commandline.RouteFinderLauncher`. Sen voi suorittaa komennolla `java -jar routefinder/target/routefinder-<version>-jar-with-dependencies.jar` olettaen, että työhakemisto on projektin juurihakemisto; `<version>` tulee korvata versiolla, esim. "0.1-SNAPSHOT").

## Algoritmeista

Tähtikartasta on rakennettava verkko, jossa yhteydet luodaan annetun maksimietäisyyden päässä toisistaan olevien tähtien välille. Naiivilla algoritmillä verkon alustaminen olisi melko hidas operaatio (tasoa O(n^2)), joten optimoinnille olisi tilausta - tosin kertaluontoisuutensa takia tämä ei ole kokonaisuuden kannalta kovin tärkeää).

Reittien laskemisessa voidaan kokeilla useampi algoritmeja, mikäli aikaa riittää. Yksinkertainen ja toimiva valinta on Breadth-First Search (BFS), jollaiseksi vakioetäisyyksien johdosta myös Dijkstran algoritmi käytännössä muuttuu. BFS:n aikavaativuus on pahimmassa tapauksessa luokkaa O(n^2), samoin kuin A\*:in, mutta jälkimmäinen on tyypillisesti nopeampi. 

