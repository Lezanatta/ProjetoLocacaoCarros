package Aulas;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AulasLabel extends JFrame 
{
	private ArrayList<JLabel> jl_lista;
	
	public AulasLabel()
	{
		this.CreateGUI();
		this.CreateEvent();
	}
	public void CreateGUI ()
	{
		this.setSize(new Dimension(600, 600));
		this.setTitle("Meu primeiro JFrame");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		this.jl_lista = new ArrayList<JLabel>();
		for (int i = 0; i < 10; i++)
		{
			this.jl_lista.add(new JLabel("Teste " + Integer.toString(i)));
		}
		
		for (int i = 0; i < this.jl_lista.size(); i++)
		{
			JLabel currJLabel = this.jl_lista.get(i);
			currJLabel.setPreferredSize(new Dimension(70,30));
			this.getContentPane().add(currJLabel);
		}
	}
	public void CreateEvent()
	{

	}
	
	public static void main(String[] args) {
		new AulasLabel().setVisible(true);
	}
}
