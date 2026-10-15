package p1_sieve;

import java.util.ArrayList;

public class Eratosthenes {
    static void main() {
        int range = 10;

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= range; i++) {
            primes.add(i);
        }

        int i = 0;
        while (i < primes.size()) {
            int j = i + 1;
            while (j < primes.size()) {
                if (primes.get(j) % primes.get(i) == 0) {
                    primes.remove(j);
                }
                j++;
            }
            i++;
        }

        System.out.println(primes);
    }
}
