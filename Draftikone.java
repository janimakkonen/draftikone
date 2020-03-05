package draftiprojekti;

public class Draftikone {
	
	public static void arvonta(String[] pelaaja, int[] pisteet, int kierros,
			boolean[][] pelattu){
		
		//tarvittavat muuttujat ja taulukot
		int[] montako = new int[16];
		int[] arvottu = new int[pelaaja.length];
		int maksimi = (kierros-1)*3, ottelut = (pelaaja.length/2), satunnainen, piste;
		int[][] parit = new int[ottelut][2];
		boolean pelitetty = false;
		int vaihto1, vaihto2, molo;
		piste = maksimi;

		
		//katsotaan paljonko kenell‰kin on mit‰kin pisteit‰
		System.out.println("Kierros "+ kierros +" alkaa.");
		for(int i = 0; i<pelaaja.length; i++){

			for(int u = 0; u<montako.length; u++){
				if(pisteet[i] == u){
					montako[u]++;


				}
				
			}
			
		}
		
		for(int i = 0; i < ottelut; i++){
			
			//pelaajien arvonta
			for(int y = 0; y < 2; y++){
				for(int u = maksimi; u >= 0; u--){
					if(montako[u] > 0){//jos k‰sitelt‰v‰ll‰ pistem‰‰r‰ll‰ on pelaajia viel‰
						//arvotaan satunnainen pelaaja kyseisell‰ pistem‰‰r‰ll‰
						do{
							satunnainen = (int)(Math.random() * (pelaaja.length));
						}while(pisteet[satunnainen] != u || arvottu[satunnainen] == 1);
						arvottu[satunnainen] = 1;
						parit[i][y] = satunnainen;
						montako[u]--;
						if(montako[piste] == 0){
							piste--;
						}
						break;//lopetaan silmukka, jos pelaaja onnistutaan arpomaan
					}
			
				}
			}
		}
		//t‰h‰n sijoitetaan ratkaisu ongelmaan
		do{
			pelitetty = false;
			for(int i = 0; i <ottelut; i++){
				
				if(pelattu[parit[i][0]][parit[i][1]] == true){
					satunnainen = (int)(Math.random() * 2);
					if(i==0){
						vaihto1 = parit[(i+1)][satunnainen];
						vaihto2 = parit[i][satunnainen];
						parit[i][satunnainen] = vaihto1;
						parit[(i+1)][satunnainen] = vaihto2;
					}
					
					else if(i == (ottelut-1)){
						vaihto1 = parit[(i-1)][satunnainen];
						vaihto2 = parit[i][satunnainen];
						parit[i][satunnainen] = vaihto1;
						parit[(i-1)][satunnainen] = vaihto2;
					}
					else {
						molo = (int)(Math.random() * 2);
						if(molo == 0){
							vaihto1 = parit[(i+1)][satunnainen];
							vaihto2 = parit[i][satunnainen];
							parit[i][satunnainen] = vaihto1;
							parit[(i+1)][satunnainen] = vaihto2;
						}
						else if(molo == 1){
							vaihto1 = parit[(i-1)][satunnainen];
							vaihto2 = parit[i][satunnainen];
							parit[i][satunnainen] = vaihto1;
							parit[(i-1)][satunnainen] = vaihto2;
						}
						
						
					}
					
					
					pelitetty = true;
					
				}
			
			}
		}while(pelitetty == true); 
		
		//pelaajien tulostus ja pelattujen parien merkkaaminen 
		for(int i = 0; i<ottelut; i++){
			System.out.println ("Pari " + (i+1) + ": " + pelaaja[parit[i][0]] + " - " + 
					pelaaja[parit[i][1]]);
			pelattu[parit[i][0]][parit[i][1]] = true;	
			pelattu[parit[i][1]][parit[i][0]] = true;
		}
		
		
		
	}
	
	
	public static void lisaaPisteita(String[] pelaaja, int[] pisteet, int kierros){
		int numero, yhteensa;
		for(int i = 0; i<pelaaja.length; i++){
			System.out.println("Pelaaja " + (i+1) + " on " + pelaaja[i] + " ja pistet‰ on: " + pisteet[i]);
		}
		do{
			System.out.println("Kenen pisteit‰ haluat muuttaa?(anna numero)");
		
			numero = (Oma.lueInt() - 1);
			if(numero < 0 || numero>(pelaaja.length-1)){
				System.out.println("Valitsit pelaajan, jota ei ole olemassa.");
			}
		}while(numero < 0 || numero>(pelaaja.length-1));
		do{
			System.out.println("Lis‰‰ pelaajalle " + pelaaja[numero] + " pisteit‰. (Voit myˆs v‰hent‰‰)");		
			yhteensa = (pisteet[numero] + Oma.lueInt());
			if(yhteensa>kierros*3){
				System.out.println("Et voi lis‰t‰ pisteit‰ yli maksimin.");
			}
		}while(yhteensa>(kierros*3));
		pisteet[numero] = yhteensa;
		
	}
	
	
	public static void poistaPelaaja(String[] pelaaja, int[] pisteet){
		char ok = 'p';
		int numero;
		for(int i = 0; i<pelaaja.length; i++){
			System.out.println("Pelaaja " + (i+1) + " on " + pelaaja[i] + " ja pistet‰ on: " + pisteet[i]);
		}
		do{
		System.out.println("Kenet haluat poistaa?(anna numero)");
		numero = Oma.lueInt();
		System.out.println("Oliko " + pelaaja[numero-1] + " varmasti oikein?\n" +
		" k tai e(poistuu poistamismetodista)");
		ok = Oma.lueChar();
		}while(ok != 'k' && ok != 'e');
		if(ok == 'k'){
			pelaaja[numero] = "bye";
			pisteet[numero] = 0;
		}
	}
	
