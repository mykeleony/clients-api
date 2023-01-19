package com.myke.clients.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Problem {

    private Integer status;
    private LocalDateTime dateAndHour;
    private String title;
    private List<Field> fields;

    @Getter
    @AllArgsConstructor
    public static class Field {

        private String name;
        private String message;

    }

}
