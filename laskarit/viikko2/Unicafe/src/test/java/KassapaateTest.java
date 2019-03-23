/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jajuuso
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
        
    }
    @Test
    public void rahamaaraAlussaOikein() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    @Test
    public void myytyjenMaaraAlussaOikein() {
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0 && kassa.maukkaitaLounaitaMyyty() == 0);
    }
    @Test
    public void kateismaksuKasvattaaKassanSaldoaOikein() {
        kassa.syoEdullisesti(300);
        kassa.syoEdullisesti(250);
        kassa.syoMaukkaasti(500);
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(100);
        kassa.syoEdullisesti(100);
        assertTrue(kassa.kassassaRahaa() == 101300);
    }
    @Test
    public void kateismaksunVaihtorahaOnOikeinJosSummaRiittava() {
        assertTrue(kassa.syoEdullisesti(280) == 30);
        assertTrue(kassa.syoEdullisesti(250) == 0);
        assertTrue(kassa.syoMaukkaasti(500) == 100);
        assertTrue(kassa.syoMaukkaasti(100) == 100);
        assertTrue(kassa.syoMaukkaasti(400) == 0);
    }
    @Test
    public void kateisMaksuPalautetaanKokonaanJosSummaEiRiita() {
        assertTrue(kassa.syoEdullisesti(100) == 100);
        assertTrue(kassa.syoMaukkaasti(100) == 100);
    }
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaJosKateismaksuRiittava() {
        kassa.syoEdullisesti(250);
        kassa.syoEdullisesti(50);
        kassa.syoEdullisesti(500);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 2);
    }
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaJosKateismaksuRiittava() {
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(500);
        kassa.syoMaukkaasti(250);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 2);
    }
    @Test
    public void josKateismaksuEiRiittavaKassanSaldoEiMuutu() {
        kassa.syoEdullisesti(150);
        kassa.syoMaukkaasti(350);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    @Test
    public void josKateismaksuEiRiittavaMyytyjenLounaidenMaaraEiMuutu() {
        kassa.syoEdullisesti(150);
        kassa.syoMaukkaasti(350);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }
    @Test
    public void josKortillaRiittavastiRahaaVeloitetaanSummaKortilta() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertTrue(kortti.saldo() == 350);
    }
    @Test
    public void josKortillaRiittavastiRahaaPalautetaanTrue() {
        assertTrue(kassa.syoEdullisesti(kortti) == true);
        assertTrue(kassa.syoMaukkaasti(kortti) == true);
    }
    @Test
    public void josKortillaRiittavastiRahaaMyytyjenLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }
    @Test
    public void josKortillaEiTarpeeksiRahaaPalautetaanFalse() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.syoEdullisesti(kortti) == false);
        assertTrue(kassa.syoMaukkaasti(kortti) == false);
    }
    @Test
    public void josKortillaEiTarpeeksiRahaaSaldoEiMuutu() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo() == 200);
    }
    @Test
    public void josKortillaEiTarpeeksiRahaaMyytyjenLounaidenMaaraEiMuutu() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti); // Rahat eivät riitä tätä pidemmälle
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        
        assertTrue(kassa.edullisiaLounaitaMyyty() == 2);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstettaessa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    @Test
    public void korttiaLadattaessaKortinSaldoKasvaaJosPositiivinen() {
        kassa.lataaRahaaKortille(kortti, 500);
        kassa.lataaRahaaKortille(kortti, 0);
        assertTrue(kortti.saldo() == 1500);
    }
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(kortti, -200);
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    
    
    @Test
    public void korttiaLadattaessaKassanRahamaaraKasvaa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertTrue(kassa.kassassaRahaa() == 100500);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
