import java.util.ArrayList;
import java.util.Scanner;

public class IntcodeComp {
    public static int run(ArrayList<Integer> initialList, int noun, int verb){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>(initialList);
        list.set(1, noun); // day 2
        list.set(2, verb); // day 2

        int instructLen;
        int pIncrease;

        for(int i = 0; i < list.size(); i += pIncrease) {
            boolean jump = false;
            String op = list.get(i).toString();
            int opCode;
            String params = "";

            // if opcode includes parameter modes, then split them up and label separately

            if (op.length() > 2) {
                opCode = Integer.parseInt(op.substring(op.length() - 2));
                params = op.substring(0, op.length() - 2);
            }else{
                opCode = Integer.parseInt(op);
            }
            instructLen = numParams(opCode);

            int[] mode = new int[instructLen];
            int[] p = new int[instructLen];

            // extend params string to include missing zeros

            if (params.length() < instructLen && params.length() != 0) {
                for (int j = 0; j <= mode.length - params.length(); j++)
                    params = "0" + params;
            }

            // iterate through the entire instruction

            for (int j = 0; j < instructLen; j++ ) {

                // if the instruction includes parameter modes, change the mode from the default 0

                if(op.length() > 2)
                    mode[j] = Character.getNumericValue(params.charAt(params.length() - j - 1));

                // set parameters based on mode && opcode

                if(j != instructLen - 1 && mode[j] == 0){
                    // position mode, default
                    // the -1 after instructLen is meant for opcodes whose last parameter points to a position
                    // in which a value will be stored
                    p[j] = list.get(list.get(i + j + 1));
                }else if(j == instructLen - 1 || mode[j] == 1) { // immediate mode
                    p[j] = list.get(i  + j + 1);

                    // if opcode is 4, 5, or 6 and the mode was supposed to be position, revoke the line above
                    // IDEA: if, in the future we do not run into more parameter modes, we can set all parameters
                    // to be in immediate mode, and iterate through mode[] and alter a few with list.get(p[])
                    // rip... looked a little further and saw "relative mode"

                    if (mode[j] == 0) {

                        // these opcodes are exceptions because their last parameter do not contain
                        // a position in which to store a value

                        if (opCode == 4 || opCode == 5 || opCode == 6) {
                            p[j] = list.get(list.get(i + j + 1));
                        }
                    }
                }
            }

            if(opCode == 1){
                list.set(p[2], p[0] + p[1]);
            }else if(opCode == 2){
                list.set(p[2], p[0] * p[1]);
            }else if(opCode == 3){
                int inp = sc.nextInt();
                list.set(p[0], inp);
            }else if(opCode == 4){
                System.out.println(p[0]);
            }else if(opCode == 5){
                if (p[0] != 0){
                    i = p[1];
                    jump = true;
                }
            }else if(opCode == 6){
                if (p[0] == 0) {
                    i = p[1];
                    jump = true;
                }
            }else if(opCode == 7){
                if (p[0] < p[1]) list.set(p[2], 1);
                else list.set(p[2], 0);
            }else if(opCode == 8){
                if (p[0] == p[1]) list.set(p[2], 1);
                else list.set(p[2], 0);
            }else if(opCode == 99){
                break;
            }else{
                System.out.println("Bad opcode");
                break;
            }
            if (!jump){
                pIncrease = instructLen + 1;
            }else{
                pIncrease = 0;
            }
        }
        return list.get(0);
    }

    private static int numParams(int code){
        if (code == 1 || code == 2 || code == 7 || code == 8) return 3;
        else if (code == 5 || code == 6) return 2;
        else if (code == 3 || code == 4) return 1;
        else return 0;
    }
}
