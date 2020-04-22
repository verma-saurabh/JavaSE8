package StreamAPI;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class StreamMatchExample {
    public static void main(String[] args) {
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("Nataraja G", "Accounts", 8000));
        empList.add(new Employee("Nagesh Y", "Admin", 15000));
        empList.add(new Employee("Vasu V", "Security", 2500));
        empList.add(new Employee("Amar", "Admin", 12500));

        boolean result = empList.stream().anyMatch(emp -> emp.getAccount().matches("Admin"));
        System.out.println(result);

        result = empList.stream().allMatch(emp -> emp.getAccount().matches("Admin"));
        System.out.println(result);


        result = empList.stream().noneMatch(emp -> emp.getAccount().matches("Admin"));
        System.out.println(result);


    }
}

@Getter
@Setter
class Employee {
    private String name;
    private String account;
    private Integer salary;

    public Employee(String name, String account, Integer salary) {
        this.name = name;
        this.account = account;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", salary=" + salary +
                '}';
    }
}