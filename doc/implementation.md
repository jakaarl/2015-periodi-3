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

## Aika- ja tilavaativuus

## Suorituskyky

## Puutteet ja parannusideat
