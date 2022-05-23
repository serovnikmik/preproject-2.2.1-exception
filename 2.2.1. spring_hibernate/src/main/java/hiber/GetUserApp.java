package hiber;

import hiber.config.AppConfig;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

import hiber.config.AppConfig;

import hiber.model.Car;
import hiber.model.User;

import hiber.service.UserService;

public class GetUserApp {

    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        String carModel = "Car Model 1";
        int carSeries = 1;
        User user = userService.getUserByCarInfo(carModel, carSeries);
        System.out.println(user.userInfo());
    }

}
