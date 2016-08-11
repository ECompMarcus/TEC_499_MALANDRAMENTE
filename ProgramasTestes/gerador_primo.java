#include<stdio.h>    //Biblioteca para entrada e saída de dados
#include<stdlib.h>  //Biblioteca para utilizar funções próprias do Sistema Operacional
main()
{
      int a=0,b,c,n,d;           //Declarando as variáveis
    	n = 10;
     	 d=n*(-1);
    
      do        
      {  a++;         
         c=0;
         for(b=1;b<a;b++)
             if(a%b==0)
             c++;
         if(c==1){
             printf("%i\n",a);      //Imprimindo os números primos
             d++;
                 }
      }while(d); 
}     
