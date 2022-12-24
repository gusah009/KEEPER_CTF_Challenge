package acmicpc.hyeonmo._2022.keeper.fibonacci;

/*
 * Demonstration of fast Fibonacci algorithms (Java)
 * by Project Nayuki, 2017. Public domain.
 * https://www.nayuki.io/page/fast-fibonacci-algorithms
 */

public final class FastFibonacci {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java fastfibonacci N");
            System.exit(1);
            return;
        }
        long n = Long.parseLong(args[0]);
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        long startTime;
        startTime = System.nanoTime();
        long x = fastFibonacciDoubling(n);
        System.out.printf("Fast doubling: %d ms%n", (System.nanoTime() - startTime) / 1000000);
        System.out.printf("Answer: %d%n", x);
        System.out.println();
    }

    private static long fastFibonacciDoubling(long n) {
        long a = 0;
        long b = 1;
        for (long bit = Long.highestOneBit(n); bit != 0; bit >>>= 1) {
            // Loop invariant: a = F(m), b = F(m+1)

            // Double it
            long d = a * (2 * b - a);
            long e = a * a + b * b;
            a = d % 10_000_000;
            b = e % 10_000_000;

            // Advance by one conditionally
            if ((n & bit) != 0) {
                long c = a + b;
                a = b % 10_000_000;
                b = c % 10_000_000;
            }
        }
        return a % 10_000_000;
    }
}