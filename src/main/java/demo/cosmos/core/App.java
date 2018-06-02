package demo.cosmos.core;

import demo.cosmos.model.ContentRecord;
import demo.cosmos.model.PsCMSContentRecordImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;

//import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {

		// For XML
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

		// For Annotation
//		 ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		PsCMSContentRecordImpl psCMSContentRecord = (PsCMSContentRecordImpl) ctx.getBean(PsCMSContentRecordImpl.class);
		ContentRecord contentRecord = new ContentRecord();
		contentRecord.setContentId("123");
		contentRecord.setUserId("123");
		contentRecord.setAmount(123);
		contentRecord.setCreateTime(new Date().getTime());

		for(int i=0; i<10000000; i++)
		{
			psCMSContentRecord.save(contentRecord);
		}

	}

}