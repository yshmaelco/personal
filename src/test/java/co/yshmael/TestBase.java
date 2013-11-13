package co.yshmael;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:test-applicationContext.xml"
	,"classpath:test-applicationContext-xml-util.xml"
	,"classpath:test-applicationContext-ba.xml"
})
/*
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional*/
public abstract  class TestBase {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected String OUTPUT_FILE = "database-scripts.sql";

}
