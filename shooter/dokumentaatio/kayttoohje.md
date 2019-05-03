# Käyttöohje
## Konfigurointi
Ohjelma vaatii toimiakseen, että käynnistyshakemistossa on tiedosto _config.properties_. Tiedostossa määritellään mm. näytön koko (leveys ja pituus pikseleinä), käytettävän tietokannan nimi, sekä vihollisten määrään liittyvät luvut. Luo tiedosto _config.properties_ ja kopioi siihen seuraava: 
```
# Screen

width=1920
height=1080

# Database

database=jdbc:sqlite:leaderboard.db

# Starting difficulty

waves=2
enemiesInWave=2
interval=7

# How much the number of enemies increases per level

wavesIncreaseBy=1
enemiesInWaveIncreaseBy=1
```
Tiedoston arvoja voi vapaasti muuttaa, mutta on tärkeää, että näytön resoluutio on oikein, jotta ohjelma osaa laskea mm. vihollisten ilmestymispaikat oikein. Tarkista siis tarvittaessa näyttösi resoluutio koneesi asetuksista. Jos haluat muuttaa tietokannan nimeä, korvaa kohta _leaderboard.db_, jollain toisella (_.db_:hen päättyvällä) nimellä, mutta älä koske rivin muuhun tekstiin. 

Kohdassa _Starting difficulty_ määritellään ensimmäisen tason vaikeus. Kohta _waves_ kertoo montako "vihollisaaltoa" ilmestyy ja _enemiesInWave_ montako vihollista yhdessä aallossa on. _Interval_ puolestaan määrittelee aikavälin vihollisaaltojen ilmestymiselle sekunteina. 

_wavesIncreaseBy_ kertoo kuinka monella vihollisaallolla seuraavan tason aaltojen määrä kasvaa ja _enemiesInWaveIncreasyBy_ vastaavasti kuinka monella vihollisella yksittäisen aallon vihollismäärä kasvaa. Jos haluat muuttaa numeerisia arvoja, käytä pelkkiä positiivisia kokonaislukuja. 


## Ohjelman käynnistäminen
Ohjelman voi käynnistää tuplaklikkaamalla tiedostoa _Shooter.jar_, jos viikon 7 Release:n jar-tiedosto on ladattu. Voi olla, että aluksi asetuksista täytyy antaa lupa sovelluksen suorittamiselle. Käynnistäminen onnistuu myös komennolla
```
java -jar Shooter.jar
```
## Aloitusnäyttö
Sovelluskäynnistyy aloitusnäyttöön 
## Kontrollit
Pelihahmo liikkuu eri ilmansuuntiin W,A,S ja D-näppäimillä ja juokseminen tapahtuu
pitämällä SHIFT-näppäintä pohjassa. Klikkaamalla hiirtä pelaaja voi ampua kursorin osoittamaan suuntaan.  

## Pelin kulku
Sovelluksen käynnistyttyä valitaan New Game, jonka jälkeen aukeaa ikkuna, jossa pelaajalle annetaan nimi. Tämän jälkeen itse peli alkaa. Hirviöt liikkuvat kohti pelaajaa ja pelaajan tehtävänä on ampua ne. Jos hirviö osuu pelaajaan, pelaajan hp laskee. Osuman jälkeen on 2 sekunin aikaikkuna, jolloin peli ei rekisteröi uusia osumia pelaajaan. Mikäli pelaajaan osutaan liian monta kertaa pelin häviää. Jos pelaaja onnistuu ampumaan kaikki tason hirviöt, aukeaa ikkunna, josta voi siirtyä seuraavalle tasolle klikkaamalla Next level!-nappia tai lopettaa pelin klikkaamalla Quit game-nappia. Jos pelaaja häviää pelin, täytyy peliruutu sulkea raksista  
