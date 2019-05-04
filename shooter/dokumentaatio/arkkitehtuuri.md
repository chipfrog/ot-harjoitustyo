# Arkkitehtuuri

## Rakenne

## Käyttöliittymä

Käyttöliittymä koostuu neljästä näkymästä:
- aloitusikkuna
- uuden pelaajan luominen
- peliruutu
- leaderboard

Jokaisella näkymällä on oma Scene-olionsa, joista aina yksi on sijoitettu sovelluksen Stageen. Jokaista näkymää vastaa myös oma luokka, jossa näkymän ulkoasu ja toiminnot, kuten napit ja tekstikentät määritellään. Käyttöliittymään liittyvät luokat sijaitsevat kansiossa shooter.ui. Käyttöliittymä (ja koko sovellus) käynnistyy luokassa shooter.ui.MainMenu. Näkymien vaihtamisesta vastaa SceneSwitcher-luokka, jonka muut käyttöliittymäluokat MainMenu:a luukuunottamatta perivät.

Pelin animaatiot (peliobjektien liikkeet ruudulla, tekstien vaihtuminen) tapahtuvat GameWindow-luokassa ja niiden toteutukseen käytetään JavaFX:n AnimationTimeria. GameWindow-luokan yhteys pelin loogisiin rakenteiseiin rajoittuu GameLogic-luokan välttämättömien metodien kutsuun, eli toimintoihin joita kutsutaan AnimationTimerin sisällä, kuten mm. objektien liikkuminen, kuolleiden objektien poistaminen, pisteiden, hitpointsien muuttaminen ja pelin pysäyttäminen. Lisäksi luokalla on yhteys PlayerDao-luokkaan, jonka kautta tietokantaan voidaan tallentaa pelitulokset. Myös leaderboard-luokka käyttää PlayerDao:a tulosten hakemiseen tietokannasta. Muutoin sovelluslogiikka on pitkälti eriytetty käyttöliittymästä. 

## Sovelluslogiikka

Sovelluksen toiminnallisista ominaisuuksista vastaavat pääasiassa luokat GameLogic, LevelHandler, Level ja PlayerMovement. PlayerMovement sisältää metodit pelihahmon ohjaamiseen näppäimistön ja hiiren avulla. Level-luokka vastaa luotavasta tasosta. Siinä määritellään yhtä pelitasoa kohti luotavien vihollisten määrä, sekä tyyppi ja seurataan onko tason kaikki viholliset tuhottu.

LevelHandler-luokka vastaa uusien tasojen (level) luomisesta ja seuraa millä tasolla pelaaja on. Luokassa _GameLogic_ kootaan pelin toiminalliset luokat ja metodit yhteen. Se sisältää metodit esim. kuolleiden vihollisten poistamiseen peliruudusta, sekä AnimationTimerin aikana suoritettavat peliruudun päivitykset (pelihahmojen liikkeiden kutsuminen, pisteiden päivittäminen, törmäysten tarkistaminen). AnimationTimerin aikana kutsuttavat metodit on koottu _GameLogic_ -luokassa metodin update() alle. _GameLogic_ -luokalla on myös joitakin luokkia alustavia metodeja.

Luokat _GameObject_, _Enemy_, ja _Bullet_ kuvaavat peliruudulla liikkuvia objekteja ja näiden metodeja. _GameObject_ on em. luokkien yläluokka, joka sisältää metodit objektien törmäyksen tarkistamiseen ja objektin tilan (kuollut/elossa) asettamiseen. _Enemy_ - ja _Bullet_ - luokkien konstruktoreissa määritellään attribuutit, kuten nopeus, hitpointsit, pelihahmon kuva jne.

_Player_ -luokka sisältää pelaajan attribuutit ja tietokantaan tallennettavat tiedot, eli pelihahmon nimen ja pisteet. Lisäksi sillä on joitakin omia metodeja.  

## Tietojen tallennus

Pakkauksen _shooter.db_ luokat _Database_, _Dao_ ja _PlayerDao_ vastaavat tietojen pysyväistallennuksesta tietokantaan. _Database_ -luokalla luodaan uusi tietokantataulu, mikäli sellaista ei ole olemassa ja muodostetaan tauluun yhteys. _Dao_ toimii rajapintana _PlayerDao_ -luokalle, jossa määritellään tarkemmin metodit tietokannan käsittelyyn. Suunnittelu noudattaa Data Access Object-mallia. Tietokantajärjestelmänä käytetään SQLiteä. 

## Kongfigurointi 



### Luokkakaavio
<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/kansiorakenne.png">

### Sekvenssikaavio
<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/sekvenssikaavio.png">
Kuvassa tilanne, jossa pelaajan ammus osuu hirviöön ensimmäisen kerran ja antaa pelaajalle pisteen.
