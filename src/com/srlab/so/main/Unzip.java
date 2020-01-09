package com.srlab.so.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

public class Unzip {
	
	public static String SOPath_Old = "/home/sr-p2irc-big14/Desktop/stackexchange_old/";
	
	public void unzipZipsInDirTo(Path searchDir, Path unzipTo ){

	    final PathMatcher matcher = searchDir.getFileSystem().getPathMatcher("glob:**/*.zip");
	    try (final Stream<Path> stream = Files.list(searchDir)) {
	        stream.filter(matcher::matches)
	                .forEach(zipFile -> unzip(zipFile,unzipTo));
	    }catch (IOException e){
	        //handle your exception
	    }
	}
	
	public void decompressSevenZInDirTo(Path searchDir, Path unzipTo ){

	    final PathMatcher matcher = searchDir.getFileSystem().getPathMatcher("glob:**/*.7z");
	    try (final Stream<Path> stream = Files.list(searchDir)) {
	        stream.filter(matcher::matches)
	                .forEach(zipFile -> decompress(zipFile,unzipTo));
	    }catch (IOException e){
	        //handle your exception
	    }
	}

	public void unzip(Path zipFile, Path outputPath){
		System.out.println(zipFile.toString());
	    try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile))) {

	        ZipEntry entry = zis.getNextEntry();

	        while (entry != null) {

	            Path newFilePath = outputPath.resolve(entry.getName());
	            if (entry.isDirectory()) {
	                Files.createDirectories(newFilePath);
	            } else {
	                if(!Files.exists(newFilePath.getParent())) {
	                    Files.createDirectories(newFilePath.getParent());
	                }
	                try (OutputStream bos = Files.newOutputStream(outputPath.resolve(newFilePath))) {
	                    byte[] buffer = new byte[Math.toIntExact(entry.getSize())];

	                    int location;

	                    while ((location = zis.read(buffer)) != -1) {
	                        bos.write(buffer, 0, location);
	                    }
	                
	                    bos.close();
	                }
	                
	            }
	            entry = zis.getNextEntry();
	            
	        }
	    }catch(IOException e){
	        throw new RuntimeException(e);
	        //handle your exception
	    }
	}
	
	public static void decompress(Path in, Path destination){
		System.out.println(in.toString());
        try {
			SevenZFile sevenZFile = new SevenZFile(new File(in.toString()));
	        SevenZArchiveEntry entry;
	        while ((entry = sevenZFile.getNextEntry()) != null){
	            if (entry.isDirectory()){
	                continue;
	            }
	            File curfile = new File(destination.toFile() + File.separator + in.getFileName().toString().replace(".", "_"), entry.getName());
	            File parent = curfile.getParentFile();
	            if(!parent.exists())
	            	parent.mkdirs();
	            FileOutputStream out = new FileOutputStream(curfile);
	            byte[] content = new byte[(int) entry.getSize()];
	            sevenZFile.read(content, 0, content.length);
	            out.write(content);
	            out.close();
	            
	        }
	        Path temp = Files.move(Paths.get(destination.toFile() + File.separator + in.getFileName().toString()), Paths.get(SOPath_Old + in.getFileName().toString()));
        }catch(IOException e) {
	        throw new RuntimeException(e);
        }
    }
}
