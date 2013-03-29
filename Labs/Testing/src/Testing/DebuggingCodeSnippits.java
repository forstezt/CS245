package Testing;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebuggingCodeSnippits {
	public static void main(String[] args) {
		try {
			FileReader in = new FileReader(new File("thing.txt"));
		} catch (Exception cause) {
			StackTraceElement elements[] = cause.getStackTrace();
			for (int i = 0, n = elements.length; i < n; i++) {
				System.err.println(elements[i].getFileName() + ":"
						+ elements[i].getLineNumber() + ">> "
						+ elements[i].getMethodName() + "()");
			}
		}

		try {
			Handler handler = new FileHandler("OutFile.log");
			Logger.getLogger("").addHandler(handler);
		} catch (IOException e) {
			Logger logger = Logger.getLogger("package.name");
			StackTraceElement elements[] = e.getStackTrace();
			for (int i = 0, n = elements.length; i < n; i++) {
				logger.log(Level.WARNING, elements[i].getMethodName());
			}
		}

	}
}
