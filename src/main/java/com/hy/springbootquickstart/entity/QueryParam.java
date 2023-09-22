package com.hy.springbootquickstart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 请求参数
 * Author: yhong
 * Date: 2023/9/22
 */
public class QueryParam {
    private String category;
    private String sort;

    public QueryParam() {
    }

    public String getCategory() {
        return category;
    }

    public String getSort() {
        return sort;
    }

    public static class Builder{
        private String category;
        private String sort;
        public Builder() {}
        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }
        public Builder withSort(String sort) {
            this.sort = sort;
            return this;
        }

        public QueryParam build() {
            QueryParam queryParam= new QueryParam();
            queryParam.category = this.category;
            queryParam.sort = this.sort;
            return queryParam;
        }
    }
    public static Builder newBuilder() {
        return new Builder();
    }
}
