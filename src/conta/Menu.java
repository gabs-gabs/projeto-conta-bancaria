package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import conta.util.Cores;
import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

public class Menu {
    public static void main(String[] args) {
        ContaController contas = new ContaController();

        Scanner leia = new Scanner(System.in);

        int opcao, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

        while (true) {

            System.out.println(Cores.TEXT_PURPLE + "*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                BANCO DO BRAZIL COM Z                ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Criar Conta                          ");
            System.out.println("            2 - Listar todas as Contas               ");
            System.out.println("            3 - Buscar Conta por Numero              ");
            System.out.println("            4 - Atualizar Dados da Conta             ");
            System.out.println("            5 - Apagar Conta                         ");
            System.out.println("            6 - Sacar                                ");
            System.out.println("            7 - Depositar                            ");
            System.out.println("            8 - Transferir valores entre Contas      ");
            System.out.println("            9 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     " + Cores.TEXT_RESET);

            try {
                opcao = leia.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nDigite valores inteiros!");
                leia.nextLine();
                opcao = 0;
            }
            if (opcao == 9) {
                System.out.println(Cores.TEXT_PURPLE + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
                sobre();
                leia.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta\n\n");

                    System.out.println("Digite o numero da agência");
                    agencia = leia.nextInt();
                    System.out.println("Digite o nome do titular");
                    leia.skip("\\R?");
                    titular = leia.nextLine();
                    do {
                        System.out.println("Digite o tipo de conta (1-CC ou 2-CP)");
                        tipo = leia.nextInt();
                    } while (tipo < 1 && tipo > 2);

                    System.out.println("Sigite o saldo da conta");
                    saldo = leia.nextFloat();

                    switch (tipo) {
                        case 1: {
                            System.out.println("Digite o limite de credito");
                            limite = leia.nextFloat();
                            contas.cadastrar(
                                    new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
                        }
                        case 2: {
                            System.out.println("Digite o dia do aniversario da conta");
                            aniversario = leia.nextInt();
                            contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo,
                                    aniversario));
                        }
                    }
                    keyPress();
                    break;
                case 2:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
                    contas.listarTodas();
                    keyPress();
                    break;
                case 3:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta - por número\n\n");
                    System.out.println("Digite o número da conta: ");
                    numero = leia.nextInt();

                    contas.procurarPorNumero(numero);
                    keyPress();
                    break;
                case 4:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta\n\n");

                    System.out.println("Digite o numero da conta");
                    numero = leia.nextInt();

                    var buscaConta = contas.buscarNaCollection(numero);

                    if (buscaConta != null) {
                        tipo = buscaConta.getTipo();

                        System.out.println("Digite o numero da agencia");
                        agencia = leia.nextInt();
                        System.out.println("Digite o nome do titular");
                        leia.skip("\\R?");
                        titular = leia.nextLine();

                        System.out.println("Digite o saldo da conta");
                        saldo = leia.nextFloat();

                        switch (tipo) {
                            case 1:
                                System.out.println("Digite o limite de crédito");
                                limite = leia.nextFloat();

                                contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));

                            case 2:
                                System.out.println("Digite o dia do aniversario da conta");
                                aniversario = leia.nextInt();

                                contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));

                            default:
                                System.out.println("Tipo de conta invalida!");
                        }
                    } else {
                        System.out.println("A conta não foi encontrada!");
                    }
                    keyPress();
                    break;

                case 5:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta\n\n");
                    System.out.println("Digite o numero da conta:");
                    numero = leia.nextInt();
                    contas.deletar(numero);
                    keyPress();
                    break;
                case 6:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");
                    System.out.println("Digite o numero da conta");
                    numero = leia.nextInt();

                    do {
                        System.out.println("Digite o valor do saque");
                        valor = leia.nextFloat();
                    } while (valor <= 0);
                    keyPress();
                    break;
                case 7:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");

                    System.out.println("Digite o numero da conta:");
                    numero = leia.nextInt();

                    do {
                        System.out.println("Digite o valor do depósito");
                        valor = leia.nextFloat();
                    } while (valor <= 0);
                    keyPress();
                    break;
                case 8:
                    System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas\n\n");
                    System.out.println("Digite o numero da conta de origem:");
                    numero = leia.nextInt();
                    System.out.println("Digite o numero da conta de destino:");
                    numeroDestino = leia.nextInt();

                    do {
                        System.out.println("Digite o valor de transferência");
                        valor = leia.nextFloat();
                    } while (valor <= 0);

                    contas.transferir(numero, numeroDestino, valor);

                    keyPress();
                    break;
                default:
                    System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
                    keyPress();
                    break;
            }
        }

    }

    public static void sobre() {
        System.out.println("\n*********************************************************");
        System.out.println("Projeto Desenvolvido por: Gabrielly Vitória Fernanda de Souza");
        System.out.println("Generation Brasil - generation@generation.org");
        System.out.println("github.com/conteudoGeneration");
        System.out.println("*********************************************************");
    }

    public static void keyPress() {
        try {
            System.out.println(Cores.TEXT_RESET + "\nPressione Enter para continuar...");
            System.in.read();
        } catch (IOException e) {
            System.out.println("Você pressionou uma tecla diferente de enter!");
        }
    }

}
