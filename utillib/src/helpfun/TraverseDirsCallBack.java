package helpfun;

import java.io.File;

public interface TraverseDirsCallBack {
		
	public void OnForward(File node);
	public void OnReturnFromRecursion(File node);
}
