package DictionaryCircle;
public class DictionaryCircle {
    //Node class of the linked list
    public class Node {
        int data;
        Node next;
        Node previous;

        public Node(int data) {
            this.data = data;
        }
    }
    //Declaring head and tail pointer as null.
    public static Node head = null;
    public static Node tail = null;

    //This function adds the new node at the end of the list.
    public void add(int data) {
        //Create new node
        Node newNode = new Node(data);
        //Checks if the list is empty.
        if (head == null) {
            //If list is empty, head and tail should point to new node.
            head = newNode;
            tail = newNode;
            newNode.next = tail;
        } else {
            tail.next = newNode;
            //New node will become new tail.
            tail = newNode;
            //Since it is circular linked list, tail will point to head.
            tail.next = head;
        }
    }
    static char [] vowels = {'a','e','ı','i','o','ö','ü','u'};
    static char [] consonants = {'b','c','ç','d','f','g','ğ','h','j','k','l','m','n','p','r','s','ş','t','v','y','z'};
    public static boolean randomBoolean() {
        return Math.random() < 0.5;
    }
    //void print method to see what is in the linked list
    public static void print(Node head) {
        Node current = head;
        do {
            System.out.print(" " + current.data);
            current = current.next;
        } while (current != head);
        System.out.println("\n");
    }
    public static boolean isVowel(String str, int i){
        for(char vowel : vowels)
            if(str.charAt(i) == vowel)
                return true;
        return false;
    }
    public static boolean isConsonant(String str, int i){
        for(char consonant : consonants)
            if(str.charAt(i) == consonant)
                return true;
        return false;
    }
    public static boolean isTurkish(String str){
        for(int i = 0; i< str.length(); i++){
            if(isVowel(str,i)){
                for(int j = i+1; j < str.length(); j++){
                    if(isVowel(str,j)) return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        for(int i = 0; i< 20; i++){
            String str = "", newStr = "";
            DictionaryCircle cl = new DictionaryCircle();
            //Adds data to the list clockwise
            //int clockwise = (int)(Math.random() * 2 + 1);
            if (randomBoolean()) {// True or false, the loop should be toward clock wise or counter-clock wise
                cl.add(1);cl.add(9);cl.add(6);cl.add(1);cl.add(7);cl.add(3);cl.add(6);cl.add(4);cl.add(8);cl.add(0);
            } else {//toward to the other side
                cl.add(1);cl.add(0);cl.add(8);cl.add(4);cl.add(6);cl.add(3);cl.add(7);cl.add(1);cl.add(6);cl.add(9);
            }
            Node current = head;
            int orderTop = (int)(Math.random() * 9 + 1);
            int startPoint = 0;
            while(startPoint < orderTop){
                head = current;
                current = current.next;
//                System.out.println("order Top : " + orderTop + "current: "+current.data);
                startPoint++;
            }
            int [] numbers = new int [6];
            int numberCounter = 0, oneDigitCounter = 0;
            while(numberCounter < 6){
                if(randomBoolean() && oneDigitCounter < 2){//one digit situation
                    numbers[numberCounter] = current.data;
                    oneDigitCounter++;
                }else { //two digit situation
                    numbers[numberCounter] = current.data *10 + current.next.data;
                    current = current.next;
                }
                current = current.next;
                numberCounter++;
            }
            for(int j = 0; j < numbers.length; j++){
                String units[] = {"", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz"};
                String tens[] = {"", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan"};
                if(numbers[j] >= 10){
                    str = tens[numbers[j] /10] + units[numbers[j] % 10];
                  newStr += randomBoolean() ? str.charAt(0) : str.charAt(str.length()-1);
                }else{
                    str = numbers[j] == 0 ? "sıfır" : units[numbers[j]];
                    newStr += randomBoolean() ? str.charAt(0) : str.charAt(str.length()-1);
                }
            }
            if(isTurkish(newStr))
                System.out.println(newStr);
            // Write a turkish word validation method
        }
    }
}