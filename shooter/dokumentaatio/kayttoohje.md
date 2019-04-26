# Käyttöohje
## Ohjelman käynnistäminen
Ohjelman voi käynnistää tuplaklikkaamalla tiedostoa _Shooter.jar_, jos viikon 6 Release:n jar-tiedosto on ladattu. Voi olla, että aluksi asetuksista täytyy antaa lupa sovelluksen suorittamiselle. Käynnistäminen onnistuu myös komennolla
```
java -jar Shooter.jar
```

## Kontrollit
Pelihahmo liikkuu eri ilmansuuntiin W,A,S ja D-näppäimillä ja juokseminen tapahtuu
pitämällä SHIFT-näppäintä pohjassa. Klikkaamalla hiirtä pelaaja voi ampua kursorin osoittamaan suuntaan.  

## Pelin kulku
Sovelluksen käynnistyttyä valitaan New Game, jonka jälkeen aukeaa ikkuna, jossa pelaajalle annetaan nimi. Tämän jälkeen itse peli alkaa. Hirviöt liikkuvat kohti pelaajaa ja pelaajan tehtävänä on ampua ne. Jos hirviö osuu pelaajaan, pelaajan hp laskee. Osuman jälkeen on 2 sekunin aikaikkuna, jolloin peli ei rekisteröi uusia osumia pelaajaan. Mikäli pelaajaan osutaan liian monta kertaa pelin häviää. Jos pelaaja onnistuu ampumaan kaikki tason hirviöt, aukeaa ikkunna, josta voi siirtyä seuraavalle tasolle klikkaamalla Next level!-nappia tai lopettaa pelin klikkaamalla Quit game-nappia. Jos pelaaja häviää pelin, täytyy peliruutu sulkea raksista  
