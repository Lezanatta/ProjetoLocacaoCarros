package Pck_ProjetoCarros;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelComponentes extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public PanelComponentes() {
		setSize(440,250);
		setLocation(20,50);
		setBackground(new Color(238, 213, 183));
		setLayout(null);
	}
	
	public void PanelPrincipal() {
		setBorder(BorderFactory.createTitledBorder("Pagina Inicial"));
	}
	public void PanelIncluirCarro() {
		removeAll();
		setBorder(BorderFactory.createTitledBorder("Cadastramento"));
	}
	
	public void PanelAlterarCarro() {
		removeAll();
		setBorder(BorderFactory.createTitledBorder("Alterar Carro"));
	}
	
	public void PanelExcluirCarro() {
		removeAll();
		setBorder(BorderFactory.createTitledBorder("Excluir Carro"));
	}
	
	public void PanelIncluirLocacao() {
		removeAll();
		setBorder(BorderFactory.createTitledBorder("Incluir Locacao"));
	}
}
