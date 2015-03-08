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
