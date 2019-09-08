
/**
 * Amoeba.java
 * Name: Gabor Roczei
 * Group: G-3S8
 * Date: 2004-05-11
*/

public class GameItem {
	private String PlayerName;
	private boolean BackgroundColor=false;
	public GameItem(String n) {
		PlayerName=new String(n);
	}
	public GameItem(GameItem a){
		PlayerName=new String(a.PlayerName);
	}
	public String getPlayerName() {
		return PlayerName;
	}
	public void setBackgroundColor(){
		BackgroundColor=true;
	}
	public boolean getBackgroundColor(){
		return BackgroundColor;
	}
}
