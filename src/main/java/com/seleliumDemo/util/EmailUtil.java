package com.seleliumDemo.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author mike_yi
 * @Description 读取邮件验证码
 * @since 2017-12-26
 */
public abstract class EmailUtil {
	private static Properties props = new Properties();
	static {
		props.setProperty("mail.pop3.host", "pop.qq.com");
		props.setProperty("mail.transport.protocol", "pop3");
		props.setProperty("mail.pop3.port", "995");
		props.setProperty("mail.pop3.ssl.enable", "true");
	}

	/**
	 * 获取验证码
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws NoSuchProviderException
	 */
	@SuppressWarnings("static-access")
	public static String getvalicode(String email, String password) {
		//延迟1秒，等待接收邮件
		try {
			Thread.currentThread().sleep(4000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String valicode = null;
		try {
			Store store = getStore();
			store.connect(email, password);
			Folder folder = store.getFolder("INBOX");// 获得用户的邮件帐户
			folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限

			Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件
			Message message = messages[messages.length - 1];
			String messageAdress = message.getFrom()[0].toString();
			if(messageAdress.contains("@amazon.com")) {
				System.out.println("亚马逊验证码消息"+messageAdress);
				Multipart mp = (Multipart) message.getContent();
				StringBuffer strBuff = new StringBuffer();
				for (int i = 0; i < mp.getCount(); i++) {
					if (mp.getBodyPart(i).isMimeType("text/html")) {
						strBuff.append(mp.getBodyPart(i).getContent().toString());
					}
				}
				Document html = Jsoup.parse(strBuff.toString());
				Elements ele = html.getElementsByClass("otp");
				valicode = ele.get(0).text();
			}
			folder.close(false);// 关闭邮件夹对象
			store.close(); // 关闭连接对象
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			if(e instanceof AuthenticationFailedException) {
				System.out.println("授权信息不正确！");
			}
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			System.out.println("没有获取到");
			e.printStackTrace();
		}
		return valicode;
	}

	public static Store getStore() throws NoSuchProviderException {
		Session session = Session.getDefaultInstance(props);
		session.setDebug(false);

		// 获取Store对象
		Store store = session.getStore("pop3");
		return store;
	}

	public static void main(String[] args) {
		String email = "1836318977@qq.com";
		String password = "xcihobwkgroydigc";
		String valicode=getvalicode(email, password);
		System.out.println(valicode);
	}
}
