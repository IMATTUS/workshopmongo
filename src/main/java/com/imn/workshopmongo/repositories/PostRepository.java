package com.imn.workshopmongo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.imn.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'title': {$regex: ?0, $options: 'i'} }")
	List<Post> searchTitle(String text);
	
	
	//this method will produce the same result as searchTitle, but using JPA to create the 
	//	query to do the search, the method name needs to start with 
	//	findBy<followed by the field and criteria>
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [ {'date':{$gte: ?1} }, {'date':{$lte: ?2} },"
				+ "{ $or: [ {'title': {$regex: ?0, $options: 'i'},"
						+ "{'body': {$regex: ?0, $options: 'i' },"
						+ "{'comments.text': {$regex: ?0, $options: 'i' } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
