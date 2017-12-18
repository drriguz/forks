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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.riguz.gags.base.Strings;

public class Query {
    public enum QUERY {
        EQUAL("="), LIKE("LIKE"), NOT_EQUAL("<>"), LESS_THEN("<"), GREATER_THEN(">"), LESS_EQUAL("<="), GREATER_EQUAL(">="), ORDERBY("ORDER BY");

        final String queryType;

        private QUERY(String queryType) {
            this.queryType = queryType;
        }

        @Override
        public String toString() {
            return this.queryType;
        }
    }

    String       sql;
    List<Object> params;

    public Query(String sql, List<Object> params) {
        this.sql = sql;
        this.params = params;
    }

    public String getSql() {
        return sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public static Query build(Condition... args) {
        return build("", args);
    }

    public static Query build(String select, Condition... args) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder builder = new StringBuilder();
        String orderBy = "";
        for (Condition arg : args) {
            if (arg.getParam() == null || (arg.getParam() instanceof String && Strings.isNullOrEmpty((String) arg.getParam()))) continue;
            if (arg.queryType == QUERY.ORDERBY) {
                orderBy = MessageFormat.format(" ORDER BY {0} {1}", arg.getFieldName(), arg.getParam());
            } else {
                builder.append(MessageFormat.format(" AND `{0}` {1} ?", arg.getFieldName(), arg.getQueryType().toString()));
                params.add(arg.getParam());
            }
        }
        return new Query(select + " " + builder.toString() + orderBy, params);
    }
}
