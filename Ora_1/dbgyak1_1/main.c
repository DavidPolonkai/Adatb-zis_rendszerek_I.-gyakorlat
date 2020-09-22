#include <stdio.h>
#include <stdlib.h>
#include <string.h> //Ez az include a strcmp() függvény miatt szükséges, így eltűnik az emiatt megjelent figyelmeztetés

/*
A feladat:

Autók adatainak a tárolása egy car.dat nevezetű fáljban és velük való műveletek.


ui.:A printfeket szépítettem, a többi módosítást a módosítás helyén leírtam.
*/


struct Auto{
    char rsz[6];
    char tipus[20];
    int ar;
    char szin[15];
};

void felvisz();             //Adatok felvitelére szolgáló függvény, amely tetszőleges mennyiségű autot visz fel.
void kiir();                //A .dat fileban található adatokat írja ki a konzolra.
void pirosauto();           //A piros színű autókat keresi meg majd írja ki az adataikat a konzolra.
void beolvasi(int index);   //A beolvasi(index) az indexedik adatot keresi meg a fájlban, majd kiírja azt.
void rendez();              //Beolvassa az adatokat a fileból egy struktúra tömbbe majd az adatokat rendezi egy küldő maximum rendező függvény segítségével.
void carrendez(struct Auto cars[],int darab); //A cars struktúra tömbben rendezi az adatokat. **Módosítva**


int main()
{
    //felvisz();
    //pirosauto();
    //beolvasi(2);
    rendez();
    kiir();
    return 0;
}

void felvisz()
{
    FILE *f;
    int szam;
    struct Auto car;

    f=fopen("car.dat","wb");
    printf("Adja meg a rekordok szamat: ");
    scanf("%d",&szam);
    for (int i=0;i<szam;i++)
    {
        printf("Adja meg az auto rendszamat: ");
        scanf("%s",car.rsz);
        printf("Adja meg az auto tipus: ");
        scanf("%s",car.tipus);
        printf("Adja meg az auto ar: ");
        scanf("%d",&car.ar);
        printf("Adja meg az auto szinet: ");
        scanf("%s",car.szin);

        fwrite(&car,sizeof(struct Auto),1,f);
    }
    fclose(f);
}


void  pirosauto()
{
    FILE *f;
    struct Auto car;

    f=fopen("car.dat","rb");
    if (f==NULL) exit(-1);
    while (fread(&car,sizeof(struct Auto),1,f)==1)
    {
        if (strcmp(car.szin,"piros")==0) printf("rsz: %s szin: %s \n",car.rsz,car.szin);
    }
    fclose(f);
}


void beolvasi(int index)
{
    //A max változó ami a fileban tárol autok darabszámára utal átneveztem darab-ra.
    FILE *f;
    struct Auto car;
    int darab;

    f=fopen("car.dat","rb");
    fseek(f,0,SEEK_END);
    darab=ftell(f)/sizeof(struct Auto);

    index--; //Így nem 0 ról indulnak az adatok, hanem 1. 2. 3. stb adatok lesznek.

    if (index<darab)
    {
        fseek(f,index*sizeof(struct Auto),SEEK_SET);
        fread(&car,sizeof(struct Auto),1,f);
        printf("rsz: %s tipus: %s ar: %d szin: %s\n",car.rsz,car.tipus,car.ar,car.szin);
    }
    else printf("Túl nagy index");
    fclose(f);
}

void rendez()
{
    //A max változó ami a fileban tárol autok darabszámára utal átneveztem darab-ra

    FILE *f,*g;
    int i=0;
    int darab;

    f=fopen("car.dat","rb");
    g=fopen("temp.tmp","wb");
    if  (f==NULL || g==NULL)
    {
        printf("File hiba");
        exit(-1);
    }

    fseek(f,0,SEEK_END);
    darab=ftell(f)/sizeof(struct Auto);
    fseek(f,0,SEEK_SET);

    struct Auto cars[darab];

    while(fread(&cars[i],sizeof(struct Auto),1,f)==1)
    {
        i++;
    }
    carrendez(cars,darab);

    for (int i=0;i<darab;i++)
    {
        fwrite(&cars[i],sizeof(struct Auto),1,g);
    }
    fclose(f);
    fclose(g);
    remove("car.dat");
    rename("temp.tmp","car.dat");

}


void carrendez(struct Auto cars[],int darab) //a carrendeznek a paraméter szignatúráját bővítettem az autók darabszámával, így megspóroltuk a felesleges számolást és a hiba                                             //is megszűnéséhez is hozzájárul
{
//Mivel a csak a tomb pointerét adtuk át, hogy tudjuk módosítani az értékét és nem magát a tömb egészét,
// ezért nem lehet rajta helyesen alkalmazni a sizeof(tombneve)/sizeof(tombneve[0]) metódust a tömb nagyságának megállapításához
    struct Auto temp;
    for(int i=0;i<darab;i++)        //ebben
    {
        int maxindex=i;
        for(int j=i;j<darab;j++)    //és ebben a for ciklusban a sizeof függvényeket kicseréltem darabra és a hiba megoldódott
        {
            if (cars[maxindex].ar>cars[j].ar) maxindex=j; //A rendezés milyensége az itt található relációtól függ, jelenleg növekvő sorrendben rendez
        }
        temp=cars[i];
        cars[i]=cars[maxindex];
        cars[maxindex]=temp;
    }
}

void kiir()
{
    FILE *f;
    struct Auto car;

    f=fopen("car.dat","rb");
    while(fread(&car,sizeof(struct Auto),1,f)==1)
    {
        printf("rsz: %s tipus: %s ar: %d szin: %s\n",car.rsz,car.tipus,car.ar,car.szin);

    }

    fclose(f);

}
