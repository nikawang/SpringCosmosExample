package demo.cosmos.core;

import demo.cosmos.model.ContentRecordSummer;
import demo.cosmos.model.PsCMSContentRecordImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.springframework.context.support.GenericXmlApplicationContext;

public class Reproducer {

	public static void main(String[] args) {

		// For XML
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

		// For Annotation
//		 ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		PsCMSContentRecordImpl psCMSContentRecord = (PsCMSContentRecordImpl) ctx.getBean(PsCMSContentRecordImpl.class);

		List<String> userList = new ArrayList<String>();
		String contentId = "99999";

		for(int i=0; i<991; i++)
		{
			userList.add(i+"");
		}

		psCMSContentRecord.findByContentIdAndUserIdInAndStartTimeAndEndTime(contentId, userList,0 ,9);

	}

}