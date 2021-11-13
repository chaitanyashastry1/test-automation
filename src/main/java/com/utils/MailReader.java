package com.utils;

import net.sourceforge.htmlunit.corejs.javascript.ast.Loop;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class MailReader {
    public String getOTPFromGmail(String username, String password, String emailSubjectContent) {
        try {
            Thread.sleep(20000); //Putting hardwait for now: It has to be replaced with the loop logic
            String otp = "";
            Properties props = new Properties();
            props.put("mail.imap.ssl.enable", "true");
            Session session = javax.mail.Session.getInstance(props);
            Store store = session.getStore("imap");
            store.connect("imap.gmail.com", username, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            int unreadMsgCount = inbox.getUnreadMessageCount();


//            for(int i=0; i<3; i++){
//                Thread.sleep(3000);
//                System.out.println("Sleeping for 3 Second");
//                int latestUnreadMsgCount = inbox.getUnreadMessageCount();
//                if(latestUnreadMsgCount  >= unreadMsgCount){
//                    break;
//                }
//            }

            Message[] messages = inbox.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            // Sort messages from recent to oldest
            System.out.println("Sorting start");
            Arrays.sort(messages, (m1, m2) -> {
                try {
                    return m2.getSentDate().compareTo(m1.getSentDate());
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("Sorting complete");

            for (Message message : messages) {
                if (message.getSubject().contains(emailSubjectContent)) {
                    otp = message.getSubject().replaceAll("[^0-9]", "");
                    System.out.println("OTP is: " + otp);
                    message.setFlag(Flags.Flag.DELETED, true);
                    break;
                }
            }
            inbox.close();
            store.close();
            return otp;
        }catch (Exception ex){
            System.out.println("Exception occur at getOTP");
            return null;
        }
    }

    public String getOTPFromGmailOld(String username, String password, String emailSubjectContent, Date date) {
        try {
            String otp = null;
            Properties props = new Properties();
            props.put("mail.imap.ssl.enable", "true");
            Session session = javax.mail.Session.getInstance(props);
            Store store = session.getStore("imap");
            store.connect("imap.gmail.com", username, password);
            Folder inbox;
            Message message;
            for(int i = 1; i <= 8; i++) {
                inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_WRITE);

                Message[] messages = inbox.search(
                        new FlagTerm(new Flags(Flags.Flag.SEEN), false));

                if(messages != null) {
                    // Sort messages from recent to oldest
                    System.out.println("Sorting start");
                    Arrays.sort(messages, (m1, m2) -> {
                        try {
                            return m2.getSentDate().compareTo(m1.getSentDate());
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    System.out.println("Sorting complete");

                    message = messages[0];

                    if (message.getSentDate().after(date) && message.getSubject().contains(emailSubjectContent)) {
                        otp = message.getSubject().replaceAll("[^0-9]", "");
                        System.out.println("OTP is: " + otp);
                        message.setFlag(Flags.Flag.DELETED, true);
                        inbox.close();
                        store.close();
                        break;
                    } else {
                        Thread.sleep(2000);
                    }
                } else {
                    Thread.sleep(2000);
                }
            }
            return otp;
        } catch (Exception ex) {
            System.out.println("Exception occurred at getOTP");
            ex.printStackTrace();
            return null;
        }
    }

    public String getOTPFromGmail(String username, String password, String emailSubjectContent, Date date) {
        Folder inbox = null;
        Store store;
        try {
            String otp = null;
            Properties props = new Properties();
            props.put("mail.imap.ssl.enable", "true");
            Session session = javax.mail.Session.getInstance(props);
            store = session.getStore("imap");
            store.connect("imap.gmail.com", username, password);
            Message message;

            outerLoop:
            for(int i = 1; i <= 6; i++) {

                inbox = store.getFolder("INBOX");

                inbox.open(Folder.READ_WRITE);

                int messageCount=inbox.getMessageCount();
                int unreadMessageCount=inbox.getUnreadMessageCount();

                if(unreadMessageCount>0){
                    for(int j=messageCount; j>(messageCount-5); j--) {
                        message = inbox.getMessage(j);
                        if(message.getSentDate().after(date) && message.getSubject().contains(emailSubjectContent)){
                            otp = message.getSubject().replaceAll("[^0-9]", "");
                            System.out.println("OTP is: " + otp);
                            message.setFlag(Flags.Flag.DELETED, true);
                            break outerLoop;
                        }
                    }
                }

            }
            inbox.close();
            store.close();
            return otp;
        } catch (Exception ex) {
            System.out.println("Exception occurred at getOTP: "+ex.getMessage());
            return null;
        }
    }
}
