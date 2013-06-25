package net.mrblockplacer.WarsOfThePlants.network;

import java.io.IOException;

import net.mrblockplacer.WarsOfThePlants.packets.SomeRequest;
import net.mrblockplacer.WarsOfThePlants.packets.SomeResponse;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class MainNetwork {// implements Runnable {
	// public Socket socket = null;
	// public Thread thread = null;
	// // private DataInputStream console = null;
	// private DataOutputStream streamOut = null;
	// private ChatClientThread client = null;
	// public static JFrame jf;
	// public static JTextArea jta = new JTextArea();
	// public static JTextField jtf = new JTextField();
	public Client client = new Client();

	public MainNetwork() {
		// jta.setEditable(false);
		// jf = new JFrame();
		// // jf.setUndecorated(true);
		//
		// jf.setSize(Game.width * 2, Game.height * 2);
		// // jf.setLocationRelativeTo(Game.frame);
		// // jf.setLocationRelativeTo(null);
		// jta.setSize(jf.getWidth(), jf.getHeight() - 50);
		// jf.add(jta);
		// jf.add(jtf, BorderLayout.SOUTH);
		// jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// jf.setVisible(true);
		// Game.frame.requestFocus();
		try {
			new MainNetwork("direct.mrblockplacer.net", 25565);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MainNetwork(String serverName, int serverPort) throws IOException {
		// jta.append("Establishing connection. Please wait...\n");
		// try {
		// socket = new Socket(serverName, serverPort);
		// jta.append("Connected: " + socket + "\n");
		// start();
		// } catch (UnknownHostException uhe) {
		// jta.append("Host unknown: " + uhe.getMessage() + "\n");
		// } catch (IOException ioe) {
		// jta.append("Unexpected exception: " + ioe.getMessage() + "\n");
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// client = new Client();
		client.start();
		client.connect(5000, "localhost", 54555, 54777);
		Kryo kryo = client.getKryo();
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);

		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				Handler.handle(connection, object);
			}
		});

		SomeRequest request = new SomeRequest();
		request.text = "Connecting to server....!";
		client.sendTCP(request);
		// client.
	}

	// public void run() {
	// sendText(".addPlayer:" + Game.player.x + ":" + Game.player.y);
	// jtf.addActionListener(new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// sendText(jtf.getText());
	// jtf.setText("");
	// }
	//
	// });
	// while (thread != null) {
	// sendText(".modPlayer:" + Game.player.x + ":" + Game.player.y);
	// }
	// }

	// public static void sendIt(String mess) {
	// instance.sendText(mess);
	// }

	public void sendText(String mess) {
		// JOptionPane.showMessageDialog(null, mess);
		SomeRequest st = new SomeRequest();
		st.text = mess;
		client.sendTCP(st);
		// JOptionPane.showMessageDialog(null, "LOL");
	}
	//
	// public void handle(String msg) throws InterruptedException {
	// System.err.println(msg);
	// if (msg.equals(".bye")) {
	// // System.out.println("Good bye. Press RETURN to exit ...");
	// jta.append("Goodbye...\n");
	// stop();
	// } else if (msg.startsWith(".addPlayer")) {
	// String[] test = msg.split(":");
	// try {
	// Game.playerlist.add(new Player(Integer.parseInt(test[2]),
	// Integer.parseInt(test[3]), Integer.parseInt(test[1])));
	// } catch (Exception e) {
	// e.printStackTrace();
	//
	// }
	// // Game.playerlist.get(Integer.parseInt(test[1])).init(Game.level);
	// for (Player p : Game.playerlist) {
	// if (p.id == Integer.parseInt(test[1]))
	// p.init(Game.level);
	// }
	// } else if (msg.startsWith(".modPlayer")) {
	// String[] test = msg.split(":");
	// for (Player p : Game.playerlist) {
	// if (p.id == Integer.parseInt(test[1])) {
	// p.x = Integer.parseInt(test[2]);
	// p.y = Integer.parseInt(test[3]);
	// }
	// }
	// } else if (msg.startsWith(".removePlayer")) {
	// String[] test = msg.split(":");
	// for (Player p : Game.playerlist) {
	// if (p.id == Integer.parseInt(test[1]))
	// p.remove();
	// }
	// } else
	// jta.append(msg + "\n");
	//
	// }
	//
	// public void start() throws IOException, InterruptedException {
	// // console = new DataInputStream(System.in);
	// streamOut = new DataOutputStream(socket.getOutputStream());
	// // System.err.println(streamOut.writeUTF("HI"));// == null ? "hi" :
	// // "hello");
	// streamOut.write("HI".getBytes());// writeUTF("HI");
	// // if (thread == null) {
	// client = new ChatClientThread(this, socket);
	// thread = new Thread(this);
	// thread.start();
	// // }
	// }
	//
	// public void stop() throws InterruptedException {
	// if (thread != null) {
	// thread.join();
	// thread = null;
	// }
	// try {
	// // if (console != null)
	// // console.close();
	// if (streamOut != null)
	// streamOut.close();
	// if (socket != null)
	// socket.close();
	// } catch (IOException ioe) {
	// jta.append("Error closing...\n");
	// }
	// client.close();
	// client.join();
	// }

}
