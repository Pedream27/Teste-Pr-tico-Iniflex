import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

       List<Funcionario> listaDeFuncionarios = new ArrayList<Funcionario>();
        DateTimeFormatter dataFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        listaDeFuncionarios.add(new Funcionario("Maria", LocalDate.of(2000 ,10,18), 2009.44, "Operador"));
        listaDeFuncionarios.add(new Funcionario("João", LocalDate.of(1990 ,05,12 ), 2284.38, "Operador"));
        listaDeFuncionarios.add(new Funcionario("Caio", LocalDate.of(1961,05,02 ), 9836.14, "Coordenador"));
        listaDeFuncionarios.add(new Funcionario("Miguel", LocalDate.of(1988 ,10,14), 19119.88, "Diretor"));
        listaDeFuncionarios.add(new Funcionario("Alice", LocalDate.of(1995,1,05 ),2234.68, "Recepcionista"));
        listaDeFuncionarios.add(new Funcionario("Heitor", LocalDate.of(1999 ,11,19 ), 1582.72, "Operador"));
        listaDeFuncionarios.add(new Funcionario("Arthur", LocalDate.of(1993,03,31 ), 4071.84, "Contador"));
        listaDeFuncionarios.add(new Funcionario("Laura", LocalDate.of(1994 ,07,8 ), 3017.45, "Gerente"));
        listaDeFuncionarios.add(new Funcionario("Heloísa", LocalDate.of(2003 ,05,24 ), 1606.85, "Eletricista"));
        listaDeFuncionarios.add(new Funcionario("Helena", LocalDate.of(1996 ,9,02 ), 2799.93, "Gerente"));

        //3.2 – Remover o funcionário “João” da lista.
        removerFuncionario("João", listaDeFuncionarios);
        /*
        3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        • informação de data deve ser exibido no formato dd/mm/aaaa;
        • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
         */
        imprimirListaDeFuncionario(listaDeFuncionarios);
        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
       darAumento(1.10 , listaDeFuncionarios);
       imprimirListaDeFuncionario(listaDeFuncionarios);

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = listaDeFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(funcionario -> System.out.println("  " + funcionario.getNome()));
        });
       // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.

        List<Funcionario> aniversariantes = listaDeFuncionarios.stream()
                .filter(funcionario -> {
                    int mesNascimento = funcionario.getDataNascimento().getMonthValue();
                    return mesNascimento == 10 || mesNascimento == 12;
                })
                .collect(Collectors.toList());

        System.out.println("Funcionários que fazem aniversário em outubro ou dezembro:");
        aniversariantes.forEach(funcionario -> System.out.println(funcionario.getNome() + " - " + funcionario.getDataNascimento().format(dataFormatador)));

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade
        Funcionario funcionarioMaisVelho = listaDeFuncionarios.stream()
                .max(Comparator.comparingInt(Funcionario::getIdade))
                .orElseThrow(null);
        if(funcionarioMaisVelho != null) {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("Nome do funcionario mais velho : ".concat(funcionarioMaisVelho.getNome()).concat(" com " + funcionarioMaisVelho.getIdade()));
            System.out.println("---------------------------------------------------------------------------------------");
        }

       // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        List<Funcionario> funcionariosOrdenados = listaDeFuncionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());

        System.out.println("Lista de funcionários por ordem alfabética:");
        imprimirListaDeFuncionario(funcionariosOrdenados);

    //3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = listaDeFuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Total dos salários dos funcionários: " + totalSalarios);
        System.out.println("---------------------------------------------------------------------------------------");

     //   3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        listaDeFuncionarios.forEach(funcionario -> {
            BigDecimal quantSalariosMinimos = funcionario.getSalario()
                    .divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println(funcionario.getNome() + " ganha " + quantSalariosMinimos + " salários mínimos.");
            System.out.println("---------------------------------------------------------------------------------------");
        });

    }

    private static void darAumento(double v, List<Funcionario> listaDeFuncionarios) {
        listaDeFuncionarios.forEach( f -> f.aumentoDeSalario(1.10));
    }

    private static void imprimirListaDeFuncionario(List<Funcionario> listaDeFuncionarios) {
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
        public static void removerFuncionario(String nomeFuncionario , List<Funcionario> listaDeFuncionarios) {
            Funcionario remover = listaDeFuncionarios.stream().filter(f -> f.getNome().equals(nomeFuncionario)).findFirst().orElse(null);
            if (remover != null) {
                listaDeFuncionarios.remove(remover);
                System.out.println( "O funcionario ".concat(remover.getNome()).concat(" foi removido com sucesso!"));
            }else {
                System.out.println("Funcionario não encontrado");
            }
        }

    }

