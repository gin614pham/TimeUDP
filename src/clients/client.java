package clients;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class client extends Thread {
    DatagramSocket ds;
    InetAddress ip;
    byte[] buf;
    byte[] data;
    clientGUI gui;
    boolean running = true;
    final int RUN = 1;
    final int STOP = 2;
    final int EXITS = 3;

    public client(DatagramSocket ds, InetAddress ip, byte[] buf, byte[] data) {
        this.ds = ds;
        this.ip = ip;
        this.buf = buf;
        this.data = data;
        this.gui = new clientGUI();
        this.gui.btnStop.addActionListener(new ButtonListener());
        this.gui.frame.addWindowListener(new WindowAdapter() {
            /**
             * Handles the window closing event.
             *
             * @param w the WindowEvent object representing the closing event
             */
            @Override
            public void windowClosing(WindowEvent w) {
                try {
                    int choice = JOptionPane.showConfirmDialog(gui.frame, "Are you sure to exit?", "Exit",
                            JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        running = false;
                        sendData(EXITS);
                        System.exit(0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.start();
    }

    /**
     * Sends data over a network using a UDP protocol.
     *
     * @param i the integer value to be sent
     * @throws IOException if an I/O error occurs while sending the data
     */
    public void sendData(int i) throws IOException {
        buf = Integer.toString(i).getBytes();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, ip, 5000);
        ds.send(dp);
    }

    /**
     * Runs the function.
     *
     * @param None No parameters are needed.
     * @return No return value.
     */
    public void run() {
        try {
            while (running) {
                sendData(RUN);

                DatagramPacket packet = new DatagramPacket(data, data.length);
                ds.receive(packet);
                String s = new String(packet.getData()).trim();
                gui.textFTime.setText(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A description of the entire Java function.
     *
     * @param args the command-line arguments passed to the program
     * @throws IOException if an I/O error occurs while creating the DatagramSocket
     *                     or getting the InetAddress
     */
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        byte[] buf = new byte[1024];
        byte[] data = new byte[1024];
        new client(ds, ip, buf, data);
    }

    public class ButtonListener implements ActionListener {

        /**
         * Performs an action in response to an ActionEvent.
         *
         * @param a the ActionEvent object
         * @return void
         */
        public void actionPerformed(ActionEvent a) {
            try {
                JButton b = (JButton) a.getSource();
                switch (a.getActionCommand()) {
                    case "Run":
                        b.setActionCommand("Stop");
                        b.setText("Stop");
                        sendData(RUN);
                        running = true;
                        break;
                    case "Stop":
                        b.setActionCommand("Run");
                        b.setText("Run");
                        sendData(STOP);
                        running = false;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
