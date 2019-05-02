package com.projects.flightrest;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class CompanhiaAerea {

    private @Id @GeneratedValue (strategy = GenerationType.SEQUENCE) Long id;
    private String name;
    private int codigo;

    public CompanhiaAerea(){

    }

    public CompanhiaAerea(String name, int codigo){

        this.name = name;
        this.codigo = codigo;
    }
}
