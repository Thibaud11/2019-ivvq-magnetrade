package fr.univtlse3.m2dl.magnetrade.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/family")
public class FamilyController {

    private FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    public FamilyController() {
        // Empty
    }

    public void setFamilyService(FamilyService familyService) {
        this.familyService = familyService;
    }

    public FamilyService getFamilyService() {
        return familyService;
    }

    /**
     * Method to create or update a family in the back-end.
     *
     * @param family the family to create/update
     */
    @PostMapping({"/create","/edit"})
    public Family createOrUpdateFamily(@RequestBody Family family) {
        return familyService.saveFamily(family);
    }

    @GetMapping("/{id}")
    public Family findFamilyById(@PathVariable("id") long id){
        return familyService.findFamilyById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<Family> findAllFamily(){
        return familyService.findAllFamilys();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFamily(@PathVariable("id") long id){
        familyService.deleteFamily(id);
    }

}
