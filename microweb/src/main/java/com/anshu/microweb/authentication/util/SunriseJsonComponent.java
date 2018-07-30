package com.anshu.microweb.authentication.util;

import java.io.IOException;
import java.util.Date;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.anshu.microweb.authentication.domain.CurrencyDTO;

@JsonComponent
public class SunriseJsonComponent {

	
	public static class JsonDateToMillisecondsSerializer extends JsonSerializer<Date> {

		@Override
		public void serialize(Date date, JsonGenerator jsongen, SerializerProvider sp) throws IOException {	
			System.out.println("JsonDateToMillisecondsSerializer>>>..............XXXXXXXXXXXXx");
			jsongen.writeNumber(date.getTime());
			
		}

	}
	
	
	public static  class JsonMillisecondsToDateDeSerializer extends JsonDeserializer<Long> {

		@Override
		public Long deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
			return Long.valueOf(((arg0.getValueAsString()).replaceAll("-", "")));
		}

	}
	
	public static class CurrencyJsonDeserializer extends JsonDeserializer<CurrencyDTO>{

		@Override
		public CurrencyDTO deserialize(JsonParser arg0, DeserializationContext arg1)
				throws IOException, JsonProcessingException {

			String x=arg0.getValueAsString();
			x=x.replace("$", "");
			System.out.println("x>>>>>>>>>>>>>"+x);
			CurrencyDTO currencyDTO =new CurrencyDTO();
			currencyDTO.setAmount(Double.valueOf(x));
			currencyDTO.setCountryCode("US");

			return currencyDTO;
		}

	}


	
	
}
