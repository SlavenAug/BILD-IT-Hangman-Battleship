package hangman;

import java.util.InputMismatchException;

public class Hangman {

	public static void main(String[] args) {

		try {
			java.util.Scanner input = new java.util.Scanner(System.in);
			// Kreiramo niz rijeci.
			String[] words = { "kuca", "suncanice", "telefon", "procesor", "laptop" };
			// Nasumicno odabiremo rijec iz niza.
			String guessingWord = words[(int) (Math.random() * words.length)].toLowerCase();
			// Kreiramo niz duzine nasumicno odabrane rijeci.
			char[] guess = new char[guessingWord.length()];
			// Postavljamo brojac promasaja.
			int counter = 0;
			// Postavljamo uslov petlje.
			boolean game = true;

			while (game) {

				/*
				 * Ispisujemo rijec koju treba pogoditi skrivenu sa "*", ako je
				 * neko slovo pogodjeno ispisujemo ga.
				 */
				for (int i = 0; i < guess.length; i++) {
					// Ako je uneseni karakter jedank defaultnoj vrijednosti
					// karaktera ispisujemo zvjezdicu "*".
					if (guess[i] == '\u0000') {
						System.out.print("*");
					} else {
						// U suprotnom ispisujemo pogodjeni karakter.
						System.out.print(guess[i]);
					}
				}

				// Unos karaktera.
				char unos = (char) input.next().charAt(0);

				// Postavljamo uslov za unesene karaktere,
				// true ako je pogodjen karakter,
				// u suprotnom false;
				boolean correct = false;

				for (int i = 0; i < guessingWord.length(); i++) {
					// Ako je karakter rijeci jedank unesenom karakteru,
					if (guessingWord.charAt(i) == unos) {
						// onda postavljamo karakter na tacnu poziciju niza
						// za pogadjanje rijeci.
						guess[i] = unos;
						// Postavljamo uslov za pogodjeni karakter na ture;
						correct = true;
					}
				}

				// Ako nije pogodjen karakter onda uvecavamo brojac gresaka.
				if (!correct) {
					counter++;
				}
				// Kreiramo brojac pogodjenih karaktera.
				int hit = 0;
				for (int i = 0; i < guessingWord.length(); i++) {
					// Ako pogadjanja rijec nije jednaka defaultnoj vrijednosti
					// karaktera,
					// onda uvecavamo brojac, tj. brojimo pogodjena slova.
					if (guess[i] != '\u0000') {
						hit++;
					}
				}
				// Ako je brojac gresaka jedank 10,
				if (counter == 10) {
					// ispisujemo poruku i prekidamo program.
					System.out.println("\nYou lose!!!");
					game = false;
					break;
				}

				// Ako je brojac pogodjenih slova jedank broju slova trazene
				// rijeci,
				if (hit == guess.length) {
					// onda je igrac pobjedio i ispisujemo poruku, prekidamo
					// program.
					System.out.println("\nYou Win");
					System.out.println("Correct word is: " + guessingWord);
					game = false;
					break;
				}
			}
			// Zatvaramo skener.
			input.close();
		} catch (InputMismatchException e) {
			System.out.println("Wrong input!");
		}

	}
}
