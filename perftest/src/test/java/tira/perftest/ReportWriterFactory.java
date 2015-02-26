package tira.perftest;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A helper class for creating a writer.
 */
public class ReportWriterFactory {
	
	/**
	 * Gets a report writer for given test class. The writer will create (or overwrite) a file
	 * with the class name, in current working directory.
	 * 
	 * @param testClass	test class to create a writer for.
	 * 
	 * @return	report writer.
	 */
	public static Writer reportWriterFor(Class<?> testClass) {
		try {
			return Files.newBufferedWriter(
				Paths.get(System.getProperty("user.dir"), testClass.getSimpleName()),
				Charset.defaultCharset());
		} catch (IOException ioe) {
			throw new IllegalStateException("Failed to create report writer", ioe);
		}
	}

}
