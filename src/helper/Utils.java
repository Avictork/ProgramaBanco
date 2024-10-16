package helper;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    static NumberFormat nf = new DecimalFormat("R$ #,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public Utils() {
    }

    public static String dateParaString(Date data) {
        return sdf.format(data);
    }

    public static Date stringParaData(String data) {
        try{
            return Utils.sdf.parse(data);
        }catch(ParseException e) {
            return null;
        }
    }

    public static String doubleParaString(Double valor) {
        return nf.format(valor);
    }

    public static Double stringParaDouble(String valor) {
        try {
            return (Double) nf.parse(valor);
        } catch (ParseException var2) {
            return null;
        }
    }

    public static void pausar(int segundos) {
        try {
            TimeUnit.SECONDS.sleep((long) segundos);
        } catch (InterruptedException var2) {
            System.out.println("Erro ao pausar por " + segundos + " segundos.");
        }

    }
}
