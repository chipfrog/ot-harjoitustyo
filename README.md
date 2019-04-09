# Top-Down-Shooter
Kyseessä on 2D-peli, jossa tarkoituksena on kerätä pisteitä ampumalla hirviöitä ja välttää syödyksi tuleminen.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/chipfrog/ot-harjoitustyo/blob/master/Top-Down-Shooter/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/chipfrog/ot-harjoitustyo/blob/master/Top-Down-Shooter/dokumentaatio/tyoaikakirjanpito.md)

[Käyttöohje](https://github.com/chipfrog/ot-harjoitustyo/blob/master/Top-Down-Shooter/dokumentaatio/kayttoohje.md)

## Komentorivitoiminnot

### Testaus
Testit voidaan suorittaa komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla
```
mvn test jacoco:report
```
Sovellus voidaan käynnistää komennolla
```
mvn compile exec:java -Dexec.mainClass=ui.Ui
```
### Checkstyle
Tiedoston [checkstyle.xml](https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/checkstyle.xml)
mukaan määritellyt tarkistukset voidaan suorittaa komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
