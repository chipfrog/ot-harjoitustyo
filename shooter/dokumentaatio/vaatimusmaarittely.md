# Vaatimusmäärittely
## Sovelluksen tarkoitus

Kyseessä on ns. Top-Down Shooter eli lintuperspektiivistä kuvattu 2D-ampumispeli, jossa tarkoituksena on ampua pelaajaa lähestyvät hirviöt ja väistellä hirviöiden hyökkäyksiä. Jos pelaaja saa ammuttua kaikki hirviöt läpäisee hän tason ja voi siirtyä seuraavalle vaikeammalle tasolle. Jos taas hirviöt onnistuvat hyökkäämään pelaajan kimppuun liian monta kertaa, pelaaja kuolee ja häviää pelin.

## Käyttäjät

Kyseessä on peli, joten käyttäjänä on ainoastaan pelaaja.

## Käyttöliittymäluonnos
Käyttöliittymä tulee koostumaan ainakin kolmesta ruudusta. Alla esimerkki: 
<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/Kayttoliittymaluonnos.PNG">

## Nykyiset toiminallisuudet

### Ennen pelin alkua
- voidaan aloittaa uusi peli ja nimetä pelihahmo
- hahmon nimen tullaa olla pituudeltaan 4-15 merkkiä
- voidaan tarkastella leaderboardia, josta nähdään parhaimmat pisteet saavuttaneet pelaajat ja pistetulokset
- leaderboard voidaan nollata poistamalla kaikki aiemmat tulokset
- pelin vaikeutta voidaan muokata _config.properties_ -tiedostolla, muuttamalla ilmestyvien hirviöiden määrää 

### Pelin aikana
- Pelin kulku 
  - hirviöitä ilmestyy satunnaisesti eri puolille kenttää tietty määrä
  - taso läpäistään, jos kaikki hirviöt tapetaan
  - pisteitä saa hirviöitä ampumalla. Kanoista saa 5 pistettä ja zombeista 10

- pelihahmon toiminnot
  - hahmo voi liikkua tasolla eri suuntiin W, A, S, D -näppäimillä.
  - hahmo voi ampua kursorin osoittamaansa suuntaan
  - hahmo voi juosta pitämällä shiftiä pohjassa

- hirviöiden käyttäytyminen
  - hirviöt seuraavat pelaajaa ja yrittävät saada tämän kiinni
  - jos hirviö osuu pelaajaan riittävän monta kertaa, pelaaja kuolee
  - hirviö kuolee jos pelaajan ammukset osuvat siihen riittävän monta kertaa ja katoaa tämän jälkeen peliruudusta.
  - ammukset poistetaan ruudusta joko osuman jälkeen tai, kun ne ylittävät peliruudun ulkoreunan

- Pelinäkymä
  - Hit points -mittari, joka pienenee, kun hirviö osuu pelihahmoon
  - Pisteet näyttävä  mittari, joka kertoo pelaajan yhteispisteet kyseiseltä pelikerralta

## Jatkokehitysmahdollisuuksia
- _config.properties_ -tiedostoon voisi lisätä kohdat, joilla pelihahmon, luotien ja vihollisten nopeutta voisi säätää
- Mahdollisuus ostaa saaduilla pisteillä uusia aseita tai palauttaa pelaajan menettämiä hit pointseja
- Mahdollisuus poimia kentältä pelaajalle hyödyllisiä esineitä, kuten aseita tai potioneita. Nämä ilmestyvät satunnaisiin       paikkoihin
- Pelin voi tallentaa ja jatkaa siitä mihin viimeksi jäi
- Pelihahmolle ja hirviöille yksinkertainen kävelyanimaatio
- Ääniefektit esim. aseille ja askelille
- Erilaisia esteitä, joiden läpi ei voi kävellä 
- Uusia kenttiä erilaisilla teemoilla
- Leaderboardiin voidaan lisätä uusia tarkasteltavia asioita, kuten esimerkiksi pelaajan osumatarkkuus (Osuneiden ammusten     määrä jaettuna ammuttujen ammusten määrällä)


