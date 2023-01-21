package uz.tech4ecobackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.tech4ecobackend.entity.Parameter;
import uz.tech4ecobackend.entity.dto.ParameterDTO;
import uz.tech4ecobackend.repository.ParameterRepository;

import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ParameterService {
    private final ParameterRepository parameterRepository;

    public ParameterService(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    public Parameter create(Parameter parameter){
        parameter.setTime(new Date());
        Parameter result = parameterRepository.save(parameter);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Parameter> findAll(){
        List<Parameter> result =  parameterRepository.findAll();
        return result;
    }


    @Transactional(readOnly = true)
    public List<?> findAllByNodeId(Long nodeId){

        List<List<?>> parameters = new ArrayList<>();

        parameters.add(parameterRepository.findAirTemperaturesByNodeId(nodeId));
        parameters.add(parameterRepository.findHumiditiesByNodeId(nodeId));
        parameters.add(parameterRepository.findSoilTemperaturesByNodeId(nodeId));
        parameters.add(parameterRepository.findSoilMoisturesByNodeId(nodeId));
        parameters.add(parameterRepository.findTimesByNodeId(nodeId));

        return parameters;


/*
        List<ParameterDTO> result = new ArrayList<>();
        List<Parameter> parameters = parameterRepository.findAllByNode_Id(nodeId);

        ParameterDTO soilMoisture = new ParameterDTO("Soil moisture", parameters.get(0).getTime(), parameters.get(0).getSoilMoisture());
        result.add(soilMoisture);
        ParameterDTO soilTemperature = new ParameterDTO("Soil Temperature", parameters.get(0).getTime(), parameters.get(0).getSoilTemperature());
        result.add(soilTemperature);

        ParameterDTO humidity = new ParameterDTO("Humidity", parameters.get(0).getTime(), parameters.get(0).getHumidity());
        result.add(humidity);
        ParameterDTO airTemperature = new ParameterDTO("Air Temperature", parameters.get(0).getTime(), parameters.get(0).getAirTemperature());
        result.add(airTemperature);

        for (int i=1; i<parameters.size(); i++){
            soilMoisture = new ParameterDTO("Soil moisture", parameters.get(i).getTime(), parameters.get(i).getSoilMoisture());
            result.add(soilMoisture);
            soilTemperature = new ParameterDTO("Soil Temperature", parameters.get(i).getTime(), parameters.get(i).getSoilTemperature());
            result.add(soilTemperature);

            humidity = new ParameterDTO("Humidity", parameters.get(i).getTime(), parameters.get(i).getHumidity());
            result.add(humidity);
            airTemperature = new ParameterDTO("Air Temperature", parameters.get(i).getTime(), parameters.get(i).getAirTemperature());
            result.add(airTemperature);


        }

        return result;

 */
    }
    @Transactional(readOnly = true)
    public Parameter findById(Long id){
        return parameterRepository.findById(id).get();
    }
    @Transactional(readOnly = true)
    public void delete(Long id){
        parameterRepository.deleteById(id);
    }

}
