package chap06;

public interface PopularityContest<T> {
    boolean addContestant(T contestant);

    int voteFor(T contestant);

    T getMostVoted();

    int getVotes(T contestant);
}
