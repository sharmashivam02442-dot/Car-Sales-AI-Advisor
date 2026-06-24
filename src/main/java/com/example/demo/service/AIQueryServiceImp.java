package com.example.demo.service;


import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AIQueryServiceImp implements AIQueryService  {
	
	private final ChatClient chatClient;
	private final JdbcTemplate jdbcTamolate;
	public AIQueryServiceImp (ChatClient.Builder builder, JdbcTemplate jdbcTamolate) {
		this.chatClient = builder.build();
		this.jdbcTamolate = jdbcTamolate;
	}
	
	
	@Override
	public String process(String question) {
		// TODO Auto-generated method stub
		String sql = generateSQL(question);
		
		if(sql.equalsIgnoreCase("INVALID")) {
			return "Only table-related question allowed";
		}
		
		if(!isSafe(sql)) {
			
			return "Unsafe query";
		}
		
		try {
			List<Map<String, Object>> result = jdbcTamolate.queryForList(sql);
			
			if(result.isEmpty()) {
				return "No data Found";
			}
			
			//Convert Result->HFM
			
			return toNaturalLanguage(question, result);
			
		} catch(Exception e){
			
			return "Query failed";
		}
	}
	
		private String toNaturalLanguage(String question, List<Map<String, Object>> result) {
			String prompt ="""
					Convert database result into  human redable answer.
					
					User Question:
					""" + question + """
							
							DB Result:
							""" + result.toString() + """
									
								Rules:
								- Answer clearaly (don't write too much)
								- Do not show JSON
								- Do not explain SQL
								
									"""	;
			
			return chatClient.prompt()
				.user(prompt)
				.call()
				.content()
				.trim();
			
			}
	
	//Validation
	private boolean isSafe(String sql) {
		String lower = sql.toLowerCase();
		return lower.startsWith("select")
				&& !lower.contains("drop")
				&& !lower.contains("delete")
				&& !lower.contains("update")
				&& !lower.contains("insert");
	}
	
	private String generateSQL(String question) {
		
		String prompt = """
                You are a SQL generator.

                Table: car_sales
                Columns: id, brand, car_number, city, color, contact_number, customer_name, date_of_purchase, email, engine, fuel_type, mileage, model, payment_mode, price, state, time_of_purchase, warranty_period, year

                Rules:
				- Only SELECT queries
				- Use only given columns
				- If not related, return: INVALID
				- Return only SQL(do not write anything like here is there or your sql..etc

                Question:
                """ + question;
		
		
		
		return	chatClient.prompt().user(prompt).call().content().trim();
		
		
	}

}
