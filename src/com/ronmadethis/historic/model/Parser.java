package com.ronmadethis.historic.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ronnie on 3/1/14.
 */
public class Parser {

	public Parser() {
	}

	/**
	 * Parse the csv, there are random commas so we have to look for quotes
	 * which indicate there will be a comma we can skip.
	 * 
	 * @param file
	 * @return
	 */
	public ArrayList<HistoricSite> parseData(InputStream file) {
		ArrayList<HistoricSite> siteList = new ArrayList<HistoricSite>();
		Scanner s = null;
		try {
			s = new Scanner(file);
			s.nextLine();// skip the column titles
			String line;
			while (s.hasNext()) {
				line = s.nextLine();
				boolean inQuote = false;
				StringBuilder sb = new StringBuilder();
				ArrayList<String> site = new ArrayList<String>();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '"') {
						inQuote = !inQuote;
					} else if (line.charAt(i) == ',' && !inQuote) {
						site.add(sb.toString());
						sb = new StringBuilder();
					} else if (i == line.length() - 1) {
						sb.append(line.charAt(i));
						site.add(sb.toString());
					} else {
						sb.append(line.charAt(i));
					}
				}
				siteList.add(new HistoricSite(site.get(1), site.get(2), site
						.get(3), site.get(4), site.get(5), site.get(6), site
						.get(7), site.get(8), Float.parseFloat(site.get(9)),
						Float.parseFloat(site.get(10))));
			}
		} catch (Exception fe) {
			System.out.print(fe.toString());
		} finally {
			s.close();
		}
		return siteList;
	}
}