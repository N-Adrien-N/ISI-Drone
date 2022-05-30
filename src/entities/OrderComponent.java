/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author isi
 */
public class OrderComponent extends Order {

    String clientName;

    public OrderComponent() {
        super();
    }

    public OrderComponent(String clientName, int id, String date) {
        this.clientName = clientName;
        super.id = id;
        super.date = date;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String name) {
        this.clientName = name;
    }

}
