package com.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.assingment.entity.Deal;
import com.mongodb.WriteResult;
@Repository
public class FileRepositoryImpl implements FileRepository<Deal> {
	
	
	@Autowired
	MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Deal> getAllObjects() {
		return mongoTemplate.findAll(Deal.class);
	}

	@Override
	public void saveObject(List<Deal> Deal) {
		mongoTemplate.insert(Deal);
		
	}

	@Override
	public Deal getObject(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),Deal.class);
	}

	@Override
	public WriteResult updateObject(String id, String name) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),	Update.update("name", name), Deal.class);
	}

	@Override
	public void deleteObject(String id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Deal.class);
		
	}

	@Override
	public void createCollection() {
		if (!mongoTemplate.collectionExists(Deal.class)) {
			mongoTemplate.createCollection(Deal.class);
		}
		
	}

	@Override
	public void dropCollection() {
		if (mongoTemplate.collectionExists(Deal.class)) {
			mongoTemplate.dropCollection(Deal.class);
		}
		
	}

}
