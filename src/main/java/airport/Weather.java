package airport;

public class Weather {
    public String generateConditions() {
        return Math.random() > 0.7 ? "Stormy" : "Sunny";
    }
}
