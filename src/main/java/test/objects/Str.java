package test.objects;

public class Str {
    private String s;

    public String addInt(int i) {
        s += i;
        return s;
    }

    public String getS() {
        return s;
    }
}