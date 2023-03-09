package uz.tech4ecobackend.service;

import org.springframework.stereotype.Service;
import uz.tech4ecobackend.entity.Node;
import uz.tech4ecobackend.entity.Parameter;
import uz.tech4ecobackend.repository.FieldRepository;
import uz.tech4ecobackend.repository.NodeRepository;
import uz.tech4ecobackend.repository.ParameterRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.lang.Double.valueOf;

@Service
public class ParameterService {
    private final ParameterRepository parameterRepository;
    private final NodeRepository nodeRepository;
    private final FieldRepository fieldRepository;

    public ParameterService(ParameterRepository parameterRepository, NodeRepository nodeRepository, FieldRepository fieldRepository) {
        this.parameterRepository = parameterRepository;
        this.nodeRepository = nodeRepository;
        this.fieldRepository = fieldRepository;
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
        double son=2.5;
        son = son + ((double) parameterRepository.findSoilMoisturesByNodeId(nodeId).get(0));
        System.out.println(son);

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

    public List<Integer> getFieldSoilMoistureLevels(){
        List<Integer> fieldMoistureLevels = new ArrayList<>();
        for (int i=0; i<fieldRepository.findAll().size(); i++){
            // Gathering nodeIds in the field
            List<Long> nodeIds = new ArrayList<>();
            for (int n=0; n<nodeRepository.findByField_Id(fieldRepository.findAll().get(i).getId()).size(); n++){
                nodeIds.add(nodeRepository.findByField_Id(fieldRepository.findAll().get(i).getId()).get(n).getId());
            }
            // Soil Moisture Levels
            double TotalMoistureLevel = 0.0;
            for (int m=0; m<nodeIds.size(); m++){
                int lastIndex = parameterRepository.findSoilMoisturesByNodeId(nodeIds.get(m)).size()-1;
                TotalMoistureLevel = TotalMoistureLevel + ((double) parameterRepository.findSoilMoisturesByNodeId(nodeIds.get(m)).get(lastIndex));
            }
            Float avarageMoistureLevel = (float) TotalMoistureLevel / (nodeIds.size());
            fieldMoistureLevels.add(Math.round(avarageMoistureLevel));
        }
        return fieldMoistureLevels;
    }

}
