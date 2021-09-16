package System.pages;
import System.objects.ObjVMMgr;

public class PageVMMgr extends Page {

	public PageVMMgr() {
		
		// Create VM(Vending Machine) Manager
		instanceCreate(new ObjVMMgr());
		System.out.println("PageVMMgr Created.");

	}
	
	public void finalize() {
		
		objectList = null;
		objectListSize = 0;
		System.gc();
		System.out.println("PageVMMgr Deleted.");
		
	}
	
}