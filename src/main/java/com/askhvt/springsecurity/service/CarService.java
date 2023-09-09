package com.askhvt.springsecurity.service;

import com.askhvt.springsecurity.entity.Assembly;
import com.askhvt.springsecurity.entity.CarCity;
import com.askhvt.springsecurity.entity.SalonAvto;

import java.util.List;

public interface CarService {

    CarCity addCar(CarCity carCity);
    List<CarCity> getAllCars();
    CarCity getCar(Long id);
    void deleteCar(CarCity carCity);
    CarCity saveCar(CarCity carCity);

    List<SalonAvto> getAllsalons();
    SalonAvto addSalon(SalonAvto salonAvto);
    SalonAvto saveSalon(SalonAvto salonAvto);
    SalonAvto getSalon(Long id);

    List<Assembly> getAllAssembly();
    Assembly getAssembly(Long id);
    Assembly saveAssembly(Assembly assembly);
    Assembly addAssembly(Assembly assembly);
    void deleteAssembly(Assembly assembly);
}
