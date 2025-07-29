package com.snowy.client.modular.wxplan.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Content implements java.io.Serializable {
    private LocalDateTime dateTime;
    private String describle;
}
