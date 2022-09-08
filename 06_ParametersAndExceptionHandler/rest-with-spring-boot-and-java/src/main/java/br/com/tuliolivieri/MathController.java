package br.com.tuliolivieri;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tuliolivieri.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public double subtraction(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}

	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public double multiplication(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}

	@RequestMapping(value = "/division/{dividend}/{divider}", method=RequestMethod.GET)
	public double division(
			@PathVariable(value = "dividend") String dividend,
			@PathVariable(value = "divider") String divider
	) throws Exception {
		if(!isNumeric(divider) || !isNumeric(dividend)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return convertToDouble(dividend) / convertToDouble(divider);
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public double mean(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
	}

	@RequestMapping(value = "/sqrt/{number}", method=RequestMethod.GET)
	public double sqrt(
			@PathVariable(value = "number") String number
	) throws Exception {
		if(!isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return Math.sqrt(convertToDouble(number));
	}

	private Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		
		String number = strNumber.replaceAll(",", ".");
		
		if(isNumeric(number)) return Double.parseDouble(strNumber);
		
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		
		String number = strNumber.replaceAll(",", ".");
		
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
