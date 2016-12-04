package br.com.bitwaysystem.main;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import br.com.bitwaysystem.file.ListAllFiles;
import br.com.bitwaysystem.processsor.SendFilesToEmail;

public class Main {

	private static final String folder = "C:\\books";

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		// List All Files by Type
		ListAllFiles listAllFiles = new ListAllFiles();
		Map<String, List<File>> mapFilesByType = listAllFiles.listf(folder);

		Iterator<?> it = mapFilesByType.entrySet().iterator();

		if (it.hasNext()) {

			while (it.hasNext()) {

				SendFilesToEmail sendFilesToEmail = new SendFilesToEmail();
				sendFilesToEmail.sendFilesToEmail((Map.Entry) it.next());

			}
		}else{
			System.out.println("No files found to process");
		}
	}
}
