package servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import models.VectorModel;
import models.clientModel;

public class serverThread extends Thread {
    public VectorModel client;
    public DatagramSocket ds;
    public byte[] buf = new byte[1024];

    public serverThread(VectorModel client, DatagramSocket ds) {
        this.client = client;
        this.ds = ds;
    }

    /**
     * Get the client vector model.
     *
     * @return the client vector model
     */
    public VectorModel getClient() {
        return client;
    }

    /**
     * Sets the client for the function.
     *
     * @param client the client to be set
     */
    public void setClient(VectorModel client) {
        this.client = client;
    }

    /**
     * Adds a client to the list of clients.
     *
     * @param cm the client model to be added
     */
    public void addClient(clientModel cm) {
        this.client.add(cm);
    }

    /**
     * Sends the current time to a specified IP address and port.
     *
     * @param ip   the IP address to send the time to
     * @param port the port number to send the time to
     * @throws IOException if an I/O error occurs
     */
    public void sendTime(InetAddress ip, int port) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss E dd/MM/yyyy");
        String time = sdf.format(date);
        buf = time.getBytes();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, ip, port);
        ds.send(dp);
    }

    /**
     * Runs the function indefinitely, iterating over the client list and performing
     * actions based on the client's ID.
     *
     * @param None
     * @return None
     */
    public void run() {
        try {
            while (true) {
                Iterator<clientModel> iterator = client.iterator();
                while (iterator.hasNext()) {
                    clientModel c = iterator.next();
                    try {
                        switch (c.getId()) {
                            case 1:
                                sendTime(c.getIp(), c.getPort());
                                break;
                            case 2:
                                break;
                            case 3:
                                iterator.remove();
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                sleep(1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
