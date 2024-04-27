package com.perdidoseachados.perdidoseachados;

import java.io.*;
import java.util.*;

class Radix {

    // Encontra o maior elemento em um array de inteiros
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // Ordena um array usando ordenação por contagem
    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Conta a frequência de cada dígito no expoente especificado
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Constrói prefixo somas para contagem de dígitos
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Ordena o array usando a contagem de dígitos
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copia o array ordenado para o array original
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // Imprime um array de inteiros
    static void print(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        int arr[] = { 170, 45, 75, 90, 802, 24, 2, 66 };
        int n = arr.length;

        // Ordena o array usando Radix Sort
        radixsort(arr, n);

        // Imprime o array ordenado
       print(arr, n);
       /*
        int numero = 12345;
        int divisor = 1;

        while (numero > 0) {
            int digito = numero % 10;
            System.out.print(digito + " "); // Imprime os dígitos na ordem inversa (5 4 3 2 1)
            numero /= 10;
            divisor *= 10;
        }*/
    }

    // Ordena um array de inteiros usando Radix Sort
    static void radixsort(int arr[], int n) {
        int m = getMax(arr, n); // Encontra o maior elemento

        // Ordena o array por dígitos, começando do menos significativo
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }
}
