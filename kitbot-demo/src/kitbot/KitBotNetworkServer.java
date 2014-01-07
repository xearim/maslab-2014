package kitbot;

import java.io.BufferedReader;
import java.io.InputStream;
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
			InputStream input = receive.getInputStream();
			for (int inByte = input.read(); inByte != -1; inByte = input.read()) {
				serialPort.writeInt(inByte);
			}
			serialPort.closePort();
			receive.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}
