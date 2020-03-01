package test.com.example.mypackage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Map1 {
    public final static String [][] testData1 = {
            { "Happy", "Cheerful disposition" },
            { "Sleepy", "Prefers dark, quiet places" },
            { "Grumpy", "Needs to work on attitude" },
            { "Doc", "Fantasizes about advanced degree" },
            { "Dopey", "'A' for effort" },
            { "Sneezy", "Struggles with allergies" },
            { "Bashful", "Needs self-esteem workshop" }

    };

    public final static String [][] testData2 = {
            { "Belligerent", "Disruptive influence" },
            { "Lazy", "Motivational problems" },
            { "Comatose", "Excellent behavior" } };

    // 相当于addAll()
    public static Map fill(Map m, Object[][] o) {
        for (int i = 0; i< o.length; i++) {
            m.put(o[i][0], o[i][1]);
        }
        return m;
    }

    // 产生a set of the keys
    public static void printKeys(Map m) {
        System.out.println("Size = " + m.size() + ", Keys: " +  m.keySet());
    }

    // Producing a Collection of the values:
    public static void printValues(Map m) {
        System.out.print("Values: " + m.values());
    }

    public static void print(Map m) {
        Collection entries = m.entrySet();
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            System.out.println("Key = " + e.getKey() + ", Value = " + e.getValue());
        }
    }

    public static void main(String[] args) {
        Map m = new HashMap();
        fill(m, testData1);
        fill(m, testData2);
        printKeys(m);
        printValues(m);
        print(m);
    }



}
