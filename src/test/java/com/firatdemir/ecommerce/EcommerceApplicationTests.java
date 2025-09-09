package com.firatdemir.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = com.firatdemir.starter.EcommerceApplicationStarter.class)
@ActiveProfiles("test")
class EcommerceApplicationTests {

	@Test
	void contextLoads() {
	}

}
