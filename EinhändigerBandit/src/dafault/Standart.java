package dafault;
import java.util.Random;
import java.io.BufferedInputStream;
import java.io.IOException;


public class Standart {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Random random = new Random();
		for (int i = 0; 1<100;i++) {
			int randomzahl = random.nextInt(9);
			System.out.print(randomzahl);
			sleep(100);
			System.out.print((char) 8);
			
			}
	}

private static void sleep(long millis) {
    try {
        Thread.sleep(millis);
    } catch (InterruptedException ignored) {
    }
}
}

