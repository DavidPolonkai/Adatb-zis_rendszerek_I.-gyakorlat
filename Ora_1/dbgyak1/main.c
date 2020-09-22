#include <stdio.h>
#include <stdlib.h>

//Fájl létrehozása és írás bele, majd a beleírt szöveg nagy betűssé alakítása


int main()
{
    //beker();
    nagybetu();
    return 0;
}


void beker()
{

    FILE *f;
    char fn[40];
    char ch;

    printf("Add meg a file nevet: ");
    scanf("%s",&fn);

    f=fopen(fn,"w");

        printf("Add meg a file szoveget: ");
        while((ch=getchar())!='#')
        {
            fputc(ch,f);
        }
    fclose(f);
}

void nagybetu()
{
    FILE *f;
    char ch;

    f=fopen("a.txt","r+");
        while((ch=fgetc(f))!=EOF)
        {
            fseek(f,-1,SEEK_CUR);
            fputc(toupper(ch),f);

        }
    fclose(f);
}
