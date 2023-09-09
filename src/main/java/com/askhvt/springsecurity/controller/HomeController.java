package com.askhvt.springsecurity.controller;

import com.askhvt.springsecurity.dto.UserDto;
import com.askhvt.springsecurity.entity.Assembly;
import com.askhvt.springsecurity.entity.CarCity;
import com.askhvt.springsecurity.entity.SalonAvto;
import com.askhvt.springsecurity.entity.User;
import com.askhvt.springsecurity.repository.UserRepository;
import com.askhvt.springsecurity.service.CarService;
import com.askhvt.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    private UserRepository userRepository;

    @GetMapping(value = "/index")
    public String index(Model model){
        List<CarCity> cars = carService.getAllCars();
        model.addAttribute("kolik", cars);
//        User currentUser = userService.currentUser();
//        model.addAttribute("currentUser", currentUser);
        List<SalonAvto> salonAvto = carService.getAllsalons();
        model.addAttribute("salon", salonAvto);
        return "index";
    }

    @GetMapping(value = "/about")
    public String about(Model model){
        return "about";
    }

    @GetMapping(value = "/addcar")
    public String addCar(Model model){
        List<SalonAvto> salonAvto = carService.getAllsalons();
        model.addAttribute("salon", salonAvto);
        return "addcar";
    }


    @PostMapping(value = "/addcar")
    public String addCar(@RequestParam(name = "salon_id", defaultValue = "0") Long id,
                         @RequestParam(name = "car_name", defaultValue = "EMPTY") String name,
                         @RequestParam(name = "car_years", defaultValue = "0") int year,
                         @RequestParam(name = "car_country", defaultValue = "CITY") String country,
                         @RequestParam(name = "car_price", defaultValue = "0") int price){

        SalonAvto cnt = carService.getSalon(id);
        if (cnt!=null){

            CarCity carCity = new CarCity();
            carCity.setName(name);
            carCity.setYear(year);
            carCity.setCountry(country);
            carCity.setPrice(price);
            carCity.setSalonAvto(cnt);

            carService.addCar(carCity);
        }

        return "redirect:/index";

    }

    @GetMapping(value = "/details/{idsh}")
    public String details(Model model, @PathVariable(name = "idsh" ) Long id){
        CarCity car = carService.getCar(id);
        model.addAttribute("car", car);


        List<SalonAvto> salonAvto = carService.getAllsalons();
        model.addAttribute("salon", salonAvto);

        List<Assembly> assemblies = carService.getAllAssembly();
        model.addAttribute("assembly", assemblies);
        return "details";
    }

    @GetMapping(value = "/editcar/{idsh}")
    public String edit(Model model, @PathVariable(name = "idsh" ) Long id){
        CarCity car = carService.getCar(id);
        model.addAttribute("car", car);


        List<SalonAvto> salonAvto = carService.getAllsalons();
        model.addAttribute("salon", salonAvto);

        List<Assembly> assemblies = carService.getAllAssembly();
        assemblies.removeAll(car.getAssemblies());

        model.addAttribute("assembly", assemblies);


        return "editcar";
    }

    @PostMapping(value = "/savecar")
    public String saveCar(@RequestParam(name = "car_id", defaultValue = "0") Long id,
                          @RequestParam(name = "salon_id", defaultValue = "0") Long salonId,
                          @RequestParam(name = "car_name", defaultValue = "EMPTY") String name,
                          @RequestParam(name = "car_years", defaultValue = "0") int year,
                          @RequestParam(name = "car_country", defaultValue = "CITY") String country,
                          @RequestParam(name = "car_price", defaultValue = "0") int price){
        CarCity car = carService.getCar(id);
        if (car!=null){
            SalonAvto cnt = carService.getSalon(salonId);
            if (cnt!=null){
            car.setName(name);
            car.setYear(year);
            car.setCountry(country);
            car.setPrice(price);
            car.setSalonAvto(cnt);
            carService.saveCar(car);
            }
        }
        return "redirect:/index";
    }

    @PostMapping(value = "/deletecar")
    public String deleteCar(@RequestParam(name = "car_id", defaultValue = "0") Long id){
        CarCity car = carService.getCar(id);
        if (car!=null){
            carService.deleteCar(car);
        }
        return "redirect:/index";
    }



    @PostMapping(value = "/assingassembly")
    public String assingAssembly(@RequestParam(name = "car_id") Long carId,
                                 @RequestParam(name = "assembly_id") Long assemblyId){

        Assembly as = carService.getAssembly(assemblyId);
        if (as!=null){
            CarCity carCity = carService.getCar(carId);
            if (carCity!=null){
                List<Assembly> assemblies = carCity.getAssemblies();
                if (assemblies==null){
                    assemblies = new ArrayList<>();
                }
                assemblies.add(as);

                carService.saveCar(carCity);
                return "redirect:/editcar/" + carId+"#assemblyDiv";
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/unassingassembly")
    public String unAssingAssembly(@RequestParam(name = "car_id") Long carId,
                                 @RequestParam(name = "assembly_id") Long assemblyId){

        Assembly as = carService.getAssembly(assemblyId);
        if (as!=null){
            CarCity carCity = carService.getCar(carId);
            if (carCity!=null){
                List<Assembly> assemblies = carCity.getAssemblies();
                if (assemblies==null){
                    assemblies = new ArrayList<>();
                }
                assemblies.remove(as);

                carService.saveCar(carCity);
                return "redirect:/editcar/" + carId+"#assemblyDiv";
            }
        }
        return "redirect:/";
    }


    /////


    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "register";

    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }



}
