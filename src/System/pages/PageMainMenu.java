package System.pages;
import System.objects.ObjMainMenu;

public class PageMainMenu extends Page {

	public PageMainMenu() {
		
		// Create Main Menu Manager
		instanceCreate(new ObjMainMenu());
		System.out.println("PageMainMenu Created.");
		
	}
	
	public void finalize() {
		
		objectList = null;
		objectListSize = 0;
		System.gc();
		System.out.println("PageMainMenu Deleted.");
		
	}
	
}
