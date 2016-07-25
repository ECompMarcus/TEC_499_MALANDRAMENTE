#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    int num, f;
    printf("Entre com um número: ");
    scanf("%d", &num);
    printf("A série de Fibonacci para %d elementos é:\n", num);
    for (f = 1; f <= num; f++) {
        printf("%d, ", fibonacci(f));
    }

    return (EXIT_SUCCESS);
}

int fibonacci(int n) {
    if ((n == 1) || (n == 2))
        return 1;
    else
        return (fibonacci(n - 1) + fibonacci(n - 2));
}
