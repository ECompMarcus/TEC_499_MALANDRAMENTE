#include<stdio.h>    //Biblioteca para entrada e sa�da de dados
#include<stdlib.h>  //Biblioteca para utilizar fun��es pr�prias do Sistema Operacional
main()
{
      int a=0,b,c,n,d;           //Declarando as vari�veis
    	n = 10;
     	 d=n*(-1);
    
      do        
      {  a++;         
         c=0;
         for(b=1;b<a;b++)
             if(a%b==0)
             c++;
         if(c==1){
             printf("%i\n",a);      //Imprimindo os n�meros primos
             d++;
                 }
      }while(d); 
}     
