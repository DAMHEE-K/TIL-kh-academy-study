package com.sh.app.design.pattern.proxy.after;

public class AspectImpl implements Aspect {

	@Override
	public void before() {
		System.out.println("aspect before");
		
	}

	@Override
	public void after() {
		System.out.println("aspect after");
	}

}
