package serverVersion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SimilarQuestion {
	
	public static String dataLocalPath = "/home/sr-p2irc-big14/Data/APILearn";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br1 = FileReadWrite1.FileRead(dataLocalPath + File.separator + "titlesPythonTagsId.txt");
			BufferedReader br2 = FileReadWrite1.FileRead(dataLocalPath + File.separator + "titlesJavaTagsId.txt");
			List<String> DocOne = new ArrayList<String>();
			List<String> DocTwo = new ArrayList<String>();
			
			System.out.println("Doc One Loading");
			String doc;
			
			
			while((doc = br1.readLine()) != null)
				DocOne.add(doc);
			br1.close();
			System.out.println("Doc Two loading");
			while((doc = br2.readLine()) != null)
				DocTwo.add(doc);
			System.out.println("Doc Two Loaded");
			br2.close();
			System.out.println("Starting Similarity Check");
			
			String Tagging = null;
			
			/*** Thread Implementation in Java 8 ***/
			
			ExecutorService service = Executors.newFixedThreadPool(20);
			IntStream.range(0, DocOne.size()).forEach(i -> {
				IntStream.range(0, DocTwo.size()).forEach(j -> {
					service.submit(new GroupingSimilarDocs(DocOne.get(i), DocTwo.get(j), dataLocalPath + File.separator + "TitlesJavaPython", Tagging));
				});
			});

		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}	
	}
}

	final class GroupingSimilarDocs  implements Runnable{
		
		public GroupingSimilarDocs(String DocOne, String DocTwo, String ComparisonFile)
		{
			double temp = similarTitles(DocOne, DocTwo);
			temp = temp/64;
			double hashingsimilarity = 1.0 - temp;
			double stringsimilarity = compareStrings(DocOne, DocTwo);
			double similarityScore = (hashingsimilarity + stringsimilarity)/2;
			System.out.println("Similarity Score: " + similarityScore );
			if(similarityScore > 0.5)
			{
				String TitleJoin = DocOne + ", " + DocTwo + ", " + hashingsimilarity + ", " + stringsimilarity + ", " + similarityScore;
				FileReadWrite1.FileWrite(TitleJoin, ComparisonFile);
			}
		}
		
		public GroupingSimilarDocs(String DocOne, String DocTwo, String ComparisonFile, String Tagging)
		{
			String[] tempDocsOne = DocOne.split("		");
			String[] tempDocsTwo = DocTwo.split("		");
			if (tempDocsOne.length <4 || tempDocsTwo.length <4)
			{
				System.out.println("Not Counted");
				System.out.println(DocOne);
				System.out.println(DocTwo);
			}
			else
			{
				String[] tagsOne = tempDocsOne[3].split(" ");
				String[] tagsTwo = tempDocsTwo[3].split(" ");
				int tagOneLength = tagsOne.length;
				int tagTwoLength = tagsTwo.length;
				
				OUTER_LOOP:
				for(int i = 0; i < tagOneLength; i++)
				{
					for(int j = 0; j < tagTwoLength; j++)
					{
						if(tagsOne[i].equals(tagsTwo[j]))
						{
							double temp = similarTitles(tempDocsOne[2], tempDocsTwo[2]);
							temp = temp/64;
							double hashingsimilarity = 1.0 - temp;
							double stringsimilarity = compareStrings(tempDocsOne[2], tempDocsTwo[2]);
							double similarityScore = (hashingsimilarity + stringsimilarity)/2;
							if(hashingsimilarity > 0.7 && stringsimilarity > 0.7 )
							{
								DocOne = DocOne.replaceAll("null		", "");
								DocTwo = DocTwo.replaceAll("null		", "");
								String TitleJoin = DocOne + "		" + DocTwo + "		" + hashingsimilarity + "		" + stringsimilarity + "		" + similarityScore;
								FileReadWrite1.FileWrite(TitleJoin, ComparisonFile);
								
							}
							break OUTER_LOOP;
						}
					}
				}
			
			}
		}
		
		public static int similarTitles(String DocOne, String DocTwo)
		{
			Simhash simhash = new Simhash(new BinaryWordSeg());
			
			long docOneHash = simhash.simhash64(DocOne);
			long docTwoHash = simhash.simhash64(DocTwo);
			
			int dist = simhash.hammingDistance(docOneHash, docTwoHash);
			return dist;
		}
		
		public static void similarDocs(List<String> DocOne, List<String> DocTwo, String ComparisonFile)
		{
			int docOneLength = DocOne.size();
			int docTwoLength = DocTwo.size();
			
			for(int i = 0; i < docOneLength; i++)
			{
				for(int j = 0; j < docTwoLength; j++)
				{
					int dist = similarTitles(DocOne.get(i), DocTwo.get(j));
					
					//System.out.println(DocOne.get(i) + "  " + DocTwo.get(j) + "  " + dist);
					
					if(dist <= 10)
					{
						String TitlesJoin = DocOne.get(i) + "		" + DocTwo.get(j);
						FileReadWrite1.FileWrite(TitlesJoin, ComparisonFile);
					}
				}
			}
		}
		
		public static void stringBasedSimilarDocs(List<String> DocOne, List<String> DocTwo, String ComparisonFile)
		{
			int docOneLength = DocOne.size();
			int docTwoLength = DocTwo.size();
			
			for(int i = 0; i < docOneLength; i++)
			{
				for(int j = 0; j < docTwoLength; j++)
				{
					double similarityScore = compareStrings(DocOne.get(i), DocTwo.get(j));
					
					if(similarityScore > 0.5)
					{
						String TitlesJoin = DocOne.get(i) + "		" + DocTwo.get(j);
						FileReadWrite1.FileWrite(TitlesJoin, ComparisonFile);
					}
				}
			}
		}
		
		
		 private static String[] letterPairs(String str) {
		       int numPairs = str.length()-1;
		       if(numPairs < 0)
		       {
		    	   numPairs = 0;
		       }
		       String[] pairs = new String[numPairs];
		       for (int i=0; i<numPairs; i++) {
		           pairs[i] = str.substring(i,i+2);
		       }
		       return pairs;
		   }
		   
		/** @return an ArrayList of 2-character Strings. */

		   private static ArrayList wordLetterPairs(String str) {
		       ArrayList<String> allPairs = new ArrayList<String>();
		       // Tokenize the string and put the tokens/words into an array
		       String[] words = str.split("\\s");
		       // For each word
		       for (int w=0; w < words.length; w++) {
		           // Find the pairs of characters
		           String[] pairsInWord = letterPairs(words[w]);
		           for (int p=0; p < pairsInWord.length; p++) {
		               allPairs.add(pairsInWord[p]);
		           }
		       }
		       return allPairs;
		   }
		   
		 /** @return lexical similarity value in the range [0,1] */

		   public static double compareStrings(String str1, String str2) {
		       ArrayList<String> pairs1 = wordLetterPairs(str1.toUpperCase());
		       ArrayList<String> pairs2 = wordLetterPairs(str2.toUpperCase());
		       int intersection = 0;
		       int union = pairs1.size() + pairs2.size();
		       for (int i=0; i<pairs1.size(); i++) {
		           Object pair1=pairs1.get(i);
		           for(int j=0; j<pairs2.size(); j++) {
		               Object pair2=pairs2.get(j);
		               if (pair1.equals(pair2)) {
		                   intersection++;
		                   pairs2.remove(j);
		                   break;
		               }
		           }
		       }
		       return (2.0*intersection)/union;
		   }

		   @Override
		public void run() {
			// TODO Auto-generated method stub
			try
	        { 
	            // Displaying the thread that is running 
	            System.out.println ("Thread " + 
	                  Thread.currentThread().getId() + 
	                  " is running"); 
	  
	        } 
	        catch (Exception e) 
	        { 
	            // Throwing an exception 
	            System.out.println ("Exception is caught"); 
	        } 	
			
	}			
}
	
