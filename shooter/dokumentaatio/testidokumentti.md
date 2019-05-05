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
Tietokannan ja Dao:n automatisoitu testaus puuttuu kokonaan, mutta tietokannan toimivuutta on testattu manuaalisesti. 

## Järjestelmätestaus

Suoritettiin manuaalisesti. Toimintaa testattiin asennusohjeiden mukaisesti kahdella windows-koneella ja kahdessa Linux-ympäristössä, siten että config.properties -tiedosto on
luotu ohjeiden mukaan pelin käynnistyskansioon. 

## Testauksessa havaittuja ongelmia

Testikattavuus jäi haluttua pienemmäksi ja laajempia integraatiotestejä olisi voinut tehdä enemmän. Myös tietokannan automatisoitu testaaminen jäi tekemättä. Yksi havaittu konkreettinen ongelma on, kun käyttäjä poistuu kokoruudun pelitilasta _Esc_:llä, niin seuraavien näkymien kokoasetukset menevät hetkeksi sekaisin. Pelin päivitysnopeus myös vaihtelee suuresti eri alustoilla. Selkein ero Windows- ja Linux-ympäristön välillä. Tämän voisi korjata antamalla mahdollisuuden muokata peliobjektien nopeutta _config.properties_ -tiedostossa tai käyttämällä pelianimaatiossa jotain toista toteutusta kuin _AnimationTimeria_.  
