package Pck_ProjetoCarros;

public class Locacao {
	
	private String cliente,placa;
	private int diasLocacao;
		
	public Locacao(String cliente, String placa, int diasLocacao) {
		super();
		this.cliente = cliente;
		this.placa = placa;
		this.diasLocacao = diasLocacao;
	}
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getDiasLocacao() {
		return diasLocacao;
	}
	public void setDiasLocacao(int diasLocacao) {
		this.diasLocacao = diasLocacao;
	}
	
	
}
