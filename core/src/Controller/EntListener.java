package Controller;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;

import Model.Entity;

public class EntListener {
	
	ArrayList<Entity> listenedList;
	Client client;
	public EntListener(ArrayList<Entity> listenedList, Client client)
	{
		this.listenedList = listenedList;
		this.client = client;
	}
	public void add(Entity ent)
	{
		listenedList.add(ent);
		
		client.sendTCP(ent);
	}
	
	

}
