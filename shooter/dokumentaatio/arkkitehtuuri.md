# Arkkitehtuuri

## Rakenne

## Käyttöliittymä

Käyttöliittymä tulee koostumaan viidestä näkymästä:
-aloitusikkuna
-uuden pelaajan luominen
-peliruutu
-seuraavalle tasolle siirtyminen/pelin lopettaminen
-leaderboard

Tällä hetkellä kaikki käyttöliittymän ikkunat sijaitsevat samassa luokassa omina metodeinaan. Jokaista ikkunaa varten luodaan uusi Scene- ja Stage-olio. Ajan salliessa käyttöliittymän ikkunat tullaan jakamaan omiksi luokikseen.  

## Sovelluslogiikka

Sovelluksen toiminnallisista ominaisuuksista vastaavat pääasiassa luokat GameLogic, LevelHandler, Level ja PlayerMovement. PlayerMovement sisältää metodit pelihahmon liikuttamiseen ruudulla ja pelaajan ampumien ammusten liikeratojen laskemiseen. Level-luokka vastaa luotavasta tasosta. Siinä määritellään yhtä pelitasoa kohti luotavien vihollisten määrä, sekä tyyppi ja seurataan onko tason kaikki viholliset tuhottu.

LevelHandler-luokka vastaa uusien tasojen (level) luomisesta ja seuraa millä tasolla pelaaja on. Luokassa GameLogic kootaan pelin toiminalliset luokat ja metodit yhteen. Se sisältää metodit esim. kuolleiden vihollisten poistamiseen peliruudusta, sekä AnimationTimerin aikana suoritettavat peliruudun päivitykset (pelihahmojen liikkeiden kutsuminen, pisteiden päivittäminen, törmäysten tarkistaminen).

Luokat GameObject, Enemy, ja Bullet kuvaavat peliruudulla liikkuvia objekteja ja näiden metodeja. GameObject on em. luokkien yläluokka, joka sisältää metodit objektien törmäyksen tarkistamiseen ja objektin tilan (kuollut/elossa) asettamiseen.

Player-luokka sisältää pelaajan attribuutit ja tietokantaan tallennettavat tiedot, eli pelihahmon nimen ja pisteet. Lisäksi sillä on joitakin omia metodeja.  


### Luokkakaavio
<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/kansiorakenne.png">

### Sekvenssikaavio
<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/sekvenssikaavio.png">
Kuvassa tilanne, jossa pelaajan ammus osuu hirviöön ensimmäisen kerran ja antaa pelaajalle pisteen.
