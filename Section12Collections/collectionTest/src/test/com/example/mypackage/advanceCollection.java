package test.com.example.mypackage;

import org.junit.Test;

import java.util.*;

public class advanceCollection {

    @Test
    public void test4() {
        List c = new ArrayList();
        for (int i = 0; i < 10; i++)
            c.add(Integer.toString(i));

        Iterator it = c.iterator();


        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.equals("3")) {
                System.out.println("remove");
                it.remove();
            } else {
                System.out.println(str);
            }
        }



    }






    static class Printer {
        static void printAll(Enumeration e){
            while (e.hasMoreElements()) {
                System.out.println(e.nextElement().toString());
            }
        }
    }

    @Test
    public void test3() {
        class Hamster {
            private int hamsterNumber;

            Hamster(int i) {
                hamsterNumber = i;
            }

            public String toString() {
                return "This is Hamster #" + hamsterNumber;
            }
        }

        Vector v = new Vector();
        for (int i =0; i< 3;i++) {
            v.addElement(new Hamster(i));
        }
        Printer.printAll(v.elements());
    }


    class Cat {
        private int catNumber;

        Cat(int i) {
            catNumber = i;
        }

        @Override
        public String toString() {
            return "This is Cat #" + catNumber;
        }

        void print(String msg) {
            if (msg != null) {
                System.out.println(msg);
            }
            System.out.println("Cat #" + catNumber);
        }
    }

    class Dog {
        private int dogNumber;

        Dog(int i) {
            dogNumber = i;
        }



        void print(String msg) {
            if (msg != null) {
                System.out.println(msg);
            }
            System.out.println("Dog #" + dogNumber);
        }
    }

    static class DogTrap{
        public static void caughtYa(Dog dog) {
            dog.print("Caught one ! ");

        }


    }

    class Gopher{
        private int gopherNumber;

        Gopher(int i) {
            gopherNumber = i;
        }
        void print(String msg) {
            if (msg != null) {
                System.out.println(msg);
            }
            System.out.println("Gopher #" + gopherNumber);
        }
    }

    static class GopherTrap{
        static void caughYa(Gopher g) {
            g.print("Caught one!");
        }
    }

    class GopherVector {
        private  Vector v = new Vector();

        public void addElement(Gopher g) {
            v.addElement(g);
        }

        public Gopher elementAt(int index) {
            return (Gopher) v.elementAt(index);
        }

        public int size() {
            return v.size();
        }
    }

    @Test
    public void test2() {
        GopherVector gophers = new GopherVector();
        for (int i =0; i < 3; i++) {
            gophers.addElement(new Gopher(i));
        }
    }



    @Test
    public void test1() {
        Vector cats = new Vector();
        for (int i = 0; i < 7; i++ ){
            cats.addElement(new Cat(i));
        }
        // 没问题在猫的类中加狗
        cats.addElement(new Dog(7));

        for (int i = 0; i < 8; i++ ){
            System.out.println("Free Cat: " + cats.elementAt(i));
//            DogTrap.caughtYa(cats.elementAt(i));
        }
        // 只有在运行的时候，才会返回error
    }


}
