package sh.java.network.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ChatServer {
	Scanner sc = new Scanner(System.in);
	final int PORT = 5001; // 포트 지정
	// 포트 : IP 내에서 애플리케이션 상호 구분(프로세스 구분)을 위해 사용하는 번호
	// 0~1024 (well-known port)를 피해서 지정
	
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	private void start() {
		
		try{
			// socket : 데이터를 주고 받기 위한 창구 ( 소켓의 3요소 : 프로토콜, IP, Port )
			// 포트를 바탕으로 ServerSocket을 생성하고 그걸 바탕으로 Socket을 만들어 서버에 접속한다
			
			// 1. server socket 생성
			// => 클라이언트의 요청을 받기 위한 준비를 한다.
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while(true) {
				System.out.println(InetAddress.getLocalHost().getHostAddress() + ":" + PORT + "에서 서버 대기중");
				
				// 2. client 요청 대기 / 요청이 오면 새로운 소켓을 다시 생성해서 통신한다
				// => 클라이언트의 요청을 받아 들인다.
				Socket socket = serverSocket.accept();
				System.out.println(socket.getInetAddress().getHostAddress()+ "로부터 요청!!");
				
				// 3. 입출력생성
				// => 클라이언트가 보낸 데이터를 출력 한다.(BufferedReader)
				// => 클라이언트에 메시지를 보낸다. (BufferedWriter)
				try(
					// (버퍼트리더(문자-바이트 연결 스트림(소켓으로 부터 받은 기본 스트림)))
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					// bridge Stream(위에서 InputStreamReader) 없이 byte 사용 가능
					PrintWriter pw = new PrintWriter(socket.getOutputStream());
				){
					// 웰컴메세지 출력
					pw.println("Welcome~ 💩💩");
					pw.flush(); // 내부 버퍼가 차지 않았어도 전송!
					
					String inMsg = null;
					while((inMsg = br.readLine()) != null) {
						if("exit".equals(inMsg)) {
							System.out.println("클라이언트가 채팅방을 떠났습니다...");
							break;
						}
						
						System.out.println("클라이언트 : " + inMsg);
						System.out.print("서버 : ");
						String outMsg = sc.nextLine();
						pw.println(outMsg);
						pw.flush();
						}
					
					} catch(Exception e) {
						System.err.println(e.getMessage());
					}
				
				} 
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
