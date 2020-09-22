package dbgyak2_2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * 2.Feladat:
 * 
 * olvassuk be és tároljuk el autó osztályokat egy fileban.
 * !!A működséhez szükség van az Auto osztályra
 * 
 * a)kérjük be az adatokat és mentsük el őket a memoriába
 * b)írjuk ki őket fileba
 * c)olvassik ki majd írjuk ki a konzolra a megadott indexedik autó adatait
 * d)töröljünk ki egy autót úgy hogy a fennmaradó adatokat átírjuk egy másik fileba és átnevezzük ezt a filet
 * e)irassuk ki az összes autót a kijelzőre
 */
public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Auto> cars = setCars();	//deklarálja a cars listát és beállítja az adatait a setCars() metódus segítségével
		writeToFile(cars);				//kiiírja a paraméterben átadott ArrayListben tárolt adatokat a fileba
		readByIndex(2);					//beolvas minden rekordod egészen addig amíg nem éri el az indexediket ekkor kilép és a legutolsó beolvasottat kiírja 
		DeleteOne(2);						//Egy autot kitöröl a fileből
		writeOutAll();						//minden fileban található autót kiír a konzolra
	}
	
	public static ArrayList<Auto> setCars(){
		ArrayList<Auto> a = new ArrayList<Auto>();
		int n;
		
		
		System.out.println("Adja meg a beolvasni kívánt autok számát: ");
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		Auto car = null;
		for (int i=0;i<n;i++) {
			car = new Auto();
			System.out.println("Add meg a rendszamot: ");
			car.setRsz(sc.next());
			System.out.println("Add meg a tipus: ");
			car.setTipus(sc.next());
			System.out.println("Add meg az arat: ");
			car.setAr(sc.nextInt());
			
			a.add(car);
		}
		sc.close(); //A Scannert is illik bezárni ugyebár
		return a;
	}
	
	public static void writeToFile(ArrayList<Auto> cars) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Autok.dat"));
			
			for(Auto car: cars) {
				oos.writeObject(car);
			}
			
			oos.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readByIndex(int i) {
		int count=0;
		Auto car;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Autok.dat"));
			
			while(true) {//ezt a ciklust is egy try{}catch blokkba raktam hogy ha az index nagyobb mint a lementett adatok száma kilépjen és ne írjok ki semmilyen adatot.
				try {
				count++;
				car=(Auto) ois.readObject();
				if (count==i) break; 
				car=null;
				}catch(EOFException exit) {
					car=null;
					break;
	
				}
			}
			System.out.println(car);
			
			ois.close();
		}catch(Exception e) {
			
			e.printStackTrace();
	}
	}
	
	public static void DeleteOne(int i) {
		try {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Autok.dat"));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp.dat"));
		Auto car;
		int count=0;
		
		while (true) { //itt ugyebár az volt a gondd hogy ezt nem lehet így ellenőrizni hogy a file végére értünk ezért try catch blokkba rakom a ciklus belsejét
			try {
		    car=(Auto)ois.readObject();
			count++;
			if (count!=i) {
				oos.writeObject(car);
			}
			car=null;
			}catch(EOFException exit) { //ilyen exceptiont dob az ObjectInputStream osztály ha elérte a file végét
				break;
			}
		}
		
		
		ois.close();
		oos.close();
		 
		File f2=new File("temp.dat");							//A 2 File osztályt példányosítunk és a renameTo(File f) metódust használva felülírjuk a temp.dat tal az Autok.dat ot, így a neve is Autok.dat lesz
		System.out.println(f2.renameTo(new File("Autok.dat")));	//A File egy elég fancy osztály van ebbe is kiirás létrehozás mappakezelés és ilyen jó cuccok :)
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
		
	}
	
	public  static void writeOutAll() {
		try {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Autok.dat"));
		Auto car;
		
		
		while (true) { //itt is a DeleteOne függvénynél leírtakat csináltam meg
			try {
			car=(Auto)ois.readObject();
			System.out.println(car);
			car=null;
			}catch(EOFException exit) {
				break;
			}
		}
		ois.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