	public static void pelaajienNimet(String[] pelaaja, boolean parillinen){
		int pituus;
		
		if(parillinen == false){
			pituus=(pelaaja.length-1);
			pelaaja[pituus] = "Bye";
		}
		
		else{
			pituus=pelaaja.length;
		}
		
		for(int i=0; i<pituus; i++){
			System.out.println("Pelaajan " + (i+1) + " nimi:");
			pelaaja[i]= Oma.lueString();


		}	
		
	}
	
	public static void tulostaPelaajat(String[] pelaaja, int[] pisteet){
		for(int i = 0; i<pelaaja.length; i++){
			System.out.println("Pelaaja " + (i+1) + " on " + pelaaja[i] + " ja pistet‰ on: " + pisteet[i]);
		}
		
	}
	
	public static void draftiArvonta(String[] pelaaja, boolean parillinen){
		//Tehd‰‰n muuttujat

		int[] arvonta;
		String[] jarjestys;
		int satunnainen;
		int luku;
		
		if(parillinen == false){
			luku = (pelaaja.length-1); 
			
		}
		else{
			luku = pelaaja.length;
		}
		arvonta = new int[pelaaja.length];
		 
		//alustetaan arvonta-taulukko
		for(int i = 0; i<luku; i++){
			arvonta[i] = 1;
		}
		if(parillinen == false){
			arvonta[luku] = 0;
		}
		jarjestys = new String[luku];
	
		//arvotaan j‰rjestys
		int laskuri2 = 0;
		for(int i = 0; i<luku; i++){
			do{
				satunnainen = (int)(Math.random() * (luku));
			}while(arvonta[satunnainen] == 0);
			arvonta[satunnainen] = 0;
			jarjestys[i] = pelaaja[satunnainen];
			laskuri2++;
		}
		satunnainen = 0;
		laskuri2 = 2;
		//tulostetaan pˆyd‰t jos pelaajam‰‰r‰ on 4:ll‰ jaollinen
		System.out.println("Pˆyt‰ 1: ");
		if(luku %4 == 0){
			for(int i = 0; i<luku; i++){
				System.out.print(jarjestys[i]+ " ");
				satunnainen++;
				if(satunnainen == 4){
					satunnainen = 0;
					System.out.println();
					System.out.println("Pˆyt‰ " +laskuri2+ ": ");
					laskuri2++;
				}
			}
		}
		//tulostetaan pˆyd‰t jos pelaajam‰‰r‰ ei mene nelj‰n pˆytiin
		else{
			//5 pelaajan pˆyd‰t jos pelaajam‰‰r‰ jaollinen viidell‰
			if(luku %5 == 0){
				
				for(int i = 0; i<luku; i++){
			
					System.out.print(jarjestys[i]+ " ");
					satunnainen++;
					if(satunnainen == 5){
						satunnainen = 0;
						System.out.println();
						System.out.println("Pˆyt‰ " +laskuri2+ ": ");
						laskuri2++;
					}
				}
				
			}
			//Jos pelaajia on nelj‰n pˆytiin yht‰ lukuunottamatta
			else if(luku %4 == 1){
				int laskuri = 0;
				for(int i = 0; i<luku; i++){
					
					System.out.print(jarjestys[i]+ " ");
					satunnainen++;
					if(satunnainen == 4 && (laskuri + 5) < luku){
						satunnainen = 0;
						System.out.println();
						System.out.println("Pˆyt‰ " +laskuri2+ ": ");
						laskuri2++;
					}
					laskuri++;
				}
			}
			//Jos aikaisemmat eiv‰t p‰de
			else{
				
				System.out.println("Tehk‰‰ pˆyd‰t itse. T‰ss‰ kuitenkin pelaajien satunnainen j‰rjestys:");
				
				for(int i = 0; i<luku; i++){
					System.out.println(jarjestys[i]);
				}
			}
			
		}
		
	}
	
