package core;

import model.Funcionario;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CorePort {
    void removerFuncionario(String nomeFuncionario , List<Funcionario> listaDeFuncionarios);

    void imprimirListaDeFuncionario(List<Funcionario> listaDeFuncionarios);

    void darAumento(double v, List<Funcionario> listaDeFuncionarios);

    Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> listaDeFuncionarios);

    void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao);

    void imprimirFuncionariosAniversariantes(List<Funcionario> listaDeFuncionarios, int mes1, int mes2);

    void funcionarioMaisVelho(List<Funcionario> listaDeFuncionarios);

    List<Funcionario> odernarListaDeFuncionarioEmOrdemAlfabetica(List<Funcionario> listaDeFuncionarios);

    void somarEImprimirTotalDosSalarios(List<Funcionario> listaDeFuncionarios);

    void imprimirQntSalariosMinPorFuncionario(List<Funcionario> listaDeFuncionarios, BigDecimal salarioMinimo);

}
