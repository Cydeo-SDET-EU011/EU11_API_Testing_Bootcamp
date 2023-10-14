package POJO;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchSpartan {

    //@JsonProperty("total Element")
    private int totalElement;
    private List<SingleSpartan> content;
}
