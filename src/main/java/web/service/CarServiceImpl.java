package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final List<Car> cars = new ArrayList<>();

    {
        cars.add(new Car("BMW", 5, "Black"));
        cars.add(new Car("Audi", 7, "White"));
        cars.add(new Car("Mercedes", 222, "Silver"));
        cars.add(new Car("Toyota", 50, "Red"));
        cars.add(new Car("Lada", 2107, "Blue"));
    }

    @Override
    public List<Car> getCars(Integer count) {
        if (count == null || count >= 5 || count < 0) {
            return cars;
        }
        return cars.subList(0, count);
    }
}