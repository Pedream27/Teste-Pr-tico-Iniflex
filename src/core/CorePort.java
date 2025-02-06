package core;

import model.Funcionario;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CorePort {

    public void removerFuncionario(String nomeFuncionario , List<Funcionario> listaDeFuncionarios);

    public void imprimirListaDeFuncionario(List<Funcionario> listaDeFuncionarios);

    public void darAumento(double v, List<Funcionario> listaDeFuncionarios);

    public Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> listaDeFuncionarios);

    public void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao);

    public void imprimirFuncionariosAniversariantes(List<Funcionario> listaDeFuncionarios, int mes1, int mes2);

    public void funcionarioMaisVelho(List<Funcionario> listaDeFuncionarios);

    public List<Funcionario> odernarListaDeFuncionarioEmOrdemAlfabetica(List<Funcionario> listaDeFuncionarios);

    public void somarEImprimirTotalDosSalarios(List<Funcionario> listaDeFuncionarios);

    public void imprimirQntSalariosMinPorFuncionario(List<Funcionario> listaDeFuncionarios, BigDecimal salarioMinimo);

}
