package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;

import java.util.Map;

public class PatternTrieRouter<T>
    extends AbstractTrieRouter<T, PatternTrieNode<Map<HttpMethod, T>>> {

    public PatternTrieRouter() {
        super(PatternTrieNode.empty());
    }

    @Override
    protected String descPath(PatternTrieNode<Map<HttpMethod, T>> node) {
        String paramDesc = node.hasPattern() ? "{" + node.getParamName() + "}" : "";
        return paramDesc + super.descPath(node);
    }

    @Override
    public Map<HttpMethod, T> find(String path) {
        return super.find(path);
    }

    @Override
    public void complete() {
        String tree = this.dump();
        System.out.println(tree);
    }
}
