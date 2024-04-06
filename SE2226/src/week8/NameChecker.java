package week8;

public class NameChecker {

    public static boolean check(String name) {
        String regex = "^[A-Z][a-z]*( ?[A-Za-z])*[A-Za-z]+$";

        if (name == null || name.equals("") || name.length() == 0 || !name.matches(regex)) {
            return false;
        } else {
            return true;
        }
    }
}
