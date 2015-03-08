# Toteutus

## Ohjelman rakenne

Projekti on jaettu ylätasolla kolmeen hakemistoon:
 * doc - dokumentaatio
 * perftest - suorituskykytestit
 * routefinder - itse ohjelma ja sen yksikkötestit

Hakemistojen `perftest` ja `routefinder` rakenne on Maven-käytännön
mukainen, oleellisimpien alihakemistojen ollessa:
 * src/main/java - varsinainen ohjelmakoodi
 * src/test/java - testikoodi
 * src/main/resources - ajonaikaiset resurssit
 * src/test/resources - testiresurssit

Maven-projektirakenne on toteutettu siten, että juurihakemistossa sijaitsee
POM-tiedosto, joka määrittelee alihakemistot `perftest`ja `routefinder`
moduleikseen. Näin juurihakemistossa ajetut Maven-komennot suoritetaan
kummallekin modulille, ja modulien hakemistoissa ajettuina komennot koskevat
vain kyseistä modulia.

### Pakkaukset ja luokat

Sovellus on jaettu useampaan pakkaukseen:
 * tira.collections - tietorakennetoteutukset
 * tira.commandline - käynnistyskomennot
 * tira.domain - sovelluksen omia tietotyyppejä
 * tira.input - JSON-syötteen käsittely
 * tira.navigation - reitinhakulogiikka ja sen käyttämiä tietotyyppejä
 * tira.visualization.starmap - alkeelliselle asteelle jäänyt visualisointi

Testiluokat ovat testattavia luokkia vastaavissa pakkauksissa.
Lisäksi `perftest` -modulin paketissa `tira.perftest` on pari luokkaa
suorituskykytestaamista varten, sekä yksikkötesti niille. 

