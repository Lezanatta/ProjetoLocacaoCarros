package Pck_ProjetoCarros;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Componentes implements ActionListener {
	String telaUsuario;
	private MenuBar menuPrincipal;
	private Menu menuCarro, menuLocacao, menuConsultar;
	private MenuItem itemIncluirCarro, itemExcluirCarro, itemAlterarCarro, itemIncluirLocacao, itemAlterarLocacao,
			itemExcluirLocacao, itemConsultarcarroLocados, itemClientesCarrosLocados, itemMontanteDeLocacao;
	private List<Button> listButAlterar = new ArrayList<Button>();
	private List<Button> listButExcluir = new ArrayList<Button>();
	private List<Automovel> listAutomovel = new ArrayList<Automovel>();
	private Label labelCadastro, labelDe, labelCarros, labelPlaca, labelMarca, labelModelo, labelValorCarro,
			labelValorLocacao, labelCliente, labelDiasLocacao;
	private TextField txtPlaca, txtMarca, txtModelo, txtValorCarro, txtValorLocacao, txtCliente, txtDiasLocacao;
	private Button btnInserir, btnLimpar, btnSair, btnAlterar;

	PanelComponentes panelPrincipal = new PanelComponentes();

	FramePrincipal frame = new FramePrincipal();

	public Componentes() {
		GerarMenus();
		GerarLabelPaginaInicio();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Incluir Carro")
			GerarPaneIncluirCarro();

		if (e.getActionCommand() == "INSERIR" && telaUsuario == "Incluir Carro") {
			RealizarCadastro();
		}

		if (e.getActionCommand() == "LIMPAR")
			LimparTela();

		if (e.getActionCommand() == "SAIR")
			GerarLabelPaginaInicio();

		if (e.getActionCommand() == "Alterar Carro")
			CriarPaneAlterar();

		if (e.getActionCommand() == "Excluir Carro")
			CriarPaneExcluirCarro();

		if (e.getActionCommand() == "Incluir Locacao")
			GerarPaneIncluirLocacao();

		for (int posicao = 0; posicao < listButExcluir.size(); posicao++) {
			if (e.getSource() == listButExcluir.get(posicao))
				RemoverAutomovel(posicao);
		}

		for (int posicao = 0; posicao < listButAlterar.size(); posicao++) {
			if (e.getSource() == listButAlterar.get(posicao))
				CriarPaneAlterarAutomovel(posicao);

		}
		if (e.getSource() == btnAlterar)
			AlterarAutomovel();
	}

	public void RemoverAutomovel(int posicao) {
		listAutomovel.remove(posicao);
		CriarPaneExcluirCarro();
	}

	public void AlterarAutomovel() {
		if (TemCamposVaziosNaTela())
			MostrarMensagemNaTela("Tem Campos Vazio!");
		else
			PegarAutomovelAlterado();
	}

	public void PegarAutomovelAlterado() {
		for (int posicao = 0; posicao < listAutomovel.size(); posicao++) {
			if (listAutomovel.get(posicao).getFoiAlterado()) {
				listAutomovel.get(posicao).setPlaca(txtPlaca.getText());
				listAutomovel.get(posicao).setMarca(txtMarca.getText());
				listAutomovel.get(posicao).setModelo(txtModelo.getText());
				listAutomovel.get(posicao).setValorCarro(Double.parseDouble(txtValorCarro.getText()));
				listAutomovel.get(posicao).setValorLocacao(Double.parseDouble(txtValorLocacao.getText()));
				MostrarMensagemNaTela("Alteração Realizada!");
				listAutomovel.get(posicao).setAlterar(false);
				CriarPaneAlterar();
			}
		}
	}

	public void CriarPaneAlterarAutomovel(int posicao) {
		panelPrincipal.removeAll();
		GerarLabelAlterarCarroExcluirCarro();
		listAutomovel.get(posicao).setAlterar(true);

		Label auxPlaca = new Label();
		auxPlaca.setText(listAutomovel.get(posicao).getPlaca());
		auxPlaca.setBounds(20, 100, 70, 30);

		Label auxMarca = new Label();
		auxMarca.setText(listAutomovel.get(posicao).getMarca());
		auxMarca.setBounds(100, 100, 70, 30);

		Label auxModelo = new Label();
		auxModelo.setText(listAutomovel.get(posicao).getModelo());
		auxModelo.setBounds(180, 100, 70, 30);

		Label auxValorCarro = new Label();
		auxValorCarro.setText(Double.toString(listAutomovel.get(posicao).getValorCarro()));
		auxValorCarro.setBounds(275, 100, 70, 30);

		Label auxValorLocacao = new Label();
		auxValorLocacao.setText(Double.toString(listAutomovel.get(posicao).getValorLocacao()));
		auxValorLocacao.setBounds(395, 100, 70, 30);

		txtPlaca = new TextField("");
		txtPlaca.setBounds(20, 150, 60, 20);

		txtMarca = new TextField("");
		txtMarca.setBounds(100, 150, 60, 20);

		txtModelo = new TextField("");
		txtModelo.setBounds(180, 150, 60, 20);

		txtValorCarro = new TextField("");
		txtValorCarro.setBounds(275, 150, 60, 20);

		txtValorLocacao = new TextField("");
		txtValorLocacao.setBounds(395, 150, 60, 20);

		btnAlterar = new Button("Alterar");
		btnAlterar.setBounds(250, 250, 80, 50);
		btnAlterar.setBackground(new Color(150, 120, 255));
		btnAlterar.addActionListener(this);

		panelPrincipal.add(txtPlaca);
		panelPrincipal.add(txtMarca);
		panelPrincipal.add(txtModelo);
		panelPrincipal.add(txtValorCarro);
		panelPrincipal.add(txtValorLocacao);

		panelPrincipal.add(auxPlaca);
		panelPrincipal.add(auxMarca);
		panelPrincipal.add(auxModelo);
		panelPrincipal.add(auxValorCarro);
		panelPrincipal.add(auxValorLocacao);

		panelPrincipal.add(btnAlterar);
	}

	public void RealizarCadastro() {
		if (TemCamposVaziosNaTela())
			MostrarMensagemNaTela("Tem Campos Vazios!");
		else {
			listAutomovel.add(new Automovel(txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(),
					Double.parseDouble(txtValorCarro.getText()), Double.parseDouble(txtValorLocacao.getText())));
			MostrarMensagemNaTela("Cadastro Feito!");
		}
	}

	public boolean TemCamposVaziosNaTela() {
		if (txtPlaca.getText().isEmpty() || txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty()
				|| txtValorCarro.getText().isEmpty() || txtValorLocacao.getText().isEmpty())
			return true;
		else
			return false;
	}

	public void GerarPaneIncluirLocacao() {
		telaUsuario = "Incluir Locacao";
		panelPrincipal.PanelIncluirLocacao();
		GerarLabelIncluirLocacao();
		GerarButtonTelas();
		GerarTextIncluirLocacao();
	}

	public void GerarLabelIncluirLocacao() {
		Font font = new Font("Serif", Font.BOLD, 15);
		labelCliente = new Label("Cliente: ");
		labelCliente.setBounds(20, 50, 70, 20);
		labelCliente.setFont(font);

		labelPlaca = new Label("Placa: ");
		labelPlaca.setBounds(20, 130, 70, 20);
		labelPlaca.setFont(font);

		labelDiasLocacao = new Label("Dias Locacao: ");
		labelDiasLocacao.setBounds(20, 210, 100, 20);
		labelDiasLocacao.setFont(font);

		panelPrincipal.add(labelCliente);
		panelPrincipal.add(labelPlaca);
		panelPrincipal.add(labelDiasLocacao);
	}

	public void GerarLabelPaginaInicio() {
		panelPrincipal.removeAll();
		Font font = new Font("Serif", Font.BOLD, 30);
		labelCadastro = new Label("Cadastro");
		labelCadastro.setFont(font);
		labelCadastro.setBounds(230, 70, 120, 50);

		labelDe = new Label("De");
		labelDe.setBounds(270, 160, 100, 50);
		labelDe.setFont(font);

		labelCarros = new Label("Carros");
		labelCarros.setFont(font);
		labelCarros.setBounds(245, 250, 100, 50);

		panelPrincipal.add(labelCadastro);
		panelPrincipal.add(labelDe);
		panelPrincipal.add(labelCarros);
		panelPrincipal.PanelPrincipal();

		frame.setContentPane(panelPrincipal);
	}

	public void GerarTextIncluirLocacao() {
		txtCliente = new TextField("");
		txtCliente.setBounds(160, 50, 120, 20);

		txtPlaca = new TextField("");
		txtPlaca.setBounds(160, 130, 120, 20);

		txtDiasLocacao = new TextField("");
		txtDiasLocacao.setBounds(160, 210, 120, 20);

		panelPrincipal.add(txtCliente);
		panelPrincipal.add(txtPlaca);
		panelPrincipal.add(txtDiasLocacao);

	}

	public MenuBar GerarMenus() {
		menuCarro = new Menu("Carro");
		itemIncluirCarro = new MenuItem("Incluir Carro");
		itemIncluirCarro.addActionListener(this);

		itemExcluirCarro = new MenuItem("Excluir Carro");
		itemExcluirCarro.addActionListener(this);

		itemAlterarCarro = new MenuItem("Alterar Carro");
		itemAlterarCarro.addActionListener(this);

		menuCarro.add(itemIncluirCarro);
		menuCarro.add(itemExcluirCarro);
		menuCarro.add(itemAlterarCarro);

		menuLocacao = new Menu("Locacao");
		itemIncluirLocacao = new MenuItem("Incluir Locacao");
		itemIncluirLocacao.addActionListener(this);

		itemAlterarLocacao = new Menu("Alterar Locacao");
		itemAlterarLocacao.addActionListener(this);

		itemExcluirLocacao = new Menu("Excluir Locacao");
		itemExcluirLocacao.addActionListener(this);

		menuLocacao.add(itemIncluirLocacao);
		menuLocacao.add(itemAlterarLocacao);
		menuLocacao.add(itemExcluirLocacao);

		menuConsultar = new Menu("Consultar");
		itemConsultarcarroLocados = new MenuItem("Carros Locados");
		itemConsultarcarroLocados.addActionListener(this);

		itemClientesCarrosLocados = new MenuItem("Clientes aos carros locados");
		itemClientesCarrosLocados.addActionListener(this);

		itemMontanteDeLocacao = new MenuItem("Montante de Locacao");
		itemMontanteDeLocacao.addActionListener(this);

		menuConsultar.add(itemConsultarcarroLocados);
		menuConsultar.add(itemClientesCarrosLocados);
		menuConsultar.add(itemMontanteDeLocacao);

		menuPrincipal = new MenuBar();
		menuPrincipal.add(menuCarro);
		menuPrincipal.add(menuLocacao);
		menuPrincipal.add(menuConsultar);

		frame.setMenuBar(menuPrincipal);

		return menuPrincipal;
	}

	public void GerarPaneIncluirCarro() {
		telaUsuario = "Incluir Carro";
		panelPrincipal.PanelIncluirCarro();
		GerarLabelIncluirCarro();
		GerarTextIncluirCarro();
		GerarButtonTelas();
	}

	public void CriarPaneAlterar() {
		telaUsuario = "Alterar Carro";
		panelPrincipal.PanelAlterarCarro();
		GerarLabelAlterarCarroExcluirCarro();
		GerarListButtonAlterar();
		InserirLabelsAlterarExcluir();
	}

	public void CriarPaneExcluirCarro() {
		telaUsuario = "Excluir Carro";
		panelPrincipal.PanelExcluirCarro();
		GerarListButtonExcluir();
		GerarLabelAlterarCarroExcluirCarro();
		InserirLabelsAlterarExcluir();
	}

	public void GerarLabelIncluirCarro() {

		Font font = new Font("Serif", Font.BOLD, 15);
		labelPlaca = new Label("Placa: ");
		labelPlaca.setBounds(20, 50, 70, 20);
		labelPlaca.setFont(font);

		labelMarca = new Label("Marca: ");
		labelMarca.setBounds(20, 100, 70, 20);
		labelMarca.setFont(font);

		labelModelo = new Label("Modelo: ");
		labelModelo.setBounds(20, 150, 70, 20);
		labelModelo.setFont(font);

		labelValorCarro = new Label("Valor do Carro: ");
		labelValorCarro.setBounds(20, 200, 105, 20);
		labelValorCarro.setFont(font);

		labelValorLocacao = new Label("Valor da Locacao: ");
		labelValorLocacao.setBounds(20, 250, 120, 20);
		labelValorLocacao.setFont(font);

		panelPrincipal.add(labelPlaca);
		panelPrincipal.add(labelMarca);
		panelPrincipal.add(labelModelo);
		panelPrincipal.add(labelValorCarro);
		panelPrincipal.add(labelValorLocacao);
	}

	public void GerarTextIncluirCarro() {
		txtPlaca = new TextField("");
		txtPlaca.setBounds(160, 50, 120, 20);

		txtMarca = new TextField("");
		txtMarca.setBounds(160, 100, 120, 20);

		txtModelo = new TextField("");
		txtModelo.setBounds(160, 150, 120, 20);

		txtValorCarro = new TextField("");
		txtValorCarro.setBounds(160, 200, 120, 20);

		txtValorLocacao = new TextField("");
		txtValorLocacao.setBounds(160, 250, 120, 20);

		panelPrincipal.add(txtPlaca);
		panelPrincipal.add(txtMarca);
		panelPrincipal.add(txtModelo);
		panelPrincipal.add(txtValorCarro);
		panelPrincipal.add(txtValorLocacao);
	}

	public void GerarButtonTelas() {
		btnInserir = new Button("INSERIR");
		btnInserir.setBounds(30, 350, 80, 50);
		btnInserir.setBackground(new Color(150, 120, 255));
		btnInserir.addActionListener(this);

		btnLimpar = new Button("LIMPAR");
		btnLimpar.setBounds(250, 350, 80, 50);
		btnLimpar.setBackground(new Color(150, 120, 255));
		btnLimpar.addActionListener(this);

		btnSair = new Button("SAIR");
		btnSair.setBounds(455, 350, 80, 50);
		btnSair.setBackground(new Color(150, 120, 255));
		btnSair.addActionListener(this);

		panelPrincipal.add(btnInserir);
		panelPrincipal.add(btnLimpar);
		panelPrincipal.add(btnSair);

	}

	public void InserirLabelsAlterarExcluir() {
		int posicaoVertical = 100;
		for (int i = 0; i < listAutomovel.size(); i++) {

			Label auxPlaca = new Label();
			auxPlaca.setText(listAutomovel.get(i).getPlaca());
			auxPlaca.setBounds(20, posicaoVertical, 70, 30);

			Label auxMarca = new Label();
			auxMarca.setText(listAutomovel.get(i).getMarca());
			auxMarca.setBounds(100, posicaoVertical, 70, 30);

			Label auxModelo = new Label();
			auxModelo.setText(listAutomovel.get(i).getModelo());
			auxModelo.setBounds(180, posicaoVertical, 70, 30);

			Label auxValorCarro = new Label();
			auxValorCarro.setText(Double.toString(listAutomovel.get(i).getValorCarro()));
			auxValorCarro.setBounds(275, posicaoVertical, 70, 30);

			Label auxValorLocacao = new Label();
			auxValorLocacao.setText(Double.toString(listAutomovel.get(i).getValorLocacao()));
			auxValorLocacao.setBounds(395, posicaoVertical, 70, 30);

			panelPrincipal.add(auxPlaca);
			panelPrincipal.add(auxMarca);
			panelPrincipal.add(auxModelo);
			panelPrincipal.add(auxValorCarro);
			panelPrincipal.add(auxValorLocacao);

			posicaoVertical += 50;
		}
	}

	public void MostrarMensagemNaTela(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void GerarLabelAlterarCarroExcluirCarro() {
		Font font = new Font("Serif", Font.BOLD, 15);
		labelPlaca = new Label("Placa");
		labelPlaca.setBounds(20, 50, 70, 30);
		labelPlaca.setFont(font);

		labelMarca = new Label("Marca");
		labelMarca.setBounds(100, 50, 70, 30);
		labelMarca.setFont(font);

		labelModelo = new Label("Modelo");
		labelModelo.setBounds(180, 50, 70, 30);
		labelModelo.setFont(font);

		labelValorCarro = new Label("Valor Carro");
		labelValorCarro.setBounds(275, 50, 120, 30);
		labelValorCarro.setFont(font);

		labelValorLocacao = new Label("Valor Locacao");
		labelValorLocacao.setBounds(395, 50, 130, 30);
		labelValorLocacao.setFont(font);

		panelPrincipal.add(labelPlaca);
		panelPrincipal.add(labelMarca);
		panelPrincipal.add(labelModelo);
		panelPrincipal.add(labelValorCarro);
		panelPrincipal.add(labelValorLocacao);
	}

	public void GerarListButtonAlterar() {
		listButAlterar.removeAll(listButAlterar);
		int posicaoVertical = 100;
		for (int i = 0; i < listAutomovel.size(); i++) {
			Button auxBut = new Button("Alterar");
			auxBut.addActionListener(this);
			auxBut.setBounds(500, posicaoVertical, 70, 20);
			auxBut.setBackground(new Color(150, 120, 255));
			panelPrincipal.add(auxBut);
			listButAlterar.add(auxBut);
			posicaoVertical += 50;
		}
	}

	public void GerarListButtonExcluir() {
		int posicaoVertical = 100;
		listButExcluir.removeAll(listButExcluir);
		for (int i = 0; i < listAutomovel.size(); i++) {
			Button auxBut = new Button("Excluir");
			auxBut.addActionListener(this);
			auxBut.setBounds(500, posicaoVertical, 70, 20);
			auxBut.setBackground(new Color(150, 120, 255));
			panelPrincipal.add(auxBut);
			listButExcluir.add(auxBut);
			posicaoVertical += 50;
		}
	}

	public void LimparTela() {
		if (telaUsuario == "Incluir Locacao") {
			txtCliente.setText("");
			txtDiasLocacao.setText("");
			txtPlaca.setText("");
		} else {
			txtPlaca.setText("");
			txtMarca.setText("");
			txtModelo.setText("");
			txtValorCarro.setText("");
			txtValorLocacao.setText("");
		}
	}
}
