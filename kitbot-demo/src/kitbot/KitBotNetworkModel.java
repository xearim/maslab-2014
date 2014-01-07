package kitbot;

import java.io.PrintWriter;
import java.net.Socket;

public class KitBotNetworkModel {
	private byte motorA = 0;
    private byte motorB = 0;
    private Socket connection;
    PrintWriter out;
    
	public KitBotNetworkModel(String ip) {
		try {
			connection = new Socket(ip, 4040);
			out = new PrintWriter(connection.getOutputStream(), true);
		} catch ( Exception e) {
			System.out.println(e);
		}
	}
	
	public void setMotors( double powerA, double powerB ) {
		motorA = (byte)(-powerA*127);
		motorB = (byte)(powerB*127);
		modified();
	}
	
	public void modified() {
		try {
			byte[] data = new byte[4];
			data[0] = 'S';		// Start signal "S"
			data[1] = motorA;	// Motor A data
			data[2] = motorB;	// Motor B data
			data[3] = 'E';		// End signal "E"
			out.println(new String(data, "UTF-8"));
		} catch ( Exception ex ) {
			System.out.println(ex);
		}
	}
	
	public void finalize() {
		try {
			out.close();
		} catch ( Exception ex ) {
			System.out.println(ex);
		}
	}
}
