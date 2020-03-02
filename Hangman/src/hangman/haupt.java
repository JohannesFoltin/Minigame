package hangman;

import java.util.Scanner;
import java.util.Random;
import java.util.*;
import java.io.*;


public class haupt {
	public static void main(String[] args) throws IOException {
		String[] woerter = null;
		int versuche = 13;
		
		Random random = new Random();
		Scanner scannerdifficulty = new Scanner(System.in);
		System.out.println("Wähle einen Schwierigkeitsgrad (einfach wörter oder schwere Wörter) (0 oder 1) : ");
		int difficulty = scannerdifficulty.nextInt();
		if (difficulty == 1) {
			String[] woerter1 = new String[378];
			FileReader ja = new FileReader("/home/johannes/eclipse-workspace/Minigame-master/IchMagKekse/src/woerter.txt");
			BufferedReader nein = new BufferedReader(ja);
			for(int i = 0; i < 378; i++) {
				woerter1[i] = nein.readLine();
			}
			nein.close();
			woerter = woerter1;
		}
		else if (difficulty == 0){
			String[] woerter0 = new String[378];
			FileReader ja = new FileReader("/home/johannes/eclipse-workspace/Minigame-master/IchMagKekse/src/woerter.txt");
			BufferedReader nein = new BufferedReader(ja);
			for(int i = 0; i < 378; i++) {
				woerter0[i] = nein.readLine();
			}
			nein.close();
			woerter = woerter0;
		
		}
		int randomwort = 0 ;
		randomwort = random.nextInt(woerter.length);
		String wort2 = woerter[randomwort];
		char[] wort = new char[wort2.length()]; 
		for (int i = 0; i < wort2.length(); i++) { 
	            wort[i] = wort2.charAt(i); 
	        } 
		
		boolean gibbesnicht = true;
		Vector<Character> wrongchars = new Vector<>();
		ArrayList<String> loesungswort = new ArrayList<>();
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
}
