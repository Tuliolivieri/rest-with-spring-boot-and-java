package br.com.tuliolivieri.math;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tuliolivieri.exceptions.UnsupportedMathOperationException;
import br.com.tuliolivieri.utils.NumberUtils;

@RestController
public class MathController {
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return CustomMath.sum(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public double subtraction(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return CustomMath.subtraction(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public double multiplication(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return CustomMath.multiplication(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/division/{dividend}/{divider}", method=RequestMethod.GET)
	public double division(
			@PathVariable(value = "dividend") String dividend,
			@PathVariable(value = "divider") String divider
	) throws Exception {
		if(!NumberUtils.isNumeric(divider) || !NumberUtils.isNumeric(dividend)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return CustomMath.division(NumberUtils.convertToDouble(dividend), NumberUtils.convertToDouble(divider));
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public double mean(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
	) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return CustomMath.mean(NumberUtils.convertToDouble(numberOne), NumberUtils.convertToDouble(numberTwo));
	}

	@RequestMapping(value = "/sqrt/{number}", method=RequestMethod.GET)
	public double sqrt(
			@PathVariable(value = "number") String number
	) throws Exception {
		if(!NumberUtils.isNumeric(number)) {
			throw new UnsupportedMathOperationException("Please set numeric values");
		}
		
		return CustomMath.sqrt(NumberUtils.convertToDouble(number));
	}
}
