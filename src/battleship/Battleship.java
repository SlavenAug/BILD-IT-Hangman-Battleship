package battleship;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Battleship {

	public static void main(String[] args) {
		// Nasumicno generisemo brodove za
		// igraca i racunara.
		int[][] comp = createMap();
		int[][] player = createMap();
		// Postavljamo uslov petlje.
		boolean game = true;
		while (game) {
			// Ispisujemo mape za igraca i racunar.
			printMap(comp, player);
			// Korisnikov unos.
			inputTable(comp, 1);
			// Generisemo unos za racunar.
			inputTable(player, 2);
			// Provjeravamo ima li pobjednika.
			if (checkWin(comp)) {
				// Ako je korisnik pobijedio, prikazi protivnicku mapu.
				printWin(comp);
				System.out.println("Player win!");
				// Postavljamo uslov na false, izlaz iz igre.
				game = false;
			} else if (checkWin(player)) {
				// Ako je racunar pobijedio.
				game = false;
				System.out.println("Computer won! You loose!");
			}
		}

	}

	// Metod za unos korisnika ogranicen od 0 do 9.
	public static int input() {
		Scanner input = new Scanner(System.in);
		// Kreiramo varijablu za unos.
		int num = 0;
		// Postavljamo uslov na true.
		boolean condition = true;

		while (condition) {
			try {
				num = input.nextInt();
				if (num >= 0 && num < 10) {
					// Ako je ocekivana vrijednost unesena, posavi uslov na
					// false i izadji iz petlje,
					condition = false;
				} else {
					// ako nije, ispisi poruku.
					System.out.println("Input must be from 0 to 9! Try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("You not entered int value! Try again: ");
				input.nextLine();
			}
		}
		return num;
	}

	// Metod za ispis tabela.
	public static void printMap(int[][] comp, int[][] player) {
		System.out.println("\t  Computer's battlefield\t\t\t\t  Player's battlefield\n"
				+ "    0   1   2   3   4   5   6   7   8   9" + "\t\t    0   1   2   3   4   5   6   7   8   9\n"
				+ "   ----------------------------------------" + "\t\t   ----------------------------------------");
		for (int i = 0; i < comp.length; i++) {
			System.out.print(i + " |");

			// Ispisujemo mapu racunara.
			for (int j = 0; j < comp[i].length; j++) {
				System.out.print((comp[i][j] == 2 ? " * " : comp[i][j] == 3 ? " X " : "   "));
				System.out.print("|");
			}
			// Ispisujemo mapu igraca.
			System.out.print("\t\t" + i + " |");
			for (int j = 0; j < player[i].length; j++) {
				System.out.print(
						(player[i][j] == 2 ? " * " : player[i][j] == 3 ? " X " : player[i][j] == 1 ? " 0 " : "   "));
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.print(
				"  -----------------------------------------" + "\t\t  -----------------------------------------\n");
	}

	// Metod za unos koordinata u tabelu.
	public static void inputTable(int[][] map, int player) {
		// Varijable za cuvanje koordinata
		int i = 0;
		int j = 0;
		boolean condition = true;
		while (condition) {
			// Za igraca.
			if (player == 1) {
				System.out.print("Enter colum: ");
				i = input();
				System.out.print("Enter row: ");
				j = input();
			} else {
				// Ako je kompjuter, generisu se nasumicni brojevi
				// od 0 do 9.
				i = (int) (Math.random() * 10);
				j = (int) (Math.random() * 10);
			}
			// Ako se ne nalazi 0 na tim kordinatama, onda su vec koristene.
			if (map[j][i] == 2 || map[j][i] == 3) {
				// Ispisi poruku da korisnik proba ponovo unijeti koordinate.
				System.out.println("You cannot play that field! Try another field.");
			} else if (map[j][i] == 1) {
				// Unos broja korisnika na unesenim koordinatama.
				map[j][i] = 3;
				// izlaz iz petlje
				condition = false;
			} else {
				// Unos broja korisnika na unesenim koordinatama.
				map[j][i] = 2;
				// izlaz iz petlje
				condition = false;
			}
		}
	}

	// Metod provjerava slobodna polja u redovima na koja se brod postavlja.
	public static boolean checkRows(int[][] map, int i, int j, int ship) {
		int col = 0;
		int row = 0;

		// Ako je "i" vece od 0 i manje od 9.
		if (i > 0 && i < 9) {
			// Vracamo i jedno polje.
			i--;
			// i postavi uslov na i + 3.
			// ovim se provjerava red na koji se brod postavlja i
			// gornji i donji red oko broda
			// npr: ako je i = 5, petlja ce proci kroz
			// i = 4 (red iznad broda)
			// i = 5 (red na koji bi se trebao postaviti brod)
			// i = 6 (red ispod broda)
			col = 3 + i;
		} else if (i == 9) {
			// Vraca jedno polje.
			i--;
			// Provjerava dva reda.
			col = 2 + i;
		} else {
			// ovo je slucaj kad je i = 0.
			// provjeravaju se samo dva reda
			// npr: i = 0
			// i = 1;
			col = 2;
		}

		// ako je "j" vece od 0 i manje od 9.
		if (j > 0 && j < 9) {
			// Vrati j za jedno polje.
			j--;
			// postavi za uslov poziciju "j" + duzina brodica + 2
			// npr: ako j = 5 i duzina broda 3, u petlji ce se proci kroz
			// koordinate od j = 4 do j = 9
			// j = 4 je jedno polje sa lijeve strane brodica
			// j od 5 do 7 su polja broda, a j = 8 je polje koje se provjerava
			// desno od broda
			row = ship + 2 + j;
		} else if (j > 0 && j == 9) {
			// Vrati za jedno polje.
			j--;
			// Ovo je slucaj kad je brod zalijepljen za desnu stranu mape.
			row = ship + 1 + j;
		} else {
			row = ship + 1 + j;
		}
		// Petlja provjerava svako polje na pozicijama odredjenih koordinata i
		// uslova iznad.
		for (; i < col; i++) {
			for (int k = j; k < row; k++) {
				// Ako je trenutno provjereno polje zauzeto,
				if (map[i][k] == 1) {
					// prekini pretragu i vrati false, tj. brod se ne moze
					// postaviti na zadanim pocetnim koordinatama.
					return false;
				}
			}
		}
		// Ako su sva provjerena polja prazna, vrati true.
		return true;
	}

	// Metod provjerava slobodna polja u kolonama na koja se brod postavlja.
	public static boolean checkColums(int[][] map, int i, int j, int ship) {
		// Isto kao i za metod iznad, samo sto su obrnuti uslovi za provjeru.
		int vetCond = 0;
		int horCond = 0;
		// Postavljanje uslova za j.
		if (j > 0 && j < 9) {
			j--;
			horCond = j + 3;
		} else if (j == 9) {
			j--;
			horCond = j + 2;
		} else {
			horCond = j + 2;
		}
		// Postavljenje uslova za i.
		if (i > 0 && i < 9) {
			i--;
			vetCond = ship + i + 2;
		} else if (i > 0 && i == 9) {
			i--;
			vetCond = ship + i + 2;
		} else {
			vetCond = i + ship + 1;
		}
		// Provjera slobodnih polja na odredjenim koordinatama.
		for (; i < vetCond; i++) {
			for (int k = j; k < horCond; k++) {
				// Ako se na provjerenim poljima nalazi
				// neki brod, vrati false.
				if (map[i][k] == 1) {
					return false;
				}
			}
		}
		// ako su polja slobodna, vrati true.
		return true;
	}

	// Metod provjerava da li se brod moze postaviti na mapi.
	public static boolean check(int[][] map, int ship, int pozX, int pozY, int orientation) {
		// Za horizontalnu orijentaciju broda.
		if (orientation == 0) {
			// Poziv metode za provjeru slobodnih polja na mapi.
			if (checkRows(map, pozY, pozX, ship)) {
				// Ako su sva polja na koordinatama slobodna i polja oko broda,
				// postavi brod na tim koordinatama.
				for (int k = 0, j = pozX; k < ship; j++, k++) {
					map[pozY][j] = 1;
				}
				return true;
			}
		} else if (orientation == 1) {
			// Za vertikalnu orijentaciju broda.
			if (checkColums(map, pozY, pozX, ship)) {
				for (int k = 0, j = pozY; k < ship; k++, j++) {
					map[j][pozX] = 1;
				}
				return true;
			}
		}
		// Ako koordinate ne odgovaraju, vrati false.
		return false;
	}

	// Metod za generisanje pozicije broda na mapi.
	public static boolean createShip(int[][] map, int ship) {
		// Pocetne tacke koordinate brodica.
		int x = 0;
		int y = 0;
		// Ako se dobije 0 brod je horiznotalno
		// orijentisan ako je 1 onda je vertikalno orijentisan.
		int orientation = (int) (Math.random() * 2);
		// Ako je horizontalno orijentisan.
		if (orientation == 0) {
			// x koordinata se generise od 0 do 9
			x = (int) (Math.random() * 10);
			// y koorditnata se generise od 0 do 9 - duzina brodica
			// ovo se radi da ne bi brodic bio duzi od duzine niza.
			y = (int) (Math.random() * (10 - ship));
			// Ako je brodic uspijesno postavljen, vrati true.
			if (check(map, ship, y, x, orientation)) {
				return true;
			}
		} else {
			// Isto kao i za vertikano orijentisane brodice.
			x = (int) (Math.random() * (10 - ship));
			y = (int) (Math.random() * 10);
			if (check(map, ship, y, x, orientation)) {
				return true;
			}

		}
		// Ako brod nije postavljen vraca se false, tj. generisu se nove pocetne
		// koordinate broda.
		return false;

	}

	// Metod generise mapu sa brodovima.
	public static int[][] createMap() {
		// Kreiramo mapu velicine 10 x 10.
		int[][] map = new int[10][10];
		// Kreiramo brojac.
		int posible = 0;
		// Kreiramo brojac za brodove.
		int count = 10;
		while (count > 0) {
			// Povecavamo tokom svake iteracije ovaj brojac
			// ako dodje do 10000 metod se ponovo poziva.
			posible++;
			if (count == 10) {
				// Pozivamo metod za generisanje
				// brodica od 5 polja
				if (createShip(map, 5)) {
					// Ako je brod uspijesno generisan
					// umanjujemo brojac.
					count--;
				}
			} else if (count == 9 || count == 8) {
				// Generisanje 2 brodica velicine 4 polja.
				if (createShip(map, 4)) {
					count--;
				}
			} else if (count == 7 || count == 6 || count == 5) {
				// Generisaje 3 brodica velicine 3 polja.
				if (createShip(map, 3)) {
					count--;
				}
			} else {
				// Generisanje 4 brodica velicine 2 polja.
				if (createShip(map, 2)) {
					count--;
				}
			}

			// Ako se u 10000 kombinacija ne nadju odgovarajuce pozicije za
			// brodice izvrsava se rekurzivni poziv, tj, trazimo drugi slucaj.
			if (posible > 10000) {
				return createMap();
			}
		}
		// Vracamo mapu sa generisanim pozicijama brodica.
		return map;
	}

	// Metod za provjeru da li ima brodova na mapi.
	public static boolean checkWin(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				// Ako se na mapi nalazi brod vraca false.
				if (map[i][j] == 1) {
					return false;
				}
			}
		}
		// Ako nema brodova na mapi, igrac je pobjedio.
		return true;
	}

	// Metod ispisuje mapu sa potopljenim brodovima.
	public static void printWin(int[][] map) {
		System.out.println(" _______________________________________");
		for (int i = 0; i < map.length; i++) {
			System.out.print("|");
			for (int j = 0; j < map[i].length; j++) {
				System.out.print((map[i][j] == 3 ? " X " : "   "));
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------");
	}

}
