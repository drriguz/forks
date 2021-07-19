package com.riguz.forks.http;

public interface RequestDelegate {

	void delegate(HttpRequest request, HttpResponse response);
}
