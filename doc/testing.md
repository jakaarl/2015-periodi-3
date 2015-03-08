# Testaus

## Yksikkötestit

Sovelluksen yksikkötestit sijaitsevat hakemistossa `routefinder/src/test/java`
ja ovat tyypillisiä JUnit-yksikkötestejä. Testit pyrkivät kattamaan
oleellisimman sovelluslogiikan, kattavuus on noin 80%.

## Suorituskykytestit

Modulissa `perftest` sijaitsee muutama apuluokka, sekä suorituskykytestit (myös
toteutettu JUnitia käyttäen) luokille `JsonStarListReader` sekä `StarMap`.
Isommat, 10000 tähden massaa käsittelevät testit on oletusarvoisesti poistettu
käytöstä @Ignore -annotaatiolla. Niiden suorittamiseksi tulisi annotaatioiden
poistamisen lisäksi lisätä käytössä olevan muistin määrää, jotta JVM:n
roskienkeruu ei hallitse suoritusaikaa.

## Testiresurssit

Testaamista varten on testidataa kummankin modulin alla hakemistossa
`src/test/resources`. JSON-tiedostot sisältävät tähtilistauksia niitä lukevan
`JsonStarListReader` -luokan ymmärtämässä muodossa.

## Testien suorittaminen

Testit on helpointa suorittaa komentoriviltä Mavenilla, `mvn test`, tai
IDE:stä. Tarkemmat ohjeet suorittamiseen löytyvät käyttöohjeesta.

## Suorituskykytestin tulokset

JsonStarListReaderPerfTest, 10 suorituskerran keskiarvo:
 * n=12, 5 ms
 * n=100, 14 ms
 * n=1000, 127 ms
 * n=10000, 10469 ms

StarMapPerfTest, 10 suorituskerran keskiarvo:
 * n=12, ~0 ms
 * n=100, 2 ms
 * n=1000, 103 ms
 * n=10000, 10449 ms

Suoritusajoissa oli pientä varianssia eri ajokerroilla, mutta suuruusluokat
pysyivät jokseenkin samoina. Yksittäisellä ajolla ei ollut juurikaan eroa
kymmenen ajon keskiarvoon.