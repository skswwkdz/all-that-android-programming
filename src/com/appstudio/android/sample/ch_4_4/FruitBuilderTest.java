package com.appstudio.android.sample.ch_4_4;

import com.appstudio.android.sample.ch_4_4.FruitBuilder;
import com.appstudio.android.sample.ch_4_4.Fruit;

public class FruitBuilderTest {
	public static void main(String[] args){
		//빌터 패턴 이용하지 않을 경우
		FruitNonBuilder oldFruit = new FruitNonBuilder(
				"apple",
				"orange",
				"grape",
				"banana",
				"melon"
				);
		
		
		//빌더 패턴 이용할 경우
		FruitBuilder builder = 
				new FruitBuilder().setApple("apple").setOrange("orange").setBanana("banana").setMelon("melon");
		Fruit newFruit = builder.build();
	}
}
