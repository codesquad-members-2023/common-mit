package org.example;

import java.util.List;

public class ResultPrinter {
    public void print(List<String> result) {
        result.forEach(System.out::println);
        System.out.println();
    }

}
