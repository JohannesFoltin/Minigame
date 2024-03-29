

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Hauptman {
	public char[] spielfeld = new char[9];
	public boolean spieler = true;

	public static void main(String[] args) throws IOException {
		Hauptman mainklasse = new Hauptman();
		mainklasse.spiel();
	}

	public void spiel() throws IOException {
		setSheet();
		System.out.println("SPIELFELD:");
		writeSheet();
		System.out.println("LASS UNS HANGMAN SPIELEN!");
		System.out.println("GEBE NUN EINE ZAHL EIN (0-8)");
		while (true) {
			Scanner scanner1 = new Scanner(System.in);
			int eingabe = scanner1.nextInt();
			if(eingabeTester(eingabe) == false) {
				System.out.println("DIE ZAHL GEHT NICHT SRY...VERSUCH ES DOCH NOCHMAL!");
				continue;
			}
			if(spieler == true) {
			spielfeld[eingabe] = 'X';	
			spieler = false;
			}
			else if (spieler == false) {
				spielfeld[eingabe] = 'O';
				spieler = true;
				
			}
			if(endeVonAllem() == true) {
				System.out.println("LOLO");				
			}
			else {
				writeSheet();
			
			}
			
		}
		//System.out.println("UND VORBEI!");
	}
	public String spielfeldToString() {
		String reuckgabe = new String();
		for (int i=0; i < 9;i++) {
			reuckgabe = reuckgabe + spielfeld[i];
		}
		
		return reuckgabe;
		
	}

	public void writeSheet() {
		for (int r = 0; r < spielfeld.length; r++) {
			System.out.print((spielfeld[r]==' ')?'*':spielfeld[r]);
			if (r % 3 == 2) {
				System.out.println();
			}

		}
	}

	public void setSheet() {
		for (int h = 0; h < 9; h++) {
			spielfeld[h] = ' ';
		}
	}
	
	public boolean eingabeTester(int keingabe) {
		if(keingabe < 9 && keingabe > -1 && spielfeld[keingabe] == ' ') {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	private boolean endeVonAllem() {
		int endForAll = 0;
		for(int e=0; e< 9 ; e++) {
			if(spielfeld[e] != ' ') {
				endForAll = endForAll+ 1 ;
			}
		}
		if(endForAll== spielfeld.length) {
			endForAll = 0;
			return true;
		}
		else {
			return false;
		}
	}
	
	private static int call911(String currentGame) throws IOException {
		System.out.println("SENDEN " + currentGame);
        int result = -1;
        URL url = new URL("http://heinz:8080/tictactoe?currentGame=" + URLEncoder.encode(currentGame, "UTF-8"));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        result = Integer.parseInt(content.toString());
        System.out.println("EMPFANGEN " + result);
        return result;
    }
}
