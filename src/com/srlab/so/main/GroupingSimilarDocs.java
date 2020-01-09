package com.srlab.so.main;

import com.srlab.hashalgo.*;

public class GroupingSimilarDocs {
	public static int similarTitles(String DocOne, String DocTwo)
	{
		Simhash simhash = new Simhash(new BinaryWordSeg());
		
		long docOneHash = simhash.simhash64(DocOne);
		long docTwoHash = simhash.simhash64(DocTwo);
		
		int dist = simhash.hammingDistance(docOneHash, docTwoHash);
		return dist;
	}

}
