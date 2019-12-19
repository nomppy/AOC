public class Day4 {
    public static boolean meetsRequirements(String pwd){
        boolean increasing = true;
        boolean rep = true;
        boolean repeats = false;


        for (int i = 0; i < pwd.length() - 1
                && increasing; i++) {
            increasing = (int)pwd.charAt(i) <= (int)pwd.charAt(i + 1);
        }

        pwd = " " + pwd + " ";
        for (int i = 1; i < pwd.length() - 2; i ++) {
            rep = pwd.charAt(i) == pwd.charAt(i + 1);
            if (rep) {
                rep = pwd.charAt(i - 1) != pwd.charAt(i) && pwd.charAt(i) != pwd.charAt(i + 2);
            }
            repeats = repeats || rep;
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
