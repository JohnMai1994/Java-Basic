package com.example.mypackage.StreamPractice;

import org.junit.Before;
import org.junit.Test;


import java.util.*;
import java.util.stream.Stream;

public class TestTransaction {

    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader trader1 = new Trader("John", "China");
        Trader trader2 = new Trader("Mario", "Milan");
        Trader trader3 = new Trader("Alan", "Koren");
        Trader trader4 = new Trader("Brian", "Japan");
        Trader trader5 = new Trader("Jessica", "China");

        transactions = Arrays.asList(
                new Transaction(trader1, 2015, 300),
                new Transaction(trader2, 2015, 390),
                new Transaction(trader3, 2015, 3050),
                new Transaction(trader4, 2005, 3300),
                new Transaction(trader3, 2015, 200),
                new Transaction(trader5, 2025, 500)
        );
    }



    // 1. 找出2015年发生的所有交易，并按交易额顺序排序（从低到高）
    @Test
    public void Test1() {
        transactions.stream()
                .filter((transaction -> transaction.getYear() == 2015))
                .sorted((x, y) -> Integer.compare(x.getValue(), y.getValue()))
                .forEach(System.out::println);
    }

    // 2. 交易员都在哪些不同的城市工作
    @Test
    public void Test2() {
            transactions.stream()
                .map((transaction -> transaction.getTrader().getCity()))
                .distinct()
                .forEach(System.out::println);
    }

    // 3. 查找所有来自china的交易员，并按照姓名排序
    @Test
    public void Test3() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("China"))
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted((x, y) -> x.compareTo(y))
                .forEach(System.out::println);

    }

    // 4. 返回所有交易员姓名字符串，按字母顺序排序
    @Test
    public void Test4() {
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .forEach(System.out::println);

        System.out.println("-----------------------");
        String str = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .reduce("", String::concat);
        System.out.println(str);
        System.out.println("-----------------------");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted((s1,s2 )-> s1.compareToIgnoreCase(s2) )
                .forEach( System.out::print);

    }

    public static Stream<String > filterCharacter(String str) {
        List<String > list = new ArrayList<>();

        for (Character ch: str.toCharArray()) {
            list.add(ch.toString());
        }
        return list.stream();
    }


    // 5. 有没有交易员是在china工作的？
    @Test
    public void test5() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("China"))
                .forEach(System.out::println);

        Boolean bl = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("CHina"));


    }

    // 6. 打印生活在中国的所有交易额
    @Test
    public void test6(){
       int value =  transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("China"))
                .map(Transaction::getValue)
                .reduce(0, (x, y) -> x + y);

        System.out.println(value);


    }

    // 7. 所有交易中，最高的交易额是多少？
    @Test
    public void test7() {
        Optional<Integer> highestTrande = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);

        System.out.println(highestTrande.get());

    }

    // 8. 找到交易额最小的交易
    @Test
    public void test8() {
        Optional<Transaction> transaction = transactions.stream()
                .min((t1,t2) -> Integer.compare(t1.getValue(), t2.getValue()));

        System.out.println(transaction.get());
    }
}


