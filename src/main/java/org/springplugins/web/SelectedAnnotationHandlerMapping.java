// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 2012-08-16 오후 8:34:47
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
package org.springplugins.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class SelectedAnnotationHandlerMapping extends
		DefaultAnnotationHandlerMapping {

	// 아래는 원본
	/*private List<String> urlList;

	public SelectedAnnotationHandlerMapping() {

	}

	public void setUrls(List<String> arrayList) {
		urlList = arrayList;
	}

	public String[] getFiltered(String as[]) {

		if (as == null) {
			return null;
		}

		List<String> arraylist = new ArrayList<String>();
		int i = as.length;
		for (int j = 0; j < i; j++) {
			String str = as[j];
			for (String url : urlList) {
				Pattern pattern = Pattern
						.compile(url, Pattern.CASE_INSENSITIVE);
				if (pattern.matcher(str).find()) {
					arraylist.add(str);
				}
			}
		}
		return (String[]) arraylist.toArray(new String[arraylist.size()]);
	}

	@Override
	protected String[] determineUrlsForHandler(String s) {
		return getFiltered(super.determineUrlsForHandler(s));
	}
	
	*/
	
	
	
	// 아래는 수정된 것 
	
	
	private List<String> urls;

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public String[] getFiltered(String strs[]) {
		if (strs == null) {
			return null;
		}
		List<String> tmpList = new ArrayList<String>();
		int i = strs.length;
		for (int j = 0; j < i; j++) {
			String str = strs[j];
			for (String url : urls) {
				Pattern pattern = Pattern
						.compile(url, Pattern.CASE_INSENSITIVE);
				if (pattern.matcher(str).find()) {
					tmpList.add(str);
				}
			}
		}
		return (String[]) tmpList.toArray(new String[tmpList.size()]);
	}

	protected String[] determineUrlsForHandler(String s) {
		return getFiltered(super.determineUrlsForHandler(s));
	}
	
	
}
