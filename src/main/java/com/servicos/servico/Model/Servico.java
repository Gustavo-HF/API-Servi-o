package com.servicos.servico.Model;
import java.sql.Date;

import com.servicos.servico.Enum.Atendente;
import com.servicos.servico.Enum.CategoriaServico;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Servico{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private long id;

    @Enumerated(EnumType.STRING)
    private CategoriaServico categoriaServico;

    @NotNull(message="O campo data de criação não pode estar vazio")
    private Date dataDeCriacaoDoServico;

    private boolean isServicoConcluido;

    @NotNull(message="O campo de valor não pode estar nulo")
    private double valor;

    @Enumerated(EnumType.STRING)
    private Atendente atendente;

    @NotBlank(message="O campo de descrição não pode estar vazio")
    private String descricaoDoServico;

    public Servico() {

    }

    public Servico(Atendente atendente, CategoriaServico categoriaServico, Date dataDeCriacaoDoServico, String descricaoDoServico, long id, boolean isServicoConcluido, double valor) {
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

    public CategoriaServico getCategoriaServico() {
        return categoriaServico;
    }

    public void setCategoriaServico(CategoriaServico categoriaServico) {
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

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public String getDescricaoDoServico() {
        return descricaoDoServico;
    }

    public void setDescricaoDoServico(String descricaoDoServico) {
        this.descricaoDoServico = descricaoDoServico;
    }





    

}