# Top-Down-Shooter
Kyseessä on 2D-peli, jossa tarkoituksena on kerätä pisteitä ampumalla hirviöitä ja estää niitä osumasta pelaajaan.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/tyoaikakirjanpito.md)

[Käyttöohje](https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kayttoohje.md)

[Arkkitehtuuri](https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/arkkitehtuuri.md)

## Releaset
[Viikko 5](https://github.com/chipfrog/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Huom!
Jos alla olevat komennot eivät sellaisenaan toimi, Java 8 ei välttämättä ole käytössä ja silloin täytyy ajaa ensin komento:
```
export PATH=/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin:$PATH
```

### Testaus
Testit voidaan suorittaa komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla
```
mvn test jacoco:report
```
Raportin saa auki selaimella avaamalla tiedosto _target/site/jacoco/index.html_

Sovellus voidaan käynnistää komennolla
```
mvn compile exec:java -Dexec.mainClass=shooter.ui.Ui
```
### Checkstyle
Tiedoston [checkstyle.xml](https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/checkstyle.xml)
mukaan määritellyt tarkistukset voidaan suorittaa komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Tiedostoa voidaan tarkastella selaimella avaamalla _target/site/checkstyle.html_
### Suoritettavan jarin generointi
Jar-tiedoston luominen tapahtuu komennolla
```
mvn package
```
Suoritettava jar-tiedosto generoituu hakemiston _target_ alle nimellä _Shooter-1.0-SNAPSHOT.jar_
