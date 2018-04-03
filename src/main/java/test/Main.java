package test;

import test.objects.Str;


public class Main {
    public static void main(String[] args) {

         Str s=new Str();

        for (int i = 0; i < 11; i++) {
            s.addInt(i);
        }
        System.out.println(s);
    }
}
