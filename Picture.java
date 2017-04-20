package com.nikhilthota.testmistapp;

public class Picture{
    public Picture(String content_type, int revpos, String digest, int length, boolean stub){
        this.content_type = content_type;
        this.revpos = revpos;
        this.digest = digest;
        this.length = length;
        this.stub = stub;
    }

    String content_type;
    int revpos;
    String digest;
    int length;
    boolean stub;
//    "content_type": "image/png",
//            "revpos": 59,
//            "digest": "md5-unOScH9jdo6PNBIhBwcD2A==",
//            "length": 14622,
//            "stub": true
}
