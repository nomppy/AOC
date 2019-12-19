public class Day4 {
    public static boolean meetsRequirements(String pwd){
        boolean increasing = true;
        boolean rep = true;
        boolean repeats = false;


        for (int i = 0; i < pwd.length() - 1 && increasing; i++) {
            increasing = (int)pwd.charAt(i) <= (int)pwd.charAt(i + 1);
            rep = pwd.charAt(i) == pwd.charAt(i + 1); // if current char is the same as next char
            if(rep){
                for (int j = i + 1; j < pwd.length() - 1; j++) { // if next char is equal to the char after it
                    rep = pwd.charAt(j) != pwd.charAt(j + 1);
                    // if they happen to be equal than this series of repeating numbers is invalid
                }
            }
            repeats = repeats || rep; // once repeats is true, it cannot be switched to false
        }
        return increasing && repeats;
    }



    public static void main(String[] args){
        int min = 134792;
        int max = 675810;
        int length = 6;

        int ans = 0;

        int curr = min;
        while (curr <= max){
//            System.out.print(curr + " ");
            ans += meetsRequirements("" + curr) ? 1 : 0;
            curr++;
        }
        System.out.println(ans);


    }
    // the digits, going from left to right, never decrease
    // there must be at least 1 pair of adjacent digits that are the same


}
