package kitbot;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class KitBotNetworkModel implements KitBotModel{
	private byte motorA = 0;
    private byte motorB = 0;
    private Socket connection;
    OutputStream out;
    
	public KitBotNetworkModel(String ip) {
		try {
			connection = new Socket(ip, 4040);
			out = connection.getOutputStream();
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
			out.write(data);
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
