
package com.mycompany.unicafe;

public class Maksukortti {
 
    private int saldo;
 
    public Maksukortti(int saldo) {
        this.saldo = saldo;
    }
 
    public int saldo() {
        return saldo;
    }
 
    public void lataaRahaa(int lisays) {
        this.saldo += lisays;
    }
 
    public boolean otaRahaa(int maara) {
        if (this.saldo < maara) {
            return false;
        }
 
        this.saldo = this.saldo - maara;
        return true;
    }

    @Override
    public String toString() {
        int euroa = saldo/100;
        if (euroa < 1) {
            euroa = 0;
        }
        String apunolla ="";
        int senttia = saldo%100;
        if (senttia < 10) {
            apunolla = "0";
        }
        return "saldo: "+euroa+"."+ apunolla + senttia;
    } 
    
}
