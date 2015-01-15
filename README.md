# Reittihaku

## Ratkaistava ongelma

Levittäytyminen tähtikartalla mahdollisimman laajalti annetussa aikarajassa. Ylitettävissä oleva tähtien välimatka on rajallinen, joten päästäkseen tähdeltä A tähdelle B on usein tarpeen kulkea kiertotietä. Aikaa mitataan "vuoroissa", yhdessä vuorossa voi:
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

## Toteutuksesta

Käytetyt algoritmit yms. eivät ole kiveen kirjoitettuja. Päällimmäinen ajatus on soveltaa A* -algoritmia.
