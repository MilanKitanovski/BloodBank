package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Centre;
import org.example.model.dto.CentreDTO;
import org.example.service.CentreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@SecurityRequirement(name = "BearerAuth") //za dodavanje dugmeta auth u swageru
@Tag(name = "Centre Controller", description = "CRUD operacije za korisnike") //dodaje ime grupe i dodaje opis u swaggeru
@RestController
@RequestMapping("api/centres")
@CrossOrigin(origins = "http://localhost:4200")
public class CentreController {
    private final CentreService centreService;
    CentreController(CentreService centreService){ this.centreService = centreService;}

    @PostMapping(path = "/create")
    // @PreAuthorize("hasAuthority('CenterAdministrator')")
    public ResponseEntity<Centre> createCentre(@RequestBody CentreDTO centreDTO){
        return new ResponseEntity<>(centreService.createCentre(centreDTO), HttpStatus.OK);
    }
    @GetMapping(path = "/all-centres")
    public ResponseEntity<?> findAllCentres(){
        return new ResponseEntity<>(centreService.findAllCentre(), HttpStatus.OK);
    }

    @GetMapping(path = "/search")//pretragu necu raditi, to je trebao neko drugi iz tima da radi
    public ResponseEntity<List<Centre>> searchCentreByNameOrAddress(@RequestParam String nameoraddress){
        return new ResponseEntity<>(centreService.searchCentreByNameOrAddress(nameoraddress),HttpStatus.OK);
    }

    @GetMapping("/search/cetre-filterGrade") //pretragu necu raditi, to je trebao neko drugi iz tima da radi
    public ResponseEntity<List<Centre>>filterCentreByGrade(@RequestParam double grade){
        return new ResponseEntity<>(centreService.filterCentersByGrade(grade),HttpStatus.OK);
    }

    @GetMapping("/search/centre-filterDistance") //pretragu necu raditi, to je trebao neko drugi iz tima da radi
    public ResponseEntity<List<Centre>> filterCentreByDistance(@RequestParam double lat1, @RequestParam double lon1,
            @RequestParam double distanceLimit){
        return new ResponseEntity<>(centreService.filterCentreByDistance1(lat1, lon1, distanceLimit), HttpStatus.OK);
    }

    @GetMapping(path="/search/centre-filterWorkTime") //pretragu necu raditi, to je trebao neko drugi iz tima da radi
    public ResponseEntity<List<Centre>> filterCentreByWorkTime(@RequestParam Date startWork, @RequestBody Date endWork){
        return new ResponseEntity<>(centreService.filterCentreByWorkTime(startWork, endWork), HttpStatus.OK);
    }

    @GetMapping("/search/centre-filterDistance1") //moja nova metoda da vidim da li radi
    public ResponseEntity<List<Centre>> filterCentreByDistance1(@RequestParam double lat1, @RequestParam double lon1,
                                                               @RequestParam double distanceLimit){
        return new ResponseEntity<>(centreService.filterCentreByDistance2(lat1, lon1, distanceLimit), HttpStatus.OK);
    }
}
