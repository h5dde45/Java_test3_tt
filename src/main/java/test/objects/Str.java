package test.objects;

import org.springframework.stereotype.Component;

@Component
public class Str {
    private String s="";

    public String addInt(int i) {
        s += i;
        return s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
