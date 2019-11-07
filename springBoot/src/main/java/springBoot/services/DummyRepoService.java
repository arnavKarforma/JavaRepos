package springBoot.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import springBoot.dto.Topics;

@Service
public class DummyRepoService {

	private List<Topics> listOfTopics = new ArrayList<>(Arrays.asList(
			new Topics("Spring-Boot", "Spring Boot for java", "Web Development Bootcamp"),
			new Topics("Hibernate", "Hibernate ORM in java", "Web Development Bootcamp with prsisitence"),
			new Topics("Docker", "Docker Container for Microservices", "Web Development Bootcamp deployment6")));

	public List<Topics> getListOfTopics() {
		return listOfTopics;
	}

	public void setListOfTopics(List<Topics> listOfTopics) {
		this.listOfTopics = listOfTopics;
	}
	public void addTopics(Topics topic){
		listOfTopics.add(topic);
	}
	public Topics getTopicById(String id){
		return  listOfTopics.stream().filter(k-> k.getId().equals(id)).findFirst().get();
	}
	public void updateTopic(String id, Topics topic){
		
		listOfTopics = listOfTopics.stream()
			    .map(o -> o.getId().equals(id) ? topic : o)
			    .collect(Collectors.toList());
		
	}

	public void deleteTopic(String id) {
	  listOfTopics.removeIf(i -> i.getId().equals(id));
		
	}
}
