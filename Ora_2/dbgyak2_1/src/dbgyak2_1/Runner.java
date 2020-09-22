package dbgyak2_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
/*
 * 1. Feladat
 * 
 * Írjunk ki a fileba szöveget egészen az end-ig (azt a sort beleértve)
 * Írjuk ki a konzolra a file tartalmát nagy betűkkel
 * Írjuk ki egy másik fileba az előző file tartalmát de a számok helyére a számok szavakkal leírt változatát írjuk 
 * 
 * 
 */
public class Runner {

	public static void main(String[] args) {
		writeToFile();					//kiírja a beolvasott sorokat a fileba
		toCapital("alma.txt");			//beolvassa  és nagy betűs szavanként kiírja az alma.txt-t
		numToWords("alma.txt","a.txt");	//Szavakká alakítja a számokat
		
	}
	
	public static void writeToFile() {
		String sor;
		boolean ok=true;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = null;
		
		System.out.println("Add meg a file nevet: ");
		try {
			sor = br.readLine();
			bw = new BufferedWriter(new FileWriter(sor)); 
			
			while(ok) {
				sor = br.readLine();
				bw.write(sor);
				bw.newLine();
				
				String[] szavak;
				szavak = sor.split(" ");
				
				for (String szo: szavak) {
					if ((szo.compareTo("end"))==0) {
						ok=false;
						break;
					}
				}
				}
			
			bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void toCapital(String filename) {
		String sor;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			while ((sor = br.readLine())!= null) {
				System.out.println(sor.toUpperCase());
				
			}
			
			
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void numToWords(String filename, String filename2) {
		String sor;
		String sor2="";
		
		try {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename2));
		
		while((sor = br.readLine())!=null) {
				
			for(NumbersAndWords n: NumbersAndWords.values()) {	//vegigmegyünk az enum összes elemén és a String.replace() segítségével  lecseréljük a számokat betűkre
				
				sor=sor.replace(String.valueOf(n.getNum())," "+n.name().toLowerCase()+" ");		//a toLowerCase kis betűvé alakítja a nagybetűs konstansokat
			
			}
			bw.write(sor);
			bw.newLine();
			
		}
		
		bw.close();
		br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
