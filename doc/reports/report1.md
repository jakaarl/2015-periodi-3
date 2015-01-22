Raportti 1
==========

Ensimmäisellä viikolla perustin GitHub-projektin ja kirjoitin README-tiedostoon löyhän määritelmän. Toisella viikolla aloitin varsinaisen toteutustyön:
- joitakin domain-luokkia
- etäisyyksien laskenta
- verkon rakentaminen tähtilistasta
- JSON-tähtilistauksen import (kesken)
- joitakin testejä
- Travis CI:n käyttöönotto

Mitään varsinaisesti uutta ei ole tullut eteen; lähinnä olen lueskellut kertausmielessä algoritmeista, mm. Minimum Spannin Tree (Primin algoritmi), reittihaut (BFS, A*). Verkon rakentamiseen yritin löytää tehokkaampia algoritmeja, koska "intuitiivinen ratkaisu" on vähintäänkin luokkaa O(n^2), mutta en kevyellä googlaamisella löytänyt mitään soveltuvaa. Koska kyseessä kuitenkin on kertaluontoinen alustusoperaatio, en investoinut ongelmaan paljoa aikaa/energiaa. Ad hoc -henkisenä ratkaisuna mieleeni tuli tähtien jakaminen ruutuihin/kuutioihin, joiden koko on sama, kuin suurin ylitettävissä oleva tähtien välimatka - näin tähden "yhteyksiä" luodessa täytyisi tarkistaa vain sama ja viereiset ruudut/kuutiot. Toisaalta, itse jaoittelu olisi suhteellisen työläs, joten siitä olisi todennäköisesti hyötyä vasta suuria tähtikarttoja käyttäessä. Koska viidakon sananlaskun mukaan ennenaikainen optimointi on kaiken pahan alku ja juuri, en toistaiseksi alkanut optimoimaan verkon alustamista.

Seuraavan viikon alustavat tavoitteena:
- JSON-importin saaminen valmiiksi
- joko reitinhaku- tai priorisointialgoritmin alustava toteutus
- Coverallsin käyttöönotto testikattavuuden tarkkailemiseksi

