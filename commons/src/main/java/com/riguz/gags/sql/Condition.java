/*******************************************************************************
 * Copyright (c) 2016 Riguz.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.riguz.gags.sql;

public class Condition {

    String fieldName;
    Query.QUERY queryType;
    Object param;

    public Condition(String filedName, Query.QUERY queryType, Object param) {
        this.fieldName = filedName;
        this.queryType = queryType;
        this.param = param;

        if (queryType == Query.QUERY.LIKE && param != null && param instanceof String) {
            String likeStr = (String) param;
            if (!likeStr.startsWith("%") && !likeStr.endsWith("%")) {
                if (!likeStr.startsWith("%")) likeStr = "%" + likeStr;
                if (!likeStr.endsWith("%")) likeStr = likeStr + "%";
            }
            this.param = likeStr;
        }
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Query.QUERY getQueryType() {
        return this.queryType;
    }

    public void setQueryType(Query.QUERY queryType) {
        this.queryType = queryType;
    }

    public Object getParam() {
        return this.param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
