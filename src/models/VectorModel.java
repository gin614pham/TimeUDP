package models;

import java.util.Vector;

public class VectorModel extends Vector<clientModel> {

    /**
     * Checks if the list contains the specified object.
     *
     * @param o the object to be checked
     * @return true if the list contains the object, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        if (o instanceof clientModel) {
            clientModel model = (clientModel) o;
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getIp().equals(model.getIp()) && this.get(i).getPort() == model.getPort()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * A description of the entire Java function.
     *
     * @param o the object to search for in the Vector
     * @return the index of the object if found, otherwise -1
     */
    @Override
    public int indexOf(Object o) {
        if (o instanceof clientModel) {
            clientModel model = (clientModel) o;
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getIp().equals(model.getIp()) && this.get(i).getPort() == model.getPort()) {
                    return i;
                }
            }
        }
        return -1;
    }

}
