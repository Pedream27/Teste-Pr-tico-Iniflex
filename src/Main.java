import core.Core;
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
       Core core = new Core();
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
        core.removerFuncionario("João", listaDeFuncionarios);
        /*
        3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        • informação de data deve ser exibido no formato dd/mm/aaaa;
        • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
         */
        core.imprimirListaDeFuncionario(listaDeFuncionarios);
        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

        System.out.println( "Dando Aumento de 10%");
       core.darAumento(1.10 , listaDeFuncionarios);
       core.imprimirListaDeFuncionario(listaDeFuncionarios);

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = core.agruparFuncionariosPorFuncao(listaDeFuncionarios);
         core.imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

       // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        core.imprimirFuncionariosAniversariantes(listaDeFuncionarios, 10 , 12);

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade

        core.funcionarioMaisVelho(listaDeFuncionarios);

       // 3.10 – Imprimir a lista de funcionários por ordem alfabética
        List<Funcionario> funcionariosOrdenados = core.odernarListaDeFuncionarioEmOrdemAlfabetica(listaDeFuncionarios);
        System.out.println("Lista de funcionários por ordem alfabética:");
        core.imprimirListaDeFuncionario(funcionariosOrdenados);

    //3.11 – Imprimir o total dos salários dos funcionários.
        core.somarEImprimirTotalDosSalarios(listaDeFuncionarios);

     //   3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        core.imprimirQntSalariosMinPorFuncionario(listaDeFuncionarios , new BigDecimal("1212.00"));
    }
    }

