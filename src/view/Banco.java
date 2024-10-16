package view;

import helper.Utils;
import model.Cliente;
import model.Conta;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    static String nome = "Lan Bank";
    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Conta> contas;

    public static void main(String[] args) {
        Banco.contas = new ArrayList<Conta>();
        Banco.menu();
    }

    public static void menu() {
        int opcao = 0;
        System.out.println("=========================");
        System.out.println("========== ATM ==========");
        System.out.println("======== " + Banco.nome + " =======");
        System.out.println("=========================");
        System.out.println("Selecione uma opção no Menu: ");
        System.out.println("1 - Criar Conta");
        System.out.println("2 - Efetuar Saque");
        System.out.println("3 - Efetuar Depósito");
        System.out.println("4 - Efetuar Transferência");
        System.out.println("5 - Listar Contas");
        System.out.println("6 - Sair Do Sistema");

        try {
            opcao = Integer.parseInt(Banco.teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por Favor, Informa uma Opção Válida");
            Utils.pausar(1);
            Banco.menu();
        }

        switch (opcao) {
            case 1:
                Banco.criarConta();
                break;
            case 2:
                Banco.efetuarSaque();
                break;
            case 3:
                Banco.efetuarDeposito();
                break;
            case 4:
                Banco.efetuarTransferencia();
                break;
            case 5:
                Banco.listarContas();
                break;
            case 6:
                System.out.println("Até a Próxima! Obrigado por Escolher a Lan Bank's");
                Utils.pausar(2);
                System.exit(0);
            default:
                System.out.println("Opção Inválida.");
                Utils.pausar(2);
                Banco.menu();
                break;
        }
    }

    public static void criarConta() {
        System.out.println("Informe os Dados do Cliente");

        System.out.println("Nome do Cliente: ");
        String nome = Banco.teclado.nextLine();

        System.out.println("E-mail do Cliente: ");
        String email = Banco.teclado.nextLine();

        System.out.println("CPF do Cliente: ");
        String cpf = Banco.teclado.nextLine();

        System.out.println("Data de Nascimento do Cliente: ");
        String data_nascimento = Banco.teclado.nextLine();

        Cliente cliente = new Cliente(nome, email, cpf, Utils.stringParaData(data_nascimento));

        Conta conta = new Conta(cliente);

        Banco.contas.add(conta);
        System.out.println("Conta Criada com Sucesso.");
        System.out.println("Dados da Conta Criada: ");
        System.out.println(conta);
        System.out.println();
        Utils.pausar(4);
        Banco.menu();

    }

    public static void efetuarSaque() {
        System.out.println("Informe o Número da Conta");
        int numero = Banco.teclado.nextInt();

        Conta conta = Banco.buscarContaPorNumero(numero);

        if (conta != null) {
            System.out.println("Informe o Valor para Saque: ");
            Double valor = Banco.teclado.nextDouble();

            conta.sacar(valor);
        } else {
            System.out.println("Não foi Encontrada a Conta Número: " + numero);
        }
        Utils.pausar(3);
        Banco.menu();
    }

    public static void efetuarDeposito() {
        System.out.println("Informe o Número da Conta");
        int numero = Banco.teclado.nextInt();

        Conta conta = Banco.buscarContaPorNumero(numero);

        if (conta != null) {
            System.out.println("Informe o Valor de Depósito: ");
            Double valor = Banco.teclado.nextDouble();
            conta.depositar(valor);

        } else {
            System.out.println("Não Foi Encontrada a Conta Número: " + numero);
        }
        Utils.pausar(3);
        Banco.menu();
    }

    public static void efetuarTransferencia() {
        System.out.println("Informe o Número da Sua Conta: ");
        int numero_o = Banco.teclado.nextInt();

        Conta conta_o = Banco.buscarContaPorNumero(numero_o);

        if (conta_o != null) {
            System.out.println("Informe o Número da Conta Destino: ");
            int numero_d = Banco.teclado.nextInt();

            Conta conta_d = Banco.buscarContaPorNumero(numero_d);

            if (conta_d != null) {
                System.out.println("Informe o Valor da Trasferência: ");
                Double valor = Banco.teclado.nextDouble();

                conta_o.transferir(conta_d, valor);
            } else {
                System.out.println("A Conta Destino Número " + numero_d + " Não Foi Encontrada.");
            }
        } else {
            System.out.println("Não Foi Encontrada a Conta Número: " + numero_o);
        }
        Utils.pausar(3);
        Banco.menu();
    }

    public static void listarContas() {
        if (Banco.contas.size() > 0) {
            System.out.println("Listagem de Contas");
            System.out.println();

            for (Conta conta : Banco.contas) {
                System.out.println(conta);
                System.out.println();
                Utils.pausar(1);
            }
            System.out.println();
        } else {
            System.out.println("Não Existem Contas Cadastradas Ainda.");
        }
        Utils.pausar(3);
        Banco.menu();
    }

    private static Conta buscarContaPorNumero(int numero) {
        Conta c = null;
        if (Banco.contas.size() > 0) {
            for (Conta conta : Banco.contas) {
                if (conta.getNumero() == numero) {
                    c = conta;
                }
            }
        }
        return c;
    }
}
