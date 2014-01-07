package kitbot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import jssc.SerialPort;

public class KitBotNetworkServer {

	public static void main(String[] args) {
		SerialPort serialPort;
		ServerSocket listen;
		try {
			listen = new ServerSocket(4040);
			serialPort = new SerialPort("COM4");
            serialPort.openPort();
            serialPort.setParams(115200, 8, 1, 0);
            
            Socket receive = listen.accept();
        	BufferedReader input = new BufferedReader(new InputStreamReader(receive.getInputStream()));
        	for(String line = input.readLine(); line != null; line = input.readLine()){
        		serialPort.writeBytes(line.getBytes("UTF-8"));
        	}
        	serialPort.closePort();
        	receive.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    	
    }
}

