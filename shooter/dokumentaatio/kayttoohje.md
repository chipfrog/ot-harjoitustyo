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
Sovellus käynnistyy seuraavaan aloitusnäyttöön. Painamalla _New Game_ päästään antamaan pelaajalle nimi ja aloittamaan uusi peli.

<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/mainmenu.png">

## Pelihahmon luominen
Pelihahmolle täytyy antaa 4-15 merkin mittainen nimi. Jos nimi löytyy jo tietokannasta, uutta pistetulosta ei tallennetta kyseiselle nimelle, ellei se ole aiempaa tulosta korkeampi. Peli aloitetaan painamalla _Start!_

<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/newgame.png">

## Pelinäkymä
Pelinäkymä aukeaa koko ruudun tilaan, josta päästään tarvittaessa pois _Esc_:llä. 

<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/gameplay.png">

## Kontrollit
Pelihahmo liikkuu eri ilmansuuntiin W,A,S ja D-näppäimillä ja juokseminen tapahtuu
pitämällä SHIFT-näppäintä pohjassa. Klikkaamalla hiirtä pelaaja voi ampua kursorin osoittamaan suuntaan.  

## Pelin kulku
Hirviöt liikkuvat kohti pelaajaa ja pelaajan tehtävänä on ampua ne. Jos hirviö osuu pelaajaan, pelaajan hp laskee. Osuman jälkeen on 2 sekunin aikaikkuna, jolloin peli ei rekisteröi uusia osumia pelaajaan. Mikäli pelaajaan osutaan liian monta kertaa ja hp laskee nollaan pelin häviää. Jos pelaaja onnistuu ampumaan kaikki tason hirviöt, seuraavalle tasolle voi siirtyä klikkaamalla _Next level!_-nappia tai lopettaa pelin klikkaamalla _Quit game_ -nappia. _Quit game_ palauttaa käyttäjän takaisin aloitusnäyttöön. Painettaessa _Quit game_ -nappia tallennettaan myös pelaajan sen hetkinen pistemäärä. Seuraavassa pelissä aloitetaan taas nollasta pisteestä, vaikka käytettäisiin samannimistä hahmoa.

<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/nextlevel.png">

## Leaderboard
Valitsemalla aloitusnäytöltä _Leaderboard_ päästään tarkastelemaan pelaajien pistetuloksia. Tuloksia voidaan järjestää nimen mukaan aakkosjärjestyksessä klikkaamalla kohtaa _Name_ ja pisteiden mukaan klikkaamalla _Score_. Aluksi taulukko on järjestetty pistetulosten mukaan suurimmasta pienimpään. _Back_ -napista päästään takaisin aloitusnäyttöön ja _Delete_ napista voidaan tyhjentää tietokanta. Tyhjentämisen vaikutus näkyy vasta, kun leaderboard näkymästä poistutaan ja tullaan siihen takaisin. Tyhjentämistä ei voi perua. 

<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/leaderboard.png">
