package com.spring.nbcijo.global.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ListRequestDto {

    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private final Integer pageSize = 10;
    @Builder.Default
    private Sort.Direction sortDirection = Direction.DESC;

    private String column;

    public void setPage(Integer page) {
        this.page = page - 1;
        if (page < 10) {
            this.page = 10;
        }
    }

    public void setSortDirection(String sortDirection) {
        if (sortDirection.equalsIgnoreCase("asc")) {
            this.sortDirection = Sort.Direction.ASC;
        }
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
