package servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import models.VectorModel;
import models.clientModel;

public class server {
    /**
     * Main function that starts the server and handles client connections.
     *
     * @param args the command line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        VectorModel client = new VectorModel();
        DatagramSocket ds = new DatagramSocket(5000);
        serverThread st = new serverThread(client, ds);
        st.start();
        System.out.println("Server is running...");
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);
            int i = Integer.parseInt(new String(dp.getData()).trim());
            clientModel cm = new clientModel(dp.getAddress(), dp.getPort(), i);
            if (!st.getClient().contains(cm)) {
                st.addClient(cm);
            } else {
                client = st.getClient();
                client.set(client.indexOf(cm), cm);
                st.setClient(client);
            }
            System.out.println("This server have " + st.getClient().size() + " clients now.");
        }
    }
}
