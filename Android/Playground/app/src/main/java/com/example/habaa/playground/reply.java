package com.example.habaa.playground;

/**
 * Created by habaa on 2018-04-22.
 */

public class reply {
    private String name;
    private String reply;

    public reply(){}
    public reply(String name, String reply) {
        this.name = name;
        this.reply = reply;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
