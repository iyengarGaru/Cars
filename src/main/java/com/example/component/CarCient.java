package com.example.component;


import com.example.Operations.Dao;
import com.example.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarCient {

    @Autowired
    Dao<Car> carDao;

    public void process(){

        Car car = Car.create("City", "Honda",2012);
        System.out.println("SAVING:"+car);
        carDao.save(car);

        Car car2 = Car.create("Swift", "Suzuki",2012);
        System.out.println("SAVING:"+car2);
        carDao.save(car2);

//        carDao.delete(1);

        List<Car> list = carDao.loadAll();
        System.out.println("Loaded all:" + list);

    }
}
