package com.anshu.microweb.authentication.util;


import org.springframework.core.convert.converter.Converter;

import com.anshu.microweb.authentication.domain.CurrencyDTO;

public class CurrencyConvertor  implements Converter<String, CurrencyDTO>{

	@Override
	public CurrencyDTO convert(String arg0) {
		String x=arg0.replace("$","");
		CurrencyDTO currencyDTO = new CurrencyDTO();
		currencyDTO.setAmount(Double.valueOf(x));
		currencyDTO.setCountryCode("US");
		return currencyDTO;
	}

}
