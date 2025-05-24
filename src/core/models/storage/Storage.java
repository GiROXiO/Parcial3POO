/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import java.util.ArrayList;

/**
 *
 * @author jecas
 * @param <T>
 */
public abstract class Storage<T>{
    protected final ArrayList<T> lista;
    protected final String path;

    protected Storage(String path) {
        this.path = path;
        this.lista = new ArrayList<>();
    }
    
    public abstract boolean add(T obj);
    public abstract T get(String id);
    public abstract boolean del(int id);
    public abstract boolean load();

    public ArrayList<T> getLista() {
        return lista;
    }
}
