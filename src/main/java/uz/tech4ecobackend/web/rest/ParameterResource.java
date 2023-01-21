package uz.tech4ecobackend.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tech4ecobackend.entity.Node;
import uz.tech4ecobackend.entity.Parameter;
import uz.tech4ecobackend.entity.dto.ParameterDTO;
import uz.tech4ecobackend.service.ParameterService;

import java.time.Instant;
import java.time.InstantSource;
import java.util.List;

//@CrossOrigin(origins = "http://dottore.uz/")
@RestController
@RequestMapping("/api/parameter")
public class ParameterResource {
    private final ParameterService parameterService;

    public ParameterResource(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody Parameter parameter){
        Parameter result = parameterService.create(parameter);
        return ResponseEntity.ok(result);
    }
    /*      Typical request sample:
{
    "airTemperature": "17",
    "humidity": "25.7",
    "soilMoisture": "35",
    "soilTemperature": "14.3",
    "node":
    {
        "id": "1",
        "batteryCharge": "58"
    }
}
     */


    @GetMapping("/get")
    public ResponseEntity<?> getAll(){
        List result = parameterService.findAll();
        return ResponseEntity.ok(result);
    }



    @GetMapping("/data/{nodeId}")
    public ResponseEntity<?> getByNodeNumber(@PathVariable Long nodeId){
        //List<ParameterDTO> result = parameterService.findAllByNodeId(nodeId);
        return ResponseEntity.ok(parameterService.findAllByNodeId(nodeId));
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
       Parameter result = parameterService.findById(id);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        parameterService.delete(id);
        return ResponseEntity.ok("Parameter deleted successfully");
    }


}
