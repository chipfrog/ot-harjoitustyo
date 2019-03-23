package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void palautaOikeaSaldo() {
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLatausKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(150);
        assertEquals("saldo: 1.60", kortti.toString());
    }
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(6);
        assertEquals("saldo: 0.04", kortti.toString());
    }
    @Test
    public void saldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void rahatRiittivat() {
        assertTrue(kortti.otaRahaa(10) != false);
        
    }
    
}
