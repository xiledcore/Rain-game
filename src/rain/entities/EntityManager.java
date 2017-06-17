package rain.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rain.entities.living.Player;
import rain.main.Handler;

public class EntityManager {

	private Player player;
	public List<Entity> entities = new ArrayList<>();
	private Handler handler;
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		addEntity(player);
	}
	
	public void update() {
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()) {
			Entity e = it.next();
			e.update();
			if(!e.isActive()) {
				it.remove();
			}
		}
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
}
