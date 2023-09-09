package com.askhvt.springsecurity.service.impl;

import com.askhvt.springsecurity.entity.Assembly;
import com.askhvt.springsecurity.entity.CarCity;
import com.askhvt.springsecurity.entity.SalonAvto;
import com.askhvt.springsecurity.repository.AssemblyRepository;
import com.askhvt.springsecurity.repository.CarCityRepository;
import com.askhvt.springsecurity.repository.SalonRepository;
import com.askhvt.springsecurity.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarCityRepository carCityRepository;

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Override
    public CarCity addCar(CarCity carCity) {
        return carCityRepository.save(carCity);
    }

    @Override
    public List<CarCity> getAllCars() {
        return carCityRepository.findAll();
    }

    @Override
    public CarCity getCar(Long id) {
        return carCityRepository.findByIdAndPriceGreaterThan(id, 0);
    }

    @Override
    public void deleteCar(CarCity carCity) {
        carCityRepository.delete(carCity);
    }

    @Override
    public CarCity saveCar(CarCity carCity) {
        return carCityRepository.save(carCity);
    }

    @Override
    public List<SalonAvto> getAllsalons() {
        return salonRepository.findAll();
    }

    @Override
    public SalonAvto addSalon(SalonAvto salonAvto) {
        return salonRepository.save(salonAvto);
    }

    @Override
    public SalonAvto saveSalon(SalonAvto salonAvto) {
        return salonRepository.save(salonAvto);
    }

    @Override
    public SalonAvto getSalon(Long id) {
        return salonRepository.getOne(id);
    }

    @Override
    public List<Assembly> getAllAssembly() {
        return assemblyRepository.findAll();
    }

    @Override
    public Assembly getAssembly(Long id) {
        return assemblyRepository.getOne(id);
    }

    @Override
    public Assembly saveAssembly(Assembly assembly) {
        return assemblyRepository.save(assembly);
    }

    @Override
    public Assembly addAssembly(Assembly assembly) {
        return assemblyRepository.save(assembly);
    }

    @Override
    public void deleteAssembly(Assembly assembly) {
        assemblyRepository.delete(assembly);
    }
}
