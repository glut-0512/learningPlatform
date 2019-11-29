package com.glut.learningplatform.util.mail;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class DelMail {
	 
    public void delmails(String username,String password) throws Exception {
    	 String host = "pop3.163.com";
         String userName = username;
         String passWord = password;
          
         Properties props = new Properties();
         //Properties props = System.getProperties();这种方法创建 Porperties 同上
         Session session = Session.getDefaultInstance(props);
         session.setDebug(true);
         try {
             Store store = session.getStore("pop3");
             store.connect(host, userName, passWord);//验证邮箱
             Folder folder = store.getFolder("INBOX");
             folder.open(Folder.READ_WRITE);//设置我读写方式打开
             int countOfAll = folder.getMessageCount();//取得邮件个数
             int unReadCount = folder.getUnreadMessageCount();//已读个数
             int newOfCount = folder.getNewMessageCount();//未读个数
             /*System.out.println("总个数：" +countOfAll);
             System.out.println("已读个数：" +unReadCount);
             System.out.println("未读个数：" +newOfCount);*/
             for(int i=1; i<=countOfAll; i++)
             {
            	 
                 Message message = folder.getMessage(i);
                 //message.setFlag(Flags.Flag.SEEN, true);
                 message.setFlag(Flags.Flag.DELETED, true);//设置已删除状态为true
                /* Flags flags = message.getFlags();
                 if (flags.contains(Flags.Flag.SEEN))
                     System.out.println("这是一封已读邮件");
                 else {
                     System.out.println("未读邮件");
                     message.setFlag(Flags.Flag.SEEN, true);
                 }*/
                 /*if(message.isSet(Flags.Flag.DELETED))
                     System.out.println("已经删除第"+i+"邮件。。。。。。。。。");*/
             }
             folder.close(true);
             store.close();
         } catch (NoSuchProviderException e) {
             e.printStackTrace();
         } catch (MessagingException e) {
             e.printStackTrace();
         }
    }
}