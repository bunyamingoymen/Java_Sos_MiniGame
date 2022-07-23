import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Scanner;

public class SosService {

    private static int used; //Kaç karenin kullanıldığını sayıyor. Bu sayı dizinin boyutuna eşit olursa bütün kareler dolduruldu demek oluyor.
    private static int health; // Kullanıcı tarafından 3 tane hatalı giriş yapılırsa otomatik olarak diskalifiye oluyor (Normalden daha büyük yada eksi bir satır veya sütün girerse canı azalıyor. Max 3 kez hatalı giriş yapılabilir.)
    private static int computerScore; //bilgisayarın skorunu tutuyor
    private static int playerScore; // kullanıcının skorunu tutuyor
    private static boolean playerS; // true ise kullanıcı s değerini oynuyor. False ise bilgisayar s değerini oynuyor
    private static boolean whoIsNext; //true ise kullanıcı oynuyor. False ise bilgisayar

    //Başlangıç Fonksiyonu. İlk değerler atanıp. Oyun bitene kadar döngü oluşturan fonksiyon:
    public static void run(@NotNull Sos sos){

        boolean isFinish = false;

        playerS = new Random().nextBoolean(); // rastgele bir boolean değer atanıyor
        whoIsNext = new Random().nextBoolean(); // rastgele bir boolean değer atanıyor

        health= 3;
        computerScore = 0;
        playerScore = 0;

        System.out.println("\n-----------Oyun Başlasın!!!!-----------\n");

        System.out.println("İlk başlayacak kişi: " + (whoIsNext ? "Kullanıcı" : "Bilgisayar") + "dır.");

        System.out.println("Sizin kullanabileceğiniz değer: " + (playerS ? "S": "O") + " değeridir. " +
                "Bilgisayar ise " + (playerS ? "O": "S") + " değerini kullanacaktır.");

        while(!isFinish){
            if(used == sos.getN() * sos.getN() || health <= 0) isFinish = true;
            else{
                if(whoIsNext)
                    playerGame(sos);
                else
                    computerGame(sos);

                System.out.println("Skorunuz: " + playerScore);
                System.out.println("Bilgisayarın Skoru: " + computerScore);
            }
        }

        whoWin(health);
    }

    //Bilgisayar oynarken yapılan işlemlerin olduğu fonksiyon
    static void computerGame(Sos sos){

        System.out.println("\n-------Bilgisayar Oynuyor!!!--------\n");

        int n;
        int m;

        do {
            int max = sos.getN();
            int min = 1;
            int range = max - min + 1;
            n = (int) (Math.random() * range) + min;
            n--;

            range = max - min + 1;
            m = (int) (Math.random() * range) + min;
            m--;
        } while (isUsed(n, m, sos.getSos()));

        String newSos = playerS ? "O" : "S";

        System.out.println("Bilgisayarın Oynadığı satır: "  + (n+1));
        System.out.println("Bilgisayarın Oynadığı sütun: "  + (m+1));

        sos.setSos(n,m,newSos);
        sos.printSos();
        score(sos.getSos(),n,m,0, newSos);

        used++;

    }

    //Kullanıcı oynarken işlemlerin yapıldığı ve ekrana yazacak olan şeylerin olduğu fonksiyon
    static void playerGame(Sos sos){

        System.out.println("\n--------Siz oynuyorsunuz!!! ------------\n");

        Scanner s = new Scanner(System.in);

        System.out.println("Satır sayısını giriniz: ");
        int n = s.nextInt();
        n--;

        System.out.println("Sütun sayısını giriniz: ");
        int m = s.nextInt();
        m--;

        if(n+1>sos.getN() || n<0 || m+1> sos.getN() || m<0){

            System.out.println("Hatalı girdi yaptınız. " +
                    "Bu sebeple canınız düşmüştür. Yeni canınız: " + --health +
                    " \nDaha fazla hatalı girdi yaptığınız taktirde oyundan eleneceksiniz.");
        }
        else if(isUsed(n, m, sos.getSos()))
            System.out.println("Daha önce girilmiş satır ve sütunları girdiniz. " +
                    "Bu sebeple canınız düşürülmüştür.\n Yeni canınız: " + --health +
                    " \nDaha fazla hatalı girdi yaptığınız taktirde oyundan eleneceksiniz");
        else{
            String newSos = playerS ? "S" : "O";
            sos.setSos(n,m,newSos);
            sos.printSos();
            score(sos.getSos(),n,m, 1, newSos);
            used++;
        }

    }

