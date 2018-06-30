package start;

public class Chance {

    public static boolean of(double d){
        double r = Math.random();

        System.out.println("chance: " + d + " " + r + " " + (r <= d));

        return r <= d;
    }
}
