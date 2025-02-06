package core;

import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Core implements CorePort {

    @Override
    public void removerFuncionario(String nomeFuncionario, List<Funcionario> listaDeFuncionarios) {
        Funcionario remover = listaDeFuncionarios.stream().filter(f -> f.getNome().equals(nomeFuncionario)).findFirst().orElse(null);
        if (remover != null) {
            listaDeFuncionarios.remove(remover);
            System.out.println( "O funcionario ".concat(remover.getNome()).concat(" foi removido com sucesso!"));
        }else {
            System.out.println("Funcionario não encontrado");
        }
    }

    @Override
    public void imprimirListaDeFuncionario(List<Funcionario> listaDeFuncionarios) {
        DateTimeFormatter dataFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(new Locale("pt", "BR"));
        simbolos.setDecimalSeparator(',');
        simbolos.setGroupingSeparator('.');
        DecimalFormat salarioFormatador = new DecimalFormat("#,##0.00", simbolos);

        System.out.println("-------------------------------- Lista de Funcionarios -------------------------------");
        listaDeFuncionarios.forEach(f-> {
            System.out.println("Nome: ".concat(f.getNome())
                    .concat(" | Data de Nacimento : ").concat(f.getDataNascimento().format(dataFormatador)
                            .concat(" | Salario: ").concat(salarioFormatador.format(f.getSalario())
                                    .concat(" | Função: ").concat(f.getFuncao()))) );
        });
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Override
    public void darAumento(double porcentagem, List<Funcionario> listaDeFuncionarios) {
        listaDeFuncionarios.forEach( f -> f.aumentoDeSalario(porcentagem));
    }

    @Override
    public Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> listaDeFuncionarios) {
        Map<String, List<Funcionario>> funcionariosAgrupados = listaDeFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        return funcionariosAgrupados;
    }

    @Override
    public void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(funcionario -> System.out.println("  " + funcionario.getNome()));
        });
    }

    @Override
    public void imprimirFuncionariosAniversariantes(List<Funcionario> listaDeFuncionarios, int mes1, int mes2) {
        DateTimeFormatter dataFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Funcionario> aniversariantes = listaDeFuncionarios.stream()
                .filter(funcionario -> {
                    int mesNascimento = funcionario.getDataNascimento().getMonthValue();
                    return mesNascimento == mes1|| mesNascimento == mes2;
                })
                .collect(Collectors.toList());

        System.out.println("Funcionários que fazem aniversário em outubro ou dezembro:");
        aniversariantes.forEach(funcionario -> System.out.println(funcionario.getNome() + " - " + funcionario.getDataNascimento().format(dataFormatador)));
    }

    @Override
    public void funcionarioMaisVelho(List<Funcionario> listaDeFuncionarios) {
        Funcionario funcionarioMaisVelho = listaDeFuncionarios.stream()
                .max(Comparator.comparingInt(Funcionario::getIdade))
                .orElseThrow(null);
        if(funcionarioMaisVelho != null) {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Nome do funcionario mais velho : ".concat(funcionarioMaisVelho.getNome()).concat(" com " + funcionarioMaisVelho.getIdade()));
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }

    @Override
    public List<Funcionario> odernarListaDeFuncionarioEmOrdemAlfabetica(List<Funcionario> listaDeFuncionarios) {
        List<Funcionario> funcionariosOrdenados = listaDeFuncionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
        return funcionariosOrdenados;
    }

    @Override
    public void somarEImprimirTotalDosSalarios(List<Funcionario> listaDeFuncionarios) {
        BigDecimal totalSalarios = listaDeFuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Total dos salários dos funcionários: " + totalSalarios);
        System.out.println("---------------------------------------------------------------------------------------");
    }

    @Override
    public void imprimirQntSalariosMinPorFuncionario(List<Funcionario> listaDeFuncionarios, BigDecimal salarioMinimo) {
        listaDeFuncionarios.forEach(funcionario -> {
            BigDecimal quantSalariosMinimos = funcionario.getSalario()
                    .divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + quantSalariosMinimos + " salários mínimos.");
            System.out.println("---------------------------------------------------------------------------------------");
        });
    }
}
