package System.pages;
import System.objects.ObjVMManagementLogin;

public class PageVMManagementLogin extends Page {

	public PageVMManagementLogin() {
		
		// Create VM(Vending Machine) Management Page
		instanceCreate(new ObjVMManagementLogin());
		System.out.println("PageVMManagementLogin Created.");

	}
	
	public void finalize() {
		
		objectList = null;
		objectListSize = 0;
		System.gc();
		System.out.println("PageVMManagementLogin Deleted.");
		
	}
	
}