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
		// ����ָ���Ķ˿�
		int port = 8898;
		ServerSocket server = new ServerSocket(port);
		System.out.println("--�����������������˿� --" + port);

		// server��һֱ�ȴ����ӵĵ���
		System.out.println("server��һֱ�ȴ����ӵĵ���");
		System.out.println("�����socket��һֱ���������ֱ�����յ��ͻ��˵���Ϣ");
		// �÷�����һֱ���ڼ���״̬(��ѭ��accept����)
		while (true) {
			Socket socket = server.accept();

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						// TODO Auto-generated method stub
						// ���������Ӻ󣬴�socket�л�ȡ�ͻ��������������������������ж�ȡ
						InputStream inputStream = socket.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
						String clientContent = br.readLine();

						System.out.println("�õ��ͻ��˵�����Ϣת�����ַ�����Ϣ " + clientContent);

						System.out.println("������������");
						System.out.println("123");
						
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// ��ʼ����������ظ��ͻ���
						PrintStream out = new PrintStream(socket.getOutputStream());
						out.println("2");
						System.out.println("����˻ظ����ͻ��ˣ�"+out.toString());
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