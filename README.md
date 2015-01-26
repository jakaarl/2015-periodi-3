# Reittihaku

[![Build Status](https://travis-ci.org/jakaarl/2015-periodi-3.svg?branch=master)](https://travis-ci.org/jakaarl/2015-periodi-3) [![Coverage Status](https://coveralls.io/repos/jakaarl/2015-periodi-3/badge.svg)](https://coveralls.io/r/jakaarl/2015-periodi-3)

## Ratkaistava ongelma

Levittäytyminen tähtikartalla mahdollisimman laajalti annetussa aikarajassa. Ylitettävissä oleva välimatka on rajallinen, joten päästäkseen tähdeltä A tähdelle B on usein tarpeen kulkea kiertotietä. Aikaa mitataan "vuoroissa", yhdessä vuorossa voi:
- kulkea avaruusaluksella yhden "hypyn" tähtien välillä
 - "kantama" on yksi hyppy siirtokunnasta tai tukikohdasta
- rakentaa avaruusaluksen siirtokunnassa
- perustaa uuden siirtokunnan; tämä "käyttää loppuun" avaruusaluksen
 - vaatii, että tähdellä on väh. 1 asutettava planeetta
- perustaa uuden tukikohdan; tämäkin "käyttää" avaruusaluksen
 - vaatii, että tähdellä on väh. 1 planeetta - ei tarvitse olla asutettava tai omata mineraaleja

Ainakin aluksi siirtokunta sijoittuu tiettyyn aurinkokunnalle, ei tietylle planeetalle. Siirtokunnan perustaminen vaatii aurinkokunnasta planeetan, joka on asutettavissa. Avaruusalusten rakentaminen vaatii siirtokunnalta mineraaleja. Mineraalit ja asutettavuus ovat boolean-henkisiä "lippuja": kullakin planeetalla voi olla toinen tai kumpikin - tai ei kumpaakaan.

### Alkutilanne

Annetaan aloitussiirtokunnan ("kotiplaneetan") koordinaatit, sekä tähtikartta. Tähtikartta sisältää tähtien koordinaatit, sekä planeettakunnan tiedot (planeettojen määrä ja kunkin mahdollisen asutettavuus- ja mineraaliominaisuuden). Aloitussiirtokunnassa on yksi avaruusalus.

## Algoritmeista

Tähtikartasta on rakennettava verkko, jossa yhteydet luodaan annetun maksimietäisyyden päässä toisistaan olevien tähtien välille. Naiivilla algoritmillä verkon alustaminen olisi melko hidas operaatio (tasoa O(n^2)), joten optimoinnille olisi tilausta (tosin kertaluontoisuutensa takia tämä ei ole kokonaisuuden kannalta kovin tärkeää).

Asutettavat tähdet valitaan priorisoinnin perusteella, priorisointialgoritmi voisi toimia pääpiirteittäin näin:
- etäisyys aloitustähdestä laskee prioriteettia
 - oletuksena, että AI-pelaaja haluaa "puskuria" aloitustähtensä ympärille
 - siirtokunnat oletettavasti kehittyvät "paremmiksi" pelin kuluessa, joten mahdollisimman aikainen siirtokunnan perustaminen tuo etua
- asutettavat planeetat nostavat prioriteettia
- mineraalivaroja omaavat planeetat nostavat prioriteettia
- tähden saavuttamiseksi vaadittavat uudet tukikohdat/siirtokunnat laskevat prioriteettia, koska ne lisäävät tähden saavuttamisen "kustannuksia" (aika, resurssit)

Avaruusalusten reittien laskemiseen voidaan käyttää esim. A* -algoritmia (O(n^2) tai huonompi, riippuen verkon yhteyksien määrästä). Heuristiikkafunktiona voisi luontevasti toimia koordinaattien välinen suora etäisyys.

