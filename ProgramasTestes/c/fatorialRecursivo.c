#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {

    int num;
    printf("Entre com um número: ");
    scanf("%d", &num);
    printf("O fatorial de %d é %d.", num, fatorial(num));

    return (EXIT_SUCCESS);
}

int fatorial(int x) {
    if ((x == 0) || (x == 1))
        return 1;
    else
        return (x * fatorial(x - 1));
}
