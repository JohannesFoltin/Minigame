package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.Vector;


public class Hauptman_ohne_player {
	public char[] spielfeld = new char[9];
	public int spielzug;
	public int rofl = 1;

	public static void main(String[] args) throws IOException {
		Hauptman_ohne_player mainklasse = new Hauptman_ohne_player();
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
				Spielverlauf(rofl);
				System.out.println("Vorher: ");
				writeSheet();
				spielfeld[call911(spielfeldToString())] = 'O';
				System.out.println("Nacher: ");
				writeSheet();
				rofl = rofl +1;
			
				if (hasWon(spielfeldToString(), 'X') == true) {
					System.out.println("Du hast gewonnen");
					break;
					
				}
				else if (hasWon(spielfeldToString(), 'O') == true) {
					System.out.println("Oh man alter du hast gegen einen Algo verloren. Peinlich");
					break;
				}
			
		}
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
	
	public void Spielverlauf(int zugstand) {
		boolean zwickiodernoraml = true;
		switch (zugstand) {
		case 1:
			setzte(6);
			break;
		case 2:
			if(einzelnerStellenTester(2, 'O') == false) {
			setzte(2);
			break;
			}
			else {
			setzte(8);
			zwickiodernoraml = false;
			break;
			}
		case 3:
			if (zwickiodernoraml = false) {
				setzte(7);
			}
			else {
			if(einzelnerStellenTester(0, 'O') == false) {
				setzte(0);
				spielzug = 0;
				break;
			}
			else if (einzelnerStellenTester(8, 'O') == false){
				setzte(8);
				spielzug = 1;
				break;
				}
			}
		case 4:
			if(spielzug == 0 && einzelnerStellenTester(1, 'O')== false) {
				setzte(1);
				break;
			}
			else if (spielzug == 0 && einzelnerStellenTester(3, 'O')== false) {
				setzte(3);
				break;
			}
			if (spielzug == 1 && einzelnerStellenTester(7, 'O')== false) {
				setzte(7);
				break;
			}
			else if (spielzug == 1 && einzelnerStellenTester(5, 'O')== false) {
				setzte(5);
				break;
			}
		}
		
		
		
	}
	public Vector<Integer> stellentester(char Xor0) {
		Vector<Integer> belegteStellen = null;
		for (int i = 0 ; i < spielfeld.length;i++) {
			if(spielfeld[i] == Xor0) {
				belegteStellen.add(i);
			}
		}
		return belegteStellen;
	}
	public boolean einzelnerStellenTester(int stelle, char Xor0) {
		if(spielfeld[stelle] == Xor0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public void setzte(int Ort) {
		if (spielfeld[Ort] != 'O') {
			spielfeld[Ort] = 'X';
		}
		else {
			System.out.println("!NOPE!");
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
	private boolean hasWon(String currentGame, char x) {
	    ROW:
	    for (int row = 0; row < 3; ++row) {
	      for (int column = 0; column < 3; ++column) {
	        if (currentGame.charAt(column * 3 + row) != x) {
	          continue ROW;
	        }
	      }
	      return true;
	    }
	    COLUMN:
	    for (int column = 0; column < 3; ++column) {
	      for (int row = 0; row < 3; ++row) {
	        if (currentGame.charAt(column * 3 + row) != x) {
	          continue COLUMN;
	        }
	      }
	      return true;
	    }
	    if (currentGame.charAt(0) == x && currentGame.charAt(4) == x && currentGame.charAt(8) == x) {
	      return true;
	    }
	    if (currentGame.charAt(2) == x && currentGame.charAt(4) == x && currentGame.charAt(6) == x) {
	      return true;
	    }
	    return false;
	  }
}