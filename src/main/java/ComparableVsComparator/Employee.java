package ComparableVsComparator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    public static final Employee DEFAULT_EMPLOYEE = new Employee(0, "Saurabh Verma");
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
