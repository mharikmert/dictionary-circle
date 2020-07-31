package DictionaryCircle;
public class CodeWood {
    public static void main(String []args){
        int [] numerals = {1,9,6,1,7,3,6,4,8,0};
        int reverseNumerals [] = reverse(numerals);
        for(int i= 0; i < reverseNumerals.length-1; i++){
            System.out.println(reverseNumerals[i]);
        }
    }
    public static int [] reverse(int [] array ){
        int [] reversedArray = new int [array.length + 1];
        for(int i = 0; i < array.length; i++){
            reversedArray[i] = array[array.length-i-1];
        }
        reversedArray[array.length] = array[array.length-1];
        return reversedArray;
    }
}
