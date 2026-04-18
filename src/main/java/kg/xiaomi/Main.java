package kg.xiaomi;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        String sequence = "0010101010001111011001111100";
        int max0 = 0, max1 = 0;
        int count0 = 0, count1 = 0;

        for (char c : sequence.toCharArray()) {
            if (c == '0') {
                count0++;
                count1 = 0;
            } else if (c == '1') {
                count1++;
                count0 = 0;
            }
            max0 = Math.max(max0, count0);
            max1 = Math.max(max1, count1);

        }
        System.out.println("0-" + max0);
        System.out.println("1-" + max1);
    }

}