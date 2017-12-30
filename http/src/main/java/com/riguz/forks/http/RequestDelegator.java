package com.riguz.forks.http;

public interface RequestDelegator {

	void delegate(HttpRequest request, HttpResponse response);
}
