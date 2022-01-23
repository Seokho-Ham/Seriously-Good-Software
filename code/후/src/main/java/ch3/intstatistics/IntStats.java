package ch3.intstatistics;

public interface IntStats {

    /**
     * 정수 하나를 리스트에 추가한다. 어떤 순서로든 추가될 수 있다.
     *
     * @param n
     */
    void insert(int n);


    /**
     * 리스트에 추가된 모든 정수의 산술 평균을 구한다.
     *
     * @return average
     */
    double getAverage();

    /**
     * 리스트에 추가된 모든 정수의 중앙 값을 구한다. 정렬된 정수 목록에서 가운데 위치한 값. 짝수일 때는 가운데 위치한 두 요소의 산술 평균 값.
     *
     * @return median
     */
    double getMedian();
}
