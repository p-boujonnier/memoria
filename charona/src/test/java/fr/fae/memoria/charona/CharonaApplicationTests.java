package fr.fae.memoria.charona;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test", "jpa"})
class CharonaApplicationTests {

	@Test
	void contextLoads() {
	}

}
