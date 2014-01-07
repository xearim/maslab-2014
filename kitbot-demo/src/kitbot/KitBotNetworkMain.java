package kitbot;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class KitBotNetworkMain {
	
	public static void main(String[] args) {
		int width = 1366;
    	int height = 768;
    	
    	try {
    		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    		width = dim.width;
    		height = dim.height;
    	} catch ( Exception e ) {
    		System.out.println( e );
    	}
    	
    	JFrame window = new JFrame("Kit Bot Interface");
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Replace ip address with what we give you
    	KitBotNetworkModel model = new KitBotNetworkModel("18.111.118.226");
    	KitBotView view = new KitBotView(width,height,window);
    	KitBotController controller = new KitBotController(model,view);
    	
    	window.setSize(width,height);
    	window.setVisible(true);
    	
    	while ( true ) {
    		try {
    			Thread.sleep(100);
    			view.repaint();
    		} catch ( Exception e ) {}
    	}
	}

}
