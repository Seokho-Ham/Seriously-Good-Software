package ch6.practice;

public interface PopularityContest<T> {

    boolean addContestant(T contestant);

    int voteFor(T contestant);

    int getVotes(T contestant);

    T getMostVoted();
}
