package springBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springBoot.dto.Topics;
import springBoot.services.DummyRepoService;

@RestController
public class HelloController {
	@Autowired
	DummyRepoService dserv;

	@RequestMapping("/topics")
	public List<Topics> dummyTopics() {
		return dserv.getListOfTopics();
	}
	@RequestMapping("/topics/{id}")
	public Topics dummyTopicById(@PathVariable String id){
		return dserv.getTopicById(id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/topics")
	public void addTopics(@RequestBody Topics topic){
		dserv.addTopics(topic);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
	public void updateTopics(@PathVariable String id, @RequestBody Topics topic){
		dserv.updateTopic(id, topic);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
	public void deleteTopics(@PathVariable String id){
		dserv.deleteTopic(id);
	}
}
