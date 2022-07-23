import java.util.Scanner;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {

        int resultAnswer = 3; //Kullanıcının n değerini doğru girme hakkını tutan fonksiyon. 3 den fazla hatalı değer girerse otomatik oyun başlamadan durduruluyor.
        Scanner s = new Scanner(System.in); //Ekrandan veri almak için oluşturulan değişken
        Sos sos = null; // Sos nesnesindeki değerlere eşirmek için oluşturulan değişken

        //resultAnswer sıfırdan büyük olduğu müddetçe kullanıcıya hak tanınıyor.
        while (resultAnswer>0){

            //girilen n değeremin 3 ile 7 arasında olup olmadığı kontrol ediliyor. Sos sınıfında n değeri bu şartlara uymazsa Exception atıldığı için try catch ile bu değerleri kontrol edebiliyoruz.
            try {

                out.println("Lütfen nxn boyutunu giriniz. (min 3 max 7): ");
                int n = s.nextInt();

                sos = new Sos(n);

                break;

            }catch (Exception e){
                //Eğer girilen değer 3 ile 7 arasında değilse kullanıcının hakkı bir düşrülüp uyarı veirliyor.
                out.println(e.getMessage() + "\nVerileri doğru girmek için kalan hakkınız: " + --resultAnswer +
                        "\nDaha fazla hatalı girdi yaptığınız taktirde oyun durdurulacaktır.");
            }
        }

        //Eğer resultAnswer değeri 0 a kadar düşmüşse kullanıcı çok fazla hatalı giriş yapmış demek oluyor. Bu sebeple oyun sonlandırılıyor
        if(resultAnswer <= 0) out.println("Çok fazla hatalı girdi yaptığınız için oyun sonlandırılmıştır");
        else SosService.run(sos); // Eğer kullanıcı doğru değerleri girmişse run static metodu ile oyun başlıyor.
    }
}
