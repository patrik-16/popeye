package com.popeye.backend.entity;

import com.google.common.collect.Multimap;
import com.popeye.backend.enums.Bodypart;
import com.popeye.backend.enums.Difficulty;
import com.popeye.backend.enums.Goal;
import com.popeye.backend.enums.TimePerDay;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.popeye.backend.constants.ProgramConstants.SECONDS_PER_SET;

@Getter
@Setter
public class ProgramSession {

    private List<Exercise> exerciseList;
    private int day;
    private Goal goal;

    public ProgramSession(List<Exercise> exerciseList, int day) {
        this.exerciseList = exerciseList;
        this.day = day;
    }

    public ProgramSession(List<Exercise> exerciseList, int day, Goal goal) {
        this.exerciseList = exerciseList;
        this.day = day;
        this.goal = goal;
    }

    public int getSecondsPerSession() {
        return this.exerciseList.stream()
                .mapToInt(exercise -> exercise.returnLengthInSeconds())
                .sum();
    }

    public void updateRestTime(int restTime) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            this.exerciseList.get(i).setRest(restTime);
        }
    }

    public void updateSetNumberByDifficulty(Difficulty difficulty, int newSetNumber) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            if (this.exerciseList.get(i).getDifficulty().equals(difficulty.getValue())) {
                this.exerciseList.get(i).setSets(newSetNumber);
            }
        }
    }

    public void updateSetNumberByDifficultyAndBodypart(Difficulty difficulty, Bodypart bodypart, int newSetNumber) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            if (this.exerciseList.get(i).getDifficulty() == difficulty) {
                if (this.exerciseList.get(i).getBodypartToEffectiveness().containsKey(bodypart.toString())) {
                    this.exerciseList.get(i).setSets(newSetNumber);
                }
            }
        }
    }

    public void deleteExerciseByBodypart(Bodypart bodypart) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            if (this.exerciseList.get(i).getBodypartToEffectiveness().containsKey(bodypart.toString())) {
                this.exerciseList.removeIf(exercise -> exercise.getBodypartToEffectiveness().containsKey(bodypart.toString()));
            }
        }
    }

    public ProgramSession sessionAdaptation(Userinput userinput, Multimap<String, Integer> filteringMap) {

        //saved all exercises of session created in ProgramService, if we need more exercises later for priority
        List<Exercise> allExercises = new ArrayList<>(this.exerciseList); // wir speichern uns mal alle exercises ab aus der Datenbank für später
        List<Exercise> filteredList = new ArrayList<>();

        filteringMap.forEach(
                (key, value)
                        -> {
                    for (int i = 0; i < this.exerciseList.size(); i++) {
                        if (this.exerciseList.get(i).getBodypartToEffectiveness().containsKey(key)) {
                            String effectiveness = this.exerciseList.get(i).getBodypartToEffectiveness().get(key); //with get key we actually get the value, definied in multimaps
                            if (Integer.valueOf(effectiveness).equals(value)) {
                                filteredList.add(this.exerciseList.get(i));
                            }
                        }
                    }
                }
        );

        //finally remove all existing exercises that aren't used for this program after filtering and add the filtered list in the current Program Session
        this.exerciseList.removeAll(exerciseList);
        this.exerciseList = filteredList;

        if (!userinput.getPriorities().contains(Bodypart.NONE)) { //if NONE we do not handle priorities, not used in frontend currently
            handlePriority(allExercises, userinput.getPriorities(), userinput.getGoal(), userinput.getTimePerDay());
        }

        int setsForTimeAdaption = 0; //not much to do in hypertrophy case, since in database it is defined with 3 sets
        if (userinput.getGoal().equals(Goal.HYPERTROPHY)) {
            setsForTimeAdaption = 3;
        }
        //modify reps and sets according to difficulty
        if (userinput.getGoal().equals(Goal.STRENGTH)) {
            for (Exercise e : exerciseList) {
                switch (e.getDifficulty()) {
                    case EASY -> {
                        setsForTimeAdaption = 3;
                        modifyRepsSets(e, setsForTimeAdaption, 8, 1.0F);
                    }
                    case MEDIUM -> {
                        if (userinput.getTimePerDay().equals(TimePerDay.FORTY)) {
                            setsForTimeAdaption = 3;
                        } else {
                            setsForTimeAdaption = 4;
                        }
                        modifyRepsSets(e, setsForTimeAdaption, 6, 1.0F);
                    }
                    case HARD -> {
                        if (userinput.getTimePerDay().equals(TimePerDay.FORTY)) {
                            setsForTimeAdaption = 3;
                        } else {
                            setsForTimeAdaption = 6;
                        }
                        modifyRepsSets(e, setsForTimeAdaption, 4, 1.0F);
                    }
                }
            }
        } else if (userinput.getGoal().equals(Goal.CONDITIONING)) {
            for (Exercise e : exerciseList) {
                switch (e.getDifficulty()) {
                    case EASY -> {
                        setsForTimeAdaption = 2;
                        modifyRepsSets(e, setsForTimeAdaption, 15, 3.0F);
                    }
                    case MEDIUM -> {
                        setsForTimeAdaption = 3;
                        modifyRepsSets(e, setsForTimeAdaption, 12, 3.0F);
                    }
                    case HARD -> {
                        setsForTimeAdaption = 3;
                        modifyRepsSets(e, setsForTimeAdaption, 10, 3.0F);
                    }
                }
            }
        }

        this.timeAdaptation(userinput, setsForTimeAdaption);

        /*to check if calculated session are ok
        for (Exercise currentExercise : this.exerciseList) {
            System.out.println("Day: " + this.getDay() + ": " + currentExercise.getName() + currentExercise.getReps() + currentExercise.getDifficulty() + " rest time: " + currentExercise.getRest());
        }
        System.out.println(this.getDay() + "Day - all in one: " + this.exerciseList.size() + "exercises and total time: " + this.getSecondsPerSession());*/

        return this; // ???return this program session
    }

    /***
     * This function focus on the userinput and does the corresponding calculation
     */
    private void timeAdaptation(Userinput userinput, int setsForTimeAdaption) {
        System.out.println(userinput.getTimePerDay());
        switch (userinput.getTimePerDay()) {
            case FORTY -> this.updateRestTime(calculateRestTime(TimePerDay.FORTY, setsForTimeAdaption));
            case SIXTY -> this.updateRestTime(calculateRestTime(TimePerDay.SIXTY, setsForTimeAdaption));
            case EIGHTY -> this.updateRestTime(calculateRestTime(TimePerDay.EIGHTY, setsForTimeAdaption));
            case UNLIMITED -> {
                this.updateRestTime(calculateRestTime(TimePerDay.EIGHTY, setsForTimeAdaption) + 30);
            }
        }
    }

    private int calculateRestTime(TimePerDay timePerDay, int sets) {
        int secondsExercises = 0;
        for (Exercise e : this.exerciseList) {
            secondsExercises += SECONDS_PER_SET * e.getSets();
        }
        // e.g. 6 exercises: 6 rests (pause auch nach dem letzten satz letzte übung)
        return ((timePerDay.getSeconds() - secondsExercises) / this.exerciseList.size()) / sets; //pausenzeit für die ganze session nochmal durch die sets
    }

    //modify one exercise at a time
    private void modifyRepsSets(Exercise currentExercise, int sets, int reps, Float intensiveness) { //könnte man noch umbenennen nach intensiveness auch
        currentExercise.setSets(sets);
        currentExercise.setReps(reps);
        currentExercise.setIntensiveness(intensiveness);
    }


    private void handlePriority(List<Exercise> allExercises, List<Bodypart> priorities, Goal goal, TimePerDay timePerDay) {
        List<Exercise> prioExerciseList = new ArrayList<>();
        int amountPrios = 2; //die Anzahl an exercises nach prio die in der finalen session drin sein sollte
        if (goal.equals(Goal.STRENGTH) && timePerDay.equals(TimePerDay.FORTY)) { //strength nur 1 weils so wenig übungen sind
            amountPrios = 1;
        }

        //if priority exist put exercise on first position and look for one other priority exercise and put it on the second position; afterwards delete last element of exercise
        //if priority doesn't exist: look for two exercises with this priority and add them on first and second position - delete the last 2 exercises
        //if goal is Strength one prio exercise is required - else 2 exercises with prio are required
        int countPrioExercises = 0; //zählt die übungen die als prio schon in der session sind (z.B.: Bizeps kommt einmal vor = 1 countPrioExercises
        for (Bodypart priority : priorities) { //weil wir im userinput list übergeben
            //loops through all Exercises which where before filtering in the current session
            for (Exercise e : allExercises) {
                if (e.getBodypartToEffectiveness().containsKey(priority.toString())) { //holen wir uns die übungen gefiltert nach bodypart aus allExercises
                    if (!exerciseExistsInSession(e)) { //wenn die übungen nicht in der session existiert, fügen wir sie der prio Liste hinzu und erhöh den counter
                        prioExerciseList.add(e);
                    } else {
                        countPrioExercises += 1;
                    }
                }
            }
        }
        System.out.println(countPrioExercises);

        //if 2 exercises with prio allready exist in session: do nothing - else
        for (int i = countPrioExercises; i <= amountPrios; i++) { // so wie's jetzt ist kommen 3 Übungen rein (weil kleiner gleich)
            if (!prioExerciseList.isEmpty()) {
                this.exerciseList.add(i, prioExerciseList.get(0)); //füge die übung an erster Stelle ein in der session
                prioExerciseList.remove(0); //entferne die übung aus der Liste und die nächste rückt nach
                if (timePerDay.equals(TimePerDay.FORTY) || timePerDay.equals(TimePerDay.SIXTY)) { //wenn nur 40 oder 60min Zeit, dann entfern die letzte übung einfach
                    this.exerciseList.remove(this.exerciseList.size() - 1);
                }
            }
        }
        if (timePerDay.equals(TimePerDay.EIGHTY)) {
            this.exerciseList.remove(this.exerciseList.size() - 1);
        }
    }

    private Boolean exerciseExistsInSession(Exercise exercise) {

        for (Exercise e : this.exerciseList) {
            if (e.equals(exercise)) {
                //this exercise switches to the first position
                Exercise ex = e;
                this.exerciseList.remove(ex);
                this.exerciseList.add(0, ex);
                return true;
            }
        }
        //exercise not found
        return false;
    }
}
