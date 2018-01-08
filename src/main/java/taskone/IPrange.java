package taskone;

import java.util.regex.Pattern;

public class IPrange {

    private static final String IP_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private static Pattern pattern;


    public static void ShowRange (String i1, String i2) throws Exception {
        long longIp1, longIp2;
        //проверка на null
        if (i1==null || i2==null) throw new Exception("Ошибка: пустое значение");
        //проверка на валидность ip
        pattern = Pattern.compile(IP_PATTERN);
        boolean valid1 = pattern.matcher(i1).matches();
        boolean valid2 = pattern.matcher(i2).matches();
        if (!valid1 || !valid2) throw new Exception("Ошибка: ip-адрес некорректен");

        //ip в long
        longIp1=IPtoLong(i1);
        longIp2=IPtoLong(i2);

        if (longIp1>longIp2) throw new Exception("Ошибка: ip1 > ip2");
        //ip обратно в String
        for (long i=longIp1+1; i<longIp2; i++) {
            System.out.printf(LongIPtoString(i)+"\n");
        }

    }

    protected static long IPtoLong (String stringIp) {
        String[] ip=stringIp.split("\\.");
        long i0 = Integer.parseInt(ip[0]);
        long i1 = Integer.parseInt(ip[1]);
        long i2 = Integer.parseInt(ip[2]);
        long i3 = Integer.parseInt(ip[3]);

        long res = i0*256*256*256+i1*256*256+i2*256+i3;
        return (res);
    }

    protected static String LongIPtoString(long ip) {
        int i0 = (int)ip/(256*256*256);
        if (i0<0) i0+=255;
        int i1 = (int)(ip - i0*(256*256*256))/(256*256);
        if (i1<0) i1+=255;
        int i2 = (int)(ip - i0*(256*256*256) - i1*(256*256))/256;
        if (i2<0) i2+=255;
        int i3 = (int)(((ip - i0*(256*256*256)) - i1*(256*256)) - i2*256);
        return (Integer.toString(i0)+"."+Integer.toString(i1)+"."+Integer.toString(i2)+"."+Integer.toString(i3));
    }
}