package castle;

public class HandlerGo extends Handler{
	public HandlerGo(Game game) {
		super(game);
	}
	
	public void doCmd(String word) {
		game.goRoom(word);
	}
}
