package sp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
	public static void main(String[] args) throws Exception {
		// 监听指定的端口
		int port = 8898;
		ServerSocket server = new ServerSocket(port);
		System.out.println("--开启服务器，监听端口 --" + port);

		// server将一直等待连接的到来
		System.out.println("server将一直等待连接的到来");
		System.out.println("服务端socket会一直阻塞在这里，直到接收到客户端的信息");
		// 让服务器一直处于监听状态(死循环accept方法)
		while (true) {
			Socket socket = server.accept();

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						// TODO Auto-generated method stub
						// 建立好连接后，从socket中获取客户端输入流，并建立缓冲区进行读取
						InputStream inputStream = socket.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
						String clientContent = br.readLine();

						System.out.println("得到客户端的流信息转换的字符串信息 " + clientContent);

						System.out.println("服务器已连接");
						System.out.println("123");
						
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// 初始化输出流，回复客户端
						PrintStream out = new PrintStream(socket.getOutputStream());
						out.println("2");
						System.out.println("服务端回复给客户端："+out.toString());
						out.close();

						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}).start();
		}

	}

}