final class FileReadWrite1 {
		
		public static void FileWrite(String textToWrite, String FileName)
		{
			FileWriter fw = null;
	        BufferedWriter bw = null;
	        PrintWriter pw = null;

	        try {
	            
	        	fw = new FileWriter(FileName, true);
	            bw = new BufferedWriter(fw);
	            pw = new PrintWriter(bw);

	            pw.println(textToWrite.toString());
	            System.out.println("Data Successfully appended into file");
	            pw.flush();
	            pw.close();
	            bw.close();
	            fw.close();

	        } catch (IOException io) {// can't do anything }
	            io.printStackTrace();
	        } 
	    }
		
		public static BufferedReader FileRead(String FileName) throws FileNotFoundException, IOException
		{
			File file = new File(FileName); 
			  
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			
			return br;
			
		}

	}

final class BinaryWordSeg implements IWordSeg {

	@Override
	public List<String> tokens(String doc) {
		List<String> binaryWords = new LinkedList<String>();
		for(int i = 0; i < doc.length() - 1; i += 1) {
			StringBuilder bui = new StringBuilder();
			bui.append(doc.charAt(i)).append(doc.charAt(i + 1));
			binaryWords.add(bui.toString());
		}
		return binaryWords;
	}

	@Override
	public List<String> tokens(String doc, Set<String> stopWords) {
		return null;
	}

}
interface IWordSeg {

	public List<String> tokens(String doc);
	
	public List<String> tokens(String doc, Set<String> stopWords);
}
final class Simhash {

	private IWordSeg wordSeg;

	public Simhash(IWordSeg wordSeg) {
		this.wordSeg = wordSeg;
	}

