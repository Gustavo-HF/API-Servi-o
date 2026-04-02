package com.servicos.servico.Model;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Servico{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private long id;

    private Enum categoriaServico;

    private Date dataDeCriacaoDoServico;

    private boolean isServicoConcluido;

    private double valor;

    private Enum atendente;

    private String descricaoDoServico;

    public Servico() {

    }

    public Servico(Enum atendente, Enum categoriaServico, Date dataDeCriacaoDoServico, String descricaoDoServico, long id, boolean isServicoConcluido, double valor) {
        this.atendente = atendente;
        this.categoriaServico = categoriaServico;
        this.dataDeCriacaoDoServico = dataDeCriacaoDoServico;
        this.descricaoDoServico = descricaoDoServico;
        this.id = id;
        this.isServicoConcluido = isServicoConcluido;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enum getCategoriaServico() {
        return categoriaServico;
    }

    public void setCategoriaServico(Enum categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    public Date getDataDeCriacaoDoServico() {
        return dataDeCriacaoDoServico;
    }

    public void setDataDeCriacaoDoServico(Date dataDeCriacaoDoServico) {
        this.dataDeCriacaoDoServico = dataDeCriacaoDoServico;
    }

    public boolean isIsServicoConcluido() {
        return isServicoConcluido;
    }

    public void setIsServicoConcluido(boolean isServicoConcluido) {
        this.isServicoConcluido = isServicoConcluido;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Enum getAtendente() {
        return atendente;
    }

    public void setAtendente(Enum atendente) {
        this.atendente = atendente;
    }

    public String getDescricaoDoServico() {
        return descricaoDoServico;
    }

    public void setDescricaoDoServico(String descricaoDoServico) {
        this.descricaoDoServico = descricaoDoServico;
    }





    

}