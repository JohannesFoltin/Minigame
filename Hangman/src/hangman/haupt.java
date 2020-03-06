package hangman;

import java.util.Scanner;
import java.util.Random;
import java.util.*;
import java.io.*;


public class haupt {
	public static String[] woerter = null;
	public static int versuche = 13;
	public static int randomwort = 0 ;
	public static String[] woerter1 = new String[378];
	public static String[] woerter0 = new String[378];
	public static String wort2;
	public static boolean gibbesnicht = true;
	public static Vector<Character> wrongchars = new Vector<>();
	public static ArrayList<String> loesungswort = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		System.out.println("Wähle einen Schwierigkeitsgrad (einfach wörter oder schwere Wörter) (0 oder 1) : ");
		diffikulty();
		zufallswort();
		char[] wort = new char[wort2.length()]; 
		for (int i = 0; i < wort2.length(); i++) { 
	            wort[i] = wort2.charAt(i); 
	        } 
		
		for(int i=0; i<wort.length; i++){
			loesungswort.add("_");
		}
		System.out.println("Willkommen zu Hangman.\nDu hast " + versuche + " Versuche das Wort zu erraten");
		System.out.print("Das Wort was du suchst hat " + wort.length + " Buchstaben." + " Gib nun einen Buchstaben an");
		System.out.println();
		int richtigeZahlen = -1;
		int j = 0;
		while(j ==0 ) {
			Scanner scanner1 = new Scanner(System.in);
			char eingabe = scanner1.next().charAt(0);
			System.out.println("Du hast eingegeben: " + eingabe);
			for(int i=0; i< wort.length;i++) {
				if (eingabe == wort[i]){
					String test = String.valueOf(eingabe);
					 loesungswort.set(i, test);
					 gibbesnicht = false;
				}	
				
			}
			if (gibbesnicht == true) {
				
				wrongchars.add(eingabe);
				System.out.println("Der Buchstabe ist Falsch!..Leider");
			}
			gibbesnicht = true;
			System.out.println(loesungswort + " Falsche Buchstaben:" + wrongchars + " Buchstaben left : " + (versuche - wrongchars.size()));
			if (wrongchars.size() == versuche) {
				System.out.println("GAME OVER");
				System.out.println("--------\n|      |\n|      0\n|     \\ /\n|      |\n|     / \\\n|");
				System.out.print(wort);
				System.out.print(" wäre richtig gewesen");
				break;
				
			}
			else {
			for(int k=0; k < wort.length; k++) {
				if (loesungswort.get(k).contentEquals(String.valueOf(wort[k]))){
					 richtigeZahlen = richtigeZahlen + 1;
					 
				}	
			}
			if(richtigeZahlen == wort.length) {
				System.out.println("Du hast es geschafft!! Ein Menschenleben wurde gerettet. HURRA");
				break;
			}
			else {
				richtigeZahlen = 0;
				
			}
			}
		}
}

public static void diffikulty() throws IOException {
	
	Scanner scannerdifficulty = new Scanner(System.in);
	
	int difficulty = scannerdifficulty.nextInt();	
	if (difficulty == 1) {
			FileReader ja = new FileReader("H:\\Fächer\\Whl. ITg\\Java\\Hangman\\src\\woerter.txt");
			BufferedReader nein = new BufferedReader(ja);
			for(int i = 0; i < 378; i++) {
			woerter1[i] = nein.readLine();
		}
		nein.close();
		woerter = woerter1;
	}
	else if (difficulty == 0){
		FileReader ja = new FileReader("/home/johannes/eclipse-workspace/Minigame-master/IchMagKekse/src/woerter.txt");
		BufferedReader nein = new BufferedReader(ja);
		for(int i = 0; i < 378; i++) {
			woerter0[i] = nein.readLine();
		}       
		nein.close();
		woerter = woerter0;
		}
	}
public static void zufallswort() {
	Random random = new Random();
	randomwort = random.nextInt(woerter.length);
	String wort2 = woerter[randomwort];
	
	
	
}
}