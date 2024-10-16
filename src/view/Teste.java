package view;

import helper.Utils;
import model.Cliente;
import model.Conta;

public class Teste {
    public static void main(String[] args) {
        Cliente allan = new Cliente("Allan Victor", "allan@gmail.com", "123.456.789-10", Utils.stringParaData("25/10/1998"));
        Cliente stella = new Cliente("Stella Goulart", "stella@gmail.com", "321.654.987-01", Utils.stringParaData("18/05/2001"));

        Conta c101 = new Conta(allan);
        Conta c102 = new Conta(stella);

        //Depositando um valor
        c101.depositar(500.00);
        c102.depositar(500.00);

        //c101.sacar(-100.00);
        //c102.sacar(-100.00);
        c101.setLimite(200.0);
        c102.setLimite(200.0);

        System.out.println(c101);
        System.out.println();
        System.out.println(c102);

    }
}
