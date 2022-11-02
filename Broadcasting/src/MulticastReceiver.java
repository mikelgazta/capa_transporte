import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];

    @SuppressWarnings("deprecation")
	public void run() {
        try {
			socket = new MulticastSocket(4446);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			InetAddress group = InetAddress.getByName("230.0.0.0");
			socket.joinGroup(group);
			while (true) {
			    DatagramPacket packet = new DatagramPacket(buf, buf.length);
			    socket.receive(packet);
			    String received = new String(
			      packet.getData(), 0, packet.getLength());
			    if ("end".equals(received)) {
			        break;
			    }
			}
			socket.leaveGroup(group);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        socket.close();
    }
}