package taskone;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ip1, ip2;

        System.out.println("exit - выход");
        do {
            System.out.print("Введите ip1: ");
            ip1 = in.nextLine();
            if (Objects.equals(ip1, "exit")) break;
            System.out.print("Введите ip2: ");
            ip2 = in.nextLine();
            if (!Objects.equals(ip2, "exit")) {
                try {
                    IPrange.ShowRange(ip1, ip2);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } while (!Objects.equals(ip2, "exit"));
    }
}
