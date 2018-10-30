package com.mykins.linkin.bean;

/**
 * Created by yjn on 2017/11/12.
 */

public class ChatMessage {
    CharSequence name;
    CharSequence msg_text;
    long owner_id;
    CharSequence datetime;

    public ChatMessage(CharSequence name, CharSequence msg_text, long owner_id, CharSequence datetime) {
        this.name = name;
        this.msg_text = msg_text;
        this.owner_id = owner_id;
        this.datetime = datetime;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public CharSequence getMsg_text() {
        return msg_text;
    }

    public void setMsg_text(CharSequence msg_text) {
        this.msg_text = msg_text;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public CharSequence getDatetime() {
        return datetime;
    }

    public void setDatetime(CharSequence datetime) {
        this.datetime = datetime;
    }
}
