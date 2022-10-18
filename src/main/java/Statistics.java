
import java.util.ArrayList;

public class Statistics {

    FakeEmployeeDatabase fakeEmployeeDatabase;


    public Statistics() {
        fakeEmployeeDatabase = new FakeEmployeeDatabase();
        fakeEmployeeDatabase.fillDatabase();
    }

    public int getAverageSalary() {
        int totalSalary = 0;
        int counter = 0;
        for (Employee e : fakeEmployeeDatabase.get()) {
            totalSalary = totalSalary + e.getSalary();
            counter++;
        }
        return totalSalary / counter;
    }



}
