package com.mastery.interview.patterns.intervals;

// 03 Patterns, section 8 Intervals: book/en/03-patterns.md
public final class IntervalExercises {

    private IntervalExercises() {
    }

    // E01  Each meeting is {start, end}, in any order. Return true if one person can attend all of them (no overlap; end == next start is fine).
    //      Chaque réunion est {début, fin}, dans le désordre. Renvoyer true si une personne peut assister à toutes (pas de chevauchement ; fin == début suivant est permis).
    //      canAttendAll({{0, 30}, {5, 10}, {15, 20}}) -> false    ({{7, 10}, {2, 4}}) -> true
    public static boolean canAttendAll(int[][] meetings) {
        throw new UnsupportedOperationException("TODO");
    }

    // E02  Merge every overlapping or touching intervals. Return the result sorted by start.
    //      Fusionner tous les intervalles qui se chevauchent ou se touchent. Renvoyer le résultat trié par début.
    //      merge({{1, 3}, {2, 6}, {8, 10}, {15, 18}}) -> {{1, 6}, {8, 10}, {15, 18}}
    public static int[][] merge(int[][] intervals) {
        throw new UnsupportedOperationException("TODO");
    }

    // E03  The intervals are sorted and do not overlap. Add newInterval, merging when needed, and return the sorted result.
    //      Les intervalles sont triés et ne se chevauchent pas. Ajouter newInterval en fusionnant si besoin, et renvoyer le résultat trié.
    //      insert({{1, 3}, {6, 9}}, {2, 5}) -> {{1, 5}, {6, 9}}
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        throw new UnsupportedOperationException("TODO");
    }

    // E04  Return the smallest number of rooms needed so that every meeting has a room. A room freed at time t can be reused at t.
    //      Renvoyer le plus petit nombre de salles pour que chaque réunion ait une salle. Une salle libérée au temps t peut resservir à t.
    //      minMeetingRooms({{0, 30}, {5, 10}, {15, 20}}) -> 2
    public static int minMeetingRooms(int[][] meetings) {
        throw new UnsupportedOperationException("TODO");
    }
}
