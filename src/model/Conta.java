package model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {
	
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	private int agencia;
	private int numero;
	private double saldo;
	private Cliente cliente;
	private List<String> extrato = new ArrayList<String>();

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if(valor > 0 && saldo < valor) {
			System.out.println("Saldo insuficiente para saque");
		}
		else {
		saldo -= valor;
		registrarTransacao("Saque", valor);
		System.out.println("Saque de: " + valor);
		}
	}

	@Override
	public void depositar(double valor) {
		if(valor <= 0) {
			System.out.println("Valor de deposito inválido");
		}
		else {
		saldo += valor;
		registrarTransacao("Deposito", valor);
		System.out.println("Deposito registrado no valor de: " + valor);
		}
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}
	
	private void registrarTransacao(String tipo, double valor) {
        Instant dataHora = Instant.now();
        extrato.add(fmt.format(dataHora) + " - " + tipo + ": " + valor);
    }


	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	  public void imprimirExtrato() {
	        System.out.println("=== Extrato ===");
	        for (String transacao : extrato) {
	            System.out.println(transacao);
	        }
	        System.out.println("Saldo atual: " + saldo);
	    }

}