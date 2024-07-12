package com.eric.sec02;

import com.eric.common.Util;
import com.eric.sec02.assignment.FileService;
import com.eric.sec02.assignment.FileServiceImpl;

public class Lec12Assignment {

    public static void main(String[] args) {

        FileService fileService = new FileServiceImpl();
        fileService.write("file1.txt", "This is my text file")
                .subscribe(Util.subscriber("MySub1"));
        fileService.read("file1.txt")
                .subscribe(Util.subscriber("MySub2"));
        fileService.delete("file1.txt")
                .subscribe(Util.subscriber("MySub3"));
    }
}
