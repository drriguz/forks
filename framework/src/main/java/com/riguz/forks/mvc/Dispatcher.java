package com.riguz.forks.mvc;

import javax.inject.Inject;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.http.RequestDelegator;
import com.riguz.forks.router.Router;

public class Dispatcher implements RequestDelegator {
	protected final Router router;

	@Inject
	public Dispatcher(Router router) {
		this.router = router;
	}

	@Override
	public void delegate(HttpRequest request, HttpResponse response) {

		response.writeContent("Hello :" + request.getRequestURI());
	}
}
