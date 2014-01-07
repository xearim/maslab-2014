package kitbot;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KitBotNetworkController implements MouseListener {
	private KitBotNetworkModel model;
	private KitBotView view;
	
	public KitBotNetworkController( KitBotNetworkModel model, KitBotView view ) {
		this.model = model;
		this.view = view;
		
		this.view.getwindow().addMouseListener( this );
	}
	
	public void mousePressed( MouseEvent me ) {
		int x = me.getX();
		int y = me.getY();
		
		if ( view.left.contains(x,y) ) {
			model.setMotors(0.2,-0.2);
		} else if ( view.right.contains(x,y) ) {
			model.setMotors(-0.2,0.2);
		} else if ( view.forward.contains(x,y) ) {
			model.setMotors(0.2,0.2);
		} else if ( view.stop.contains(x,y) ) {
			model.setMotors(0,0);
		}
	}
	
	public void mouseReleased( MouseEvent me ) {
	}
	
	public void mouseClicked( MouseEvent me ) {
	}
	
	public void mouseEntered( MouseEvent me ) {
	}
	
	public void mouseExited( MouseEvent me ) {
	}
}

