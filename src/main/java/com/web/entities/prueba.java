package com.web.entities;

import java.text.NumberFormat;

public class prueba {

	public static void main(String[] args) {
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(4); 
		nf.setGroupingUsed(false);
		nf.setMaximumIntegerDigits(4);
		for(int i=0; i<100;i++) {
			String valor=nf.format(i);
			System.out.println(valor);

	}}

}
