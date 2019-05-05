# Testausdokumentti

Peliin liittyvät testit suoritettiin JUnitilla pääasiassa erilaisin yksikkötestein ja manuaalisesti järjestelmätasolla. Integraatiotestit jäivät hieman vähemmälle, mutta niitäkin on jonkin verran. Tietokannan ja PlayderDaon toimivuutta on testattu 
väliaikaisen testitietokannan avulla.

## Yksikkötestaus

Miltei jokaiselle pelilogiikkaa ja peliobjekteja käsittelevälle luokalle on luotu oma testiluokka, jossa metodien toimivuutta
on testattu. Testeissä on pyritty siihen, että metodit eivät tuottaisi virheellisiä tuloksia missään tilanteissa. Erityisesti on keskitytty pelin toiminnan aikaisiin tilanteisiin, kuten esim. siihen, että peli osaa pitää kirjaa elossa olevista peliobjekteista ja 
poistaa ruudulta oikeat objektit tarvittaessa. Monet luokat vaativat JavaFx:n käyttöä, joten näiden testaamista varten käytettiin TestFx frameworkia. 

## Tietokannan testaus
Tietokantaa on testattu luomalla testauksen yhteydessä väliaikainen testitietokanta, johon lisätään tietoa ja, jonka tietoa muokataan PlayerDao:n metodeilla. 

## Testauskattavuus 

Testauksen rivikattavuus on 62% ja haarautumiskattavuus 47%

<img src="https://github.com/chipfrog/ot-harjoitustyo/blob/master/shooter/dokumentaatio/kuvat/jacoco.png">
 
## Järjestelmätestaus

Suoritettiin manuaalisesti. Toimintaa testattiin asennusohjeiden mukaisesti kahdella windows-koneella ja kahdessa Linux-ympäristössä, siten että config.properties -tiedosto on
luotu ohjeiden mukaan pelin käynnistyskansioon. 

## Testauksessa havaittuja ongelmia

Testikattavuus jäi haluttua pienemmäksi ja laajempia integraatiotestejä olisi voinut tehdä enemmän.Yksi havaittu konkreettinen ongelma on, kun käyttäjä poistuu kokoruudun pelitilasta _Esc_:llä, niin seuraavien näkymien kokoasetukset menevät hetkeksi sekaisin. Pelin päivitysnopeus myös vaihtelee suuresti eri alustoilla. Selkein ero Windows- ja Linux-ympäristön välillä. Tämän voisi korjata antamalla mahdollisuuden muokata peliobjektien nopeutta _config.properties_ -tiedostossa tai käyttämällä pelianimaatiossa jotain toista toteutusta kuin _AnimationTimeria_. Uusimassa releasessa jostain syystä viholliset eivät ilmesty Windowsilla. Johtuu luultavasti siitä, että Windowsilla ei päästä käsiksi _config.properties_ -tiedostoon.
