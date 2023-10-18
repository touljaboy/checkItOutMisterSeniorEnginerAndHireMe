package pl.javastart.main;

import java.util.*;

/* CHALLENGE IN POLISH: Napisz program, który spośród tablicy liczb całkowitych wybierze takie pary następujących
 po sobie liczb, dla których suma jest najmniejsza lub największa. W przypadku, gdy istnieje kilka par,
  dla których suma jest najmniejsza lub największa, to użytkownik powinien zobaczyć je wszystkie.
   Jeżeli jakaś para liczb się powtarza, to powinna być uwzględniona tylko raz.
    Tablicę liczb przekaż jako argument wywołania programu.

Przykład 1

wejście: [1, 2, 3, 4, 5]
wyjście: Najmniejsza para [1, 2], największa para [4, 5]

Przykład 2

wejście: [-5, 0, 5, -10, 5, 15]
wyjście: Najmniejsze pary [-5, 0], [5, -10], [-10, 5], największa para [5, 15]

Przykład 3

wejście: [-5, 0, 10, -5, 0, 5, 5]
wyjście: Najmniejsze pary [-5, 0], największe pary [0, 10], [5, 5]

CHALLENGE IN ENGLISH: Write code for a program, which will chose such pairs of consecutive integers from an array,
 for which the sum is the biggest or the smallest. All of the pairs should be printed out
 in an instance when there are several pairs of integers, where the sum is the biggest or the smallest.
 Duplicate pairs should be printed only once. Array of ints should be the argument of starting the program.

Example 1

In: [1, 2, 3, 4, 5]
Out: Smallest pair [1, 2], Biggest pair [4, 5]

Example 2

In: [-5, 0, 5, -10, 5, 15]
Out: Smallest pair [-5, 0], [5, -10], [-10, 5], Biggest pair [5, 15]

Example 3

In: [-5, 0, 10, -5, 0, 5, 5]
Out: Smallest pair [-5, 0], Biggest pair [0, 10], [5, 5]

 */

//I am aware that some discord mod type programmer might consider my code a disgrace, however I believe it is a beatiful
//solution to the aforementioned problem, which also showcases my knowledge of Collections found in java lib. hire me
public class ArrayNumberChallenge {
    static Integer[] smallestNumbers = new Integer[2];
    static Integer[] biggestNumbers = new Integer[2];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int[] numbers = createIntArrayFromUserInput();
        performChallengeFromIntArray(numbers);
    }

    private static void performChallengeFromIntArray(int[] numbers) {
        //TreeMap of Number Pairs created from array in the Main section
        TreeMap<Integer[], Integer> treeMap = createTreeMapNumberPairsFromArray(numbers);
        //twoFirstNumbers is an Array used to find the initial smallestSum value. It just makes sense in the
        //context of this exercise
        Integer[] twoFirstNumbers = {numbers[0], numbers[1]};
        int biggestSum = 0;
        int smallestSum = treeMap.get(twoFirstNumbers);

        //The following code is used to find the biggest and smallest sum of numbers in a TreeSet and Also assigns
        // the mentioned numbers to the correct static Integer array
        for(Map.Entry<Integer[], Integer> entry : treeMap.entrySet()) {
            if(biggestSum < entry.getValue()) {
                biggestNumbers[0] = entry.getKey()[0];
                biggestNumbers[1] = entry.getKey()[1];
                biggestSum = entry.getValue();
            }
            if (smallestSum > entry.getValue()) {
                smallestNumbers[0] = entry.getKey()[0];
                smallestNumbers[1] = entry.getKey()[1];
                smallestSum = entry.getValue();
            }
        }
        //The following code prints the result
        for(Map.Entry<Integer[], Integer> entry : treeMap.entrySet()) {
            if(entry.getValue() == biggestSum) {
                System.out.println("Biggest Sum Pair: " + Arrays.toString(entry.getKey()));
            }
            if(entry.getValue() == smallestSum) {
                System.out.println("Smallest Sum Pair " + Arrays.toString(entry.getKey()));
            }
        }
    }
    //The following code is responsible for creating a TreeMap from an int array
    //It uses the array as KEY (distinct pairs of numbers need to be found, keys in TreeMap are always distinct)
    //and sum of the two numbers as its value
    private static TreeMap<Integer[], Integer> createTreeMapNumberPairsFromArray(int[] numbers) {
        TreeMap<Integer[], Integer> treeMap = new TreeMap<>(Arrays::compare);
        int c = 0;
        while(c+1<numbers.length) {
            Integer[] pair = {numbers[c], numbers[c+1]};
            treeMap.put(pair, numbers[c] + numbers[c+1]);
            c++;
        }
        return treeMap;
    }

    private static int[] createIntArrayFromUserInput() {
        System.out.println("How many numbers do you wish to enter? ");
        int arrayLength = sc.nextInt();
        sc.nextLine();
        int[] numbers = new int[arrayLength];

        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Enter the next number");
            numbers[i] = sc.nextInt();
            sc.nextLine();
        }
        return numbers;
    }

}
