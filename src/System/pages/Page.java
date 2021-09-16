package System.pages;
import java.util.ArrayList;
import System.DrawFunc;
import System.objects.Object;
import System.objects.drinks.ObjDrinkSports;

public class Page extends DrawFunc {
	
	public ArrayList<Object> objectList;
	public Object tempObject;
	public int objectListSize;
	
	private int i;
	
	public void instanceCreate(Object objectIndex) {
		objectList.add(objectListSize, objectIndex);
		objectListSize++;
	}
	
	public Page() {
		objectList  	= new ArrayList<Object>();
		objectListSize  = 0;
		Init();
	}

	public void Init() {
		
	}

	public void Update() {
		for(i = 0; i < objectListSize; i ++) {
			tempObject = objectList.get(i);
			tempObject.Update();
		}
	}

	public void Render() {
		for(i = 0; i < objectListSize; i ++) {
			tempObject = objectList.get(i);
			tempObject.Render();
		}
	}
	
	public void mouseCheck() {
		for(i = 0; i < objectListSize; i ++) {
			tempObject = objectList.get(i);
			tempObject.mouseCheck();
		}
	}
	
	public void mousePressed() {
		for(i = 0; i < objectListSize; i ++) {
			tempObject = objectList.get(i);
			tempObject.mousePressed();
		}
	}
	
	public void mouseReleased() {
		for(i = 0; i < objectListSize; i ++) {
			tempObject = objectList.get(i);
			tempObject.mouseReleased();
		}
	}

}
