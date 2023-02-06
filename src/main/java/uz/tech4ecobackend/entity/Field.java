package uz.tech4ecobackend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Fields")
public class Field implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    private String name;
    private int settedMoistureLevel;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSettedMoistureLevel() {
        return settedMoistureLevel;
    }

    public void setSettedMoistureLevel(int settedMoistureLevel) {
        this.settedMoistureLevel = settedMoistureLevel;
    }
}
