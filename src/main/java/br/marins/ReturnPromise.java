package br.marins;

public class ReturnPromise {

	private final Object promiseOfReturn;
	
	public ReturnPromise(Object promiseOfReturn) {
		this.promiseOfReturn = promiseOfReturn;
	}

	public <T> T when(T mockedObject) {
		return mockedObject;
	}

}
