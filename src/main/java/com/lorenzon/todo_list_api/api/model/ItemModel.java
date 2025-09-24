package com.lorenzon.todo_list_api.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemModel {

    private Long id;
    private String title;
    private String description;
}
