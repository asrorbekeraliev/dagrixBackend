package uz.tech4ecobackend.entity.dto;

import java.time.Instant;
import java.util.Date;

public class ParameterDTO {
    private String group;
    private Date date;
    private Double value;

    public ParameterDTO(String group, Date date, Double value) {
        this.group = group;
        this.date = date;
        this.value = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
