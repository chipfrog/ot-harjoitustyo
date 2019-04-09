# Vaatimusmäärittely
## Sovelluksen tarkoitus

Kyseessä on ns. "Top-Down Shooter" eli lintuperspektiivistä kuvattu ampumispeli, jossa tarkoituksena on ampua pelaajaa lähestyvät hirviöt ja väistellä
hirviöiden hyökkäyksiä. Jos pelaaja saa ammuttua kaikki hirviöt tai säilyy hengissä riittävän pitkän ajan, läpäisee hän tason ja voi siirtyä vaikeammalle tasolle. Jos taas hirviöt onnistuvat hyökkäämään pelaajan kimppuun liian monta kertaa, pelaaja kuolee ja häviää pelin.

## Käyttäjät

Kyseessä on peli, joten käyttäjänä on ainoastaan pelaaja.

## Käyttöliittymäluonnos
Käyttöliittymä tulee koostumaan ainakin kolmesta ruudusta. Alla esimerkki: 
<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/Kayttoliittymaluonnos.PNG">

## Suunnitellut toiminallisuudet

### Ennen pelin alkua
- voidaan aloittaa uusi peli ja nimetä pelihahmo
- voidaan tarkastella ns. leaderboardia, josta nähdään parhaimmat pisteet saavuttaneet pelaajat ja pistetulokset 

### Pelin aikana
- Pelin kulku 
  - hirviöitä ilmestyy satunnaisesti eri puolille kenttää tietty määrä
  - taso läpäistään, jos kaikki hirviöt tapetaan tai pelaaja selviää hengissä riittävän pitkän ajan, esim. 2 minuuttia
  - mitä enemmän hirviöitä pelaaja tappaa sitä enemmän pisteitä hän saa

- pelihahmon toiminnot
  - hahmo voi liikkua tasolla eri suuntiin W, A, S, D -näppäimillä.
  - hahmo kääntyy hiiren osoittamaan suuntaan (kääntyminen voi vaihtoehtoisesti tapahtua myös näppäimillä)
  - hahmo voi ampua osoittamaansa suuntaan
  - hahmo voi juosta lyhyitä matkoja 

- hirviöiden käyttäytyminen
  - hirviöt seuraavat pelaajaa ja yrittävät saada tämän kiinni
  - jos hirviö osuu pelaajaan riittävän monta kertaa, pelaaja kuolee
  - hirviö kuolee jos pelaajan ammus osuu siihen ja katoaa tämän jälkeen peliruudusta

- Pelinäkymä
  - Health point -mittari, joka pienenee, kun hirviö osuu pelihahmoon
  - Ajastin, joka näyttää kuinka kauan pelaajan tulee vielä selviytyä tason läpäisemiseksi
  - Pisteet näyttävä  mittari, joka kertoo kuinka monta hirviötä pelaaja on kukistanut 

## Mahdolliset lisätoiminallisuudet
- Mahdollisuus ostaa saaduilla pisteillä uusia aseita tai parantaa pelihahmon kykyjä, kuten hp:ta ("Health points")
- Mahdollisuus poimia kentältä pelaajalle hyödyllisiä esineitä
- Pelin voi tallentaa ja jatkaa siitä mihin viimeksi jäi
- Pelin vaikeustasoa voi säätää esim. kasvattamalla ilmestyvien hirviöiden määrää
- Pelihahmolle ja hirviöille yksinkertainen liikkumisanimaatio
- Ääniefektit esim. aseille ja askelille
- Erilaisia esteitä, joiden läpi ei voi kävellä 


 

