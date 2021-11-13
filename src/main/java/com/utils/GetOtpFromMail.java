package com.utils;

import javax.mail.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class GetOtpFromMail {

    // To read OTP from Gmail email client.
    public String GmailMailReader(String gmailUsername, String gmailPassword, String emailSubjectContent, int lengthOfOTP) {


// emailSubjectContent(Eg- Mail for OTP), OTP length(Eg- 6)

        String hostName = "smtp.gmail.com";//change it according to your mail

        int messageCount=0;

        int unreadMsgCount;

        int latestUnreadMsgCount=0;

        String emailSubject;

        Message emailMessage;

        String passwordOTP = null;

        Properties sysProps = System.getProperties();

        sysProps.setProperty("mail.store.protocol", "imaps");

        try {

            Session session = Session.getInstance(sysProps, null);

            Store store = session.getStore();

            store.connect(hostName, gmailUsername, gmailPassword);

            Folder emailBox = store.getFolder("Inbox");

            emailBox.open(Folder.READ_WRITE);
            unreadMsgCount = emailBox.getUnreadMessageCount();


            for(int i=0; i<10; i++){
                Thread.sleep(3000);
                System.out.println("Sleeping for 3 Second");
                messageCount = emailBox.getMessageCount();
                latestUnreadMsgCount = emailBox.getUnreadMessageCount();
                if(latestUnreadMsgCount  >= unreadMsgCount){
                    break;
                }
            }

            System.out.println("Message Count: " + messageCount);
            System.out.println("UnreadMessage Count: " + latestUnreadMsgCount);

            for (int i = messageCount; i > (messageCount - unreadMsgCount); i--) {

                emailMessage = emailBox.getMessage(i);
                emailMessage.setFlag(Flags.Flag.SEEN, true);
                emailSubject = emailMessage.getSubject();

                if (emailSubject.contains(emailSubjectContent)) {

                    System.out.println("Found mail with provided subject at index : " +i);

                    String line;

                    StringBuffer buffer = new StringBuffer();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(emailMessage.getInputStream()));

                    while ((line = reader.readLine()) != null) {

                        buffer.append(line);

                    }

                    String messageContent = "<strong style=\"font-size: 24px;\">";

                    String result = buffer.toString().substring(buffer.toString().indexOf(messageContent));

                    passwordOTP = result.substring(messageContent.length(), messageContent.length() + lengthOfOTP);

                    System.out.println("Text found : " + passwordOTP);

                    emailMessage.setFlag(Flags.Flag.SEEN, true);

                    break;

                } else {
                    System.out.println("Couldn't find mail with provided subject at index : " +i);
                    emailMessage.setFlag(Flags.Flag.SEEN, true);
                }
            }

            emailBox.close(true);

            store.close();


        } catch (Exception mex) {
            mex.printStackTrace();
            System.out.println("OTP Not found due to an error ");
        }

        return passwordOTP;

    }

// To read OTP from Outlook Email client and domains other than gmail.

    public String OutlookMailReader(String outlookUsername, String outlookPassword, String emailSubjectContent, int lengthOfOTP) {


// emailSubjectContent(Eg- Mail for OTP), OTP length(Eg- 6)

        String hostName = "smtp-mail.outlook.com";//change it according to your mail

        int messageCount=0;

        int unreadMsgCount;

        int latestUnreadMsgCount =0;

        String emailSubject;

        Message emailMessage;

        String passwordOTP = null;

        Properties sysProps = System.getProperties();

        sysProps.setProperty("mail.store.protocol", "imaps");

        try {

            Session session = Session.getInstance(sysProps, null);

            Store store = session.getStore();

            store.connect(hostName, outlookUsername, outlookPassword);

            Folder emailBox = store.getFolder("Inbox");

            emailBox.open(Folder.READ_WRITE);
            unreadMsgCount = emailBox.getUnreadMessageCount();

            while(unreadMsgCount >= latestUnreadMsgCount){
                Thread.sleep(1000);
                System.out.println("Sleeping for 1 Second");

                messageCount = emailBox.getMessageCount();
                latestUnreadMsgCount = emailBox.getUnreadMessageCount();

            }

            System.out.println("Message Count: " + messageCount);
            System.out.println("UnreadMessage Count: " + latestUnreadMsgCount);

            for (int i = messageCount; i > (messageCount - unreadMsgCount); i--) {

                emailMessage = emailBox.getMessage(i);

                emailSubject = emailMessage.getSubject();

                if (emailSubject.contains(emailSubjectContent)) {

                    System.out.println("Mail with provided subject found at mail index : " +i);

                    String line;

                    StringBuffer buffer = new StringBuffer();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(emailMessage.getInputStream()));

                    while ((line = reader.readLine()) != null) {

                        buffer.append(line);

                    }

                    String messageContent = "<strong style=\"font-size: 24px;\">";

                    String result = buffer.toString().substring(buffer.toString().indexOf(messageContent));

                    passwordOTP = result.substring(messageContent.length(), messageContent.length() + lengthOfOTP);

                    System.out.println("Text found : " + passwordOTP);

                    emailMessage.setFlag(Flags.Flag.SEEN, true);

                    break;

                } else {
                    System.out.println("Couldn't find mail with provided subject at index : " +i);
                    emailMessage.setFlag(Flags.Flag.SEEN, true);
                }
            }

            emailBox.close(true);

            store.close();


        } catch (Exception mex) {

            mex.printStackTrace();

            System.out.println("OTP Not found due to an error ");

        }

        return passwordOTP;

    }
}



