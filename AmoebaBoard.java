import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;

/**
 * AmoebaBoard.java
 * Name: Gabor Roczei
 * Group: G-3S8
 * Date: 2004-05-11
*/


public class AmoebaBoard extends Canvas implements MouseListener{
	private int counter;
	private int xPosition, yPosition;
	private String Player1; // the black player
	private String Player2; // the red player 
	private int MatrixRow,MatrixColumn;
	private GameItem[][] GameMatrix;
	public void PopupWindow(String LabelText,String Title){
		final Frame PopupWindowFrame=new Frame(Title);
		PopupWindowFrame.setLayout(new FlowLayout());
		PopupWindowFrame.addWindowListener(new WindowAdapter() {
							public void windowClosing (WindowEvent e) {
								PopupWindowFrame.dispose();
						}
		});
		PopupWindowFrame.setResizable(false);
		PopupWindowFrame.setLocation(400,300);
		Label Text=new Label(LabelText);
		Button ButtonItem=new Button("OK");
		PopupWindowFrame.add(Text);
		PopupWindowFrame.add(ButtonItem);
		ButtonItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					PopupWindowFrame.dispose();		
			}
		});
		PopupWindowFrame.setSize(470,100);
		PopupWindowFrame.show();
		
	}
	public AmoebaBoard(String Player1, String Player2){
		xPosition=0;
		yPosition=0;
		counter=0;
		GameMatrix=new GameItem[20][20];
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++)
				GameMatrix[i][j]=null;
		MatrixRow=MatrixColumn=0;
		this.Player1=new String(Player1);
		this.Player2=new String(Player2);
		addMouseListener(this);		
	}
	public GameItem getMatrixElem(int row, int column){
		return GameMatrix[row-1][column-1];
	}
	public void setMatrixElem(int row, int column, GameItem a){
		GameMatrix[row-1][column-1]=new GameItem(a);
	}
	public boolean PlayerVictoryValidation(String Player,Graphics g){
		int k,i,j;
		boolean victory=false;
		Graphics2D Board = (Graphics2D) g;
		RenderingHints RenderingProperties=new RenderingHints (RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		RenderingProperties.put (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		Board.setRenderingHints (RenderingProperties);				
		for(i=0;i<5;i++){ // horizontal (-) validation
			k=0;							
			for(j=i;j<5+i;j++){
				try { // the value of the array is null or the array's index is incorrect
					if (GameMatrix[MatrixRow-1][MatrixColumn-1-4+j].getPlayerName().equals(Player)){
						k++;																		
						continue;
					}						
					else
						break;
				}
				catch (RuntimeException a){
					break;
				}
			}
           if (k==5) {												           
				for(j=i;j<5+i;j++){
					GameMatrix[MatrixRow-1][MatrixColumn-1-4+j].setBackgroundColor();
					Board.setColor(Color.GREEN);
					Board.fillRect(40+1+(MatrixColumn-1-4+j)*20,40+1+(MatrixRow-1)*20,19,19);
					if (Player.equals(Player1)){
						Board.setColor(Color.BLACK);
						Board.fillArc(40+2+(MatrixColumn-1-4+j)*20,40+2+(MatrixRow-1)*20,16,16, 0,360);				
					}
					else{
						Board.setColor(Color.RED);
						Board.fillArc(40+2+(MatrixColumn-1-4+j)*20,40+2+(MatrixRow-1)*20,16,16, 0,360);						
					}
				}
				victory=true;			
			}
		}// end of the for loop
		
				
		for(i=0;i<5;i++){
			k=0;
			for(j=i;j<5+i;j++){// vertical (|) validation
				try { // the valae of the array is null or the array's index is incorrect
					if (GameMatrix[MatrixRow-1-4+j][MatrixColumn-1].getPlayerName().equals(Player)){
						k++;																		
						continue;
					}						
					else
						break;
				}
				catch (RuntimeException a){				
					break;
				}				
			}
			if (k==5){				
				for(j=i;j<5+i;j++){
					GameMatrix[MatrixRow-1-4+j][MatrixColumn-1].setBackgroundColor();
					Board.setColor(Color.GREEN);
					Board.fillRect(40+1+(MatrixColumn-1)*20,40+1+(MatrixRow-1-4+j)*20,19,19);
					if (Player.equals(Player1)){
						Board.setColor(Color.BLACK);
						Board.fillArc(40+2+(MatrixColumn-1)*20,40+2+(MatrixRow-1-4+j)*20,16,16, 0,360);					
					}
					else{
						Board.setColor(Color.RED);
						Board.fillArc(40+2+(MatrixColumn-1)*20,40+2+(MatrixRow-1-4+j)*20,16,16, 0,360);			
					}
				}
				victory=true;
			}		
		} // end of the for loop
				 	
		for(i=0;i<5;i++){
			k=0;
			for(j=i;j<5+i;j++){ //  cross (\) validation
				try { // the valae of the array is null or the array's index is incorrect
					if (GameMatrix[MatrixRow-1-4+j][MatrixColumn-1-4+j].getPlayerName().equals(Player)){
						k++;																		
						continue;
					}						
					else
						break;
				}
				catch (RuntimeException a){				
					break;
				}				
			}
			if (k==5) {												
				for(j=i;j<5+i;j++){
					GameMatrix[MatrixRow-1-4+j][MatrixColumn-1-4+j].setBackgroundColor();
					Board.setColor(Color.GREEN);
					Board.fillRect(40+1+(MatrixColumn-1-4+j)*20,40+1+(MatrixRow-1-4+j)*20,19,19);
					if (Player.equals(Player1)){
						Board.setColor(Color.BLACK);
						Board.fillArc(40+2+(MatrixColumn-1-4+j)*20,40+2+(MatrixRow-1-4+j)*20,16,16, 0,360);					
					}
					else{
						Board.setColor(Color.RED);
						Board.fillArc(40+2+(MatrixColumn-1-4+j)*20,40+2+(MatrixRow-1-4+j)*20,16,16, 0,360);			
					}
				}
				victory=true;
			}
		} // end of the for loop
		
		for(i=0;i<5;i++){
			k=0;
			for(j=i;j<5+i;j++){// cross (/) validation
				try { // the valae of the array is null or the array's index is incorrect
					if (GameMatrix[MatrixRow-1+4-j][MatrixColumn-1-4+j].getPlayerName().equals(Player)){
						k++;																		
						continue;
					}						
					else
						break;
				}
				catch (RuntimeException a){				
					break;
				}				
			}
            if (k==5){									            
				for(j=i;j<5+i;j++){
					GameMatrix[MatrixRow-1+4-j][MatrixColumn-1-4+j].setBackgroundColor();
					Board.setColor(Color.GREEN);
					Board.fillRect(40+1+(MatrixColumn-1-4+j)*20,40+1+(MatrixRow-1+4-j)*20,19,19);
					if (Player.equals(Player1)){
						Board.setColor(Color.BLACK);
						Board.fillArc(40+2+(MatrixColumn-1-4+j)*20,40+2+(MatrixRow-1+4-j)*20,16,16, 0,360);					
					}
					else{
						Board.setColor(Color.RED);
						Board.fillArc(40+2+(MatrixColumn-1-4+j)*20,40+2+(MatrixRow-1+4-j)*20,16,16, 0,360);
					}
				}
				victory=true;
            }
		} // end of the for loop
		return victory;
	}
	
	public void mouseClicked(MouseEvent e){
		xPosition=e.getX();
		yPosition=e.getY();		
		xPosition -= (xPosition % 20);
		yPosition -= (yPosition % 20);
		if ( xPosition>=40 && xPosition<=420 && yPosition>=40 && yPosition<=420 ){
			MatrixRow=yPosition/20-1;
			MatrixColumn=xPosition/20-1;		
		    repaint();
		}
		else
			PopupWindow("You have clicked accidentally on an incorrect place","Error");  					
	}
	public void mousePressed(MouseEvent e){
	}
	public void mouseReleased(MouseEvent e){
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e){
	}
	public void paint (Graphics g) {
		Graphics2D Board = (Graphics2D) g;
		RenderingHints RenderingProperties=new RenderingHints (RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		RenderingProperties.put (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		Board.setRenderingHints (RenderingProperties);
		Board.setColor(Color.blue);		
		for(int i=40;i<=440;i+=20){
				Line2D RowLine = new Line2D.Float(40,i,440,i);
				Board.draw(RowLine);
		}
		for(int i=40;i<=440;i+=20){
			Line2D ColumnLine = new Line2D.Float(i,40,i,440);
			Board.draw(ColumnLine);
		}
		for(int i=0;i<20;i++)
			for(int j=0;j<20;j++) {			
				if (GameMatrix[i][j] != null) {
					if (GameMatrix[i][j].getBackgroundColor()){
						Board.setColor(Color.GREEN);
						Board.fillRect(41+j*20,41+i*20,19,19);						
					}
					if (GameMatrix[i][j].getPlayerName().equals(Player1)){ // Player 1
						Board.setColor(Color.BLACK);
						Board.fillArc(42+20*j,42+20*i,16,16, 0,360);					
					}
					else { // Player 2
						Board.setColor(Color.RED);
						Board.fillArc(42+20*j,42+20*i,16,16, 0,360);
					}
				}
			} // end of the 2nd for loop
		if (counter%2==0){
			Board.setColor(Color.ORANGE);
			Board.fillRect(110,465,100,40);
		}
		else {
			Board.setColor(Color.ORANGE);
			Board.fillRect(280,465,100,40);		
		}
		FontRenderContext frc = Board.getFontRenderContext ();
		Font FontType = new Font ("Times New Roman", Font.BOLD, 16);
							
		Board.setColor(Color.BLACK);
		Board.fillArc(120,475,16,16, 0,360);				
		TextLayout TextProperties1 = new TextLayout (Player1, FontType, frc);
		Board.setColor (Color.BLACK);
		TextProperties1.draw (Board, 145.0f, 491.0f);
							
		Board.setColor(Color.RED);
		Board.fillArc(290,475,16,16, 0,360);
		TextLayout TextProperties2 = new TextLayout (Player2, FontType, frc);
		Board.setColor (Color.RED);
		TextProperties2.draw (Board, 315.0f, 491.0f);										
	}
	public void update(Graphics g){
		Graphics2D Board=(Graphics2D)g;
		RenderingHints RenderingProperties=new RenderingHints (RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		RenderingProperties.put (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		Board.setRenderingHints (RenderingProperties);
		FontRenderContext frc = Board.getFontRenderContext ();
		Font FontType = new Font ("Times New Roman", Font.BOLD, 16);		
		if (GameMatrix[MatrixRow-1][MatrixColumn-1] == null){	
			if (counter%2==0){
				Board.setColor(Color.BLACK);
				Board.fillArc(xPosition + 2, yPosition + 2,16,16, 0,360);
				setMatrixElem(MatrixRow,MatrixColumn,new GameItem(Player1));
				counter++;
				Board.setColor(Color.ORANGE);
				Board.fillRect(280,465,100,40);									
				Board.setColor(Color.WHITE);
				Board.fillRect(110,465,100,40);				
				
				if (PlayerVictoryValidation(Player1,Board)){				
                                        PopupWindow("The winner: " + Player1,"Victory!");
					removeMouseListener(this);
				}		
			}
			else {
				Board.setColor(Color.RED);
				Board.fillArc(xPosition + 2, yPosition + 2,16,16, 0,360);
				setMatrixElem(MatrixRow,MatrixColumn,new GameItem(Player2));				
				counter++;
				Board.setColor(Color.ORANGE);
				Board.fillRect(110,465,100,40);
				Board.setColor(Color.WHITE);
				Board.fillRect(280,465,100,40);						
				if (PlayerVictoryValidation(Player2,Board)) {
					removeMouseListener(this);				
					PopupWindow("The winner: " + Player2,"Victory!");
				}
			}
			Board.setColor(Color.BLACK);
			Board.fillArc(120,475,16,16, 0,360);				
			TextLayout TextProperties1 = new TextLayout (Player1, FontType, frc);
			Board.setColor (Color.BLACK);
			TextProperties1.draw (Board, 145.0f, 491.0f);				
			Board.setColor(Color.RED);
			Board.fillArc(290,475,16,16, 0,360);
			TextLayout TextProperties2 = new TextLayout (Player2, FontType, frc);
			Board.setColor (Color.RED);
			TextProperties2.draw (Board, 315.0f, 491.0f);
			if (counter==400){
				PopupWindow("The end result is draw","Nobody has won");
				removeMouseListener(this);					
			}
		}
		else{		
			PopupWindow("This place has been already occupied, you cannot add one more item here","Error");
			
		}				
	}// end of the update part
}
