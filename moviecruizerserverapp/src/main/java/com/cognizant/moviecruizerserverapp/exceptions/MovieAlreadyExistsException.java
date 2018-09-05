package com.cognizant.moviecruizerserverapp.exceptions;

public class MovieAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MovieAlreadyExistsException()
	{
		
	}
	
	public MovieAlreadyExistsException(String message)
	{
		super(message);
	}

}
