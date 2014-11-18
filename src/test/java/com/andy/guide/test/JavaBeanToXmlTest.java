package com.andy.guide.test;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

public class JavaBeanToXmlTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		ServerInfo[] sis = new ServerInfo[2];
		sis[0] = new ServerInfo(); 
		sis[0].setMsgrServerType("1");
		sis[0].setMsgrServerVer("2.0");
		sis[0].setIp("1");
		sis[0].setIpType("1");
		sis[0].setSystemType("64");
		sis[0].setFileTransferYN("64");
		
		sis[1] = new ServerInfo(); 
		sis[1].setMsgrServerType("1");
		sis[1].setMsgrServerVer("2.0");
		sis[1].setIp("1");
		sis[1].setIpType("1");
		sis[1].setSystemType("64");
		sis[1].setFileTransferYN("64");

		MessageBean bean = new MessageBean();
		bean.setResult("00");
		bean.setResultMsg("성공");
		bean.setServerInfo(sis);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder xmlEncoder = new XMLEncoder(baos);
		xmlEncoder.writeObject(bean);
		xmlEncoder.close();
		
		String xml = baos.toString();
		System.out.println(xml);
		
	}
	
	private  String jaxbObjectToXML(MessageBean emp) {
		 
        try {
            JAXBContext context = JAXBContext.newInstance(MessageBean.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to System.out for debugging
            // m.marshal(emp, System.out);
 
            // Write to File
            //m.marshal(emp, new File(FILE_NAME));
            java.io.StringWriter sw = new StringWriter();
            m.marshal(emp, sw);
            return sw.toString();
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	@Test
	public void toXmlTest() {
		ServerInfo[] sis = new ServerInfo[2];
		sis[0] = new ServerInfo(); 
		sis[0].setMsgrServerType("1");
		sis[0].setMsgrServerVer("2.0");
		sis[0].setIp("1");
		sis[0].setIpType("1");
		sis[0].setSystemType("64");
		sis[0].setFileTransferYN("64");
		
		sis[1] = new ServerInfo(); 
		sis[1].setMsgrServerType("1");
		sis[1].setMsgrServerVer("2.0");
		sis[1].setIp("1");
		sis[1].setIpType("1");
		sis[1].setSystemType("64");
		sis[1].setFileTransferYN("64");

		MessageBean bean = new MessageBean();
		bean.setResult("00");
		bean.setResultMsg("성공");
		bean.setServerInfo(sis);
		
		//System.out.println(jaxbObjectToXML(bean));
		
		
	}

	
	
	@Test
	public void xstreamTest() {
		ServerInfo[] sis = new ServerInfo[2];
		sis[0] = new ServerInfo(); 
		sis[0].setMsgrServerType("1");
		sis[0].setMsgrServerVer("2.0");
		sis[0].setIp("1");
		sis[0].setIpType("1");
		sis[0].setSystemType("64");
		sis[0].setFileTransferYN("64");
		
		sis[1] = new ServerInfo(); 
		sis[1].setMsgrServerType("1");
		sis[1].setMsgrServerVer("2.0");
		sis[1].setIp("1");
		sis[1].setIpType("1");
		sis[1].setSystemType("64");
		sis[1].setFileTransferYN("64");

		MessageBean bean = new MessageBean();
		bean.setResult("00");
		bean.setResultMsg("성공");
		bean.setServerInfo(sis);

		XStream xs = new XStream();
		xs.alias("message", MessageBean.class);
		xs.alias("serverInfo", ServerInfo.class);
		String xml = xs.toXML(bean);
		System.out.println(xml);
	}
	
}