    //score yapılıp yapılmadığını hesaplayan fonksiyon
    static void score(String[][] sos, int n, int m, int whoPlay, String newSos){
        //whoPlay 0 ise bilgisayar oynuyor, 1 ise kullanıcı oynuyor
        int count = 0;
        if(newSos.equals("S")){

           /* if(n-2>=0 && m-2>=0 && sos[n-1][m-1].equals("O") && sos[n-2][m-2].equals("S")) count++;
            if(n-2>=0 && sos[n-1][m].equals("O") && sos[n-2][m].equals("S")) count++;
            if(n-2>=0 && m+2<sos.length && sos[n-1][m+1].equals("O") && sos[n-2][m+2].equals("S")) count++;
            if(m+2<sos.length && sos[n][m+1].equals("O") && sos[n][m+2].equals("S")) count++;
            if(n+2<sos.length && m+2<sos.length && sos[n+1][m+1].equals("O") && sos[n+2][m+2].equals("S")) count++;
            //if(n+2<sos.length && m+2<sos.length && sos[n+1][m+1].equals("O") && sos[n+2][m+2].equals("S")) count++;
            if(n+2<sos.length && m-1>=0 && sos[n+1][m-1].equals("O") && sos[n+2][m].equals("S")) count++;
            if(n>=0 && m-2>=0 && sos[n][m-1].equals("O") && sos[n][m-2].equals("S")) count++;
            */

           for(int i = n-1<0?0:n-1; i<=n+1 ; i++){
               int tmp = m-2;
               for(int j = m-1<0?0:m-1; j<=m+1;j++){
                    if(tmp>=0 && i<sos.length && j<sos.length){
                        if(i==n-1 && i+1<sos.length)
                            if(sos[i+1][j].equals("O") && sos[i][tmp].equals("S")) count++;
                        if(i==n && j!=m)
                            if(sos[i][j].equals("O") && sos[i][tmp].equals("S")) count++;
                        if(i==n+1 && i+1<sos.length)
                            if(sos[i][j].equals("O") && sos[i+1][tmp].equals("S")) count++;
                    }
                    tmp+=2;
                    if(tmp>sos.length) break;

               }
           }

        }else{
            if(n-1>=0 && n+1<sos.length && m-1>=0 && m+1<sos.length && sos[n-1][m-1].equals("S") && sos[n+1][m+1].equals("S")) count++;
            if(n-1>=0 && n+1<sos.length && m<sos.length && sos[n-1][m].equals("S") && sos[n+1][m].equals("S")) count++;
            if(n-1>=0 && n+1<sos.length && m-1>=0 && m+1<sos.length && sos[n-1][m+1].equals("S") && sos[n+1][m-1].equals("S")) count++;
            if(n<sos.length && m-1>=0 && m+1<sos.length && sos[n][m-1].equals("S") && sos[n][m+1].equals("S")) count++;
        }

        if(whoPlay == 0){

            //Eğer bilgisayar score yaparsa bir kez daha oynama hakkı kazanıyor. Bu if else u işe yarıyor. Count sıfırdan
            //büyükse bilgisaayr score yapmıştır demek oluyor. Bu sebeple whoIsNext'i false yapıyoruz ki bilgisayar bir hak daha kazansın.
            //Ancak count 0 ise bilgisayar score yapamadı demek oluyor. O zamanda oynama hakkı karşı tarafa yani kullanıcıya veriliyor.
            if(count>0){
                computerScore += count;
                whoIsNext = false;
            }else{
                whoIsNext = true;
            }

        }
        else{
            //Eğer kullanıcı score yaparsa bir kez daha oynama hakkı kazanıyor
            if(count>0){
                playerScore += count;
                whoIsNext = true;
            }else{
                whoIsNext = false;
            }
        }

    }

    //Girilen sütunun kullanılıp kullanılmadığını hesaplayan fonksiyon
    static boolean isUsed(int n, int m, String[][] sos) {
        return !sos[n][m].equals("-"); // kullanılmışsa true döner.
    }

    //Kimin kazandığını hesaplayan fonksiyon
    static void whoWin(int health){
        System.out.println("\n------------------Oyun Bitti!!!------------------\n");
        if(health <= 0) System.out.println("Oyunu hatalı girdiler yüzünden kaybettiniz!!!");
        else if (computerScore>playerScore) System.out.println("Oyunu bilgisayar kazanmıştır!!!!");
        else if(computerScore<playerScore) System.out.println("Oyunu kazandınız!!!!");
        else System.out.println("Oyun berabere bitti!!!");

        System.out.println("Sizin son skorunuz: " + playerScore);
        System.out.println("Bilgisayarın son skoru: " + computerScore);
    }


}
