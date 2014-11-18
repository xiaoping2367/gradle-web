// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 2012-08-16 오후 8:38:45
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
package org.springplugins.web;

import java.util.ArrayList;

import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class IgnoreSelectedAnnotationHandlerMapping extends
		DefaultAnnotationHandlerMapping {

	public IgnoreSelectedAnnotationHandlerMapping() {
	}

	public void setUrls(ArrayList arraylist) {
		a = arraylist;
	}

	public String[] getFiltered(String as[]) {
		if (as == null)
			return null;
		ArrayList arraylist = new ArrayList();
		String as1[] = as;
		int i = as1.length;
		for (int j = 0; j < i; j++) {
			String s = as1[j];
			if (!a.contains(s))
				arraylist.add(s);
		}

		return (String[]) arraylist.toArray(new String[arraylist.size()]);
	}

	protected String[] determineUrlsForHandler(String s) {
		return getFiltered(super.determineUrlsForHandler(s));
	}

	private ArrayList a;
}
