Raportti 3
==========

Tällä viikolla tehtyä:
- lisää testejä (mm. JSON-importille)
- pienoisia lisäyksiä doc commentteihin
- ArrayList-toteutus, hieman kesken
 - ainakin iteraatio tökkii
 - runsaasti testejä
- karkea StarMap-visualisaatio aloitettu

Collections frameworkin rajapintojen käyttäminen osoittautui huonoksi ratkaisuksi: metodeja on suuri määrä, eivätkä kaikki ole tarpeen. Tästä koituu paljon turhaa työtä. Olisi ollut järkevämpää määritellä rajapinta, jossa on vain tarvittavat operaatiot, ja kirjoittaa aluksi luokkakirjaston luokille delegoiva toteutus.

Seuraavan viikon tavoitteet:
- ArrayList-toteutuksen saaminen valmiiksi
 - Queue-toteutuksen aloittaminen
- priorisointialgoritmin alustava/karkea toteutus
- komentoriviltä ajaminen JSON-syötetiedoston ja annettujen parametrien perusteella
 - esim. luodaan materiaalista verkko, annetaan alku- ja loppupiste, ohjelma tulostaa reitin
- visualisaation parantelemista (ehkä JavaFX:ää käyttäen?)
