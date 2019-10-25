package br.org.cremesp.agenda.sala.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DataUtilsTest {
	
    @Test
    public void whenNewInvallidDateFormat_thenShouldRetunrNull() {
		assertThat(DataUtils.newDateWithFormat("12345678")).isEqualTo(null);
    }

}
