package org.example.service;

import com.sun.jdi.event.MonitorContendedEnteredEvent;
import org.example.model.Centre;
import org.example.model.dto.CentreDTO;
import org.example.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CentreService {

    private final CentreRepository centreRepository;
    public CentreService(CentreRepository centreRepository) {this.centreRepository = centreRepository;}

    public Centre createCentre(CentreDTO centreDTO){
        Centre centre = new Centre();
        centre.setName(centreDTO.getName());
        centre.setAddress(centreDTO.getAddress());
        centre.setLongitude(centreDTO.getLongitude());
        centre.setLatitude(centreDTO.getLatitude());
        centre.setDescription(centreDTO.getDescription());
        centre.setCity(centreDTO.getCity());
        centre.setStartWork(centre.getStartWork());
        centre.setEndWork(centreDTO.getEndWork());

        return centreRepository.save(centre);
    }
    public List<Centre> findAllCentre() {return centreRepository.findAll();}

    public List<Centre> searchCentreByNameOrAddress(String search) { return centreRepository.searchCentreByNameOrAddress(search);}

    public List<Centre> filterCentersByGrade(double grade){  //za filtriranje po oceni
        List<Centre> centers = centreRepository.findAll();
        List<Centre> centersResult = new ArrayList<>();
        double left = 0;
        double right = 0;

        if(grade == 5) {        //ako uneses ocenu 5, naci ce sve centre izmedju left i right i ispisati ih
            left = 4.5;
            right = 5;
        } else if (grade == 4) {
            left = 3.5;
            right = 4.49;
        } else if (grade == 3) {
            left = 2.5;
            right = 3.49;
        }else if (grade == 2) {
            left = 1.5;
            right = 2.49;
        }else if (grade == 1) {
            left = 1.49;
            right = 0;
        }

        for(Centre center : centers){
            if(center.getAvgGrade() >= left && center.getAvgGrade() <= right){
                centersResult.add(center);
            }
        }

        return centersResult;
    }

    public List<Centre> filterCentreByDistance1(double lat1, double lon1, double distanceLimit) { //za pretragu po distanci, napravicu jos 1 f-ju distance2 koja radi isto ali je brze implementirana i lakse

        List<Centre> centers = centreRepository.findAll();
        List<Centre> centersResult = new ArrayList<>();

        for(Centre center : centers){

            double lat2 = center.getLatitude();
            double lon2 = center.getLongitude();

            int R = 6371; // Radius of the earth

            double latDistance = Math.toRadians(lat2 - lat1);
            double lonDistance = Math.toRadians(lon2 - lon1);
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c * 1000; // convert to meters
            if(distance <= distanceLimit){
                centersResult.add(center);
            }
        }
        return centersResult;
    }

    public List<Centre> filterCentreByDistance2(double lat, double lon, double distanceLimit) {
        return centreRepository.findCentresWithinDistance(lat, lon, distanceLimit);
    }

    public List<Centre> filterCentreByWorkTime(Date startWork, Date endWork){ //pretraga po radnom vremenu
        List<Centre> centres = centreRepository.findAll();
        List <Centre> centresResult = new ArrayList<>();

        for(Centre center : centres){
            if(center.getStartWork().after(startWork) && center.getEndWork().before(endWork))
                centresResult.add(center);
        }
        return centresResult;
    }
}
