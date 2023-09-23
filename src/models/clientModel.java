package models;

import java.net.InetAddress;

public class clientModel {
    InetAddress ip;
    int port;
    int id;

    public clientModel(InetAddress ip, int port, int id) {
        this.ip = ip;
        this.port = port;
        this.id = id;
    }

    /**
     * Retrieves the IP address associated with this object.
     *
     * @return the IP address
     */
    public InetAddress getIp() {
        return ip;
    }

    /**
     * Returns the port number.
     *
     * @return the port number
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns the ID of the object.
     *
     * @return the ID of the object
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the object.
     *
     * @param id the new ID value
     */
    public void setId(int id) {
        this.id = id;
    }

}
