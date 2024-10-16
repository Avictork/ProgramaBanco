package model;

import helper.Utils;

public class Conta {
    private static int codigo = 1001;

    private int numero;
    private Cliente cliente;
    private Double saldo = 0.0;
    private Double limite = 0.0;

    public Conta(Cliente cliente) {
        this.numero = Conta.codigo;
        this.cliente = cliente;
        Conta.codigo += 1;
        this.atualizaSaldoTotal();
    }

    private Double saldoTotal;


    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
        this.atualizaSaldoTotal();
    }

    public Double getSaldoTotal() {
        return saldoTotal;
    }

    private void atualizaSaldoTotal() {
        this.saldoTotal = this.getSaldo() + this.getLimite();
    }

    @Override
    public String toString() {
        return "Número da Conta: " + this.getNumero() +
                "\nCliente : " + this.cliente.getNome() +
                "\nSaldo Total : " + Utils.doubleParaString(this.getSaldoTotal());
    }

    public void depositar(Double valor) {
        if (valor > 0) {
            this.saldo = this.getSaldo() + valor;
            this.atualizaSaldoTotal();
            System.out.println("Depósito Efeutuado com Sucesso.");
        } else {
            System.out.println("Erro ao Efetuar Depósito. Tente Novamente.");
        }

    }

    public void sacar(Double valor) {
        if (valor > 0 && this.getSaldoTotal() >= valor) {
            if (this.getSaldo() >= valor) {
                this.saldo = this.getSaldo() - valor;
                this.atualizaSaldoTotal();
                System.out.println("Saque Efetuado com Sucesso.");
            } else {
                Double restante = this.getSaldo() - valor;
                this.limite = this.getLimite() + restante; // Regra de Sinais salvou o código + - = -
                this.saldo = 0.0;
                this.atualizaSaldoTotal();
                System.out.println("Saque Efetuado com Sucesso.");
            }
        } else {
            System.out.println("Saque Não Realizado Tente Novamente.");
        }
    }

    public void transferir(Conta destino, Double valor) {
        if (valor > 0 && this.getSaldoTotal() >= valor) {
            if (this.getSaldo() >= valor) {
                this.saldo = this.getSaldo() - valor;
                destino.saldo = destino.getSaldo() + valor;
                this.atualizaSaldoTotal();
                destino.atualizaSaldoTotal();
                System.out.println("Transferência Realizada com Sucesso.");
            } else {
                Double restante = this.getSaldo() - valor;
                this.limite = this.getLimite() + restante;
                this.saldo = 0.0;
                destino.saldo = destino.getSaldo() + valor;
                this.atualizaSaldoTotal();
                destino.atualizaSaldoTotal();
                System.out.println("Transferência Realizada com Sucesso");
            }
        } else {
            System.out.println("Transferência Não Realizada Tente Novamente.");
        }
    }
}
