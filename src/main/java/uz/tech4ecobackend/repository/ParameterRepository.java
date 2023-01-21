package uz.tech4ecobackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.tech4ecobackend.entity.Parameter;
import uz.tech4ecobackend.entity.dto.ParameterDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    public List<Parameter> findAllByNode_Id(Long Node_Id);
    @Query(value = "SELECT params.air_temperature from parametrlar params where params.node_id = ?1 ORDER BY time ", nativeQuery = true)
    public List<Object> findAirTemperaturesByNodeId(Long node_id);

    @Query(value = "SELECT params.humidity from parametrlar params where params.node_id = ?1 ORDER BY time ", nativeQuery = true)
    public List<Object> findHumiditiesByNodeId(Long node_id);

    @Query(value = "SELECT params.soil_temperature from parametrlar params where params.node_id = ?1 ORDER BY time ", nativeQuery = true)
    public List<Object> findSoilTemperaturesByNodeId(Long node_id);

    @Query(value = "SELECT params.soil_moisture from parametrlar params where params.node_id = ?1 ORDER BY time ", nativeQuery = true)
    public List<Object> findSoilMoisturesByNodeId(Long node_id);

    @Query(value = "SELECT params.time from parametrlar params where params.node_id = ?1 ORDER BY time ", nativeQuery = true)
    public List<Object> findTimesByNodeId(Long node_id);
    @Modifying
    @Query(value = "DELETE from parametrlar p where p.node_id=?1", nativeQuery = true)
    void deleteAllByNodeId(int node_id);





}
