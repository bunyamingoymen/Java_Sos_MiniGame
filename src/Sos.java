
import static java.lang.System.out;

public class Sos {

    private final int n; //Matrisin Boyutu: n*n
    private String[][] sos; // Girilen değerleri tutan Matris

    //Yapıcı metot:
    public Sos(int n) throws Exception {
        //Eğer n 3 den küçük ya da 7 den büyükse Exception yolluyoruz.
        if(n<3 || n>7 ){
            throw new Exception("n veya m sayısını hatalı girdiniz!!!");
        }

        //Eğer n 3 ile 7 arasındaysa sınıfta bulunan n değerini gelen değere eşitliyoruz.
        this.n = n;

        //Matrisin boyutunu ayarlıyoruz:
        sos = new String[this.n][this.n];

        //Matristeki her değere tire(-) işareti koyuyoruz. Matriste oynanan yerlerde O ya da S değerleri mevcut oalcak iken. Oynanmayan yerlerde - işareti mevcut olacaktır.
        for(int i = 0; i<n;i++)
            for (int j = 0; j<n;j++)
                sos[i][j] = "-";

    }

    //Getter ve Setter Metotları:
    public int getN() {
        return n;
    }

    public String[][] getSos() {
        return sos;
    }

    public void setSos(int n, int m, String newSos) {
        this.sos[n][m] = newSos;
    }

    //Matrisin içeriğini ekrana yazan fonksiyon:
    public void printSos(){

        out.println("");

        out.print("  ");
        for(int i = 1; i<=this.n;i++){
            out.print(" " + i + " ");
        }
        out.println("(Sütun)");

        for(int i = 0; i<this.n;i++){
            out.print((i+1) + " ");
            for (int j = 0; j<this.n;j++)
                out.print(" " + this.sos[i][j] + " ");
            out.println("");
        }

        out.println("");

    }
}
