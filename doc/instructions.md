# Käyttöohje

## Järjestelmävaatimukset

 * JDK 1.7 tai 1.8 (testattu Oracle ja OpenJDK, Linux ja Windows)
 * Maven 3 (testattu versiolla 3.1.1)

## Kääntäminen

Koko projekti, mukaan lukien suorituskykytestit, kääntyy suorittamalla
projektin juurihakemistossa komennon `mvn compile`. Tämä ei luo JAR-pakettia,
vaan sen luomiseksi on suoritettava `mvn package` tai `mvn install`
(jälkimmäinen asentaa JAR-paketin käyttäjän paikalliseen repositoryyn).

## Testit

Testit on kirjoitettu käyttäen JUnit-testikirjastoa.
Yksikkötestit sijaitsevat modulin `routefinder` hakemistossa `src/test/java`.
Ne suoritetaan ajamalla `routefinder`-hakemistossa komento `mvn test`.
Yhteenveto testituloksista tulostuu näytölle, ja raportit XML- ja
tekstimuodossa kirjoittuvat hakemiston `target/surefire-reports` alle.

Suorituskykytestit ovat `perftest`-modulissa ja ne ajetaan samalla tavoin, kuin
yksikkötestit. Modulihakemistoon ilmestyy `*PerfTest` -päätteiset
tekstitiedostot, joissa kuvataan lyhyesti ajetut testit ja niihin kulunut aika.
Suurella tietomassalla ajettavat testit on oletusarvoisesti poistettu käytöstä,
ne saa ajettua poistamalla @Ignore -annotaatiot. Niitä ajaessa kannattaa antaa
Mavenille lisää muistia käytettäväksi, jotta VM:n roskienkeruu ei dominoisi
suoritusaikaa, tyyliin `MAVEN_OPTS="-Xms768m -Xmx1280m" mvn test`.

## Suorittaminen

Kääntäessä projekti komennolla `mvn package` (tai `mvn install`) syntyy
`routefinder/target` -hakemistoon 2 JAR-pakettia: "tavallinen" JAR, sekä kaikki
riippuvuudet sisältävä JAR, joka on helposti suoritettavissa komentoriviltä:
```
java -jar routefinder/target/routefinder-0.1-SNAPSHOT-jar-with-dependencies.jar
<jsonFile> <fromStar> <toStar>
```
(argumentit samalle riville; argumentin sisältäessä välilyöntejä tms.
komentorivitulkille merkittävää, argumentin tulee olla lainausmerkeissä)

### Syötteet

 * `jsonFile` - JSON-muotoinen tähtikartta (ks. luokka `JsonStarListReader`)
 * `fromStar` - lähtötähti
 * `toStar` - kohdetähti

### Tuloste

Ohjelma tulostaa reitin, mukaan lukien lähtö- ja kohdetähdet, näytölle tähti
per rivi. Mikäli reittiä ei löydy, tulostuu "No route found.".
