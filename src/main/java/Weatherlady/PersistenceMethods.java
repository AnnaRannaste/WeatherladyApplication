//package Weatherlady;
//
//import org.hibernate.cfg.AvailableSettings;
//
//import java.lang.reflect.Field;
//
//public class PersistenceMethods {
//    public static void main(String[] args) {
//        Field[] fields = AvailableSettings.class.getDeclaredFields();
//
//        for (Field fld : fields) {
//
//
//            try {
//                System.out.println(fld.get(null));
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//
//            }
//        }
//    }  }