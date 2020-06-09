/**
*
* @author Serkan POLAT ve serkan.polat2@ogr.sakarya.edu.tr
* @since 06.05.2018
* <p>
* Açıklama : Ana Class Thread ile Paralel ve Seri Hesaplama Yapıyor.
* </p>
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class AnaProgram {
    AnaProgram(){    
    }
    public static void main(String[] args) throws IOException {
        BufferedReader sr = new BufferedReader(new FileReader("sayi1.txt")); //250 bin basamakli sayi
        BigInteger CarpilacakSayi = new BigInteger(sr.readLine());
        sr.close();
        sr = new BufferedReader(new FileReader("sayi2.txt")); //200 bin basamakli sayi
        BigInteger CarpacakSayi = new BigInteger(sr.readLine());
        sr.close();
        BigInteger ToplamDegeri = new BigInteger("0");
        BigInteger carpan;
        HesaplaThread Thread1  = new HesaplaThread(CarpilacakSayi.toString(),CarpacakSayi.toString(),
                                              0,CarpacakSayi.toString().length()/3);
        HesaplaThread Thread2  = new HesaplaThread(CarpilacakSayi.toString(),CarpacakSayi.toString(),
                                              CarpacakSayi.toString().length()/3,CarpacakSayi.toString().length()/3*2);
        HesaplaThread Thread3  = new HesaplaThread(CarpilacakSayi.toString(),CarpacakSayi.toString(),
                                              CarpacakSayi.toString().length()/3*2,CarpacakSayi.toString().length());
        
       ExecutorService ThreadHavuz = Executors.newFixedThreadPool(3);
        
        long basla = System.nanoTime();
        
        ThreadHavuz.execute(Thread1);
        ThreadHavuz.execute(Thread2);
        ThreadHavuz.execute(Thread3);
        ThreadHavuz.shutdown();
        while(!ThreadHavuz.isTerminated()){ }
        long Bitis = System.nanoTime()-basla;
        double sure = Bitis/1000000.0;
        System.out.println("Paralel Hesaplama Suresi: " + String.format("%.2f", sure) + " milisaniye.");
        FileWriter yaz = new FileWriter("sonuc.txt");
        yaz.write(Thread1.SonucuGetir().add(Thread2.SonucuGetir().add(Thread3.SonucuGetir())).toString());
        yaz.close();
        String Gecici;
        Gecici = CarpacakSayi.toString();
        basla = System.nanoTime();
        for (int i = 0; i < Gecici.length(); i++) {
            carpan = new BigInteger(""+Gecici.charAt(i));
            ToplamDegeri = ToplamDegeri.add(CarpilacakSayi.multiply(carpan));
        }
        Bitis = System.nanoTime()-basla;
        sure = Bitis/1000000.0;
        System.out.println("Seri Hesaplanma Süresi " + String.format("%.2f", sure) + " milisaniye."); 
        System.out.println("Sonuc Dosyaya Yazildi");
        
    }
}