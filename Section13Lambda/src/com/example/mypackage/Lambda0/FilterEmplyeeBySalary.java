package com.example.mypackage.Lambda0;

public class FilterEmplyeeBySalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee e) {
        return e.getSalary() >= 200;
    }
}
