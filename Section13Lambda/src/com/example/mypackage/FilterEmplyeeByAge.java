package com.example.mypackage;

public class FilterEmplyeeByAge implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
