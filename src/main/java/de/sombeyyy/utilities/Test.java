package de.sombeyyy.utilities;

import de.sombeyyy.utilities.data.tuple.Triplet;
import de.sombeyyy.utilities.data.tuple.Tuple;
import de.sombeyyy.utilities.data.tuple.Unit;

public class Test {

    public static void main(String... args) {
        Tuple one = Tuple.of(3);
        Tuple two = Tuple.of("Hallo", 4);
        Tuple three = Tuple.of(3, "hallo", 5.425);

        System.out.println(one.toString());
        System.out.println(two.toString());
        System.out.println(three.toString());

        Tuple four = ((Triplet) three).add(Tuple.of("Hallo", 34));
        System.out.println(four);
    }

}