	public int hammingDistance(int hash1, int hash2) {
		int i = hash1 ^ hash2;
		i = i - ((i >>> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		i = (i + (i >>> 4)) & 0x0f0f0f0f;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		return i & 0x3f;
	}

	public int hammingDistance(long hash1, long hash2) {
		long i = hash1 ^ hash2;
		i = i - ((i >>> 1) & 0x5555555555555555L);
		i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
		i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		i = i + (i >>> 32);
		return (int) i & 0x7f;
	}

	public long simhash64(String doc) {
		int bitLen = 64;
		int[] bits = new int[bitLen];
		List<String> tokens = wordSeg.tokens(doc);
		for (String t : tokens) {
			long v = MurmurHash.hash64(t);
			for (int i = bitLen; i >= 1; --i) {
				if (((v >> (bitLen - i)) & 1) == 1)
					++bits[i - 1];
				else
					--bits[i - 1];
			}
		}
		long hash = 0x0000000000000000;
		long one = 0x0000000000000001;
		for (int i = bitLen; i >= 1; --i) {
			if (bits[i - 1] > 0) {
				hash |= one;
			}
			one = one << 1;
		}
		return hash;
	}

	public long simhash32(String doc) {
		int bitLen = 32;
		int[] bits = new int[bitLen];
		List<String> tokens = wordSeg.tokens(doc);
		for (String t : tokens) {
			int v = MurmurHash.hash32(t);
			for (int i = bitLen; i >= 1; --i) {
				if (((v >> (bitLen - i)) & 1) == 1)
					++bits[i - 1];
				else
					--bits[i - 1];
			}
		}
		int hash = 0x00000000;
		int one = 0x00000001;
		for (int i = bitLen; i >= 1; --i) {
			if (bits[i - 1] > 1) {
				hash |= one;
			}
			one = one << 1;
		}
		return hash;
	}
}

final class MurmurHash {

	public static int hash32(String doc) {
		byte[] buffer = doc.getBytes(Charset.forName("utf-8"));
		ByteBuffer data = ByteBuffer.wrap(buffer);
		return hash32(data, 0, buffer.length, 0);
	}

	public static int hash32(ByteBuffer data, int offset, int length, int seed) {
		int m = 0x5bd1e995;
		int r = 24;

		int h = seed ^ length;

		int len_4 = length >> 2;

		for (int i = 0; i < len_4; i++) {
			int i_4 = i << 2;
			int k = data.get(offset + i_4 + 3);
			k = k << 8;
			k = k | (data.get(offset + i_4 + 2) & 0xff);
			k = k << 8;
			k = k | (data.get(offset + i_4 + 1) & 0xff);
			k = k << 8;
			k = k | (data.get(offset + i_4 + 0) & 0xff);
			k *= m;
			k ^= k >>> r;
			k *= m;
			h *= m;
			h ^= k;
		}

		// avoid calculating modulo
		int len_m = len_4 << 2;
		int left = length - len_m;

		if (left != 0) {
			if (left >= 3) {
				h ^= (int) data.get(offset + length - 3) << 16;
			}
			if (left >= 2) {
				h ^= (int) data.get(offset + length - 2) << 8;
			}
			if (left >= 1) {
				h ^= (int) data.get(offset + length - 1);
			}

			h *= m;
		}

		h ^= h >>> 13;
		h *= m;
		h ^= h >>> 15;

		return h;
	}

	public static long hash64(String doc) {
		byte[] buffer = doc.getBytes(Charset.forName("utf-8"));
		ByteBuffer data = ByteBuffer.wrap(buffer);
		return hash64(data, 0, buffer.length, 0);
	}

	public static long hash64(ByteBuffer key, int offset, int length, long seed) {
		long m64 = 0xc6a4a7935bd1e995L;
		int r64 = 47;

		long h64 = (seed & 0xffffffffL) ^ (m64 * length);

		int lenLongs = length >> 3;

		for (int i = 0; i < lenLongs; ++i) {
			int i_8 = i << 3;

			long k64 = ((long) key.get(offset + i_8 + 0) & 0xff)
					+ (((long) key.get(offset + i_8 + 1) & 0xff) << 8)
					+ (((long) key.get(offset + i_8 + 2) & 0xff) << 16)
					+ (((long) key.get(offset + i_8 + 3) & 0xff) << 24)
					+ (((long) key.get(offset + i_8 + 4) & 0xff) << 32)
					+ (((long) key.get(offset + i_8 + 5) & 0xff) << 40)
					+ (((long) key.get(offset + i_8 + 6) & 0xff) << 48)
					+ (((long) key.get(offset + i_8 + 7) & 0xff) << 56);

			k64 *= m64;
			k64 ^= k64 >>> r64;
			k64 *= m64;

			h64 ^= k64;
			h64 *= m64;
		}

		int rem = length & 0x7;

		switch (rem) {
		case 0:
			break;
		case 7:
			h64 ^= (long) key.get(offset + length - rem + 6) << 48;
		case 6:
			h64 ^= (long) key.get(offset + length - rem + 5) << 40;
		case 5:
			h64 ^= (long) key.get(offset + length - rem + 4) << 32;
		case 4:
			h64 ^= (long) key.get(offset + length - rem + 3) << 24;
		case 3:
			h64 ^= (long) key.get(offset + length - rem + 2) << 16;
		case 2:
			h64 ^= (long) key.get(offset + length - rem + 1) << 8;
		case 1:
			h64 ^= (long) key.get(offset + length - rem);
			h64 *= m64;
		}

		h64 ^= h64 >>> r64;
		h64 *= m64;
		h64 ^= h64 >>> r64;

		return h64;
	}
}

