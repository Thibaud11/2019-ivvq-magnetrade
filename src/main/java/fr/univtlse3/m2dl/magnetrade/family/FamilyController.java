package fr.univtlse3.m2dl.magnetrade.family;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    }

    public void setFamilyService(FamilyService familyService) {
        this.familyService = familyService;
    }

    public FamilyService getFamilyService() {
        return familyService;
    }

    /**
     * Method to create a family in the back-end
     *
     * @param family the family to create
     */
    @PostMapping({"/save","/update"})
    public Family createFamily(@RequestBody Family family) {
        return familyService.saveFamily(family);
    }

    @GetMapping("read/{familyId}")
    public ResponseEntity<Family> findFamilyById(@PathVariable("familyId") long familyId){
        Family m = familyService.findFamilyById(familyId);
        if(m == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public List<Family> findAllFamily(){
        return familyService.findAllFamilys();
    }

    @DeleteMapping("delete/{familyId}")
    public void deleteFamily(@PathVariable("familyId") long familyId){
        familyService.deleteFamily(familyId);
    }

}