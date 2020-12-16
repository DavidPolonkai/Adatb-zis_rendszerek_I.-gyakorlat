#include <stdio.h>
#include <stdlib.h>
#include "sqlite3.h"
#include <string.h>


int main()
{
    printf("%s\n",sqlite3_libversion());

    sqlite3 *db;
    int success;
    char* error;

    char id[30];
    char name[30];
    int ar;
    char sar[30];

    success=sqlite3_open("db",&db);
    if(success){
        printf("Hiba");
        exit(-1);
    }
    else printf("Jó");

    char *sql="DROP table if exists Cars; create table cars(id int,name text, price int); insert into cars values(1,'Audi',123123);";
    success = sqlite3_exec(db,sql,0,0,&error);
    if (success!=SQLITE_OK){
        printf("Hiba %s",error);

        sqlite3_free(error);
        sqlite3_close(db);
    }

    printf("id=");
    scanf("%s",&id);
    printf("name=");
    scanf("%s",&name);
    printf("ar=");
    scanf("%d",&ar);
    sprintf(sar,"%d",ar);

    char sqlstring[200];
    strcpy(sqlstring,"Insert into cars values('");
    strcat(sqlstring,id);
    strcat(sqlstring,"','");
    strcat(sqlstring,name);
    strcat(sqlstring,"',");
    strcat(sqlstring,sar);
    strcat(sqlstring,");");

    printf("\n%s",sqlstring);

    success=sqlite3_exec(db,sqlstring,0,0,&error);



    printf("\n%s",error);
    printf("Success");
    sqlite3_close(db);
    return 0;
}
