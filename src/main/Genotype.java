package main;

import java.util.*;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;


public class Genotype {
	 List<Integer> genotypeArray = new ArrayList<>();

    public Genotype(){
    	Random random=new Random();
        int max=32;
		int currentCount= random.nextInt(25)+1;
		int current=random.nextInt(8);
		int howManyToGo=7;
		while(howManyToGo>0){
			for(int i=0;i<currentCount;i++) genotypeArray.add(current);
			max-=currentCount;

			if((max-howManyToGo)==0) currentCount=1;
			else currentCount=random.nextInt(max-howManyToGo)+1;
			current=(current+1)%8;
			howManyToGo-=1;
		}
		for(int i=0;i<max;i++) genotypeArray.add(current);
		Collections.sort(this.genotypeArray);
    }

    public Genotype(Animal mother, Animal father){
    	Random rand=new Random();
		int idx1, idx2;
		Genotype fatherGen=father.getGenotype();
		Genotype motherGen=mother.getGenotype();
		idx1=rand.nextInt(30);
		do{
		idx2=rand.nextInt(30-idx1)+idx1;} while(!(idx2==idx1));
		int fatherCount=0;
		List<Integer> firstSub;
		List<Integer> secondSub;
		List<Integer> thirdSub;
		int random=rand.nextInt(1);
		if(random==0){
			firstSub=fatherGen.genotypeArray.subList(0,idx1+1);
			fatherCount++;}
		else firstSub=motherGen.genotypeArray.subList(0,idx1+1);
		this.genotypeArray.addAll(firstSub);
		random=rand.nextInt(1);
		if(random==0){
			secondSub=fatherGen.genotypeArray.subList(idx1+1,idx2+1);
			fatherCount++;
		}
		else secondSub=motherGen.genotypeArray.subList(idx1+1,idx2+1);
		this.genotypeArray.addAll(secondSub);
		if(fatherCount==0) thirdSub=fatherGen.genotypeArray.subList(idx2+1,32);
		else if (fatherCount==2) thirdSub=motherGen.genotypeArray.subList(idx2+1,32);
		else{
			random=rand.nextInt(1);
			if(random==0){
			thirdSub=fatherGen.genotypeArray.subList(idx2+1,32);
			}
			else{
				thirdSub=motherGen.genotypeArray.subList(idx2+1,32);
				}
		}
		this.genotypeArray.addAll(thirdSub);
		for(int i=0;i<8;i++){
			int frequency=Collections.frequency(this.genotypeArray,i);
			while(frequency==0){
				Random rand1=new Random();
				int randomidx=rand1.nextInt(32);
				this.genotypeArray.remove(randomidx);
				this.genotypeArray.add(i);
				frequency=Collections.frequency(this.genotypeArray,i);
			}
		}
		Collections.sort(this.genotypeArray);
    }

    public List<Integer> getGenotypeArray() {
		return genotypeArray;
	}

    public Integer randomizeOrientation(){
		return this.genotypeArray.get(new Random().nextInt(this.genotypeArray.size()));
	}
}
