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
package com.riguz.commons.tuple;

import java.io.Serializable;
import java.util.Objects;

public class Pair<L, R> implements Serializable {

    private static final long serialVersionUID = 2634501807207662794L;

    private final L left;
    private final R right;

    public static <L, R> Pair<L, R> of(final L left, final R right) {
        return new Pair<L, R>(left, right);
    }

    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Pair<?, ?>) {
            final Pair<?, ?> other = (Pair<?, ?>) obj;
            return Objects.equals(getLeft(), other.getLeft()) && Objects.equals(getRight(), other.getRight());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (getLeft() == null ? 0 : getLeft().hashCode()) ^ (getRight() == null ? 0 : getRight().hashCode());
    }

    @Override
    public String toString() {
        return new StringBuilder().append('(').append(getLeft()).append(',').append(getRight()).append(')').toString();
    }
}
