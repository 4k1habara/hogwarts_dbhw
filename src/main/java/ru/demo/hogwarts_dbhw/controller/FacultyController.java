package ru.demo.hogwarts_dbhw.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.demo.hogwarts_dbhw.model.Faculty;
import ru.demo.hogwarts_dbhw.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("add")
    public Faculty addFaculty(Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("find")
    public ResponseEntity<Faculty> findFaculty(long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping("edit")
    public ResponseEntity<Faculty> editFaculty(Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteFaculty(long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("{color}")
    public ResponseEntity<Collection<Faculty>> getFacultiesWithSameColor(@PathVariable String color) {
        Collection<Faculty> faculties = facultyService.getFacultiesByColor(color);
        if (faculties == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }
}
