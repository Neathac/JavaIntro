// package javaIntro.grafm;
package cz.cuni.mff.java.homework.jfind;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JFind {
    public static void find(String[] args) {
        try {
            String name = null;
            String iname = null;
            String regex = null;
            String size = null;
            String ssize = null;
            String bsize = null;
            String path = args[0];
            for(int i = 1; i < args.length; ++i) {
                switch(args[i]) {
                    case "-name":
                        if (name != null) {
                            System.out.println("ERROR");
                            return;
                        }
                        name = args[i+1];
                        name = name.replaceAll("\\.", "\\\\.").replaceAll("\\?", ".").replaceAll("\\*", ".*");
                        break;
                    case "-iname":
                        if (iname != null) {
                            System.out.println("ERROR");
                            return;
                        }
                        iname = args[i+1];
                        iname = iname.replaceAll("\\.", "\\\\.").replaceAll("\\?", ".").replaceAll("\\*", ".*");
                        break;
                    case "-regex":
                        if (regex != null) {
                            System.out.println("ERROR");
                            return;
                        }
                        regex = args[i+1];
                        break;
                    case "-size":
                        if (size != null) {
                            System.out.println("ERROR");
                            return;
                        }
                        size = args[i+1];
                        break;
                    case "-ssize":
                        if (ssize != null) {
                            System.out.println("ERROR");
                            return;
                        }
                        ssize = args[i+1];
                        break;
                    case "-bsize":
                        if (bsize != null) {
                            System.out.println("ERROR");
                            return;
                        }
                        bsize = args[i+1];
                        break;
                    default:
                        System.out.println("ERROR");
                        return;
                }
                ++i;
            }
            final String tempName = name;
            final String tempIname = iname;
            final String tempRegex = regex;

            final String tempSize = size;
            final String tempSsize = ssize;
            final String tempBSize = bsize;

            findRecursively(Paths.get(path), p -> { 
                boolean passed = true;
                String filename = p.toFile().getName().toString();
                
                if (tempName != null) {
                    Pattern namePattern = Pattern.compile(tempName);
                    Matcher mat = namePattern.matcher(filename);
                    if (!mat.matches()) {
                        passed = false;
                    }
                } 

                if (tempIname != null) {
                    Pattern inamePattern = Pattern.compile(tempIname, Pattern.CASE_INSENSITIVE);
                    Matcher inameMat = inamePattern.matcher(filename);
                    if (!inameMat.matches()) passed = false;
                } 
                
                if (tempRegex != null) {
                    Pattern regexPattern = Pattern.compile(tempRegex);
                    Matcher regexMat = regexPattern.matcher(filename);
                    if (!regexMat.matches()) passed = false;
                } 

                if (tempSize != null && !isOfSize(tempSize, p.toFile())) passed = false;
                if (tempSsize != null && !isOfSSize(tempSsize, p.toFile())) passed = false;
                if (tempBSize != null && !isOfBSize(tempBSize, p.toFile())) passed = false;

                if (passed) {
                  System.out.println(p.toFile().getPath().substring(path.length() + 1));
                }
              });
        } catch(Exception e) {
            System.out.println("ERROR");
        }
    }

    public static void findRecursively(Path path, Consumer<Path> c) {
        try (DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path)) {
          StreamSupport.stream(newDirectoryStream.spliterator(), false)
                       .peek(p -> {
                         c.accept(p);
                         if (p.toFile()
                              .isDirectory()) {
                           findRecursively(p, c);
                         }
                       })
                       .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
        }
      }

    private static double getFileSizeMegaBytes(File file) {
		return (double) file.length() / (1024 * 1024);
	}
	
	private static double getFileSizeKiloBytes(File file) {
		return (double) file.length() / 1024;
	}

	private static double getFileSizeBytes(File file) {
		return file.length();
	}

    private static boolean isOfSize(String size, File file) {
        if (size.endsWith("M")) return (double) Integer.parseInt(size.substring(0, size.length()-1)) == getFileSizeMegaBytes(file);
        else if (size.endsWith("k")) return (double) Integer.parseInt(size.substring(0, size.length()-1)) == getFileSizeKiloBytes(file);
        else return (double) Integer.parseInt(size) == getFileSizeBytes(file);
    }

    private static boolean isOfSSize(String size, File file) {
        if (size.endsWith("M")) return (double) Integer.parseInt(size.substring(0, size.length()-1)) > getFileSizeMegaBytes(file);
        else if (size.endsWith("k")) return (double) Integer.parseInt(size.substring(0, size.length()-1)) > getFileSizeKiloBytes(file);
        else return (double) Integer.parseInt(size) > getFileSizeBytes(file);
    }

    private static boolean isOfBSize(String size, File file) {
        if (size.endsWith("M")) return (double) Integer.parseInt(size.substring(0, size.length()-1)) < getFileSizeMegaBytes(file);
        else if (size.endsWith("k")) return (double) Integer.parseInt(size.substring(0, size.length()-1)) < getFileSizeKiloBytes(file);
        else return (double) Integer.parseInt(size) < getFileSizeBytes(file);
    }
}
