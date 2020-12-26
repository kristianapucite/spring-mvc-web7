package com.company.springmvcweb;


import com.company.springmvcweb.data.*;
import com.company.springmvcweb.data.CourseRepository;
import com.company.springmvcweb.data.Participant;
import com.company.springmvcweb.dto.CourseSearchDto;
import com.company.springmvcweb.dto.LoginSearchDto1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParticipantController {

    private CourseRepository repo;
    private ParticipantRepository repo1;

    public ParticipantController() {
        repo = new CourseRepository();
        repo1 = new ParticipantRepository();
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
    public String detailCourse(@PathVariable int id, Model model) {


        var course = repo.getCourseById(id);
        model.addAttribute("title", course != null ? course.getTitle() : "");
        model.addAttribute("course", course);
        model.addAttribute("id", id);

        return "courses_details";
    }

    @PostMapping("/courses/{id}/register")
    public String registerForCourses(@PathVariable int id, Model model) {

        var course = repo.getCourseById(id);

        //šis jāizlabo, jāsasaista ar login konta info!!!!!!!
        var participant = new Participant(0, "Kristaps", "Ozols", "kristaps@inbox.lv", "paroleirforsa", false);


        model.addAttribute("title", course != null ? course.getTitle() : "");
        model.addAttribute("course", course);
        model.addAttribute("participant", participant);
        model.addAttribute("name", participant.getName());
        model.addAttribute("surname", participant.getSurname());
        model.addAttribute("e-mail", participant.getEMail());

        repo1.registerForCourse(0, id, participant.getId());

        return "courses_details_register";
    }


    @GetMapping("/courses/{id}/register/confirm")
    public String confirmation(@PathVariable int id, Model model) {

        return "courses_details_register_confirm";
    }


    @GetMapping("/log_in")
    public String logIn(Model model) {


        var items = repo.getAllParticipants();

        model.addAttribute("title", "Log in");
        model.addAttribute("participants", items);
        return "log_in";
    }

    @GetMapping("/sign_up")
    public String signUp(Model model) {

        var items = repo.getAllParticipants();

        model.addAttribute("title", "Sign up");
        model.addAttribute("participants", items);

        return "sign_up";
    }

    @PostMapping("/user")
    public String logIn(@ModelAttribute("loginSearchDto") LoginSearchDto1 dto, Model model) {
        var user = repo1.logIn(dto.getEMail(), dto.getPassword());

        model.addAttribute("title", "User");
        model.addAttribute("user", user);

        var items = repo.getAllCourses();

        model.addAttribute("title", "Courses");
        model.addAttribute("courses", items);

        return "user";
    }

    @GetMapping("/user")
    public String getUser(Model model) {

        var items = repo.getAllParticipants();


        model.addAttribute("title", "User");
        model.addAttribute("participants", items);

        return "user";
    }

//    @PostMapping("/user/{id}")
////    public String saveCar(@PathVariable int id, @ModelAttribute("updateDto") CarUpdateDto dto, Model model) {
//    public ModelAndView registerParticipant(@PathVariable int id, @ModelAttribute("saveDto") ParticipantSaveDto dto, Model model) {
//
//        var participant = repo.getParticipantId(dto.getEMail());
//        var validator = new PasswordValidator();
//
//
//        if (dto.getName().isEmpty() || dto.getSurname().isEmpty() || dto.getEMail().isEmpty()
//                || dto.getPassword().isEmpty() || dto.getPassword1().isEmpty()){
//            throw new IllegalArgumentException("Please fill out all fields!");
//        }
//        if(participant != null) {
//            throw new IllegalArgumentException("Such email is already registered");
//        }
//        if (validator.validate(dto.getPassword(), dto.getPassword1())){
//            throw new IllegalArgumentException("Password requirements are not met or passwords do not match");
//        }
//
//        var newParticipant = new Participant();
//        newParticipant.setId(0);
//        newParticipant.setName(dto.getName());
//        newParticipant.setSurname(dto.getSurname());
//        newParticipant.setEMail(dto.getEMail());
//        newParticipant.setPassword(dto.getPassword());
//
//        repo1.register(newParticipant);
//
//        return new ModelAndView("redirect:/user/{id}");
//    }
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
