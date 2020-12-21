package com.company.springmvcweb;


import com.company.springmvcweb.data.Course;
import com.company.springmvcweb.data.CourseRepository;
import com.company.springmvcweb.dto.CourseSearchDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParticipantController {

    private CourseRepository repo;

    public ParticipantController() {
        repo = new CourseRepository();
    }

    @GetMapping("/courses")
    public String owner(Model model) {

        var items = repo.getAllCourses();

        model.addAttribute("title", "Courses");
        model.addAttribute("courses", items);
        return "courses";
    }


//    @RequestMapping(value="/user", method = RequestMethod.POST)
//    public @ResponseBody String updateUser(@RequestBody User user){
//        return userService.updateUser(user).getId();
//    }

    @PostMapping("/courses")
    public String searchCourses(@ModelAttribute("searchDto") CourseSearchDto dto, Model model) {

        var items = repo.getCoursesPerIndustryAndOrLevel(dto);

        model.addAttribute("title", "Courses");
        model.addAttribute("courses", items);

        return "courses";
    }

//    @PostMapping("/owners")
//    public String searchOwners(@ModelAttribute("searchDto") OwnerSearchDto searchDto, Model model) {
//
//        var repo = new CarsRepository();
//        var items = repo.getOwners();
//
//        model.addAttribute("title", "Owners");
//        model.addAttribute("owners", items);
//
//        return "owners";
//    }
//
    @GetMapping("/participants")
    public String participant(Model model) {

        var items = repo.getAllParticipants();

        model.addAttribute("title", "Participants");
        model.addAttribute("participants", items);

        return "participants";
    }


        @GetMapping("/courses/{id}")
    public String editCar(@PathVariable int id, Model model) {


        var course = repo.getCourseById(id);
        model.addAttribute("title", course != null ? course.getTitle(): "");
        model.addAttribute("course", course);
        model.addAttribute("id", id);

        return "courses_details";
    }
}


    //-------------------
//
//
//    @PostMapping("/cars")
//    public String searchCars(@ModelAttribute("searchDto") CarSearchDto dto, Model model) {
//        var items = repo.getCars(dto);
//
//        model.addAttribute("title", "Cars");
//        model.addAttribute("cars", items);
//
//        return "cars";
//    }
//
//    @GetMapping("/cars/{id}")
//    public String editCar(@PathVariable int id, Model model) {
//
////        var test2 = repo.getCar(id);
////
////        var test = (Car)test2;
//
////        model.addAttribute("title", test != null ? test.getNumber() : "");
////        model.addAttribute("car", test);
//
//        model.addAttribute("title", "Cars - edit");
//        model.addAttribute("car", new Car());
//        model.addAttribute("id", id);
//
//        return "cars_edit";
//    }
//}
