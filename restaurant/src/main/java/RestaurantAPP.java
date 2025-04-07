import models.Employee;
import models.RestaurantManage;
import models.enums.EmployeeRole;
import org.apache.log4j.PropertyConfigurator;

import java.util.Calendar;
import java.util.Date;


public class RestaurantAPP {



    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/java/configs/log4j.properties");

//        SwingUtilities.invokeLater(() -> {
//            new loginFrame().setVisible(true);
//        });
        RestaurantManage restaurantManage = new RestaurantManage("Pomidorek");

        Employee e1 = new Employee("Kacper", new Date(1993, Calendar.DECEMBER, 11), 30.5D, 3, EmployeeRole.WAITER);
        Employee e2 = new Employee("Kacper", new Date(1993, Calendar.DECEMBER, 11), 30.5D, 1, EmployeeRole.WAITER);
        restaurantManage.hireEmployee(e1);
        restaurantManage.hireEmployee(e2);
        System.out.println(restaurantManage.getEmployees());
        System.out.println(e1.getLevel());
    }
}
