package View;

import Controller.InputWaiter;

public class View {
	
	
//	private MakeFrame frame = new MakeFrame( wait);

	private SearchPanel searchPan;
		@SuppressWarnings("unused")
	private MenuBar menu;
	private LoginPanel loginPanel;

	
public View(){
	
	
	searchPan = new SearchPanel();
	
	loginPanel = new LoginPanel();
	
}
	public void test(){
//		frame.setSize(700,500);
		
	}
	
	
}
