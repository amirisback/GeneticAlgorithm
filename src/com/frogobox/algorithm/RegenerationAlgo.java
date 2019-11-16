package com.frogobox.algorithm;

import com.frogobox.model.Chromosome;
import com.frogobox.model.Population;
import com.frogobox.view.RegenerationView;

import java.util.ArrayList;

import static com.frogobox.base.BaseHelper.*;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * GeneticAlgorithm
 * Copyright (C) 29/09/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.algorithm
 */
public class RegenerationAlgo implements RegenerationView {

    private ArrayList<Population> newPopulationGeneration = new ArrayList<>();
    private PopulationAlgo populationAlgo;

    public RegenerationAlgo() {
        populationAlgo = new PopulationAlgo(this);
        populationAlgo.showPopulationDeclare();
    }

    private void getChromosomeSelectionParent(ArrayList<Chromosome> chromosomes){
        for (int j = 0; j < chromosomes.size(); j++) {
            String genChromosome = chromosomes.get(j).getElement().toString();
            System.out.println(CHROMOSOME + " ke " + (j+1) + "\t: " + genChromosome);
        }
    }

    public void steadyState(){

        int i = 0;
        int checkerPointChromosome1 = 0;
        int checkerPointChromosome2 = 0;

        // Crossver
        System.out.println("Hasil Steady State");
        System.out.println(LINE_VIEW);
        do {

            int pointChromosome1 = 0;
            int pointChromosome2 = 0;

            for (int j=0; j<SUM_GEN; j++){
                Population parent1 = populationAlgo.getPopulations().get(0);
                Population parent2 = populationAlgo.getPopulations().get(1);

                Chromosome chromosomeParent1 = parent1.getElement().get(i);
                Chromosome chromosomeParent2 = parent2.getElement().get(i);

                pointChromosome1 = pointChromosome1 + chromosomeParent1.getElement().get(j);
                pointChromosome2 = pointChromosome2 + chromosomeParent2.getElement().get(j);

                checkerPointChromosome1 = checkerPointChromosome1 + chromosomeParent1.getElement().get(j);;
                checkerPointChromosome2 = checkerPointChromosome2 + + chromosomeParent2.getElement().get(j);

                newPopulationGeneration.add(new ChromosomeAlgo().crossOverChromosome(chromosomeParent1, chromosomeParent2));
            }

            System.out.println("Generasi ke \t: "+ (i+1));
            getChromosomeSelectionParent(newPopulationGeneration.get(i).getElement());
            System.out.println("x1 \t\t\t\t: " +pointChromosome1);
            System.out.println("x2 \t\t\t\t: " +pointChromosome2);
            System.out.println("Fitness Point \t: " + newPopulationGeneration.get(i).getFitnessPoint());
            System.out.println(LINE_VIEW);

            pointChromosome1 = 0;
            pointChromosome2 = 0;
            i++;

        } while (checkerPointChromosome1 < X_MAX && checkerPointChromosome2 < X_MAX) ;

        // Best Result CrossOver
        int pointBestCrossover = 0;
        System.out.println("Generasi ke \t: "+ (i+1) + " (Best Fitness Point) - Crossover");
        getChromosomeSelectionParent(newPopulationGeneration.get(i).getElement());
        for (int j=0; j<SUM_CHROMOSOME; j++){
            for (int k=0; k<SUM_GEN; k++){
                pointBestCrossover = pointBestCrossover + newPopulationGeneration.get(i).getElement().get(j).getElement().get(0);
            }
            System.out.println("x"+(j+1)+" \t\t\t\t: " +pointBestCrossover);
        pointBestCrossover = 0;
        }
        System.out.println("Fitness Point \t: " + newPopulationGeneration.get(i).getFitnessPoint());
        System.out.println(LINE_VIEW);

    }

    public void generationReplacement() {

    }


    @Override
    public void selectionParent(Population parent1, Population parent2) {
        System.out.println();
        System.out.println("Hasil Seleksi Orang Tua");
        System.out.println(LINE_VIEW);
        System.out.println("Parent 1 : ");
        getChromosomeSelectionParent(parent1.getElement());
        System.out.println("Fitness Point \t: " + parent1.getFitnessPoint());
        System.out.println(LINE_VIEW);
        System.out.println("Parent 2 : ");
        getChromosomeSelectionParent(parent2.getElement());
        System.out.println("Fitness Point \t: " + parent2.getFitnessPoint());
        System.out.println(LINE_VIEW);
        System.out.println();
    }
}
