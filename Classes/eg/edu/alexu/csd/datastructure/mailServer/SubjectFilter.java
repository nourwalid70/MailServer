package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.DLinkedList;

public class SubjectFilter implements IFilter {
	File folder;
	private String field;
	
	SubjectFilter(File path) {
		this.folder = path;
	}

	@Override
	public DLinkedList applyFilter(DLinkedList list) { // linked list of info
		DoublyLinkedList filtered = new DLinkedList();
		while (list.hasNext()) {
			Info item = (Info) list.getNext();// add clone in Info
			if (item.subject.contains(field)) {
				filtered.add(item);
				String newDirectory = this.folder.getAbsolutePath() + item.directory.getName();
				copyFolder(item.directory, new File(newDirectory));
				//item.directory = newDirectory;
			}
		}
		return filtered;
	}

	public void copyFolder(File sourceFolder, File destinationFolder) throws IOException {// ********************************************************
		// Check if sourceFolder is a directory or file
		// If sourceFolder is file; then copy the file directly to new location
		if (sourceFolder.isDirectory()) {
			// Verify if destinationFolder is already present; If not then create it
			if (!destinationFolder.exists()) {
				destinationFolder.mkdir();
				// System.out.println("Directory created :: " + destinationFolder);
			}

			// Get all files from source directory
			String[] files = sourceFolder.list();

			// Iterate over all files and copy them to destinationFolder one by one
			for (String file : files) {
				File srcFile = new File(sourceFolder, file);
				File destFile = new File(destinationFolder, file);

				// Recursive function call
				copyFolder(srcFile, destFile);
			}
		} else {
			// Copy the file content from one place to another
			Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	public void cleanDir(File folder) throws IOException { // ***************************************************************

		File[] files = folder.listFiles();
		if (files != null && files.length > 0) {
			for (File f : files) {
				removeDir(f);
			}
		}
	}

	public void removeDir(File folder) throws IOException {// *************************************************************
		Files.walk(folder.toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
	}

	@Override
	public DLinkedList applyFilter(DLinkedList index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameter(String parameter) {
		this.field = parameter;		
	}
}
