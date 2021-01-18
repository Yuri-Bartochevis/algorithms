package com.company;

/*It is New Year's Day and people are in line for the Wonderland rollercoaster ride. Each person wears a sticker indicating their initial position in the queue. Initial positions increment by  from  at the front of the line to  at the back.

        Any person in the queue can bribe the person directly in front of them to swap positions. If two people swap positions, they still wear the same sticker denoting their original places in line. One person can bribe at most two others. For example, if  and  bribes , the queue will look like this: .

        Fascinated by this chaotic queue, you decide you must know the minimum number of bribes that took place to get the queue into its current state. If anyone has bribed more than two people, the line is too chaotic to compute the answer.

        Function Description

        Complete the function minimumBribes in the editor below.

        minimumBribes has the following parameter(s):

        int q[n]: the positions of the people after all bribes
        Returns

        No value is returned. Print the minimum number of bribes necessary or Too chaotic if someone has bribed more than  people.
        Input Format

        The first line contains an integer , the number of test cases.

        Each of the next  pairs of lines are as follows:
        - The first line contains an integer , the number of people in the queue
        - The second line has  space-separated integers describing the final state of the queue.*/


/*
Logics

        If there is anyone behind the queue with a 'less numbered sticker in hand than yours', that means, you have bribed him.

        If your card number is greater than (new position number in the queue +2 ), definitly you have bribed more than twice. -> Choas

        ** Keep looking from the end of the queue and remember the three smallest sticker numbers that you ever seen. At the current possion of loop(back to front). If the sticker number of the person at current possition, a. greater than all three rememberd stickers means -> bribed more than 2. b. greater than 2 of remembered sticker -> bribed 2 times (count+2). c. greater than 1 of remembered sticker -> bribed 1 time (count+1). d. less than all remembered sticker -> then not bribed.
*/

public class MinimumBribe {

    static void minimumBribes(int[] q) {

        int count = 0 ;

        //always keep track of min three vlaues observed
        //Compare currently seeing value with this three values.
        int midOfThree = Integer.MAX_VALUE;
        int maxOfThree =  Integer.MAX_VALUE;
        int minOfThree =  Integer.MAX_VALUE ;

        //iterating from left to right
        for(int i=q.length-1 ; i >= 0 ; i--){
            //person has no way to move more than two positions -> wrong
            if((q[i]-i) > 3 ) {
                System.out.println("Too chaotic");
                return;
            }
            else{
                //means current value has passed at least 3 values -> wrong
                if(q[i] > maxOfThree){
                    System.out.println("Too chaotic");
                    return;
                }
                else if(q[i] > midOfThree){ //means -> current value has bribed 2 ppl
                    count=count+2;
                }
                else if(q[i] > minOfThree){ //means-> current value has bribed 1 person.
                    count=count+1;
                }

                //Now adjust minThree values comparing, taking the current vlaue to account
                if(q[i]<minOfThree){
                    maxOfThree=midOfThree;
                    midOfThree=minOfThree;
                    minOfThree = q[i];
                }
                else if(q[i]<midOfThree){
                    maxOfThree=midOfThree;
                    midOfThree = q[i];
                }
                else if(q[i]<maxOfThree){
                    maxOfThree = q[i];
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args){
        int[] q1 = {2,1,5,3,4};
        int[] q2 = {2,5,1,3,4};

        minimumBribes(q1);
        minimumBribes(q2);
        return;
    }

}
