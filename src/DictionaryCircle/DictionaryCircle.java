package DictionaryCircle;
import java.util.ArrayList;
public class DictionaryCircle {

    //Node class of the linked list
    public class Node{
        int data;
        Node next;
        Node previous;
        public Node(int data) {
            this.data = data;
        }
    }
    public class newNode{
        int data;
        newNode newNode;
        newNode previousNode;

        public newNode(int data){
            this.data = data;
        }
    }
    //Declaring head and tail pointer as null.
    public static Node head = null;
    public static Node tail = null;

    //This function adds the new node at the end of the list.
    public void add(int data){
        //Create new node
        Node newNode = new Node(data);
        //Checks if the list is empty.
        if(head == null) {
            //If list is empty, head and tail should point to new node.
            head = newNode;
            tail = newNode;
            newNode.next = tail;
        }

        else {
            //tail will point to new node.
            tail.next = newNode;
            //New node will become new tail.
            tail = newNode;
            //Since it is circular linked list, tail will point to head.
            tail.next = head;
        }
    }
    public static boolean clockWise(){return Math.random() < 0.5;}
    //void print method to see what is in the linked list
    public void print(){
        Node current = head;
        do{
            System.out.print(" " + current.data);
            current = current.next;
        }while(current != head);
    }
    public static void main(String[] args) {
        DictionaryCircle cl = new DictionaryCircle();
        //Adds data to the list clockwise
        //int clockwise = (int)(Math.random() * 2 + 1);
        if(clockWise()){// True or false, the loop should be toward clock wise or counter-clock wise
            //Addding argumnets that we want to the linked list.
            cl.add(1); cl.add(9); cl.add(6); cl.add(1); cl.add(7); cl.add(3); cl.add(6); cl.add(4); cl.add(8); cl.add(0);
        }
        else{//toward to the other side
            cl.add(1); cl.add(0); cl.add(8); cl.add(4); cl.add(6); cl.add(3); cl.add(7); cl.add(1); cl.add(6); cl.add(9);
        }

        /****
         * Linked list part over, now creating the word.
         ****/
        String first[] = {"sıfır","bir","iki","üç","dört","beş","altı","yedi","sekiz","dokuz"};
        String second[] = {"","on","yirmi","otuz","kırk","elli","altmış","yetmiş","seksen","doksan"};

        String str = null, newStr = "";
        char ch = '\0',currentCh = '\0', nextCh = '\0';
        ArrayList<String> wordList = new ArrayList<String>();

        Node current = head;
        // for the start point of the list
        int orderTop = (int)(Math.random() * 9 + 1);
        do{
            if(current.data == orderTop){
                //make current node head of list.
                head = current;
            }
            current = current.next;
        }while(current != head);

        //System.out.println("head: " + head.data);
        int var = 0, digitOneCounter = 0, digitTwoCounter = 0;

        //to create 6 numbers from 10 numbers
        while(var < 6){
            int digit = (int)(Math.random()* 2 + 1);
            //for  not being one digit number more than two
            if(digit == 1 && digitOneCounter == 2){
                //if variable definer passes 2, then digit would be two digit now and no more one digit number.
                digit = 2;
            }
            //for not being five two digit numbers
            if(digit == 2 && digitTwoCounter == 4){
                digit = 1;
            }
            //One digit condition
            if(digit == 1){
                str = first[current.data];
                //System.out.println("current data: " + current.data + " corrent next: " + current.next.data);
                current = current.next;
                digitOneCounter++;
            }
            //Two digit contdition
            else if(digit == 2){
                int newInt = current.data * 10 + current.next.data;
                //condition for writing of two digit number that contains from a number and zero.- 10 multiple numbers
                if(current.next.data == 0){
                    str = second[newInt/10];
                }
                //normal number writing rule.
                else str = second[newInt / 10] + first[newInt % 10];

                //due to the number has two digits, next number in the linked list should be two digits after.
                current = current.next.next;
                //   current.next.data =current.next.next.data;
                digitTwoCounter++;
            }
            //System.out.println(str);

            // Randomly picking first or last charachter of the numbers' writing
            int pick = (int)(Math.random()* 2 + 1);
            if(pick == 1)
                ch = str.charAt(0);
            else if(pick == 2)
                ch = str.charAt(str.length() - 1);

           /* for(int i = 0; i < str.length(); i++){
                currentCh = str.charAt(i); nextCh = str.charAt(i + 1);
                if(currentCh == 'a'){
                    switch(nextCh){
                        case 'o': break;
                    }
                }
            }*/

            //adding first or last charachters randomly to new String to create a new word.
            newStr += "" + ch;
            //System.out.println("number: " + str);
            var++;
        }
        System.out.println("the word is: " + newStr);
    }
}


