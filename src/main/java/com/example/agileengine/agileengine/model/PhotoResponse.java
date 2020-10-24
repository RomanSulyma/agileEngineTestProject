package com.example.agileengine.agileengine.model;

import lombok.Data;
import java.util.List;

@Data
public class PhotoResponse {
    List<Picture> pictures;
    Long page;
    Long pageCount;
    Boolean hasMore;
}
