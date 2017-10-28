package com.assignment.service;

import java.util.List;

import com.mongodb.WriteResult;

public interface FileRepository<E> {
	
	public List<E> getAllObjects();

	public void saveObject(List<E> object);

	public E getObject(String id);

	public WriteResult updateObject(String id, String name);

	public void deleteObject(String id);

	public void createCollection();

	public void dropCollection();

}
