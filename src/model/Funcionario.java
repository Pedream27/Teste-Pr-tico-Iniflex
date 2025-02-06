package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;

    public Funcionario(BigDecimal salario, String funcao) {
        this.salario = salario;
        this.funcao = funcao;
    }

    public Funcionario(String nome,LocalDate dataNascimento, double salario, String funcao) {
        super( nome, dataNascimento);
        this.salario =  new BigDecimal (salario);
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public void aumentoDeSalario(double porcentagem) {
       BigDecimal aumento = this.salario.multiply(new BigDecimal(porcentagem));
        this.salario = aumento.setScale(2, RoundingMode.HALF_UP);
    }

    public int getIdade() {
        LocalDate hoje = LocalDate.now();
        return Period.between(this.getDataNascimento(), hoje).getYears();
    }
}
