import java.util.ArrayList;

public class IntcodeComp {
    public static int run(ArrayList<Integer> initialList, int noun, int verb){
        ArrayList<Integer> list = new ArrayList<Integer>(initialList);
        list.set(1, noun);
        list.set(2, verb);
//        System.out.println(list);

        int instructLen;
        for(int i = 0; i < list.size(); i += instructLen + 1) {
            String op = list.get(i).toString();
            int opCode;
            String params = "";
            if (op.length() > 2) {
                opCode = Integer.parseInt(op.substring(op.length() - 2));
                params = op.substring(0, op.length() - 2);
            }else{
                opCode = Integer.parseInt(op);
            }
            instructLen = numParams(opCode);

            int[] mode = new int[instructLen];
            int[] p = new int[instructLen];

            if (params.length() < instructLen && params.length() != 0) {
                for (int j = 0; j < mode.length - params.length(); j++)
                    params = "0" + params;
            }

            for (int j = 0; j < instructLen; j++ ) {
                if(op.length() > 2)
                    mode[j] = Character.getNumericValue(params.charAt(params.length() - j - 1));
//                System.out.println("Mode " + j + ": " + mode[j]);
                if(j != instructLen - 1 && mode[j] == 0){ // position mode, default
                    p[j] = list.get(list.get(i + j + 1));
                }else if(j == instructLen - 1 || mode[j] == 1) { // immediate mode
                    p[j] = list.get(i  + j + 1);
                }
//                System.out.println("Param " + j + ": " + p[j]);
            }

            if(opCode == 1){
                list.set(p[2], p[0] + p[1]);
            }else if(opCode == 2){
                list.set(p[2], p[0] * p[1]);
            }else if(opCode == 3){
                list.set(p[0], p[0]);
            }else if(opCode == 4){
                System.out.println(p[0]);
            }else if(opCode == 99){
                break;
            }else{
                System.out.println("Bad opcode");
                break;
            }
        }
        return list.get(0);
    }

    private static int numParams(int code){
        if (code == 1 || code == 2) return 3;
        else if (code == 3 || code == 4) return 1;
        else return 0;
    }
}
