# Testausdokumentti

Peliin liittyvät testit suoritettiin JUnitilla pääasiassa erilaisin yksikkötestein ja manuaalisesti järsjetelmätasolla. Integraatiotestit
jäivät hieman vähemmälle, mutta niitäkin on jonkin verran.

## Yksikkötestaus

Miltei jokaiselle pelilogiikkaa ja peliobjekteja käsittelevälle luokalle on luotu oma testiluokka, jossa metodien toimivuutta
on testattu. Testeissä on pyritty siihen, että metodit eivät tuottaisi virheellisiä tuloksia missään tilanteissa. Erityisesti on keskitytty
keskitytty pelin toiminnan aikaisiin tilanteisiin, kuten esim. siihen, että peli osaa pitää kirjaa elossa olevista peliobjekteista ja 
poistaa ruudulta oikeat objektit tarvittaessa. 

## Testauskattavuus 

Testauksen rivikattavuus on 51% ja haarautumiskattavuus 44%

<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/testikattavuus.png">
Tietokannan ja Dao:n automatisoitu puuttuu kokonaan, mutta tietokannan toimivuutta on testattu manuaalisesti. 

## Järjestelmätestaus

Suoritettiin manuaalisesti. Toimintaa testattiin asennusohjeiden mukaisesti kahdella windows-koneella ja kahdessa Linux-ympäristössä, siten että config.properties -tiedosto on
luotu ohjeiden mukaan pelin käynnistyskansioon. 
