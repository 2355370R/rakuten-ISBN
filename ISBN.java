import java.util.*;

public class ISBN {

  public static void main(String[] args){
    System.out.print("Enter Product ID : ");
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    List<Integer> list = new ArrayList<Integer>();
    for(char c : input.substring(3).toCharArray()) {    //convert string input to integer list excluding 1st three characters
      list.add(Integer.parseInt(String.valueOf(c)));
    }
    int productIdSum = productSum(list);//get the sum of the product id
    int modVal = productIdSum % 10; //intial checksum using prime number 10 to get last number
    list.add(modVal);
    int sumMod11 = modulusISBN(list,9);//check with modulus 11
    if(sumMod11 % 11 == 0){
      System.out.println("ISBN: "+toStringISBN(list));
    }else{
      list.remove(9);//removing value at index position since it does not give 0 when modulus performed on 11
      list.add(10); //adding integer value 10 to list
      sumMod11 = modulusISBN(list,9);
      if(sumMod11 % 11 == 0){
        list.remove(9);// removing value of 10 at index 9 which is replaced with x in printing
        System.out.println("ISBN: "+toStringISBN(list) + "x");
      }else{
        //take the ans*11 (subtract it from the previous sum from mod11for9numbers)
        //do the check again to make sure % 11 == 0
        list.remove(9);// removing value of 10 at index 9
        int sumMod11NineDigits = modulusISBN(list,8);
        if(sumMod11NineDigits % 11 == 0){
          list.add(0);//just add the list as 0
          System.out.println("ISBN: "+toStringISBN(list));
        }else{ //ensuring correct checksum in differences to have a modulus return of zero
          sumMod11NineDigits = modulusISBN(list,8);
          int divide = sumMod11NineDigits/11;
          int difference = 11*(divide+1) - sumMod11NineDigits; //A(N+1) - B.
          list.add(difference);//list.add the difference
          sumMod11 = modulusISBN(list,9);
          if(sumMod11 % 11 == 0)
            System.out.println("ISBN: "+toStringISBN(list));
        }
      }
    }
  }

  public static String toStringISBN(List<Integer> list) { //print ISBN string
    String output = "";
    for(int i: list)
    output += "" + Integer.toString(i);
    return output;
  }

  public static int productSum(List<Integer> list) { // sum up input integer values
    int sum = 0;
    for (int i: list)
    sum += i;
    return sum;
  }

  public static int modulusISBN(List<Integer> list,int condition) { //condition 8 for 9 numbers and conditon 9 for 10 numbers
    int sum = 0;
    int[] intArray = new int[]{10,9,8,7,6,5,4,3,2,1};//multiplier
    for(int i = 0; i<=condition; i++)
      sum += intArray[i]*list.get(i);// sums up values computed from inputs in List against integers in array
    return sum;
  }

}
