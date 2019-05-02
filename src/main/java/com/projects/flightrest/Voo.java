package com.projects.flightrest;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Voo {

    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;

    private String codigo;
    private String origem;
    private String destino;
    private String horario;
    private String dia_semana;
    private float preco;

    @ManyToOne
    CompanhiaAerea companhia;

    public Voo(){

    }

    public Voo(String origem, String destino, String horario, String dia_semana, float preco){

        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
        this.dia_semana = dia_semana;
        this.preco = preco;

        this.codigo = origem.substring(0,2) + destino.substring(0,2) + this.companhia.getCodigo() + dia_semana.substring(0,2);
    }
}
