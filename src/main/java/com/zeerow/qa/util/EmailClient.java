package com.zeerow.qa.util;

import com.zeerow.qa.util.api.common.Constants;

import javax.mail.*;
import java.util.Properties;

/**
 * Created by yoosuf on 7/8/2015.
 */
public class EmailClient {
    static String host = "pop.gmail.com";// change accordingly
    static  String mailStoreType = "pop3";

    public static Store getEmailStore (String user, String password) throws MessagingException{
        //create properties field
        Properties properties = new Properties();

        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);

        //create the POP3 store object and connect with the pop server
        Store store = emailSession.getStore("pop3s");

        store.connect(host, user, password);
        return store;

    }
    public static void getEmail(String user,
                                String password)
    {
        try {
            Store store = getEmailStore(user, password);
            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());

            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLastEmail(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getLastEmail(Constants.EMAIL_ADDRESS, Constants.EMAIL_PASSWORD);
    }
    public static String getLastEmail(String user,
                                String password)
    {
        try {
            Store store = getEmailStore(user, password);
            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            return messages[messages.length-1].getContent().toString();


        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public static void main(String[] args) {


        String username = Constants.EMAIL_ADDRESS;
        String password = Constants.EMAIL_PASSWORD;

      //  getEmail(username, password);

    }

}
