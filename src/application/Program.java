package application;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

public class Program {

	public static void main(String[] args) {

		Cliente vinicius = new Cliente();
		vinicius.setNome("Vinicius");

		Conta cc = new ContaCorrente(vinicius);
		Conta poupanca = new ContaPoupanca(vinicius);

		cc.depositar(500);
		cc.transferir(100, poupanca);

		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}

}
