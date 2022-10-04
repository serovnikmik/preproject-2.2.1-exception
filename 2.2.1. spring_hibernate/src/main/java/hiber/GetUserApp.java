package hiber;

import hiber.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hiber.model.User;

import hiber.service.UserService;

public class GetUserApp {

    public static void getUserByCarInfo(){

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        String carModel = "Car Model 1";
        int carSeries = 1;
        User user = userService.getUserByCarInfo(carModel, carSeries);
        System.out.println(user);
    }

}
