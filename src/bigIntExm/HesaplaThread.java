/**
*
* @author Serkan POLAT ve serkan.polat2@ogr.sakarya.edu.tr
* @since 06.05.2018
* <p>
* Açıklama : Thread ile Hesaplama Yapabilmek İçin Oluşturulmuş Class
* </p>
*/
import java.math.BigInteger;
public class HesaplaThread implements Runnable{
    BigInteger CarpilacakSayi;
    BigInteger CarpacakSayi;
    BigInteger carpan;
    BigInteger ToplamDegeri;
    String Gecici;
    private int Baslangic;
    private int Bitis;
    
    HesaplaThread(String CarpilacakSayi,String CarpacakSayi,int Baslangic,int Bitis){
        this.CarpilacakSayi = new BigInteger(CarpilacakSayi);
        this.CarpacakSayi = new BigInteger(CarpacakSayi);
        ToplamDegeri = new BigInteger("0");
        this.Baslangic = Baslangic;
        this.Bitis = Bitis;
        Gecici = CarpacakSayi;
    }
    @Override
    public void run() {
        for (int i = Baslangic; i < Bitis; i++) {
            carpan = new BigInteger(""+Gecici.charAt(i));
            ToplamDegeri = ToplamDegeri.add(CarpilacakSayi.multiply(carpan));
        }
    }

    public BigInteger SonucuGetir(){
        return ToplamDegeri;
    }
    
}
