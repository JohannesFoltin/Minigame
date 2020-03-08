package hangman;

import java.util.Scanner;
import java.util.Random;
import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class Haupt {
	private static final int DIMENSIONY = 9;
	private static final int DIMENSIONX = 14;
	public Vector<String> woerter = null;
	public char[][] zweidimo = new char[DIMENSIONX][DIMENSIONY];
	public int versuche = 14;
	public int randomwort = 0;
	public String wort2;
	public boolean gibbesnicht = true;
	public Vector<Character> wrongchars = new Vector<>();
	public ArrayList<String> loesungswort = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		Haupt mainklasse = new Haupt();
		mainklasse.spiel();
	}

	public void spiel() throws IOException {
		setSheet();
		System.out.println("Wähle einen Schwierigkeitsgrad (einfach wörter oder schwere W�rter) (0 oder 1) : ");
		diffikulty();
		zufallswort();
		char[] wort = new char[wort2.length()];
		for (int i = 0; i < wort2.length(); i++) {
			wort[i] = wort2.charAt(i);
		}

		for (int i = 0; i < wort.length; i++) {
			loesungswort.add("_");
		}
		System.out.println("Willkommen zu Hangman.\nDu hast " + versuche + " Versuche das Wort zu erraten");
		System.out.print("Das Wort was du suchst hat " + wort.length + " Buchstaben." + " Gib nun einen Buchstaben an");
		System.out.println();
		int richtigeZahlen = -1;
		while(true) {
			Scanner scanner1 = new Scanner(System.in);
			char eingabe = scanner1.next().charAt(0);
			clearScreen();
			System.out.println("Du hast eingegeben: " + eingabe);
			for (int i = 0; i < wort.length; i++) {
				if (eingabe == wort[i]) {
					String test = String.valueOf(eingabe);
					loesungswort.set(i, test);
					gibbesnicht = false;
				}

			}
			if (gibbesnicht == true) {
				wrongchars.add(eingabe);
				System.out.println("Der Buchstabe ist Falsch!..Leider");
				
			}
			else {
				System.out.println();
			}
			gibbesnicht = true;
			System.out.println(loesungswort + " Falsche Buchstaben:" + wrongchars + " Buchstaben left : "+ (versuche - wrongchars.size()));
			aufbau(wrongchars.size());
			if (wrongchars.size() == versuche) {
				System.out.println("GAME OVER");
				System.out.print(wort);
				System.out.print(" w�re richtig gewesen");
				break;

			} else {
				for (int k = 0; k < wort.length; k++) {
					if (loesungswort.get(k).contentEquals(String.valueOf(wort[k]))) {
						richtigeZahlen = richtigeZahlen + 1;

					}
				}
				if (richtigeZahlen == wort.length) {
					System.out.println("Du hast es geschafft!! Ein Menschenleben wurde gerettet. HURRA");
					break;
				} else {
					richtigeZahlen = 0;

				}
			}
		}
	}

	public void diffikulty() throws IOException {
		
		String[] woerter0 = new String[378];
		Scanner scannerdifficulty = new Scanner(System.in);

		int difficulty = scannerdifficulty.nextInt();
		if (difficulty == 1) {
			readFile("/woerter.txt");
				} else if (difficulty == 0) {
			readFile("/words0.txt");
			}
	}
	public void setSheet() {
		for (int y=0;y<DIMENSIONY;y++) {
			for(int x=0;x<DIMENSIONX;x++) {
				zweidimo[x][y]=' ';
			}
		}
		
		
	}
	public void writeSheet() {
		for (int y=0;y<DIMENSIONY;y++) {
			for(int x=0;x<DIMENSIONX;x++) {
				System.out.print(zweidimo[x][y]);
			}
			System.out.println();
		}
		
		
	}
	private void readFile(String name) throws IOException {
		InputStream ja = getClass().getResourceAsStream(name);
		BufferedReader nein = new BufferedReader(new InputStreamReader(ja));
		woerter = new Vector<>();
		while(true) {
			String readLine = nein.readLine();
			if (readLine == null) {
				break;
			}
			woerter.add(readLine);
		}
		nein.close();
	}

	public void zufallswort() {
		Random random = new Random();
		randomwort = random.nextInt(woerter.size());
		wort2 = woerter.get(randomwort);

	}
	public void aufbau(int verlauf){
		
		switch(verlauf) {
		case 14:
			zweidimo[9][4]='_';
		case 13:
			zweidimo[13][4]='_';
		case 12:
			zweidimo[13][7]='_';
		case 11:
			zweidimo[9][7]='_';
		case 10:
			zweidimo[13][7]='_';
		case 9:
			zweidimo[10][7]='/';
			zweidimo[12][7]='\\';
		case 8:
			zweidimo[11][6]='|';
		case 7:
			zweidimo[10][5]='\\';
			zweidimo[12][5]='/';
		case 6:
			zweidimo[11][4]='|';
		case 5:
			zweidimo[11][3]='O';
		case 4:
			zweidimo[11][1]='|';
			zweidimo[11][2]='|';
		case 3:
			zweidimo[4][1]='/';
			zweidimo[3][2]='/';
			zweidimo[2][3]='/';
		case 2:
			for (int x=0 ; x< 12 ;x++) {
				zweidimo[x][0]='_';
				}
		case 1:
			for (int y=1 ; y< DIMENSIONY ;y++) {
				zweidimo[0][y]='|';
				}
			writeSheet();
		}
	}
	 public static void clearScreen() {  
		    System.out.print("\033[H\033[2J");  
		    System.out.flush();  
		   } 
}