	public static void topArvonta(String[] pelaaja, int[]pisteet, int kierrokset){
		int maara, maksimi = (3*kierrokset), piste = 0, satunnainen, laskuri = 0;
		String[] top;
		int[] montako = new int[16];
		int[] arvottu = new int[pelaaja.length];
		
		do{
		System.out.println("Pelataanko top 8(1) vai 4(2)?");
		maara = Oma.lueInt();
		}while(maara != 1 && maara !=2);
		
		if(maara == 1){
			top = new String[8];
		}
		
		else{
			top = new String[4];
		}
		
		for(int i = 0; i<pelaaja.length; i++){
			
			for(int u = 0; u<montako.length; u++){
				
				if(pisteet[i] == u){
					montako[u]++;
				}
				
			}
			
		}
		
	
		piste = maksimi;	
		for(int i = 0; i<=pelaaja.length; i++){
			if(montako[piste] != 0){
				do{
					satunnainen = (int)(Math.random() * pelaaja.length);
				}while(arvottu[satunnainen] == 1 || pisteet[satunnainen] != piste);
				arvottu[satunnainen] = 1;
				top[laskuri] = pelaaja[satunnainen];
				montako[piste]--;
				laskuri++;
			}
			else{
				piste--;
				i--;
			}
			if(laskuri == top.length){
				break;
			}
		}
		
		laskuri = 0;
		
		for(int i = 0; i<arvottu.length; i++){
			arvottu[i] = 0;
		}
		
		for(int i = 0; i<top.length; i++){
			do{
				satunnainen = (int)(Math.random() * top.length);
			}while(arvottu[satunnainen] == 1);
			arvottu[satunnainen] = 1;
			System.out.print(top[satunnainen]);
			laskuri++;
			if(laskuri == 1){
				System.out.print(" - ");
			}
			else if(laskuri == 2){
				System.out.println();
				laskuri = 0;
			}
						
		}
	
	}
		
		
		
	


