package com.example.realschool.controller;

import com.example.realschool.model.Courses;
import com.example.realschool.model.Person;
import com.example.realschool.model.RealClass;
import com.example.realschool.repository.CoursesRepository;
import com.example.realschool.repository.PersonRepository;
import com.example.realschool.repository.RealClassRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    RealClassRepository realClassRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;


    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<RealClass> realClasses = realClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("realClasses", realClasses);
        modelAndView.addObject("realClass", new RealClass());
        return modelAndView;
    }


    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("realClass") RealClass realClass) {
        realClassRepository.save(realClass);
        return new ModelAndView("redirect:/admin/displayClasses");
    }


    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<RealClass> realClass = realClassRepository.findById(id);
        for (Person person : realClass.get().getPersons()) {
            person.setRealClass(null);
            personRepository.save(person);
        }
        realClassRepository.deleteById(id);
        return new ModelAndView("redirect:/admin/displayClasses");
    }


    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students");
        Optional<RealClass> realClass = realClassRepository.findById(classId);
        modelAndView.addObject("realClass", realClass.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("realClass", realClass.get());
        if (error != null) {
            errorMessage = "Invalid Email entered";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        RealClass realClass = (RealClass) session.getAttribute("realClass");
        Person personEntity = personRepository.findByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + realClass.getClassId() + "&error=true");
            return modelAndView;
        }
        personEntity.setRealClass(realClass);
        personRepository.save(personEntity);
        realClass.getPersons().add(personEntity);
        realClassRepository.save(realClass);
        modelAndView.setViewName("redirect:/admin/displayClasses?classId=" + realClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        RealClass realClass = (RealClass) session.getAttribute("realClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setRealClass(null);
        realClass.getPersons().remove(person.get());
        RealClass realClassSaved = realClassRepository.save(realClass);
        session.setAttribute("realClass", realClassSaved);
        return new ModelAndView("redirect:/admin/displayStudents?classId=" + realClass.getClassId());
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
        List<Courses> courses = coursesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure");
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("course", new Courses());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course") Courses course) {
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id, HttpSession session,
                                     @RequestParam(required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("course_students");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("courses", courses.get());
        if (error != null) {
            errorMessage = "Invalid Email entered";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }


    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");
        Person personEntity = personRepository.findByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId() + "&error=true");
            return modelAndView;
        }
        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses", courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id=" + courses.getCourseId());
        return modelAndView;
    }


    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model model, @RequestParam int personId, HttpSession session) {
        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        person.get().getCourses().remove(courses);
        courses.getPersons().remove(person.get());
        personRepository.save(person.get());
        session.setAttribute("courses", courses);
        return new ModelAndView("redirect:/admin/viewStudents?id=" + courses.getCourseId());
    }
}
 