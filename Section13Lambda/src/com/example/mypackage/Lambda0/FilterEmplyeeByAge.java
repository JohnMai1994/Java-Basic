package com.example.mypackage.Lambda0;

public class FilterEmplyeeByAge implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee e) {
        return e.getAge() >= 35;
    }
}