	public static void main(String[] args) {
		int pelaajienmaara, kierros = 0, kierrokset, valikko;
		boolean parillinen = false, lopetus = false;
		char ok = 'p';
		System.out.println("*******JANIN DRAFTIKONE*******");
		//Luodaan pelaajat
		
		do{
		System.out.println("Pelaajien m‰‰r‰?:");
		pelaajienmaara = Oma.lueInt();
		System.out.println("Menikˆ " + pelaajienmaara + " oikein? \n k tai e");
		ok = Oma.lueChar();
		}while(ok != 'k');
		if(pelaajienmaara %2 == 0){
			parillinen = true;
			System.out.println("Hienoa, kellekk‰‰n ei tule bye‰.");
		}
		if(parillinen == false){
			pelaajienmaara++;
		}
		//Pelaajien nimet yhdess‰ taulukkossa
		String[] pelaajat = new String[pelaajienmaara];
		
		//Tehd‰‰n pisteet-taulukko
		int[] pisteet = new int[pelaajienmaara];
		
		boolean[][] pelattu = new boolean[pelaajat.length][pelaajat.length];
		
		for(int i = 0; i<pelaajat.length; i++){
			for(int u = 0; u<pelaajat.length; u++){
				pelattu[i][u] = false;
			}
		}
		
		System.out.println("Seuraavaksi laitetaan nimet.");
		pelaajienNimet(pelaajat, parillinen);
		
		do{
			System.out.println("Montako kierrosta pelataan?:");
			kierrokset = Oma.lueInt();
			if(kierrokset<1 || kierrokset>5){
				System.out.println("Kierrosten lukum‰‰r‰ pit‰‰ olla v‰lill‰ 1-5.");
			}
		}while(kierrokset<1 || kierrokset>5);
		
		do{
			System.out.println();
			System.out.println("*********Menu elik‰ valikko**********");
			System.out.println("1. Lis‰‰ pisteit‰ jollekkin pelaajalle.");
			System.out.println("2. Poista l‰htenyt pelaaja.");
			System.out.println("3. Draftipˆytien arvonta.");
			System.out.println("4. Aloita uusi kierros.(maksimi on annettujen kierrosten lukum‰‰r‰)");
			System.out.println("5. Aloita top 8 tai 4.(mahdollinen sitku on pelattu annettu kierrosten m‰‰r‰)");
			System.out.println("6. N‰yt‰ pelaajat ja pisteet");
			System.out.println("0. Poistu ohjelmasta.");
			
			System.out.println("Valintasi: ");
			valikko = Oma.lueInt();
			switch(valikko){
				
				case 1:
					lisaaPisteita(pelaajat, pisteet, kierros);
					break;
				
				case 2:
					poistaPelaaja(pelaajat, pisteet);
					break;
				
				case 3:
					draftiArvonta(pelaajat, parillinen);
					break;
				
				case 4:
					if(kierros<kierrokset){
						do{
							System.out.println("Haluatko varmasti alottaa uuden kierroksen?\n k tai e");
							ok = Oma.lueChar();
						}while(ok != 'k' && ok != 'e');
						if(ok == 'k'){
							kierros++;
							arvonta(pelaajat, pisteet, kierros, pelattu);
						}

					}
					
					else{
						System.out.println("Kierroksia on pelattu maksimim‰‰r‰ jo.\n");
					}
					break;
				
				case 5:
					if(kierros>=kierrokset){
						topArvonta(pelaajat, pisteet, kierrokset);
					}
					else{
						System.out.println("Et voi alottaa toppia ennen ku kierrokset on pelattu");
					}
					break;
				
				case 6:
					tulostaPelaajat(pelaajat, pisteet);
					break;
				
				case 0:
					char varma = 'p';
					do{
						
						System.out.println("Haluatko varmasti lopettaa? k tai e");
						varma = Oma.lueChar();
						
					}while(varma != 'k' && varma != 'e');
					
					if(varma == 'k'){
						lopetus = true;
					}
					break;
					
				
			}
			
			
			
		
		}while(lopetus == false);
		System.out.println("Lopetit ohjelman....");
		
		

		

	}

}
