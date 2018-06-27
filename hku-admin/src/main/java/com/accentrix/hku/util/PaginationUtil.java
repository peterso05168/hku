package com.accentrix.hku.util;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

/**
 * 分页工具类
 */
public class PaginationUtil {

    /**
     * 
     * @param offset
     *            从第 (offset + 1) 条开始
     * @param limit
     *            每页 limit 条
     * @return pageable
     */
    public static PageRequest getPageable(int offset, int limit) {
        int page = (int) (offset / limit);
        PageRequest pageable = new PageRequest(page, limit);
        return pageable;
    }

    public static PageRequest getPageable(int offset, int limit, Direction direction, String... properties) {
        int page = (int) (offset / limit);
        PageRequest pageable = new PageRequest(page, limit, direction, properties);
        return pageable;
    }

    public static PageRequest getPageable(int offset, int limit, String sortField, SortOrder sortOrder) {
        int page = (int) (offset / limit);
        Direction direction = Direction.ASC;
        if (sortOrder == SortOrder.DESCENDING) {
            direction = Direction.DESC;
        }
        if (StringUtils.isBlank(sortField)) {
            // 默认以 update_date 降序排序
            sortField = "id";
            direction = Direction.DESC;
        }
        PageRequest pageable = new PageRequest(page, limit, direction, sortField);
        return pageable;
    }

}
