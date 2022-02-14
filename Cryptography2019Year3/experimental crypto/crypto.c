#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "bigint.h"

 int gcd(int, int);

int main(){
    
    int gCd;
    
    gCd = gcd(4864, 3458);
    printf("");
    printf("%d\n", gCd);
    
    return 0;
}

int gcd(int a, int b){
    
    int rem;
    
    do {
        
        if (a >= b){
            
            rem = a % b;
            a = b;
            b = rem;
            
            if (rem == 0){
                
                return a;
            }
        }else{
            printf("a must be larger than b");
            return 0;
        } 
    
    }while (a != 0 || b != 0);
    
    return 0;
}