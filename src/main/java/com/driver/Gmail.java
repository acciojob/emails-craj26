package com.driver;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.Triple;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity;
    private ArrayList<Triple<Date, String, String>> Inbox;
    private ArrayList<Triple<Date, String, String>> Trash;
    //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.Inbox = new ArrayList<>();
        this.Trash = new ArrayList<>();

    }

    public void receiveMail(Date date, String sender, String message){
        if(Inbox.size() == inboxCapacity){
            Triple<Date, String, String> oldestMail = Inbox.get(0);
            Inbox.remove(0);
            Trash.add(oldestMail);
        }
        Triple<Date, String, String> mail = Triple.of(date, sender, message);
        Inbox.add(mail);
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

    }

    public void deleteMail(String message){
        int index = -1;
        for(int i = 0; i<Inbox.size(); i++){
            if(message.equals(Inbox.get(i).getRight())){
                index = i;
                break;
            }
        }

        if(index != -1){
            Trash.add(Inbox.get(index));
            Inbox.remove(index);
        }
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

    }

    public String findLatestMessage(){
        if(Inbox.isEmpty())
            return null;
        return Inbox.get(Inbox.size()-1).getRight();
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

    }

    public String findOldestMessage(){
        if(Inbox.isEmpty())
            return null;
        return Inbox.get(0).getRight();
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

    }

    public int findMailsBetweenDates(Date start, Date end){
        int cnt = 0;
        for(int i = 0; i<Inbox.size(); i++){
            if((Inbox.get(i).getLeft().compareTo(start) >= 0) && (Inbox.get(i).getLeft().compareTo(end) <= 0)){
                cnt += 1;
            }
        }
        return cnt;
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

    }

    public int getInboxSize(){
        return Inbox.size();
        // Return number of mails in inbox

    }

    public int getTrashSize(){
        return Trash.size();
        // Return number of mails in Trash

    }

    public void emptyTrash(){
        Trash.clear();
        // clear all mails in the trash

    }

    public int getInboxCapacity() {
        return inboxCapacity;
        // Return the maximum number of mails that can be stored in the inbox
    }
}
