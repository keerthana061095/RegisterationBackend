package com.RegisterationAndLogin.daoImpl;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.RegisterationAndLogin.Model.UserRegisteration;
import com.RegisterationAndLogin.Util.Util;
import com.RegisterationAndLogin.dao.RegisterationAndLogindao;
import com.RegisterationAndLogin.response.RegisterResponse;
@Repository
public class RegisterationAndLoginDaoImpl implements RegisterationAndLogindao {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void save(UserRegisteration userRegis) {
		try {
		mongoTemplate.insert(userRegis);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserRegisteration alreadyExist(String emailId) {
		UserRegisteration data = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("emailId").is(emailId));
			data = mongoTemplate.findOne(query, UserRegisteration.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public UserRegisteration getById(String id) {
		UserRegisteration data = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(id));
			data = mongoTemplate.findOne(query, UserRegisteration.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void delete(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		mongoTemplate.remove(query,UserRegisteration.class);
	}

	@Override
	public List<RegisterResponse> list(Integer index, Integer pageSize) {
		List<RegisterResponse> dataList = null;
		Aggregation aggregation = null;
		long startPos = 0, endPos = pageSize;
		startPos = pageSize * index;
		try {
			dataList = new ArrayList<>();
				aggregation = newAggregation(project("firstName", "lastName", "Weight", "Height"
						).and("UserAddress.country").as("country"), skip(startPos), limit(endPos));
			
			AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "Registeration",
					Document.class);
			List<Document> documents = results.getMappedResults();
			for (Document doc : documents) {
				startPos = startPos + 1;
				RegisterResponse data = new RegisterResponse();
				data.setId((int) startPos);
				data.setFirstName(doc.getString("firstName"));
				data.setLastName(doc.getString("lastName"));
				data.setCountry(doc.getString("country"));
				data.setHeight(doc.getString("Height"));
				data.setWeight(doc.getString("Weight"));
				dataList.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	@Override
	public UserRegisteration isUserAccountExists(String userName) {
		UserRegisteration data = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("emailId").is(userName));
			data = mongoTemplate.findOne(query, UserRegisteration.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public UserRegisteration login(String userName, String password) {
		String pwd = Util.md5(password);
		UserRegisteration data = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("emailId").is(userName).and("password").is(pwd));
			data = mongoTemplate.findOne(query, UserRegisteration.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void saveAndUpdate(UserRegisteration userRegis) {
		try {
			mongoTemplate.save(userRegis);
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	}
}
