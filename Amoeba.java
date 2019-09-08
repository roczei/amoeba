import java.awt.*;
import java.awt.event.*;

/**
 * Amoeba.java
 * Name: Gabor Roczei
 * Group: G-3S8
 * Date: 2004-05-11
*/

public class Amoeba {
	private static AmoebaBoard GameBoard;
	private static String Player1,Player2;	
	public static void main(String[] args) {
		final Frame window=new Frame("Amoaba board game     Gabor Roczei      G-3S8");
		window.addWindowListener(new WindowAdapter() {
							public void windowClosing (WindowEvent e) {
							System.exit (0);
						}
		});
		window.setResizable(false);
		window.setLocation(300,80);
		MenuBar AmoebaMenu=new MenuBar();
		window.setMenuBar(AmoebaMenu);
		Menu MainMenu = new Menu("Main menu");
		AmoebaMenu.add(MainMenu);
		MenuItem MenuItem1=new MenuItem("New game");
		MainMenu.add(MenuItem1);	
	        final Panel NewGame=new Panel();
		Label Player1Label=new Label("Player 1: ");		
		NewGame.add(Player1Label);
		final TextField InputPlayer1=new TextField(10);
		NewGame.add(InputPlayer1);
		Label Player2Label=new Label("Player 2: ");
		NewGame.add(Player2Label);
		final TextField InputPlayer2=new TextField(10);
		NewGame.add(InputPlayer2);
		Button OKButton=new Button("OK");
		OKButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (InputPlayer1.getText().length()>0 && InputPlayer2.getText().length()>0 
					&& !InputPlayer1.getText().equals(InputPlayer2.getText())){
						Player1=new String(InputPlayer1.getText());
						Player2=new String(InputPlayer2.getText());
						window.remove(NewGame);
						GameBoard=new AmoebaBoard(Player1,Player2);											
						window.add(GameBoard);
						window.show();
				}
			}
		});
		NewGame.add(OKButton);
										
		MenuItem1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					window.remove(GameBoard);
				}
				catch(Exception a){
				}
				window.add(NewGame);
				window.show();										
			}
		});
		MenuItem MenuItem2=new MenuItem("Exit");
		MainMenu.add(MenuItem2);
		MainMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				System.exit(0);
			}			
		});
		window.setSize (500, 580);
		window.show();				
	}
}
