package com.lyn.designpattern.singleton;

/**
 * It is an eager way to create a singleton design pattern
 * no matter if the instance is used, it instantiate an object when the class loader load this class
 * but the eager singleton is thread safe. it guarantees each thread get the instance of EagerSingleton
 * must be the one instantiate after the class loaded.
 * @author lenovo
 *
 */
public class EagerSingleton {

	private static EagerSingleton instance = new EagerSingleton();
	
	private EagerSingleton(){}
	
	public static EagerSingleton getInstance(){
		return instance;
	}
	
}
