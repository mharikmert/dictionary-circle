package DictionaryCircle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class DictionaryCircle {
    public class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
        }
    }
    public static Node head = null;
    public static Node tail = null;

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = tail;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }
    static char [] vowels = {'a','e','ı','i','o','ö','ü','u'};
    static char [] backs = {'a','ı','o','u'};
    static char [] fronts = {'e','i','ö','ü'};
    static char [] consonants = {'b','c','ç','d','f','g','ğ','h','j','k','l','m','n','p','r','s','ş','t','v','y','z'};
    public static boolean randomBoolean() {
        return Math.random() < 0.5;
    }
    /*public static void print(Node head) {
        Node current = head;
        do {
            System.out.print(" " + current.data);
            current = current.next;
        } while (current != head);
        System.out.println("\n");
    }*/
    /*
    public static boolean backneHarmony(String str){
        for(int k = 0, i = 0; k < backs.length && i< str.length(); k++,i++) {
            if(isVowel(str.charAt(i))){

            }
        }
    }
    */
    public static boolean isVowel(char ch){
        for(char vowel : vowels)
            if(ch == vowel)
                return true;
        return false;
    }
    public static boolean isConsonant(char ch ){
        for(char consonant : consonants)
            if(ch == consonant)
                return true;
        return false;
    }
    public static boolean isTurkish(StringBuilder str){
        for(int i = 0; i< str.length()-1; i++)
            if(isVowel(str.charAt(i)) && isVowel(str.charAt(i+1)))
                return false;
        for(int i = 0; i< str.length()-2; i++)
            if(i == 0 && isConsonant(str.charAt(i)) && isConsonant(str.charAt(i+1)) ||
            (isConsonant(str.charAt(i)) && isConsonant(str.charAt(i+1)) && isConsonant(str.charAt(i+2))))
                return false;
        //never ends with 'b', 'c', 'd', 'g'
        if (isConsonant(str.charAt(str.length()-2)) && isConsonant(str.charAt(str.length()-1)) ||
                (str.charAt(str.length()-1) == 'b') || str.charAt(str.length()-1) == 'c' ||
                str.charAt(str.length()-1) == 'd' || str.charAt(str.length()-1) == 'g')
            return false;
        //c, f, ğ, h, j, l, m, n, p, r, ş, v, z
        char f = str.charAt(0);
        if( f == 'c'|| f == 'f' || f == 'ğ' || f == 'h' || f == 'l' || f == 'm' || f == 'n' || f == 'p'
        || f == 'r' || f == 'ş' || f == 'v' || f == 'z' )
            return false;
        for(int i = 2; i < str.length(); i++)
            if(str.charAt(i) == 'o' || str.charAt(i) == 'ö')
                return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        for(int i = 0; i< 100000; i++){
            String str = "";
            StringBuilder newStr = new StringBuilder();
            DictionaryCircle cl = new DictionaryCircle();
            if (randomBoolean()) {
                cl.add(1);cl.add(9);cl.add(6);cl.add(1);cl.add(7);cl.add(3);cl.add(6);cl.add(4);cl.add(8);cl.add(0);
            } else {
                cl.add(1);cl.add(0);cl.add(8);cl.add(4);cl.add(6);cl.add(3);cl.add(7);cl.add(1);cl.add(6);cl.add(9);
            }
            Node current = head;
            int orderTop = (int)(Math.random() * 9 + 1);
            int startPoint = 0;
            while(startPoint < orderTop){
                head = current;
                current = current.next;
                startPoint++;
            }
            int [] numbers = new int [6];
            int numberCounter = 0, oneDigitCounter = 0;
            while(numberCounter < 6){
                if(randomBoolean() && oneDigitCounter < 2){
                    numbers[numberCounter] = current.data;
                    oneDigitCounter++;
                }else {
                    numbers[numberCounter] = current.data *10 + current.next.data;
                    current = current.next;
                }
                current = current.next;
                numberCounter++;
            }
            for (int number : numbers) {
                String[] units = {"", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz"};
                String[] tens = {"", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan"};
                if (number >= 10)
                    str = tens[number / 10] + units[number % 10];
                else
                    str = number == 0 ? "sıfır" : units[number];
                newStr.append(randomBoolean() ? str.charAt(0) : str.charAt(str.length() - 1));
            }
            if(isTurkish(newStr)){
                FileWriter fw  = new FileWriter("words.txt",true);
                fw.write(String.valueOf(newStr));
                fw.write("\n");
                fw.close();
            }
        }
    }
}