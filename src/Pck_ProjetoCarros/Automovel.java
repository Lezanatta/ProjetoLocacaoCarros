package Pck_ProjetoCarros;

public class Automovel {
	
	private String placa,marca,modelo;
	private double valorCarro,valorLocacao;
	private boolean FoiAlterado;
	
	public Automovel(String placa, String marca, String modelo, double valorCarro, double valorLocacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.valorCarro = valorCarro;
		this.valorLocacao = valorLocacao;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getValorCarro() {
		return valorCarro;
	}
	public void setValorCarro(double valorCarro) {
		this.valorCarro = valorCarro;
	}
	public double getValorLocacao() {
		return valorLocacao;
	}
	public void setValorLocacao(double valorLocacao) {
		this.valorLocacao = valorLocacao;
	}

	public boolean getFoiAlterado() {
		return FoiAlterado;
	}
	
	public void setAlterar(boolean alterar) {
		this.FoiAlterado = alterar;
	}
		
}
