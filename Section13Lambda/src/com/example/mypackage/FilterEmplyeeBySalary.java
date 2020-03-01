package com.example.mypackage;

public class FilterEmplyeeBySalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 200;
    }
}
