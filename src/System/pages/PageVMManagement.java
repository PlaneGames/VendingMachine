package System.pages;
import System.objects.ObjVMManagement;

public class PageVMManagement extends Page {

	public PageVMManagement() {
		
		// Create VM(Vending Machine) Management Page
		instanceCreate(new ObjVMManagement());
		System.out.println("PageVMManagement Created.");

	}
	
	public void finalize() {
		
		objectList = null;
		objectListSize = 0;
		System.gc();
		System.out.println("PageVMManagement Deleted.");
		
	}
	
}